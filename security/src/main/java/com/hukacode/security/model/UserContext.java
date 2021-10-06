/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hukacode.security.deserialize.SimpleGrantedAuthorityDeserializer;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserContext {
  private String username;
  private List<GrantedAuthority> authorities;

  public static UserContext create(String username, List<GrantedAuthority> authorities) {
    if (StringUtils.isBlank(username))
      throw new IllegalArgumentException("Username is blank: " + username);
    return new UserContext(username, authorities);
  }

  @JsonDeserialize(using = SimpleGrantedAuthorityDeserializer.class)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }
}
