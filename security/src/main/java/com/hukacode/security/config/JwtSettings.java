/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.security.jwt")
@Getter
@Setter
public class JwtSettings {
  private Integer tokenExpirationTimeInMinutes;
  private String tokenIssuer;
  private String tokenSigningKey;
  private Integer refreshTokenExpTimeInMinutes;
}
