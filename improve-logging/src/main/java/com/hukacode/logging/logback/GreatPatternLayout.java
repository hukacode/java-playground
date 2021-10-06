/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.logback;

import ch.qos.logback.classic.PatternLayout;

public class GreatPatternLayout extends PatternLayout {
  static {
    PatternLayout.defaultConverterMap.put("user", UserConverter.class.getName());
    PatternLayout.defaultConverterMap.put("session", SessionConverter.class.getName());
    PatternLayout.defaultConverterMap.put("level", ShortLevelConverter.class.getName());
  }
}
