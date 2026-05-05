package xyz.ytora.core.sys.icon.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.sys.icon.model.data.SysIconData;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;
import xyz.ytora.core.sys.icon.repo.SysIconRepo;
import xyz.ytora.sqlux.orm.Page;

/**
 * 系统图标库模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysIconLogic extends BaseLogic<SysIcon, SysIconRepo> {

    /**
     * 根据CODE查询
     */
    public SysIconData queryByCode(String code) {
        SysIcon icon = repository.one(w -> w.eq(SysIcon::getCode, code));
        if (icon == null) {
            throw new BaseException("code为[" + code + "]的icon不存在");
        }
        return icon.toData();
    }

    /**
     * 分页查询系统图标库
     */
    public Page<SysIconData> pageByKey(String key, Integer pageNo, Integer pageSize) {
        Page<SysIcon> page = repository.page(w -> w
                .like(SysIcon::getCode, key)
                .or()
                .like(SysIcon::getName, key), pageNo, pageSize
        );
        return page.trans(SysIcon::toData);
    }
}
