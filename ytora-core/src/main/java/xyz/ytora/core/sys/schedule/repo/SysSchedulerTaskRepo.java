package xyz.ytora.core.sys.schedule.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.sys.schedule.model.entity.SysSchedulerTask;

/**
 * 调度任务模块的持久层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Repository
public class SysSchedulerTaskRepo extends BaseRepo<SysSchedulerTask> {
}
