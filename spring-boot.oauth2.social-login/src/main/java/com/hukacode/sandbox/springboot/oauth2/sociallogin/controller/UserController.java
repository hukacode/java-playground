/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.controller;

import com.hukacode.sandbox.springboot.oauth2.sociallogin.config.CurrentUser;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.dto.LocalUser;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.util.GeneralUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> getCurrentUser(
      @CurrentUser LocalUser user,
      @RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authorizedClient) {
    OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
    return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getContent() {
    return ResponseEntity.ok("Public content goes here");
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> getUserContent() {
    return ResponseEntity.ok("User content goes here");
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getAdminContent() {
    return ResponseEntity.ok("Admin content goes here");
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public ResponseEntity<?> getModeratorContent() {
    return ResponseEntity.ok("Moderator content goes here");
  }
}
