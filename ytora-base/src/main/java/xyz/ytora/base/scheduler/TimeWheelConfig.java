package xyz.ytora.base.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ytora.base.scheduler.timewheel.ITimeWheelScheduler;

/**
 * 时间轮配置
 */
@Configuration
public class TimeWheelConfig {
    @Bean
    public ITimeWheelScheduler timeWheelScheduler() {
        ITimeWheelScheduler instance = ITimeWheelScheduler.instance();
        instance.start();
        return instance;
    }
}
