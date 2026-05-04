package xyz.ytora.core.sys.file.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.file.model.SysFolderMapper;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.excel.SysFolderExcel;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.toolkit.tree.ITree;

import java.util.List;

/**
 * 系统文件夹响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统文件夹响应数据")
public class SysFolderData extends BaseData<SysFolder> implements ITree<SysFolderData> {

    /**
     * 父文件夹ID
     */
    @Schema(description = "父文件夹ID")
    private String pid;

    /**
     * 文件夹路径
     */
    @Schema(description = "文件夹路径")
    private String path;

    /**
     * 文件夹深度
     */
    @Schema(description = "文件夹深度")
    private Integer depth;

    /**
     * 1-文件夹/2-文件
     */
    @Schema(description = "1-文件夹/2-文件")
    private Integer type;

    /**
     * 是否叶子结点
     */
    @Schema(description = "是否叶子结点")
    private Boolean isLeaf;

    /**
     * 文件扩展名
     */
    @Schema(description = "文件扩展名")
    private String ext;

    /**
     * 子文件夹/子文件
     */
    @Column(comment = "子文件夹/子文件")
    private List<SysFolderData> children;

    @Override
    public SysFolderExcel toExcel() {
        SysFolderMapper mapper = SysFolderMapper.mapper;
        return mapper.toExcel(this);
    }
}
