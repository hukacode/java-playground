/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class AccessJwtToken implements JwtToken {
  private final String token;
  @JsonIgnore private final Claims claims;
}
