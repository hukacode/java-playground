/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.third_party_data;

import dev.huka.playground.library_management.domain.Author;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface AuthorMapper {
  Author toAuthor(AuthorOL authorOL);

  AuthorOL authorOL(Author author);

  List<Author> toAuthorList(List<AuthorOL> authorOLs);

  List<AuthorOL> toAuthorOLList(List<Author> authors);
}
