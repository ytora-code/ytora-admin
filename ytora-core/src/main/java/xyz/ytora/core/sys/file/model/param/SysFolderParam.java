package xyz.ytora.core.sys.file.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.file.model.SysFolderMapper;
import xyz.ytora.core.sys.file.model.entity.SysFolder;

/**
 * 系统文件夹请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统文件夹请求数据")
public class SysFolderParam extends BaseParam<SysFolder> {

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

    @Override
    public SysFolder toEntity() {
        SysFolderMapper mapper = SysFolderMapper.mapper;
        return mapper.toEntity(this);
    }
}
