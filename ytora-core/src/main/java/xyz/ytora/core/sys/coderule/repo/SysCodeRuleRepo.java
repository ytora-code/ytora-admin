package xyz.ytora.core.sys.coderule.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.sys.coderule.model.entity.SysCodeRule;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 系统编码规则模块的持久层
 *
 * @author 杨桐
 * @since 1.0
 */
@Repository
public class SysCodeRuleRepo extends BaseRepo<SysCodeRule> {
}
