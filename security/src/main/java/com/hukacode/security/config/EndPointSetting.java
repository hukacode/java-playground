/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.security.endpoint")
@Getter
@Setter
public class EndPointSetting {
  private String signinUrl;
  private String tokenUrl;
  private String apiRootUrl;
}
