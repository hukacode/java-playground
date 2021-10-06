/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserConverter extends ClassicConverter {
  @Override
  public String convert(ILoggingEvent iLoggingEvent) {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return "";
    } else {
      return authentication.getName();
    }
  }
}
