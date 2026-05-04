package xyz.ytora.core.sys.log.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.log.model.SysLogMapper;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.core.sys.log.model.excel.SysLogExcel;
import xyz.ytora.sqlux.core.anno.Column;

import java.time.LocalDateTime;

/**
 * 日志响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "日志表响应数据")
public class SysLogData extends BaseData<SysLog> {

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
    @Schema(description = "日志类型")
    private String type;

    /**
     * 链路跟踪 ID，用于聚合同一次调用的所有日志
     */
    @Schema(description = "链路跟踪 ID，用于聚合同一次调用的所有日志")
    private String traceId;

    /**
     * 所在线程信息
     */
    @Schema(description = "所在线程信息")
    private String thread;

    /**
     * 日志发生的位置
     */
    @Schema(description = "日志发生的位置")
    private String happenPlace;

    /**
     * 日志主体内容
     */
    @Schema(description = "日志主体内容")
    private String content;

    /**
     * 参数大小
     */
    @Schema(description = "参数大小")
    private Integer paramLength;

    /**
     * 参数
     */
    @Schema(description = "参数")
    private String params;

    /**
     * 返回值大小
     */
    @Schema(description = "返回值大小")
    private Integer resultLength;

    /**
     * 返回值
     */
    @Schema(description = "返回值")
    private String result;

    /**
     * 方法耗时
     */
    @Schema(description = "方法耗时")
    private Long cost;

    /**
     * 操作人ip
     */
    @Schema(description = "操作人ip")
    private String ip;

    /**
     * HTTP 请求路径
     */
    @Schema(description = "HTTP 请求路径")
    private String requestUrl;

    /**
     * 错误堆栈信息
     */
    @Schema(description = "错误堆栈信息")
    private String errorStack;

    public SysLogExcel toExcel() {
        SysLogMapper mapper = SysLogMapper.mapper;
        return mapper.toExcel(this);
    }
}
