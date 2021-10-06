/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionLogging {
  @Pointcut("execution(* com.hukacode.logging.controller.*.*(..))")
  public void controllerMethods() {}

  @AfterThrowing(value = "controllerMethods()", throwing = "exception")
  public void logException(JoinPoint joinPoint, Exception exception) throws Throwable {
    var codeSignature = (CodeSignature) joinPoint.getSignature();
    var className = codeSignature.getDeclaringType();
    var methodName = codeSignature.getName();
    var parameterNames = codeSignature.getParameterNames();
    var parameterValues = joinPoint.getArgs();

    var builder = new StringBuilder("->");
    builder.append(className).append(".").append(methodName).append('(');

    for (int i = 0; i < parameterValues.length; i++) {
      if (i > 0) {
        builder.append(", ");
      }

      builder.append(parameterNames[i]).append('=');
      builder.append(parameterValues[i]);
    }

    builder.append(')');
    log.error(builder.toString());
  }
}
