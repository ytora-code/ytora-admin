package xyz.ytora.core.rbac.role.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;
import xyz.ytora.ytool.document.excel.Excel;

import java.time.LocalDate;

/**
 * EXCEL请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel(fileName = "角色EXCEL数据")
public class SysRoleExcel extends BaseExcel<SysRole> {
    /**
     * 角色名称
     */
    @Excel("角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Excel("角色编码")
    private String roleCode;

    @Override
    public SysRole toEntity() {
        return SysRoleMapper.mapper.toEntity(this);
    }
}
