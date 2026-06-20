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
 * AI 会话(一次会话,包含多轮对话)
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "ai_chat_session", idType = IdType.SNOWFLAKE, comment = "AI会话")
public class AiChatSession extends BaseEntity<AiChatSession> {

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
     * 会话标题(自动生成或用户重命名)
     */
    @Column(comment = "会话标题")
    private String title;

    /**
     * 模型名
     */
    @Column(comment = "模型名")
    private String model;

    /**
     * 轮次数量
     */
    @Column(comment = "轮次数量")
    private Integer roundCount;

    /**
     * 累计输入TOKEN
     */
    @Column(comment = "累计输入TOKEN")
    private Long totalInputTokens;

    /**
     * 累计输出TOKEN
     */
    @Column(comment = "累计输出TOKEN")
    private Long totalOutputTokens;

    /**
     * 状态:1-正常/2-已归档
     */
    @Column(comment = "状态:1-正常/2-已归档")
    private Integer status;

    @Override
    public BaseData<AiChatSession> toData() {
        return null;
    }
}