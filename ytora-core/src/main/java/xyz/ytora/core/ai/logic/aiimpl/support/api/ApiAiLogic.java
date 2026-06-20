package xyz.ytora.core.ai.logic.aiimpl.support.api;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.core.ai.logic.AiChatDetailLogic;
import xyz.ytora.core.ai.logic.AiChatRecordLogic;
import xyz.ytora.core.ai.logic.AiChatSessionLogic;
import xyz.ytora.core.ai.logic.aiimpl.IAiLogic;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatMessage;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatRequest;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatResponse;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiUsage;
import xyz.ytora.core.ai.model.data.AiRespData;
import xyz.ytora.core.ai.model.entity.AiChatDetail;
import xyz.ytora.core.ai.model.entity.AiChatRecord;
import xyz.ytora.core.ai.model.entity.AiChatSession;
import xyz.ytora.toolkit.id.Ids;
import xyz.ytora.toolkit.text.Strs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 通用API方式AI实现，对外统一暴露IAiLogic。
 *
 * @author ytora
 * @since 1.0
 */
@Component("apiAiLogic")
public class ApiAiLogic implements IAiLogic {

    @Resource
    private AiApiProperty property;

    @Resource
    private List<AiApiAdapter> apiAdapters;

    @Resource
    private AiChatRecordLogic aiChatRecordLogic;

    @Resource
    private AiChatSessionLogic aiChatSessionLogic;

    @Resource
    private AiChatDetailLogic aiChatDetailLogic;

    @Override
    public List<AiChatSession> listSessions() {
        return aiChatSessionLogic.listSessions();
    }

    @Override
    public AiChatSession querySession(String sessionId) {
        return aiChatSessionLogic.queryBySessionId(sessionId);
    }

    @Override
    public List<AiChatRecord> listRecords(String sessionId) {
        return aiChatRecordLogic.listBySessionId(sessionId);
    }

    @Override
    public List<AiChatDetail> listDetails(String sessionId, String chatId) {
        return aiChatDetailLogic.listDetails(sessionId, chatId);
    }

    @Override
    public AiChatRecord newChat(String msg, Consumer<AiRespData> callback, String... args) {
        return chat(null, msg, callback, args);
    }

    @Override
    public AiChatRecord continueChat(String sessionId, String msg, Consumer<AiRespData> callback, String... args) {
        if (Strs.isEmpty(sessionId)) {
            throw new BaseException("继续对话时sessionId不能为空");
        }
        return chat(sessionId, msg, callback, args);
    }

    @Override
    public AiChatRecord chat(String sessionId, String msg, Consumer<AiRespData> callback, String... args) {
        if (Strs.isEmpty(sessionId)) {
            sessionId = Ids.nextUuid();
        }

        AiApiAdapter adapter = selectAdapter();
        List<AiChatRecord> records = aiChatRecordLogic.listBySessionId(sessionId);
        ApiChatRequest request = buildRequest(sessionId, msg, records);
        ApiChatResponse response = adapter.chat(request);

        AiChatRecord chatRecord = buildChatRecord(sessionId, msg, response, lastChatId(records), adapter);
        aiChatRecordLogic.upsert(chatRecord);
        saveDetails(chatRecord, response);
        aiChatSessionLogic.refreshByRecord(chatRecord);

        if (callback != null) {
            callback.accept(buildRespData(chatRecord, response));
        }
        return chatRecord;
    }

    private AiApiAdapter selectAdapter() {
        String provider = property.getDefaultProvider();
        if (Strs.isEmpty(provider)) {
            throw new BaseException("ytora.ai.api.default-provider不能为空");
        }
        return apiAdapters.stream()
                .filter(adapter -> adapter.supports(provider))
                .findFirst()
                .orElseThrow(() -> new BaseException("未找到AI API供应商适配器：{}", provider));
    }

    private ApiChatRequest buildRequest(String sessionId, String msg, List<AiChatRecord> records) {
        List<ApiChatMessage> messages = new ArrayList<>();
        for (AiChatRecord record : records) {
            if (Strs.isNotEmpty(record.getQuestion())) {
                messages.add(ApiChatMessage.user(record.getQuestion()));
            }
            if (Strs.isNotEmpty(record.getAnswer())) {
                messages.add(ApiChatMessage.assistant(record.getAnswer()));
            }
        }
        messages.add(ApiChatMessage.user(msg));

        ApiChatRequest request = new ApiChatRequest();
        request.setProvider(property.getDefaultProvider());
        request.setModel(property.getModel());
        request.setSessionId(sessionId);
        request.setMessages(messages);
        return request;
    }

    private AiChatRecord buildChatRecord(String sessionId, String msg, ApiChatResponse response,
                                         String lastChatId, AiApiAdapter adapter) {
        ApiUsage usage = response.getUsage();

        AiChatRecord chatRecord = new AiChatRecord();
        chatRecord.setTool(adapter.getTool());
        chatRecord.setSessionId(sessionId);
        chatRecord.setPChatId(lastChatId);
        chatRecord.setChatId(Strs.isNotEmpty(response.getChatId()) ? response.getChatId() : Ids.nextUuid());
        chatRecord.setQuestion(msg);
        chatRecord.setAnswer(response.getContent());
        chatRecord.setModel(response.getModel());
        chatRecord.setStatus(1);
        if (usage != null) {
            chatRecord.setInputCacheableTokens(usage.getCacheHitTokens());
            chatRecord.setInputTokens(usage.getInputTokens());
            chatRecord.setOutputTokens(usage.getOutputTokens());
        }
        return chatRecord;
    }

    private void saveDetails(AiChatRecord chatRecord, ApiChatResponse response) {
        List<AiChatDetail> details = new ArrayList<>();

        AiChatDetail requestDetail = new AiChatDetail();
        requestDetail.setRecordId(chatRecord.getId());
        requestDetail.setSessionId(chatRecord.getSessionId());
        requestDetail.setChatId(chatRecord.getChatId());
        requestDetail.setSeq(0);
        requestDetail.setType("api_request");
        requestDetail.setSubtype(response.getProvider());
        requestDetail.setAction("request:" + response.getModel());
        requestDetail.setContent(chatRecord.getQuestion());
        requestDetail.setRawJson(response.getRequestJson());
        details.add(requestDetail);

        AiChatDetail responseDetail = new AiChatDetail();
        responseDetail.setRecordId(chatRecord.getId());
        responseDetail.setSessionId(chatRecord.getSessionId());
        responseDetail.setChatId(chatRecord.getChatId());
        responseDetail.setSeq(1);
        responseDetail.setType("api_response");
        responseDetail.setSubtype(response.getFinishReason());
        responseDetail.setAction("response:" + response.getModel());
        responseDetail.setContent(response.getContent());
        responseDetail.setRawJson(response.getRawJson());
        details.add(responseDetail);

        aiChatDetailLogic.upsertBatch(details);
    }

    private AiRespData buildRespData(AiChatRecord chatRecord, ApiChatResponse response) {
        ApiUsage usage = response.getUsage();

        AiRespData data = new AiRespData();
        data.setTool(chatRecord.getTool());
        data.setType("result");
        data.setSessionId(chatRecord.getSessionId());
        data.setContext(response.getContent());
        data.setIsSuccess(true);
        data.setStopReason(response.getFinishReason());
        if (usage != null) {
            data.setInputTokens(usage.getInputTokens());
            data.setInputCacheableTokens(usage.getCacheHitTokens());
            data.setOutputTokens(usage.getOutputTokens());
        }
        return data;
    }

    private String lastChatId(List<AiChatRecord> records) {
        if (records == null || records.isEmpty()) {
            return null;
        }
        return records.getLast().getChatId();
    }

}
