/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.dto;

import java.util.List;
import lombok.Value;

@Value
public class UserInfo {
  private String id, displayName, email;
  private List<String> roles;
}
