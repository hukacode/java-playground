/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.spring_boot_restful.api;

import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.port.in.query.ReadAuthorQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorResource {
  private final ReadAuthorQuery readAuthorQuery;

  @GetMapping
  public List<Author> getAuthorList(@RequestParam("name") String name) {
    var input = ReadAuthorQuery.ReadAuthorInput.builder().name(name).build();
    return readAuthorQuery.execute(input);
  }
}
