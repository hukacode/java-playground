/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionConverter extends ClassicConverter {
  @Override
  public String convert(ILoggingEvent event) {
    var attrs = RequestContextHolder.getRequestAttributes();

    if (attrs != null) {
      return attrs.getSessionId().substring(0, Math.min(6, attrs.getSessionId().length()));
    }

    return "";
  }
}
