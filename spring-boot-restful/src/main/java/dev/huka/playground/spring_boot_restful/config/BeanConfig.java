/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.spring_boot_restful.config;

import dev.huka.playground.library_management.business.handler.ReadAuthorQueryHandler;
import dev.huka.playground.library_management.business.handler.ReadBookQueryHandler;
import dev.huka.playground.library_management.file_storage.BookFromCsv;
import dev.huka.playground.library_management.port.in.query.ReadAuthorQuery;
import dev.huka.playground.library_management.port.in.query.ReadBookQuery;
import dev.huka.playground.library_management.port.out.author.AuthorDataProvider;
import dev.huka.playground.library_management.port.out.book.BookDataProvider;
import dev.huka.playground.library_management.third_party_data.OpenLibrary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
  @Bean
  public ReadAuthorQuery readAuthorQuery(AuthorDataProvider authorDataProvider) {
    return new ReadAuthorQueryHandler(authorDataProvider);
  }

  @Bean
  public AuthorDataProvider authorDataProvider() {
    return new OpenLibrary();
  }

  @Bean
  public ReadBookQuery readBookQuery(BookDataProvider bookDataProvider) {
    return new ReadBookQueryHandler(bookDataProvider);
  }

  @Bean
  public BookDataProvider bookDataProvider() {
    return new BookFromCsv();
  }
}
