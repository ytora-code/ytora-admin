package xyz.ytora.core.rbac.depart.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.mvc.enums.MapperType;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.rbac.depart.model.data.SysDepartData;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.entity.SysUserDepart;
import xyz.ytora.core.rbac.depart.model.param.SysDepartParam;
import xyz.ytora.core.rbac.depart.model.param.SysUserDepartParam;
import xyz.ytora.core.rbac.depart.repo.SysDepartRepo;
import xyz.ytora.core.rbac.user.model.data.SysUserData;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.sqlux.sql.stage.select.AbsSelect;
import xyz.ytora.toolkit.text.Strs;
import xyz.ytora.toolkit.tree.Trees;

import java.util.List;
import java.util.Optional;

import static xyz.ytora.sqlux.core.SQL.*;
import static xyz.ytora.sqlux.sql.func.SqlFuncAggregation.count;

/**
 * 用户模块的业务逻辑层
 *
 * @author ytora
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysDepartLogic extends BaseLogic<SysDepart, SysDepartRepo> {

    /**
     * 查询部门树
     */
    public List<SysDepartData> tree(String departName) {
        List<SysDepart> list = select(SysDepart.class).from(SysDepart.class).orderByDesc(SysDepart::getUpdateTime).submit(SysDepart.class);
        return Trees.toTree(list.stream().map(SysDepart::toData).toList(), (level, current, parent) -> {
            current.setLevel(level);
            current.setLevelKey("└" + "─".repeat(level + 1) + ">");
            if (parent != null) {
                current.setPName(parent.getDepartName());
            }
        });
    }

    /**
     * 根据PID查询部门
     * @param pid 父部门ID
     * @return 当前父部门的所有直接子部门列表
     */
    public List<SysDepartData> listByPid(String pid) {
        List<SysDepart> departs = select(SysDepart.class).from(SysDepart.class).where(w -> w.eq(SysDepart::getPid, pid)).submit(SysDepart.class);
        return departs.stream().map(SysDepart::toData).toList();
    }

    /**
     * 新增或编辑
     * 新增时如果没有传递部门code，则自动计算
     * 规则：
     *  1.找出上级部门的code，比如 YT
     *  2.判断该上级部门有多少直接子部门，比如有三个，则当前是第四个部门，则code为 YT-04
     */
    public void insertOrUpdate(SysDepartParam param) {
        if (Strs.isEmpty(param.getPid())) {
            param.setPid("0");
        }

        if (param.getId() == null) {
            if (Strs.isEmpty(param.getDepartCode())) {
                // 自动计算部门code规则
                param.setDepartCode(generateDepartCode(param.getPid()));
            }
            insert(SysDepart.class).into().values(param.toEntity()).submit();
        } else {
            // 不能编辑departCode和pid
            param.setDepartCode(null);
            param.setPid(null);
            update(SysDepart.class)
                    .set(param.toEntity())
                    .where(w -> w.eq(SysDepart::getId, param.getId()))
                    .submit();
        }
    }

    /**
     * 规则：
     *  1.找出上级部门的code，比如 YT
     *  2.判断该上级部门有多少直接子部门，比如有三个，则当前是第四个部门，则code为 YT-04
     */
    private String generateDepartCode(String pid) {
        if (Strs.isEmpty(pid)) {
            throw new BaseException("自动产生departCode时必须传递pid");
        }
        synchronized (this) {
            // 查询上级部门
            Optional<SysDepart> parentDepartOp = select().from(SysDepart.class).where(w -> w.eq(SysDepart::getId, pid)).submit(SysDepart.class, 0);
            if (parentDepartOp.isEmpty()) {
                throw new BaseException("父级部门不存在");
            }

            SysDepart parentDepart = parentDepartOp.get();
            // 获取当前部门有多少直接子部门
            int count = select(count()).from(SysDepart.class)
                    .where(w -> w.eq(SysDepart::getPid, parentDepart.getId()))
                    .submit(Integer.class, 0)
                    .orElse(0);
            if (count >= 99) {
                throw new BaseException("当前父级部门的子部门已达上限,最多99个");
            }

            String index = Strs.fillZero(count + 1, 2);
            return parentDepart.getDepartCode() + "-" + index;
        }
    }

    /**
     * 分页查询部门下的用户
     * @param id 部门ID
     * @return 用户列表
     */
    public Page<SysUserData> pageUserByDepartId(String id) {

        // 查询当前部门已拥有的用户ID
        AbsSelect subQuery = select(SysUserDepart::getUserId).from(SysUserDepart.class).where(w -> w.eq(SysUserDepart::getDepartId, id));

        // 根据上一步查出的用户ID，查询用户详情
        Page<SysUser> page = select(SysUser.class)
                .from(SysUser.class)
                .where(w -> w.in(SysUser::getId, subQuery))
                .submit(Pages.getPage(SysUser.class));

        return page.trans(SysUser::toData);
    }

    /**
     * 分页查询部门下没有的用户
     * @param id 部门ID
     * @return 用户列表
     */
    public Page<SysUserData> pageNonUserByDepartId(String id) {
        // 查询当前部门已拥有的用户ID
        AbsSelect subQuery = select(SysUserDepart::getUserId).from(SysUserDepart.class).where(w -> w.eq(SysUserDepart::getDepartId, id));

        // 根据上一步查出的用户ID列表，查出不在这个列表中的用户详情
        Page<SysUser> page = select(SysUser.class)
                .from(SysUser.class)
                .where(w -> w.notIn(SysUser::getId, subQuery))
                .submit(Pages.getPage(SysUser.class));

        return page.trans(SysUser::toData);
    }

    /**
     * 更新用户-部门关系
     * @param param 用户-部门参数
     */
    public void refreshUserDepartMapper(SysUserDepartParam param) {
        MapperType mapperType = MapperType.get(param.getType());
        // 绑定
        if (mapperType.equals(MapperType.BIND)) {
            List<SysUserDepart> list = param.getUserIds().stream().map(userId -> {
                SysUserDepart userDepart = new SysUserDepart();
                userDepart.setUserId(userId);
                userDepart.setDepartId(param.getDepartId());
                return userDepart;
            }).toList();
            insert(SysUserDepart.class).into().values(list).submit();
        }
        // 解绑
        if (mapperType.equals(MapperType.UN_BIND)) {
            delete()
                    .from(SysUserDepart.class)
                    .where(w -> w.eq(SysUserDepart::getDepartId, param.getDepartId())
                            .in(SysUserDepart::getUserId, param.getUserIds())
                    )
                    .submit();
        }
    }

    /**
     * 查询指定用户拥有的部门
     * @param userId 用户ID
     * @return 该用户的所有部门
     */
    public List<SysDepartData> listDepartByUserId(String userId) {
        AbsSelect subQuery = select(SysUserDepart::getDepartId)
                .from(SysUserDepart.class)
                .where(w -> w.eq(SysUserDepart::getUserId, userId));

        List<SysDepart> departList = select().from(SysDepart.class)
                .where(w -> w.in(Strs.isNotEmpty(userId), SysDepart::getId, subQuery))
                .submit(SysDepart.class);
        return departList.stream().map(SysDepart::toData).toList();
    }
}
