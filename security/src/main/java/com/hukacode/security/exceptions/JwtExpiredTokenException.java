/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.exceptions;

import com.hukacode.security.model.token.JwtToken;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtExpiredTokenException extends AuthenticationException {
  private JwtToken token;

  public JwtExpiredTokenException(String msg) {
    super(msg);
  }

  public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
    super(msg, t);
    this.token = token;
  }
}
