/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.jwt.extractor;

public interface TokenExtractor {
  String extract(String payload);
}
