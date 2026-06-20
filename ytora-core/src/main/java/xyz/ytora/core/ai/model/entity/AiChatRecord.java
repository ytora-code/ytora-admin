package xyz.ytora.core.ai.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.ai.model.enums.AiType;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * AI 对话记录
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "ai_chat_record", idType = IdType.SNOWFLAKE, comment = "开发者")
public class AiChatRecord extends BaseEntity<AiChatRecord> {

    /**
     * AI工具类型
     */
    @Column(comment = "AI工具类型", type = ColumnType.VARCHAR32)
    private AiType tool;

    /**
     * 会话ID
     */
    @Column(comment = "会话ID")
    private String sessionId;

    /**
     * 上一次对话ID
     */
    @Column(comment = "上一次对话ID")
    private String pChatId;

    /**
     * 对话ID
     */
    @Column(comment = "对话ID")
    private String chatId;

    /**
     * 用户提问
     */
    @Column(comment = "用户提问", type = ColumnType.TEXT)
    private String question;

    /**
     * AI最终回答
     */
    @Column(comment = "AI最终回答", type = ColumnType.TEXT)
    private String answer;

    /**
     * 模型名
     */
    @Column(comment = "模型名")
    private String model;

    /**
     * 输入TOKEN命中缓存数量
     */
    @Column(comment = "输入TOKEN命中缓存数量")
    private Integer inputCacheableTokens;

    /**
     * 输入TOKEN数量
     */
    @Column(comment = "输入TOKEN数量")
    private Integer inputTokens;

    /**
     * 输出TOKEN
     */
    @Column(comment = "输出TOKEN")
    private Integer outputTokens;

    /**
     * 状态:1-success/2-failed/3-timeout
     */
    @Column(comment = "状态:1-success/2-failed/3-timeout")
    private Integer status;

    /**
     * 失败原因(status非success时)
     */
    @Column(comment = "失败原因(status非success时)", type = ColumnType.TEXT)
    private String errorMsg;

    /**
     * 耗时
     */
    @Column(comment = "耗时")
    private Long durationMs;

    /**
     * 成本
     */
    @Column(comment = "成本")
    private Long costUsd;

    @Override
    public BaseData<AiChatRecord> toData() {
        return null;
    }
}
