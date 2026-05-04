package xyz.ytora.core.sys.dict.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.repo.SysDictRepo;

/**
 * 字典模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysDictLogic extends BaseLogic<SysDict, SysDictRepo> {

}
