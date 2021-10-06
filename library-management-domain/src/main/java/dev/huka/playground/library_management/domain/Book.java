/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
  private String isbn;
  private String title;
  private Integer numberOfPages;
  private Double price;
  private List<Author> authors;
}
