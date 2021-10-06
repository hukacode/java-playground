/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging;

import ch.qos.logback.classic.PatternLayout;
import com.hukacode.logging.logback.SessionConverter;
import com.hukacode.logging.logback.ShortLevelConverter;
import com.hukacode.logging.logback.UserConverter;

public class HkPatternLayout extends PatternLayout {
  static {
    PatternLayout.defaultConverterMap.put("user", UserConverter.class.getName());
    PatternLayout.defaultConverterMap.put("session", SessionConverter.class.getName());
    PatternLayout.defaultConverterMap.put("level", ShortLevelConverter.class.getName());
  }
}
