/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .formLogin()
        .disable()
        .csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .permitAll();
  }
}
