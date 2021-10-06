/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.jwt;

import com.hukacode.security.auth.JwtAuthenticationToken;
import com.hukacode.security.config.JwtSettings;
import com.hukacode.security.model.UserContext;
import com.hukacode.security.model.token.RawAccessJwtToken;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
  private final JwtSettings jwtSettings;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
    var jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
    var subject = jwsClaims.getBody().getSubject();
    List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
    List<GrantedAuthority> authorities =
        scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    UserContext context = UserContext.create(subject, authorities);

    return new JwtAuthenticationToken(context, context.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
