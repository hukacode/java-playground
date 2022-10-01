/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.core;

import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.domain.Book;
import java.util.List;

public class TestData {
  public static List<Book> books =
      List.of(
          Book.builder()
              .title("Core Java Volume I")
              .authors(List.of(Author.builder().name("Cay S. Horstmann").build()))
              .price(50D)
              .build(),
          Book.builder()
              .title("Core Java Volume II")
              .authors(List.of(Author.builder().name("Cay S. Horstmann").build()))
              .price(80D)
              .build(),
          Book.builder()
              .title("Effective Java")
              .authors(List.of(Author.builder().name("Joshua Bloch").build()))
              .price(100D)
              .build(),
          Book.builder()
              .title("Head First Java")
              .authors(
                  List.of(
                      Author.builder().name("Kathy Sierra").build(),
                      Author.builder().name("Bert Bates").build()))
              .price(100D)
              .build(),
          Book.builder()
              .title("Java: A Beginner’s Guide")
              .authors(List.of(Author.builder().name("Herbert Schildt").build()))
              .price(33D)
              .build());
}
