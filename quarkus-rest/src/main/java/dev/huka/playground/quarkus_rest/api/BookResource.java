/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.quarkus_rest.api;

import dev.huka.playground.library_management.domain.Book;
import dev.huka.playground.library_management.port.in.query.ReadBookQuery;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/books")
public class BookResource {
  @Inject ReadBookQuery readBookQuery;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Book> getBookList() {
    var input = ReadBookQuery.ReadBookInput.builder().build();
    return readBookQuery.execute(input);
  }
}
