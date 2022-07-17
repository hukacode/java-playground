/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.business.handler;

import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.port.in.query.ReadAuthorQuery;
import dev.huka.playground.library_management.port.out.author.AuthorDataProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
// @Slf4j
public class ReadAuthorQueryHandler implements ReadAuthorQuery {
  private final AuthorDataProvider authorDataProvider;

  @Override
  public List<Author> execute(ReadAuthorInput readAuthorInput) {
    //    log.info(readAuthorInput.getName());
    return authorDataProvider.readAuthorList(readAuthorInput.getName());
  }
}
