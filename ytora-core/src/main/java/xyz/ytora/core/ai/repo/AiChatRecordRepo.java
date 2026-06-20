package xyz.ytora.core.ai.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.ai.model.entity.AiChatRecord;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;

/**
 * AI对话历史记录的持久层
 *
 * @author 杨桐
 * @since 1.0
 */
@Repository
public class AiChatRecordRepo extends BaseRepo<AiChatRecord> {

    /**
     * 根据会话ID查询全部对话记录。
     */
    public List<AiChatRecord> listBySessionId(String sessionId) {
        return select()
                .from(AiChatRecord.class)
                .where(w -> w.eq(AiChatRecord::getSessionId, sessionId))
                .orderByAsc(AiChatRecord::getCreateTime)
                .submit(AiChatRecord.class);
    }

}
