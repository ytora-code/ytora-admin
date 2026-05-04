package xyz.ytora.core.sys.recyclebin.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 回收站模块的持久层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Repository
public class SysRecycleBinRepo extends BaseRepo<SysRecycleBin> {
}
