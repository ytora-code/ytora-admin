package xyz.ytora.core.ai.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.ai.model.entity.AiChatDetail;
import xyz.ytora.toolkit.text.Strs;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;

/**
 * AI对话明细的持久层
 *
 * @author ytora
 * @since 1.0
 */
@Repository
public class AiChatDetailRepo extends BaseRepo<AiChatDetail> {

    /**
     * 根据会话ID和对话ID查询明细。
     */
    public List<AiChatDetail> listDetails(String sessionId, String chatId) {
        return select()
                .from(AiChatDetail.class)
                .where(w -> w
                        .eq(Strs.isNotEmpty(sessionId), AiChatDetail::getSessionId, sessionId)
                        .eq(Strs.isNotEmpty(chatId), AiChatDetail::getChatId, chatId)
                )
                .orderByAsc(AiChatDetail::getCreateTime)
                .orderByAsc(AiChatDetail::getSeq)
                .submit(AiChatDetail.class);
    }

}
