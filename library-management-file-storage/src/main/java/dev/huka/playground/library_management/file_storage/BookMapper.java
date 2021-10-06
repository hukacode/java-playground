/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.file_storage;

import dev.huka.playground.library_management.domain.Book;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
  Book toBook(BookCsvItem bookCsvItem);

  BookCsvItem bookCsvItem(Book book);

  List<Book> toBookList(List<BookCsvItem> bookCsvItems);

  List<BookCsvItem> toBookCsvItemList(List<Book> books);
}
