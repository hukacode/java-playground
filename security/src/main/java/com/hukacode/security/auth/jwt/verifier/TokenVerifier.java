/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.jwt.verifier;

public interface TokenVerifier {
  boolean verify(String jti);
}
