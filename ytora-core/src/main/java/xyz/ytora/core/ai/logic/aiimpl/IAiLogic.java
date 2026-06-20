package xyz.ytora.core.ai.logic.aiimpl;

import xyz.ytora.core.ai.model.data.AiRespData;
import xyz.ytora.core.ai.model.entity.AiChatDetail;
import xyz.ytora.core.ai.model.entity.AiChatRecord;
import xyz.ytora.core.ai.model.entity.AiChatSession;

import java.util.List;
import java.util.function.Consumer;

/**
 * 描述
 *
 * <p>说明</p>
 *
 * @author ytora 
 * @since 1.0
 */
public interface IAiLogic {

    /**
     * 获取会话列表。
     */
    List<AiChatSession> listSessions();

    /**
     * 获取指定会话信息。
     */
    AiChatSession querySession(String sessionId);

    /**
     * 获取指定会话下的全部对话记录。
     */
    List<AiChatRecord> listRecords(String sessionId);

    /**
     * 获取指定会话或对话下的事件明细。
     */
    List<AiChatDetail> listDetails(String sessionId, String chatId);

    /**
     * 开启新对话。
     */
    AiChatRecord newChat(String msg, Consumer<AiRespData> callback, String... args);

    /**
     * 继续指定会话。
     */
    AiChatRecord continueChat(String sessionId, String msg, Consumer<AiRespData> callback, String... args);

    /**
     * AI对话，并返回会话ID
     * @param chatId 会话ID，为空表示新对话
     * @param msg 消息正文
     * @param callback 处理AI回复的回调方法
     *
     * @return 对话记录消息
     */
    AiChatRecord chat(String chatId, String msg, Consumer<AiRespData> callback, String... args);

}
