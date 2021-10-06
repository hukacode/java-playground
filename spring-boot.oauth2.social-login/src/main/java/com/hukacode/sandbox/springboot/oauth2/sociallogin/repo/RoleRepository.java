/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.repo;

import com.hukacode.sandbox.springboot.oauth2.sociallogin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);
}
