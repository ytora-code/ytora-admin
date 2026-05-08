package xyz.ytora.core.online.dynamicapi.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiGroupData;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;
import xyz.ytora.core.online.dynamicapi.model.param.SysDynamicApiGroupParam;
import xyz.ytora.core.online.dynamicapi.repo.SysDynamicApiGroupRepo;
import xyz.ytora.toolkit.tree.Trees;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 动态API接口分组模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysDynamicApiGroupLogic extends BaseLogic<SysDynamicApiGroup, SysDynamicApiGroupRepo> {

    /**
     * 树形查询动态API接口分组
     */
    public List<SysDynamicApiGroupData> tree(SysDynamicApiGroupParam param) {
        List<SysDynamicApiGroup> groups = list();
        List<SysDynamicApiGroupData> dataList = groups.stream().map(SysDynamicApiGroup::toData).toList();
        return Trees.toTree(dataList);
    }
}
