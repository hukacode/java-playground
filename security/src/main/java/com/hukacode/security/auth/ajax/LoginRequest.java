/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.ajax;

import lombok.Data;

@Data
public class LoginRequest {
  private String email;
  private String password;
}
