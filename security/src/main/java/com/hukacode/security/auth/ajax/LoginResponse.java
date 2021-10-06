/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.ajax;

import lombok.Data;

@Data
public class LoginResponse {
  private String accessToken;
  private String refreshToken;
}
