package xyz.ytora.core.rbac.depart.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.resp.SysDepartResp;
import xyz.ytora.core.rbac.depart.repo.SysDepartRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
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
     * 查询部门
     */
    public List<SysDepartResp> list(String departCode) {
        List<SysDepart> list = sqlHelper.select(SysDepart.class).from(SysDepart.class).orderBy(SysDepart::getUpdateTime, OrderType.DESC).submit(SysDepart.class);
        return Trees.toTree(list.stream().map(SysDepart::toResp).toList(), departCode);
    }
}
