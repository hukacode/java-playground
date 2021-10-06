package com.hukacode.hktool.goalkickerdownloader.configuration;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AppConfiguration implements AsyncConfigurer {

  /**
   * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncExecutor()
   */
  @Override
  public Executor getAsyncExecutor() {
    return threadPoolTaskExecutor();
  }

  /**
   *
   * @return ThreadPoolTaskExecutor
   */
  @Bean
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(7);
    executor.setMaxPoolSize(42);
    executor.setQueueCapacity(11);
    executor.setThreadNamePrefix("MyExecutor-");
    executor.initialize();
    return executor;
  }


  /**
   * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncUncaughtExceptionHandler()
   */
  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new CustomAsyncExceptionHandler();
  }
}
