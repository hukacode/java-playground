/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.auth.ajax;

import com.hukacode.security.entity.User;
import com.hukacode.security.model.UserContext;
import com.hukacode.security.user.service.DatabaseUserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@AllArgsConstructor
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
  private final BCryptPasswordEncoder encoder;
  private final DatabaseUserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.notNull(authentication, "No authentication data provided");

    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();
    Optional<User> byUsername = userService.getByUsername(username);

    User user =
        byUsername.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    if (!encoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Authentication failed. Username or Password is invalid.");
    }

    if (user.getRoleList() == null)
      throw new InsufficientAuthenticationException("User has no roles assigned");

    List<GrantedAuthority> authorities =
        user.getRoleList().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());

    UserContext userContext = UserContext.create(user.getUsername(), authorities);

    return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
