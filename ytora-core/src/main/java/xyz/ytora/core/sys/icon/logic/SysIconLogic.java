package xyz.ytora.core.sys.icon.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;
import xyz.ytora.core.sys.icon.repo.SysIconRepo;

/**
 * 系统图标库模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysIconLogic extends BaseLogic<SysIcon, SysIconRepo> {

}
