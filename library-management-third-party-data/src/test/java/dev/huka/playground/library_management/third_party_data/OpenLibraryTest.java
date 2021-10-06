/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.third_party_data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import dev.huka.playground.library_management.domain.Author;
import org.junit.jupiter.api.Test;

class OpenLibraryTest {
  private final OpenLibrary openLibrary = new OpenLibrary();

  @Test
  void givenName_whenReadAuthorList_thenReturnAuthors() {
    var name = "mark";

    var response = openLibrary.readAuthorList(name);

    assertThat(response).isNotNull();
    assertThat(response).extracting(Author::getName).contains("Mark Twain");
    assertThat(response)
        .extracting(Author::getName, Author::getTopWork)
        .contains(tuple("Mark Twain", "Adventures of Huckleberry Finn"));
  }
}
