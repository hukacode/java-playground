/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.spring_boot_restful.api;

import dev.huka.playground.library_management.domain.Book;
import dev.huka.playground.library_management.port.in.query.ReadBookQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookResource {
  private final ReadBookQuery readBookQuery;

  @GetMapping
  public List<Book> getBookList() {
    var input = ReadBookQuery.ReadBookInput.builder().build();
    return readBookQuery.execute(input);
  }
}
