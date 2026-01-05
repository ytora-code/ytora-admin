package xyz.ytora.core.rbac.depart.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;
import xyz.ytora.ytool.tree.ITree;

import java.util.List;

/**
 * 部门表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDepartResp extends BaseResp<SysDepart> implements ITree<SysDepartResp> {
    /**
     * 上级部门id
     */
    @Index(1)
    @Column(comment = "上级部门id", notNull = true)
    private String pid;

    /**
     * 部门名称
     */
    @Index(2)
    @Column(comment = "部门名称")
    private String departName;

    /**
     * 部门编码
     */
    @Index(3)
    @Column(comment = "部门编码", notNull = true)
    private String departCode;

    /**
     * 部门类型
     */
    @Index(4)
    @Column(comment = "部门类型")
    private String type;

    /**
     * 部门联系人username
     */
    @Index(5)
    @Column(comment = "部门联系人username")
    private String contactUserName;

    /**
     * 子元数集合
     */
    private List<SysDepartResp> children;

    /**
     * 是否有子节点
     */
    private Boolean hasChildren = false;

    @Override
    public String getKey() {
        return departName;
    }

    @Override
    public void hasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
