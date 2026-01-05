package xyz.ytora.core.rbac.depart.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.req.SysDepartReq;
import xyz.ytora.core.rbac.depart.model.resp.SysDepartResp;
import xyz.ytora.core.rbac.depart.repo.SysDepartRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.ytool.str.Strs;
import xyz.ytora.ytool.tree.Trees;

import java.util.List;

/**
 * created by YT on 2025/12/21 16:25:59
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class SysDepartLogic extends BaseLogic<SysDepart, SysDepartRepo> {

    private final SQLHelper sqlHelper;

    /**
     * 查询部门树
     */
    public List<SysDepartResp> tree(String departName) {
        List<SysDepart> list = sqlHelper.select(SysDepart.class).from(SysDepart.class).orderBy(SysDepart::getUpdateTime, OrderType.DESC).submit(SysDepart.class);
        return Trees.toTree(list.stream().map(SysDepart::toResp).toList(), departName);
    }

    /**
     * 新增或编辑
     * 新增时如果没有传递部门code，则自动计算
     * 规则：
     *  1.找出上级部门的code，比如 YT
     *  2.判断该上级部门有多少直接子部门，比如有三个，则当前是第四个部门，则code为 YT-04
     */
    public void insertOrUpdate(SysDepartReq sysDepartReq) {
        if (Strs.isEmpty(sysDepartReq.getPid())) {
            sysDepartReq.setPid("0");
        }

        if (sysDepartReq.getId() == null) {
            if (Strs.isEmpty(sysDepartReq.getDepartCode())) {
                // 自动计算部门code规则
                // 查询上级部门
                SysDepart parentDepart = repository.one(w -> w.eq(SysDepart::getId, sysDepartReq.getPid()));
                if (parentDepart == null) {
                    throw new BaseException("父级部门不存在");
                }
                // 获取当前部门有多少直接子部门
                Long count = repository.count(w -> w.eq(SysDepart::getPid, parentDepart.getId()));
                if (count >= 99) {
                    throw new BaseException("当前父级部门的子部门已达上限,最多99个");
                }
                String index = Strs.fillZero((int) (count + 1), 2);
                String departCode = parentDepart.getDepartCode() + "-" + index;
                sysDepartReq.setDepartCode(departCode);
            }
            repository.insert(sysDepartReq.toEntity());
        } else {
            // 不能编辑departCode和pid
            sysDepartReq.setDepartCode(null);
            sysDepartReq.setPid(null);
            repository.update(sysDepartReq.toEntity(), w -> w.eq(SysDepart::getId, sysDepartReq.getId()));
        }
    }

}
