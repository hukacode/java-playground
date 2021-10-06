/*
 * Copyright 2020 hukacode
 */
package com.hukacode.security.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Privilege {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  @ToString.Exclude
  @ManyToMany(mappedBy = "privilegeList")
  private Collection<Role> roleList;
}
