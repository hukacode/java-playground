/*
 * Copyright 2022 huka.dev
 */
package com.example.keycloakexample;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class KeycloakConfig {

  @Bean
  public KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  @Bean
  @Primary
  public KeycloakSpringBootProperties properties() {
    final KeycloakSpringBootProperties props = new KeycloakSpringBootProperties();
    final PolicyEnforcerConfig policyEnforcerConfig = new PolicyEnforcerConfig();
    policyEnforcerConfig.setEnforcementMode(PolicyEnforcerConfig.EnforcementMode.ENFORCING);
    policyEnforcerConfig.setHttpMethodAsScope(true);
    props.setPolicyEnforcerConfig(policyEnforcerConfig);
    return props;
  }
}
