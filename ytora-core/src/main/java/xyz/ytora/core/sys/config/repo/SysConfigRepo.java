package xyz.ytora.core.sys.config.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.sys.config.model.entity.SysConfig;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 系统配置模块的持久层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Repository
public class SysConfigRepo extends BaseRepo<SysConfig> {
}
