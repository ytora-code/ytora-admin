package xyz.ytora.core.sys.log.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.base.sqlux.filler.CreateByFiller;
import xyz.ytora.base.sqlux.filler.CreateTimeFiller;
import xyz.ytora.core.sys.log.LogType;
import xyz.ytora.core.sys.log.model.SysLogMapper;
import xyz.ytora.core.sys.log.model.data.SysLogData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.FillType;
import xyz.ytora.sqlux.core.enums.IdType;
import xyz.ytora.sqlux.orm.AbsEntity;
import xyz.ytora.sqlux.orm.type.Text;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * 日志表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(value = "sys_log", idType = IdType.SNOWFLAKE, comment = "日志表")
public class SysLog extends AbsEntity {

    // ==================== 日志通用字段 ====================

    /**
     * 日志创建人
     */
    @Column(value = "create_by", comment = "日志创建人")
    private String createBy;

    /**
     * 日志创建日期
     */
    @Column(value = "create_time", comment = "日志创建日期")
    private LocalDateTime createTime;

    /**
     * 日志结束日期，一些周期性任务产生的日志会用到
     */
    @Column(value = "end_time", comment = "日志结束日期")
    private LocalDateTime endTime;

    /**
     * 日志类型
     */
    @Column(comment = "日志类型")
    private LogType type;

    /**
     * 链路跟踪 ID，用于聚合同一次调用的所有日志
     */
    @Column(comment = "链路跟踪 ID，用于聚合同一次调用的所有日志")
    private String traceId;

    /**
     * 所在线程信息
     */
    @Column(comment = "所在线程信息")
    private String thread;

    /**
     * 日志发生的位置
     */
    @Column(comment = "日志发生的位置")
    private String happenPlace;

    /**
     * 日志主体内容
     */
    @Column(comment = "日志主体内容")
    private Text content;

    /**
     * 参数大小
     */
    @Column(comment = "参数大小")
    private Integer paramLength;

    /**
     * 参数
     */
    @Column(comment = "参数")
    private Text params;

    /**
     * 返回值大小
     */
    @Column(comment = "返回值大小")
    private Integer resultLength;

    /**
     * 返回值
     */
    @Column(comment = "返回值")
    private Text result;

    /**
     * 方法耗时
     */
    @Column(comment = "方法耗时")
    private Long cost;

    // ==================== 网络日志字段 ====================

    /**
     * 操作人ip
     */
    @Column(comment = "操作人ip")
    private String ip;

    /**
     * HTTP 请求路径
     */
    @Column(comment = "HTTP 请求路径")
    private String requestUrl;

    // ==================== 错误日志字段 ====================

    /**
     * 错误堆栈信息
     */
    @Column(comment = "错误堆栈信息")
    private String errorStack;

    public SysLogData toData() {
        SysLogMapper mapper = SysLogMapper.mapper;
        return mapper.toData(this);
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public void setErrorStack(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        this.errorStack = sw.toString();
    }
}
