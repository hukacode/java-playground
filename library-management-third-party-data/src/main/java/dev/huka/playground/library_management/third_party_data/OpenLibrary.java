/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.third_party_data;

import static java.time.temporal.ChronoUnit.SECONDS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.huka.playground.library_management.domain.Author;
import dev.huka.playground.library_management.port.out.author.AuthorDataProvider;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import org.mapstruct.factory.Mappers;

public class OpenLibrary implements AuthorDataProvider {
  private String AUTHOR_URL = "https://openlibrary.org/search/authors.json?q=";

  @Override
  public List<Author> readAuthorList(String name) {
    var client = HttpClient.newHttpClient();
    try {
      var request =
          HttpRequest.newBuilder(new URI(AUTHOR_URL + name))
              .timeout(Duration.of(10, SECONDS))
              .GET()
              .build();
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      var authorsFromOL = parse(response.body());

      var mapper = Mappers.getMapper(AuthorMapper.class);
      return mapper.toAuthorList(authorsFromOL);
    } catch (URISyntaxException | InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private List<AuthorOL> parse(String json) {
    var objectMapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      var jsonNode = objectMapper.readTree(json);
      var authors = jsonNode.get("docs");
      var objectReader = objectMapper.readerFor(new TypeReference<List<AuthorOL>>() {});
      return objectReader.readValue(authors);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
