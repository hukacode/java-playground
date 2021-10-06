/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.port.in.query;

import com.hukacode.common.usecase.UseCase;
import dev.huka.playground.library_management.domain.Author;
import java.util.List;
import lombok.Builder;
import lombok.Data;

public interface ReadAuthorQuery extends UseCase<ReadAuthorQuery.ReadAuthorInput, List<Author>> {

  @Data
  @Builder
  class ReadAuthorInput {
    private String name;
  }
}
