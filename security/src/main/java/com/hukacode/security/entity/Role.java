/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  @ToString.Exclude
  @ManyToMany(mappedBy = "roleList")
  private Collection<User> userList;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(
      name = "role_privilege",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
  private Collection<Privilege> privilegeList;
}
