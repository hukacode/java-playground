/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.quarkus_rest.api;

import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.port.in.query.ReadAuthorQuery;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/api/authors")
@AllArgsConstructor
public class AuthorResource {
  ReadAuthorQuery readAuthorQuery;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Author> getAuthorList(@PathParam("name") String name) {
    var input = ReadAuthorQuery.ReadAuthorInput.builder().name(name).build();
    return readAuthorQuery.execute(input);
  }
}
