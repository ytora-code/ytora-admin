package xyz.ytora.core.ai.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.ai.model.entity.AiChatDetail;
import xyz.ytora.core.ai.repo.AiChatDetailRepo;

import java.util.List;

/**
 * AI对话明细的逻辑业务层
 *
 * @author ytora
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class AiChatDetailLogic extends BaseLogic<AiChatDetail, AiChatDetailRepo> {

    /**
     * 根据会话ID和对话ID查询明细。
     */
    public List<AiChatDetail> listDetails(String sessionId, String chatId) {
        return repository.listDetails(sessionId, chatId);
    }

}
