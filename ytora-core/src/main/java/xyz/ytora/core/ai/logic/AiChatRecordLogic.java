package xyz.ytora.core.ai.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.ai.model.entity.AiChatRecord;
import xyz.ytora.core.ai.repo.AiChatRecordRepo;

import java.util.List;

/**
 * AI对话历史记录的逻辑业务层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class AiChatRecordLogic extends BaseLogic<AiChatRecord, AiChatRecordRepo> {

    /**
     * 根据会话ID查询全部对话记录。
     */
    public List<AiChatRecord> listBySessionId(String sessionId) {
        return repository.listBySessionId(sessionId);
    }

}
