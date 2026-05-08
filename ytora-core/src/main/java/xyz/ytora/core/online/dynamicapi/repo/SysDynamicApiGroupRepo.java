package xyz.ytora.core.online.dynamicapi.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 动态API接口分组模块的持久层
 *
 * @author 杨桐
 * @since 1.0
 */
@Repository
public class SysDynamicApiGroupRepo extends BaseRepo<SysDynamicApiGroup> {
}
