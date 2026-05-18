package xyz.ytora.core.biz.deploy.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.biz.deploy.model.entity.BizDeploy;
import xyz.ytora.core.biz.deploy.repo.BizDeployRepo;

/**
 * 开发者模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class BizDeployLogic extends BaseLogic<BizDeploy, BizDeployRepo> {

}
