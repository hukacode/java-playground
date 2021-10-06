/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.model.token;

import com.hukacode.security.model.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("unchecked")
@AllArgsConstructor
@Getter
public class RefreshToken implements JwtToken {
  private final Jws<Claims> claims;

  public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
    Jws<Claims> claims = token.parseClaims(signingKey);

    List<String> scopes = claims.getBody().get("scopes", List.class);
    if (scopes == null
        || scopes.isEmpty()
        || scopes.stream().noneMatch(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope))) {
      return Optional.empty();
    }

    return Optional.of(new RefreshToken(claims));
  }

  @Override
  public String getToken() {
    return null;
  }

  public String getJti() {
    return claims.getBody().getId();
  }

  public String getSubject() {
    return claims.getBody().getSubject();
  }
}
