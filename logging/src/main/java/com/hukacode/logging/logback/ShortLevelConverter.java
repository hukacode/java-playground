/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ShortLevelConverter extends ClassicConverter {
  @Override
  public String convert(ILoggingEvent iLoggingEvent) {
    return iLoggingEvent.getLevel().toString().substring(0, 1);
  }
}
