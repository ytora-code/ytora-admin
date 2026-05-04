package xyz.ytora.core.sys.log.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.sys.log.model.entity.SysLog;

/**
 * 日志模块的持久层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Repository
public class SysLogRepo extends BaseRepo<SysLog> {
}
