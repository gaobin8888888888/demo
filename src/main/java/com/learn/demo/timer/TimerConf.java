package com.learn.demo.timer;

import io.netty.util.HashedWheelTimer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author gb
 * @date 2021/3/16 3:53 下午
 * @description
 */
@Configuration
public class TimerConf {

    @Bean
    public HashedWheelTimer timer(){
        HashedWheelTimer timer = new HashedWheelTimer();
        timer.start();
        return timer;
    }

    @Bean
    public ThreadPoolTaskExecutor imMessageSendThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(0);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
