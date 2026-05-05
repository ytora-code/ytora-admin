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

    public String getConfig(String name, String key) {
        return getConfig(name, key, null);
    }

    public String getConfig(String name, String key, String defaultValue) {
        SysConfig config = repository.one(w -> w
                .eq(SysConfig::getName, name)
                .eq(SysConfig::getKey, key).eq(SysConfig::getStatus, true)
        );

        return config == null ? defaultValue :config.getValue();
    }

}
