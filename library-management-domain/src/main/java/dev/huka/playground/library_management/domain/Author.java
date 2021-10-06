/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Author {
  private String key;
  private String type;
  private String name;
  private ArrayList<String> alternateNames;
  private LocalDate birthDate;
  private LocalDate deathDate;
  private String topWork;
  private ArrayList<String> topSubjects;
}
