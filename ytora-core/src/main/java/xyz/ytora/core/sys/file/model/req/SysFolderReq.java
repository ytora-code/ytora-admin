package xyz.ytora.core.sys.file.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.resp.SysFolderResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 系统文件夹
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统文件夹")
public class SysFolderReq extends BaseReq<SysFolder> {
    /**
     * 文件id，全局唯一，根据该id可以找到一个唯一对应的文件
     */
    @Index(1)
    @Schema(description = "父文件夹ID")
    private String pid;

    /**
     * 文件夹路径
     */
    @Index(2)
    @Schema(description = "文件夹路径")
    private String path;

    /**
     * 文件夹深度
     */
    @Index(3)
    @Schema(description = "文件夹深度")
    private Integer depth;

    @Override
    public SysFolder toEntity() {
        SysFolder  sysFolder = new SysFolder();
        sysFolder.setId(id);
        sysFolder.setPid(pid);
        sysFolder.setPath(path);
        sysFolder.setDepth(depth);
        return sysFolder;
    }
}
