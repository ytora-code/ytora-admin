package xyz.ytora.core.sys.coderule.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.sys.coderule.model.entity.SysCodeRule;
import xyz.ytora.core.sys.coderule.repo.SysCodeRuleRepo;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 系统编码规则模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysCodeRuleLogic extends BaseLogic<SysCodeRule, SysCodeRuleRepo> {

}
