/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.aop;

import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
public class ExecutionLoggingAspect {
  @Value("${app.debug:false}")
  private boolean enabled;

  @Pointcut("within(@org.springframework.stereotype.Service *)")
  public void service() {}

  @Pointcut("within(@org.springframework.stereotype.Controller *)")
  public void controller() {}

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void restController() {}

  @AfterThrowing(value = "controller() || restController() || service()", throwing = "exception")
  public void logException(JoinPoint joinPoint, Exception exception) throws Throwable {
    log.error(buildInput(joinPoint));
  }

  @Around("controller() || restController() || service()")
  public Object logInputOutput(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    enterMethod(proceedingJoinPoint);

    var stopWatch = new StopWatch();

    stopWatch.start();
    var result = proceedingJoinPoint.proceed();
    stopWatch.stop();

    exitMethod(proceedingJoinPoint, result, stopWatch.getTotalTimeMillis());

    var signature = proceedingJoinPoint.getSignature();
    var className = signature.getDeclaringType();
    var methodName = signature.getName();

    if (stopWatch.getTotalTimeMillis() > 10000) {
      log.warn(
          MessageFormat.format(
              "Class {0}, method {1} execution longer than {2} ms!", className, methodName, 10000));
    }

    return result;
  }

  private void enterMethod(JoinPoint joinPoint) {
    if (!enabled) return;

    log.debug(buildInput(joinPoint));
  }

  private String buildInput(JoinPoint joinPoint) {
    var codeSignature = (CodeSignature) joinPoint.getSignature();
    var className = codeSignature.getDeclaringType();
    var methodName = codeSignature.getName();
    var parameterNames = codeSignature.getParameterNames();
    var parameterValues = joinPoint.getArgs();

    var builder = new StringBuilder("\u21E2 ");
    builder.append(className).append(".").append(methodName).append('(');

    for (int i = 0; i < parameterValues.length; i++) {
      if (i > 0) {
        builder.append(", ");
      }

      builder.append(parameterNames[i]).append('=');
      builder.append(Strings.toString(parameterValues[i]));
    }

    builder.append(')');
    return builder.toString();
  }

  private void exitMethod(JoinPoint joinPoint, Object result, long lengthMillis) {
    if (!enabled) return;

    var signature = joinPoint.getSignature();
    var className = signature.getDeclaringType();
    var methodName = signature.getName();

    var hasReturnType =
        signature instanceof MethodSignature
            && ((MethodSignature) signature).getReturnType() != void.class;
    var builder =
        new StringBuilder("\u21E0 ")
            .append(className)
            .append(".")
            .append(methodName)
            .append(" [")
            .append(lengthMillis)
            .append("ms]");

    if (hasReturnType) {
      builder.append(" = ");
      builder.append(Strings.toString(result));
    }

    log.debug(builder.toString());
  }
}
