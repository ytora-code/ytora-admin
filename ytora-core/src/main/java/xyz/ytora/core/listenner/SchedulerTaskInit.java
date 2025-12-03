package xyz.ytora.core.listenner;//package org.ytor.core.listenner;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import org.ytor.common.scheduler.Task;
//import org.ytor.common.scheduler.support.timewheel.ITimeWheelScheduler;
//import org.ytor.common.scheduler.support.timewheel.TimeWheelTask;
//import org.ytor.common.util.Strs;
//import org.ytor.common.util.invoke.Reflects;
//import org.ytor.core.sysapi.scheduler.model.SysSchedulerTask;
//import org.ytor.core.sysapi.service.ISysSchedulerTaskService;
//
//import java.util.List;
//
///**
// * created by yangtong on 2025/7/9 13:31:13
// * <br/>
// * 启动时加载所有定时任务
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class SchedulerTaskInit implements ApplicationRunner {
//
//    private final ITimeWheelScheduler scheduler;
//    private final ISysSchedulerTaskService schedulerTaskService;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        log.info("准备加载定时任务...");
//        List<SysSchedulerTask> tasks = schedulerTaskService.lambdaQuery().eq(SysSchedulerTask::getStatus, 1).list();
//        for (SysSchedulerTask schedulerTask : tasks) {
//            if (Strs.isEmpty(schedulerTask.getClassName())) {
//                continue;
//            }
//            Object task = Reflects.newInstance(Class.forName(schedulerTask.getClassName()));
//            if (task instanceof Task) {
//                scheduler.addTask(new TimeWheelTask(schedulerTask.getCron(), (Task) task));
//            }
//        }
//        log.info("定时任务加载完毕!");
//    }
//}
