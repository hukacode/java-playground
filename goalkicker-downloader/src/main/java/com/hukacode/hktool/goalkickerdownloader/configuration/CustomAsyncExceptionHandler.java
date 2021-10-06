package com.hukacode.hktool.goalkickerdownloader.configuration;

import java.lang.reflect.Method;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

  /**
   * @see org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler#handleUncaughtException(java.lang.Throwable,
   *      java.lang.reflect.Method, java.lang.Object[])
   */
  @Override
  public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
    log.error("Exception message - " + throwable.getMessage());
    log.error("Method name - " + method.getName());
    for (final Object param : obj) {
      log.error("Param - " + param);
    }
  }

}
