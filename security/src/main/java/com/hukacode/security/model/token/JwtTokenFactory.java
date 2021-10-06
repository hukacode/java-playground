/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.model.token;

import com.hukacode.security.config.JwtSettings;
import com.hukacode.security.model.Scopes;
import com.hukacode.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtTokenFactory {
  private final JwtSettings jwtSettings;

  public AccessJwtToken createAccessJwtToken(UserContext userContext) {
    if (StringUtils.isBlank(userContext.getUsername()))
      throw new IllegalArgumentException("Cannot create JWT Token without username");

    if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
      throw new IllegalArgumentException("User doesn't have any privileges");

    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put(
        "scopes",
        userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

    LocalDateTime currentTime = LocalDateTime.now();
    String token =
        Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtSettings.getTokenIssuer())
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(
                Date.from(
                    currentTime
                        .plusMinutes(jwtSettings.getTokenExpirationTimeInMinutes())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
            .signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())
            .compact();

    return new AccessJwtToken(token, claims);
  }

  public JwtToken createRefreshToken(UserContext userContext) {
    if (StringUtils.isBlank(userContext.getUsername())) {
      throw new IllegalArgumentException("Cannot create JWT Token without username");
    }

    LocalDateTime currentTime = LocalDateTime.now();
    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

    String token =
        Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtSettings.getTokenIssuer())
            .setId(UUID.randomUUID().toString())
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(
                Date.from(
                    currentTime
                        .plusMinutes(jwtSettings.getRefreshTokenExpTimeInMinutes())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
            .signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())
            .compact();

    return new AccessJwtToken(token, claims);
  }
}
