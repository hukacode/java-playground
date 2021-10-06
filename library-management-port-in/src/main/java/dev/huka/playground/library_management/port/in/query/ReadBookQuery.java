/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.port.in.query;

import com.hukacode.common.usecase.UseCase;
import dev.huka.playground.library_management.domain.Book;
import java.util.List;
import lombok.Builder;
import lombok.Data;

public interface ReadBookQuery extends UseCase<ReadBookQuery.ReadBookInput, List<Book>> {

  @Data
  @Builder
  class ReadBookInput {}
}
