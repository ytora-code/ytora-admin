package xyz.ytora.core.sys.schedule.task;

import lombok.extern.slf4j.Slf4j;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.sys.schedule.ParameterTask;
import xyz.ytora.core.sys.schedule.Task;
import xyz.ytora.toolkit.time.Dates;

/**
 * 测试任务
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Task("test")
public class TestTask extends ParameterTask<SysUser> {
    @Override
    public void doTask(SysUser params) {
        log.info("哈哈哈哈....");
        log("日志执行成功:{}", Dates.now());
    }
}
