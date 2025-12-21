package xyz.ytora.core.sys.log.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 日志表
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_log", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "日志表")
public class SysLog extends BaseEntity<SysLog> {

    /**
     * 日志类型
     */
    @Index(1)
    @Column(comment = "日志类型")
    private Integer type;

    /**
     * 操作人ip
     */
    @Index(2)
    @Column(comment = "操作人ip")
    private String ip;

    /**
     * 链路跟踪 ID，用于聚合同一次调用的所有日志
     */
    @Index(3)
    @Column(comment = "链路跟踪 ID，用于聚合同一次调用的所有日志")
    private String traceId;

    /**
     * 所在线程信息
     */
    @Index(4)
    @Column(comment = "所在线程信息")
    private String thread;

    /**
     * HTTP 请求路径
     */
    @Index(5)
    @Column(comment = "HTTP 请求路径")
    private String requestUrl;

    /**
     * 日志发生的类名
     */
    @Index(6)
    @Column(comment = "HTTP 请求路径")
    private String className;

    /**
     * 日志发生的方法名
     */
    @Index(7)
    @Column(comment = "日志发生的方法名")
    private String methodName;

    /**
     * 参数
     */
    @Index(8)
    @Column(comment = "参数")
    private String params;

    /**
     * 返回值大小
     */
    @Index(9)
    @Column(comment = "返回值大小")
    private Integer resultLength;

    /**
     * 请求耗时
     */
    @Index(10)
    @Column(comment = "请求耗时")
    private Long cost;

    /**
     * 日志主体内容
     */
    @Index(11)
    @Column(comment = "日志主体内容")
    private String content;

    /**
     * 错误信息
     */
    @Index(12)
    @Column(comment = "错误信息")
    private String errorMsg;

    /**
     * 错误堆栈信息
     */
    @Index(13)
    @Column(comment = "错误堆栈信息")
    private String errorStack;

    @Override
    public BaseResp<SysLog> toResp() {
        return null;
    }
}