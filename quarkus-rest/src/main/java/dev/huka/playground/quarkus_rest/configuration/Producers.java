/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.quarkus_rest.configuration;

import dev.huka.playground.library_management.business.handler.ReadAuthorQueryHandler;
import dev.huka.playground.library_management.business.handler.ReadBookQueryHandler;
import dev.huka.playground.library_management.file_storage.BookFromCsv;
import dev.huka.playground.library_management.port.in.query.ReadAuthorQuery;
import dev.huka.playground.library_management.port.in.query.ReadBookQuery;
import dev.huka.playground.library_management.port.out.author.AuthorDataProvider;
import dev.huka.playground.library_management.port.out.book.BookDataProvider;
import dev.huka.playground.library_management.third_party_data.OpenLibrary;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Producers {

  @Produces
  public ReadBookQuery readBookQuery(BookDataProvider bookDataProvider) {
    return new ReadBookQueryHandler(bookDataProvider);
  }

  @Produces
  public BookDataProvider bookDataProvider() {
    return new BookFromCsv();
  }

  @Produces
  public ReadAuthorQuery readAuthorQuery(AuthorDataProvider authorDataProvider) {
    return new ReadAuthorQueryHandler(authorDataProvider);
  }

  @Produces
  public AuthorDataProvider authorDataProvider() {
    return new OpenLibrary();
  }
}
