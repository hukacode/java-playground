/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.security.authorization")
@Getter
@Setter
public class AuthorizationSetting {
  private String authenticationHeaderName;
}
