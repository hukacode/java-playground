/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.core.stream;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

import dev.huka.playground.core.TestData;
import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.domain.Book;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class StreamOperationTest {

  @Test
  void collect() {
    var set = TestData.books.stream().map(Book::getTitle).collect(Collectors.toSet());
    assertThat(set).hasSize(TestData.books.size());
  }

  @Test
  void map() {
    TestData.books.stream().map(Book::getTitle).forEach(System.out::println);
  }

  @Test
  void flatMap() {
    TestData.books.stream()
        .flatMap(book -> book.getAuthors().stream())
        .map(Author::getName)
        .forEach(System.out::println);
  }

  @Test
  void flatMap2() {
    TestData.books.stream()
        .map(Book::getAuthors)
        .flatMap(Collection::stream)
        .map(Author::getName)
        .forEach(System.out::println);
  }

  @Test
  void filter() {
    TestData.books.stream().filter(book -> book.getPrice() > 50).forEach(System.out::println);
  }

  @Test
  void findFirst() {
    TestData.books.stream().findFirst().ifPresent(System.out::println);
  }

  @Test
  void findAny() {
    TestData.books.stream().findAny().ifPresent(System.out::println);
  }

  @Test
  void peek() {
    TestData.books.stream()
        .peek(System.out::println)
        .peek(book -> book.setPrice(book.getPrice() - 5))
        .forEach(System.out::println);
  }

  @Test
  void sorted() {
    TestData.books.stream()
        .sorted(Comparator.comparing(Book::getTitle))
        .forEach(System.out::println);
  }

  @Test
  void sorted2() {
    TestData.books.stream()
        .sorted(Comparator.comparing(book -> book.getAuthors().size()))
        .forEach(System.out::println);
  }

  @Test
  void sum() {
    var sum = TestData.books.stream().mapToDouble(Book::getPrice).sum();
    assertThat(sum).isEqualTo(363.0);
  }

  @Test
  void grouping() {
    var group = TestData.books.stream().collect(groupingBy(Book::getAuthors));
    group.forEach(
        (authors, books) -> {
          System.out.println(authors);
          System.out.println(books.size());
        });
  }
}
