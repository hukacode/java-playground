/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.file_storage;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import dev.huka.playground.library_management.domain.Book;
import dev.huka.playground.library_management.port.out.book.BookDataProvider;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.mapstruct.factory.Mappers;

public class BookFromCsv implements BookDataProvider {
  @Override
  public List<Book> readBookList() {
    var mapper = Mappers.getMapper(BookMapper.class);
    var ms = new HeaderColumnNameMappingStrategy<BookCsvItem>();
    ms.setType(BookCsvItem.class);

    var classLoader = getClass().getClassLoader();
    var inputStream = classLoader.getResourceAsStream("book.csv");

    assert inputStream != null;
    CsvToBean<BookCsvItem> cb;
    try {
      cb =
          new CsvToBeanBuilder<BookCsvItem>(new InputStreamReader(inputStream, "UTF-8"))
              .withType(BookCsvItem.class)
              .withMappingStrategy(ms)
              .build();
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }

    var bookCsvItemList = cb.parse();

    return mapper.toBookList(bookCsvItemList);
  }
}
