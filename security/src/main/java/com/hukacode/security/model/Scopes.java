/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.model;

public enum Scopes {
  REFRESH_TOKEN;

  public String authority() {
    return "ROLE_" + this.name();
  }
}
