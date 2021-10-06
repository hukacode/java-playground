/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.file_storage;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import dev.huka.playground.library_management.domain.Author;
import java.util.List;
import lombok.Data;

@Data
public class BookCsvItem {
  @CsvBindByName private long id;
  @CsvBindByName private String title;
  @CsvBindByName private Integer numberOfPages;
  @CsvBindByName private Double price;

  @CsvBindAndSplitByName(elementType = String.class, splitOn = ";")
  private List<Author> authors;
}
