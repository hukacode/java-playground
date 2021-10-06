/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.third_party_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import lombok.Data;

@Data
public class AuthorOL {
  private String key;
  private String type;
  private String name;

  @JsonProperty("alternate_names")
  private ArrayList<String> alternate_names;

  @JsonProperty("birth_date")
  private String birthDate;

  @JsonProperty("death_date")
  private String deathDate;

  @JsonProperty("top_work")
  private String topWork;

  @JsonProperty("top_subjects")
  private ArrayList<String> topSubjects;
}
