/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.business.handler;

import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.port.in.query.ReadAuthorQuery;
import dev.huka.playground.library_management.port.out.author.AuthorDataProvider;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
// @Slf4j
public class ReadAuthorHandler implements ReadAuthorQuery {
  private AuthorDataProvider authorDataProvider;

  @Override
  public List<Author> execute(ReadAuthorInput readAuthorInput) {
    //    log.info(readAuthorInput.getName());
    return authorDataProvider.readAuthorList(readAuthorInput.getName());
  }
}
