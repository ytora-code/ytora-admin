package xyz.ytora.core.ai.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.ai.model.entity.AiChatRecord;
import xyz.ytora.core.ai.model.entity.AiChatSession;
import xyz.ytora.core.ai.repo.AiChatRecordRepo;
import xyz.ytora.core.ai.repo.AiChatSessionRepo;

import java.util.List;
import java.util.Objects;

/**
 * AI会话的逻辑业务层
 *
 * @author ytora
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class AiChatSessionLogic extends BaseLogic<AiChatSession, AiChatSessionRepo> {

    private final AiChatRecordRepo aiChatRecordRepo;

    /**
     * 获取会话列表。
     */
    public List<AiChatSession> listSessions() {
        return repository.listSessions();
    }

    /**
     * 根据会话ID查询会话。
     */
    public AiChatSession queryBySessionId(String sessionId) {
        return repository.queryBySessionId(sessionId);
    }

    /**
     * 根据最新对话记录刷新会话聚合信息。
     */
    public void refreshByRecord(AiChatRecord record) {
        if (record == null || record.getSessionId() == null) {
            return;
        }

        List<AiChatRecord> records = aiChatRecordRepo.listBySessionId(record.getSessionId());
        AiChatSession session = repository.queryBySessionId(record.getSessionId());
        if (session == null) {
            session = new AiChatSession();
            session.setSessionId(record.getSessionId());
            session.setTitle(buildTitle(record.getQuestion()));
        }

        session.setTool(record.getTool());
        session.setModel(record.getModel());
        session.setRoundCount(records.size());
        session.setTotalInputTokens(records.stream()
                .map(AiChatRecord::getInputTokens)
                .filter(Objects::nonNull)
                .mapToLong(Integer::longValue)
                .sum());
        session.setTotalOutputTokens(records.stream()
                .map(AiChatRecord::getOutputTokens)
                .filter(Objects::nonNull)
                .mapToLong(Integer::longValue)
                .sum());
        session.setStatus(1);
        upsert(session);
    }

    private String buildTitle(String question) {
        if (question == null || question.isBlank()) {
            return "新对话";
        }
        String title = question.strip();
        return title.length() > 30 ? title.substring(0, 30) : title;
    }

}
