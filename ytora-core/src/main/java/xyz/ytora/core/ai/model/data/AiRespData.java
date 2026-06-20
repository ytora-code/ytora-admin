package xyz.ytora.core.ai.model.data;

import lombok.Data;
import xyz.ytora.core.ai.model.enums.AiType;

/**
 * AI响应数据
 **
 * @author ytora 
 * @since 1.0
 */
@Data
public class AiRespData {

    /**
     * 处理消息的AI工具类型
     */
    public AiType tool;

    /**
     * 消息类型
     */
    public String type;

    /**
     * 会话ID
     */
    public String sessionId;

    /**
     * AI回复内容
     */
    public String context;

    /**
     * 输入TOKEN数量
     */
    public Integer inputTokens;

    /**
     * 输入TOKEN命中缓存数量
     */
    public Integer inputCacheableTokens;

    /**
     * 输出TOKEN
     */
    public Integer outputTokens;

    /**
     * 对话轮数
     */
    public Integer numTurn;

    /**
     * 是否成功
     */
    public Boolean isSuccess;

    /**
     * 结束原因
     */
    public String stopReason;

}
