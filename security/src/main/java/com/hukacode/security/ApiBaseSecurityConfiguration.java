/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hukacode.security.auth.ajax.AjaxAuthenticationProvider;
import com.hukacode.security.auth.ajax.AjaxLoginProcessingFilter;
import com.hukacode.security.auth.jwt.JwtAuthenticationProvider;
import com.hukacode.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.hukacode.security.auth.jwt.SkipPathRequestMatcher;
import com.hukacode.security.auth.jwt.extractor.TokenExtractor;
import com.hukacode.security.config.AuthorizationSetting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class ApiBaseSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired private AuthorizationSetting authorizationSetting;
  @Autowired private AuthenticationSuccessHandler authenticationSuccessHandler;
  @Autowired private AuthenticationFailureHandler authenticationFailureHandler;
  @Autowired private AjaxAuthenticationProvider ajaxAuthenticationProvider;
  @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
  @Autowired private TokenExtractor tokenExtractor;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private ObjectMapper objectMapper;

  protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint)
      throws Exception {
    var ajaxLoginProcessingFilter =
        new AjaxLoginProcessingFilter(
            loginEntryPoint,
            authenticationSuccessHandler,
            authenticationFailureHandler,
            objectMapper);
    ajaxLoginProcessingFilter.setAuthenticationManager(this.authenticationManager);
    return ajaxLoginProcessingFilter;
  }

  protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(
      List<String> pathsToSkip, String pattern) throws Exception {
    var matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
    var jwtTokenAuthenticationProcessingFilter =
        new JwtTokenAuthenticationProcessingFilter(
            authenticationFailureHandler, tokenExtractor, matcher, authorizationSetting);
    jwtTokenAuthenticationProcessingFilter.setAuthenticationManager(this.authenticationManager);
    return jwtTokenAuthenticationProcessingFilter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(ajaxAuthenticationProvider);
    auth.authenticationProvider(jwtAuthenticationProvider);
  }
}
