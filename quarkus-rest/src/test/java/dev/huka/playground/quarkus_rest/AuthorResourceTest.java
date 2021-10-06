/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.quarkus_rest;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AuthorResourceTest {
  @Test
  public void getAll() {
    given().when().get("/api/authors").then().statusCode(200);
  }
}
