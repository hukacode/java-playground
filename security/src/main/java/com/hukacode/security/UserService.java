/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security;

import com.hukacode.security.entity.User;
import java.util.Optional;

public interface UserService {
  Optional<User> getByUsername(String username);
}
