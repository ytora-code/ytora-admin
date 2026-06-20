package xyz.ytora.core.ai.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * AI 对话明细(一轮对话中的单个事件:思考、工具调用、文本输出等)
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "ai_chat_detail", idType = IdType.SNOWFLAKE, comment = "AI对话明细")
public class AiChatDetail extends BaseEntity<AiChatDetail> {

    /**
     * 所属对话记录ID(关联 ai_chat_record 主键)
     */
    @Column(comment = "所属对话记录ID")
    private String recordId;

    /**
     * 会话ID(冗余,便于按会话直接查明细)
     */
    @Column(comment = "会话ID")
    private String sessionId;

    /**
     * 对话ID(Claude Code输出的uuid)
     */
    @Column(comment = "对话ID")
    private String chatId;

    /**
     * 事件ID(Claude Code输出的uuid)
     */
    @Column(comment = "事件ID")
    private String eventId;

    /**
     * 父工具调用ID(子任务或工具结果追踪用)
     */
    @Column(comment = "父工具调用ID")
    private String parentToolUseId;

    /**
     * 事件顺序号(同一轮内从0递增)
     */
    @Column(comment = "事件顺序号")
    private Integer seq;

    /**
     * 事件类型(stream-json的type:assistant/user/system/result)
     */
    @Column(comment = "事件类型", type = ColumnType.VARCHAR32)
    private String type;

    /**
     * 事件子类型(subtype:thinking_tokens/init/success等)
     */
    @Column(comment = "事件子类型", type = ColumnType.VARCHAR32)
    private String subtype;

    /**
     * 工具调用ID
     */
    @Column(comment = "工具调用ID")
    private String toolUseId;

    /**
     * 工具名称
     */
    @Column(comment = "工具名称")
    private String toolName;

    /**
     * 工具入参
     */
    @Column(comment = "工具入参", type = ColumnType.TEXT)
    private String toolInput;

    /**
     * 关联文件路径
     */
    @Column(comment = "关联文件路径")
    private String filePath;

    /**
     * 操作摘要
     */
    @Column(comment = "操作摘要")
    private String action;

    /**
     * 内容(文本块/思考内容/工具结果等提取后的文本)
     */
    @Column(comment = "内容", type = ColumnType.TEXT)
    private String content;

    /**
     * 原始JSON(该行完整事件,排查与重放用)
     */
    @Column(comment = "原始JSON", type = ColumnType.TEXT)
    private String rawJson;

    @Override
    public BaseData<AiChatDetail> toData() {
        return null;
    }
}
