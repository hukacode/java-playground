/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.business.handler;

import dev.huka.playground.library_management.domain.Book;
import dev.huka.playground.library_management.port.in.query.ReadBookQuery;
import dev.huka.playground.library_management.port.out.book.BookDataProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadBookQueryHandler implements ReadBookQuery {
  private final BookDataProvider bookDataProvider;

  @Override
  public List<Book> execute(ReadBookQuery.ReadBookInput readBookInput) {
    return bookDataProvider.readBookList();
  }
}
