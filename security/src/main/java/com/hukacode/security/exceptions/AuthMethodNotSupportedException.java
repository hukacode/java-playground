/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class AuthMethodNotSupportedException extends AuthenticationServiceException {
  public AuthMethodNotSupportedException(String msg) {
    super(msg);
  }
}
