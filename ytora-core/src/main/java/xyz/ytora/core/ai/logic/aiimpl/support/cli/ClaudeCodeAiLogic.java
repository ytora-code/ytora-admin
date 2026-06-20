package xyz.ytora.core.ai.logic.aiimpl.support.cli;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.core.ai.logic.AiChatDetailLogic;
import xyz.ytora.core.ai.logic.AiChatRecordLogic;
import xyz.ytora.core.ai.logic.AiChatSessionLogic;
import xyz.ytora.core.ai.logic.aiimpl.IAiLogic;
import xyz.ytora.core.ai.model.data.AiRespData;
import xyz.ytora.core.ai.model.entity.AiChatDetail;
import xyz.ytora.core.ai.model.entity.AiChatRecord;
import xyz.ytora.core.ai.model.entity.AiChatSession;
import xyz.ytora.core.ai.model.enums.AiType;
import xyz.ytora.toolkit.id.Ids;
import xyz.ytora.toolkit.text.Strs;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 基于 CLAUDE_CODE
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Component
@Primary
public class ClaudeCodeAiLogic implements IAiLogic {

    @Value("${ytora.ai.cli-path}")
    private String CLAUDE_TYPE;

    @Resource
    private ObjectMapper MAPPER;

    @Resource
    private AiChatRecordLogic aiChatRecordLogic;

    @Resource
    private AiChatSessionLogic aiChatSessionLogic;

    @Resource
    private AiChatDetailLogic aiChatDetailLogic;

    /**
     * 命令最长执行时间
     */
    private static final long TIMEOUT_MINUTES = 60;

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

    @Nullable
    @Override
    public AiChatRecord chat(String sessionId, String msg, Consumer<AiRespData> callback, String... args) {
        List<String> cmdArr = new ArrayList<>();
        cmdArr.add(CLAUDE_TYPE);
        cmdArr.add("-p");
        cmdArr.add(msg);
        if (Strs.isEmpty(sessionId)) {
            // 新会话：生成 UUID
            sessionId = Ids.nextUuid();
            cmdArr.add("--session-id");
        } else {
            cmdArr.add("--resume");
        }
        cmdArr.add(sessionId);
        cmdArr.add("--output-format");
        cmdArr.add("stream-json");
        cmdArr.add("--append-system-prompt");
        cmdArr.add("当前环境：是在代码里面通过非交互方式直接调用 claude code cli");
        // 使用外部自定义参数
        if (args != null) {
            cmdArr.addAll(Arrays.asList(args));
        }

        Process process = null;
        try {
            process = new ProcessBuilder(cmdArr)
                    // stderr 合并进 stdout
                    .redirectErrorStream(true)
                    .start();
            process.getOutputStream().close();

            AiChatRecord chatRecord = null;
            List<AiChatDetail> details = new ArrayList<>();
            int seq = 0;

            // 调用 CLAUDE CODE
            try (BufferedReader reader = process.inputReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.debug(line);
                    try {
                        JsonNode node = MAPPER.readTree(line);
                        details.add(parseChatDetail(node, line, sessionId, seq++));
                        if ("result".equals(node.path("type").asText())) {
                            chatRecord = parseChatRecord(node);
                            chatRecord.setSessionId(sessionId);
                            chatRecord.setQuestion(msg);
                            aiChatRecordLogic.upsert(chatRecord);
                            saveDetails(details, chatRecord);
                            aiChatSessionLogic.refreshByRecord(chatRecord);
                        }

                        // 处理回调
                        if (callback != null) {
                            AiRespData data = parseRespLine(node);
                            callback.accept(data);
                        }
                    } catch (JsonProcessingException e) {
                        log.warn("跳过非 JSON 输出: {}", line);
                    }
                }
            }

            // 等待进程结束（带超时）
            if (!process.waitFor(TIMEOUT_MINUTES, TimeUnit.MINUTES)) {
                process.destroyForcibly();
                throw new BaseException("执行 CLAUDE 命令超时");
            }

            int code = process.exitValue();
            if (code != 0) {
                throw new BaseException("CLAUDE 命令异常退出，code:" + code);
            }

            return chatRecord;
        } catch (IOException e) {
            log.error("执行 CLAUDE 命令出错", e);
            throw new BaseException("执行 CLAUDE 命令出错", e);
        } catch (InterruptedException e) {
            // 恢复中断状态
            Thread.currentThread().interrupt();
            process.destroyForcibly();
            log.error("执行 CLAUDE 命令被中断", e);
            throw new BaseException("执行 CLAUDE 命令被中断", e);
        }
    }

    /**
     * 解析 stream-json 的单行输出为 AiRespData。
     */
    private AiRespData parseRespLine(JsonNode node) {
        AiRespData data = new AiRespData();
        data.setTool(AiType.CLAUDE_CODE_AGENT_CLI);
        data.setType(node.path("type").asText());
        data.setSessionId(node.path("session_id").asText());
        data.setNumTurn(node.path("num_turns").asInt());
        data.setIsSuccess(!node.path("is_error").asBoolean());
        data.setStopReason(node.path("terminal_reason").asText());

        if ("assistant".equals(data.getType())) {
            data.setContext(node.path("message").toString());
        } else if ("user".equals(data.getType())) {
            // data.setContext(node.path("result").asText());
        } else if ("result".equals(data.getType())) {
            data.setContext(node.path("result").asText());
        }

        return data;
    }

    private AiChatDetail parseChatDetail(JsonNode node, String rawJson, String defaultSessionId, int seq) {
        AiChatDetail detail = new AiChatDetail();
        detail.setSessionId(node.path("session_id").asText(defaultSessionId));
        detail.setEventId(node.path("uuid").asText(null));
        detail.setParentToolUseId(node.path("parent_tool_use_id").asText(null));
        detail.setSeq(seq);
        detail.setType(node.path("type").asText(null));
        detail.setSubtype(node.path("subtype").asText(null));
        detail.setRawJson(rawJson);
        detail.setContent(extractContent(node));

        fillToolInfo(detail, node);
        detail.setAction(buildAction(detail));
        return detail;
    }

    private void fillToolInfo(AiChatDetail detail, JsonNode node) {
        JsonNode content = node.path("message").path("content");
        if (!content.isArray()) {
            return;
        }

        for (JsonNode item : content) {
            if (!"tool_use".equals(item.path("type").asText())) {
                continue;
            }
            detail.setToolUseId(item.path("id").asText(null));
            detail.setToolName(item.path("name").asText(null));
            JsonNode input = item.path("input");
            if (!input.isMissingNode()) {
                detail.setToolInput(input.toString());
                detail.setFilePath(extractFilePath(input));
            }
            return;
        }
    }

    private String extractContent(JsonNode node) {
        String type = node.path("type").asText();
        if ("result".equals(type)) {
            return node.path("result").asText(null);
        }
        if ("system".equals(type)) {
            String subtype = node.path("subtype").asText();
            if ("init".equals(subtype)) {
                return "cwd=" + node.path("cwd").asText("")
                        + ", model=" + node.path("model").asText("")
                        + ", permissionMode=" + node.path("permissionMode").asText("");
            }
            if ("thinking_tokens".equals(subtype)) {
                return "estimated_tokens=" + node.path("estimated_tokens").asText("")
                        + ", estimated_tokens_delta=" + node.path("estimated_tokens_delta").asText("");
            }
        }

        JsonNode content = node.path("message").path("content");
        if (!content.isArray()) {
            return node.path("message").toString();
        }

        List<String> values = new ArrayList<>();
        for (JsonNode item : content) {
            String itemType = item.path("type").asText();
            if ("text".equals(itemType)) {
                values.add(item.path("text").asText());
            } else if ("thinking".equals(itemType)) {
                values.add(item.path("thinking").asText());
            } else if ("tool_use".equals(itemType)) {
                values.add("调用工具:" + item.path("name").asText() + ", input=" + item.path("input"));
            } else {
                values.add(item.toString());
            }
        }
        return String.join("\n", values);
    }

    private String extractFilePath(JsonNode input) {
        String[] keys = {"file_path", "path", "notebook_path"};
        for (String key : keys) {
            String value = input.path(key).asText(null);
            if (Strs.isNotEmpty(value)) {
                return value;
            }
        }
        return null;
    }

    private String buildAction(AiChatDetail detail) {
        if (Strs.isNotEmpty(detail.getToolName())) {
            if (Strs.isNotEmpty(detail.getFilePath())) {
                return detail.getToolName() + ":" + detail.getFilePath();
            }
            return detail.getToolName();
        }
        if (Strs.isNotEmpty(detail.getSubtype())) {
            return detail.getType() + ":" + detail.getSubtype();
        }
        return detail.getType();
    }

    private void saveDetails(List<AiChatDetail> details, AiChatRecord chatRecord) {
        if (chatRecord == null || details.isEmpty()) {
            return;
        }
        for (AiChatDetail detail : details) {
            detail.setRecordId(chatRecord.getId());
            detail.setChatId(chatRecord.getChatId());
        }
        aiChatDetailLogic.upsertBatch(details);
    }

    private AiChatRecord parseChatRecord(JsonNode node) {
        AiChatRecord chatRecord = new AiChatRecord();

        chatRecord.setTool(AiType.CLAUDE_CODE_AGENT_CLI);
        chatRecord.setChatId(node.path("uuid").asText());
        chatRecord.setPChatId(node.path("parentUuid").asText());

        JsonNode usage = node.path("usage");

        // 是否成功:is_error == false 视为成功(取不到时按失败更安全)
        boolean isError = node.path("is_error").asBoolean(true);
        // 模型拒答也算失败(按需保留)
        boolean refused = "refusal".equals(node.path("stop_reason").asText());
        boolean success = !isError && !refused;

        chatRecord.setStatus(success ? 1 : 2); // 0=成功 1=失败,按你的枚举调整

        if (success) {
            // 最终回答
            chatRecord.setAnswer(node.path("result").asText());
        } else {
            // 失败:回答可能为空,错误信息兜底取
            String apiStatus = node.path("api_error_status").asText("");
            String errorMsg;
            if (!apiStatus.isEmpty()) {
                errorMsg = apiStatus;
            } else if (refused) {
                errorMsg = "模型拒答(refusal)";
            } else {
                // 失败时 result 常是错误描述
                errorMsg = node.path("result").asText("未知错误");
            }
            chatRecord.setErrorMsg(errorMsg);
            // 失败也可能有部分内容
            chatRecord.setAnswer(node.path("result").asText(null));
        }

        // 模型名
        JsonNode modelUsage = node.path("modelUsage");
        if (modelUsage.isObject()) {
            var it = modelUsage.fieldNames();
            if (it.hasNext()) {
                chatRecord.setModel(it.next());
            }
        }

        // token 统计
        chatRecord.setInputCacheableTokens(usage.path("cache_read_input_tokens").asInt());
        chatRecord.setInputTokens(
                usage.path("input_tokens").asInt()
                        + usage.path("cache_creation_input_tokens").asInt());
        chatRecord.setOutputTokens(usage.path("output_tokens").asInt());

        // 耗时
        chatRecord.setDurationMs(node.path("duration_ms").asLong());

        return chatRecord;
    }

}
