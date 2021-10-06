/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.endpoint;

import com.hukacode.security.auth.JwtAuthenticationToken;
import com.hukacode.security.model.UserContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileEndpoint {

  @GetMapping("/api/me")
  public ResponseEntity<UserContext> readProfile(JwtAuthenticationToken token) {
    var userContext = (UserContext) token.getPrincipal();
    return ResponseEntity.ok().body(userContext);
  }
}
