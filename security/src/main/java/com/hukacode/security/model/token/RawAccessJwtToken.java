/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.model.token;

import com.hukacode.security.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;

@AllArgsConstructor
@Getter
@Slf4j
public class RawAccessJwtToken implements JwtToken {
  private final String token;

  /**
   * Parses and validates JWT Token signature.
   *
   * @throws BadCredentialsException
   * @throws JwtExpiredTokenException
   */
  public Jws<Claims> parseClaims(String signingKey) {
    try {
      return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
    } catch (UnsupportedJwtException
        | MalformedJwtException
        | IllegalArgumentException
        | SignatureException ex) {
      log.error("Invalid JWT Token", ex);
      throw new BadCredentialsException("Invalid JWT token: ", ex);
    } catch (ExpiredJwtException expiredEx) {
      log.info("JWT Token is expired", expiredEx);
      throw new JwtExpiredTokenException(this, "JWT Token expired", expiredEx);
    }
  }
}
