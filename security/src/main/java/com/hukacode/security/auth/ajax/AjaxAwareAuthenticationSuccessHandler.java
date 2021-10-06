/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hukacode.security.model.UserContext;
import com.hukacode.security.model.token.JwtToken;
import com.hukacode.security.model.token.JwtTokenFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final ObjectMapper mapper;
  private final JwtTokenFactory tokenFactory;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    UserContext userContext = (UserContext) authentication.getPrincipal();

    JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
    JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setAccessToken(accessToken.getToken());
    loginResponse.setRefreshToken(refreshToken.getToken());

    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    mapper.writeValue(response.getWriter(), loginResponse);

    clearAuthenticationAttributes(request);
  }

  /**
   * Removes temporary authentication-related data which may have been stored in the session during
   * the authentication process..
   */
  protected final void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);

    if (session == null) {
      return;
    }

    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }
}
