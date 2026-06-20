package xyz.ytora.core.ai.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.ai.model.entity.AiChatSession;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;

/**
 * AI会话的持久层
 *
 * @author ytora
 * @since 1.0
 */
@Repository
public class AiChatSessionRepo extends BaseRepo<AiChatSession> {

    /**
     * 查询所有会话，最近更新的排在前面。
     */
    public List<AiChatSession> listSessions() {
        return select()
                .from(AiChatSession.class)
                .orderByDesc(AiChatSession::getUpdateTime)
                .submit(AiChatSession.class);
    }

    /**
     * 根据会话ID查询会话。
     */
    public AiChatSession queryBySessionId(String sessionId) {
        return one(w -> w.eq(AiChatSession::getSessionId, sessionId));
    }

}
