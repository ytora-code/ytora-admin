package xyz.ytora.core.sys.log.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.log.model.SysLogMapper;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.toolkit.document.excel.Excel;

import java.time.LocalDateTime;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("日志列表")
public class SysLogExcel extends BaseExcel<SysLog> {

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
    @Excel("日志类型")
    private String type;

    /**
     * 链路跟踪 ID，用于聚合同一次调用的所有日志
     */
    @Excel("链路跟踪 ID，用于聚合同一次调用的所有日志")
    private String traceId;

    /**
     * 所在线程信息
     */
    @Excel("所在线程信息")
    private String thread;

    /**
     * 日志发生的位置
     */
    @Excel("日志发生的位置")
    private String happenPlace;

    /**
     * 日志主体内容
     */
    @Excel("日志主体内容")
    private String content;

    /**
     * 参数大小
     */
    @Excel("参数大小")
    private Integer paramLength;

    /**
     * 参数
     */
    @Excel("参数")
    private String params;

    /**
     * 返回值大小
     */
    @Excel("返回值大小")
    private Integer resultLength;

    /**
     * 返回值
     */
    @Excel("返回值")
    private String result;

    /**
     * 方法耗时
     */
    @Excel("方法耗时")
    private Long cost;

    /**
     * 操作人ip
     */
    @Excel("操作人ip")
    private String ip;

    /**
     * HTTP 请求路径
     */
    @Excel("HTTP 请求路径")
    private String requestUrl;

    /**
     * 错误堆栈信息
     */
    @Excel("错误堆栈信息")
    private String errorStack;

    public SysLog toEntity() {
        SysLogMapper mapper = SysLogMapper.mapper;
        return mapper.toEntity(this);
    }
}
