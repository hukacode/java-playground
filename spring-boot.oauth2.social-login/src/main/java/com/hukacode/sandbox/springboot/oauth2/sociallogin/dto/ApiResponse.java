/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.dto;

import lombok.Value;

@Value
public class ApiResponse {
  private Boolean success;
  private String message;
}
