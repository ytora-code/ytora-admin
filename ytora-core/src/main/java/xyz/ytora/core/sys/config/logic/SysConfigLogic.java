package xyz.ytora.core.sys.config.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.sys.config.model.entity.SysConfig;
import xyz.ytora.core.sys.config.repo.SysConfigRepo;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 系统配置模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysConfigLogic extends BaseLogic<SysConfig, SysConfigRepo> {

}
