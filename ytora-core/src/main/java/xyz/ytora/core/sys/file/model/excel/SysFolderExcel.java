package xyz.ytora.core.sys.file.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.file.model.SysFolderMapper;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("系统文件夹列表")
public class SysFolderExcel extends BaseExcel<SysFolder> {

    /**
     * 父文件夹ID
     */
    @Excel("父文件夹ID")
    private String pid;

    /**
     * 文件夹路径
     */
    @Excel("文件夹路径")
    private String path;

    /**
     * 文件夹深度
     */
    @Excel("文件夹深度")
    private Integer depth;

    @Override
    public SysFolder toEntity() {
        SysFolderMapper mapper = SysFolderMapper.mapper;
        return mapper.toEntity(this);
    }
}
