package xyz.ytora.core.sys.file.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;
import xyz.ytora.ytool.tree.ITree;

import java.util.List;

/**
 * 系统文件夹
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysFolderResp extends BaseResp<SysFolder> implements ITree<SysFolderResp> {
    /**
     * 文件id，全局唯一，根据该id可以找到一个唯一对应的文件
     */
    @Index(1)
    @Column(comment = "父文件夹ID", notNull = true)
    private String pid;

    /**
     * 文件夹路径
     */
    @Index(2)
    private String path;

    /**
     * 文件夹深度
     */
    @Index(3)
    private Integer depth;

    /**
     * 类型，1-文件夹/2-文件
     */
    @Index(4)
    private Integer type;

    /**
     * 扩展
     */
    @Index(5)
    private String ext;

    /**
     * 子文件夹
     */
    @Index(6)
    private List<SysFolderResp> children;

    /**
     * 是否有子节点
     */
    @Index(7)
    private Boolean isLeaf = false;

    @Override
    public BaseExcel<SysFolder> toExcel() {
        return null;
    }

    @Override
    public String getKey() {
        return path;
    }

    @Override
    public void hasChildren(Boolean hasChildren) {
        this.isLeaf = !hasChildren;
    }
}
