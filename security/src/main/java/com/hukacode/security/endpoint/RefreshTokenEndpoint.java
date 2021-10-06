/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.endpoint;

import com.hukacode.security.UserService;
import com.hukacode.security.auth.jwt.extractor.TokenExtractor;
import com.hukacode.security.auth.jwt.verifier.TokenVerifier;
import com.hukacode.security.config.AuthorizationSetting;
import com.hukacode.security.config.EndPointSetting;
import com.hukacode.security.config.JwtSettings;
import com.hukacode.security.entity.User;
import com.hukacode.security.exceptions.InvalidJwtToken;
import com.hukacode.security.model.UserContext;
import com.hukacode.security.model.token.JwtToken;
import com.hukacode.security.model.token.JwtTokenFactory;
import com.hukacode.security.model.token.RawAccessJwtToken;
import com.hukacode.security.model.token.RefreshToken;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTokenEndpoint {
  @Autowired private AuthorizationSetting authorizationSetting;
  @Autowired private EndPointSetting endPointSetting;
  @Autowired private JwtTokenFactory tokenFactory;
  @Autowired private JwtSettings jwtSettings;
  @Autowired private UserService userService;
  @Autowired private TokenVerifier tokenVerifier;

  @Autowired
  @Qualifier("jwtHeaderTokenExtractor")
  private TokenExtractor tokenExtractor;

  @GetMapping(value = "/api/auth/token", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody JwtToken refreshToken(
      HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String tokenPayload =
        tokenExtractor.extract(
            request.getHeader(authorizationSetting.getAuthenticationHeaderName()));

    RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
    RefreshToken refreshToken =
        RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey())
            .orElseThrow(InvalidJwtToken::new);

    String jti = refreshToken.getJti();
    if (!tokenVerifier.verify(jti)) {
      throw new InvalidJwtToken();
    }

    String subject = refreshToken.getSubject();
    User user =
        userService
            .getByUsername(subject)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

    if (user.getRoleList() == null)
      throw new InsufficientAuthenticationException("User has no roles assigned");
    List<GrantedAuthority> authorities =
        user.getRoleList().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());

    UserContext userContext = UserContext.create(user.getUsername(), authorities);

    return tokenFactory.createAccessJwtToken(userContext);
  }
}
