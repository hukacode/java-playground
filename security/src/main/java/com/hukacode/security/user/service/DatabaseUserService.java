/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.user.service;

import com.hukacode.security.UserService;
import com.hukacode.security.entity.User;
import com.hukacode.security.user.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Transactional
@Service
public class DatabaseUserService implements UserService {
  private final UserRepository userRepository;

  @Override
  public Optional<User> getByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }
}
