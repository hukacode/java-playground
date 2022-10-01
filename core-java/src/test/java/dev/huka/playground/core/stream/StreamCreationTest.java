/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.core.stream;

import dev.huka.playground.core.TestData;
import dev.huka.playground.library_management.domain.Book;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class StreamCreationTest {

  @Test
  void fromArray() {
    int[] a = {1, 2, 5};

    Arrays.stream(a).forEach(System.out::println);
  }

  @Test
  void fromOf() {
    Stream.of(1, 2, 5).forEach(System.out::println);
  }

  @Test
  void fromList() {
    TestData.books.stream().map(Book::getTitle).forEach(System.out::println);
  }

  @Test
  void fromBuilder() {
    var builder = Stream.builder();
    builder.accept(1);
    builder.accept(2);

    var stream = builder.build();
    stream.forEach(System.out::println);
  }
}
