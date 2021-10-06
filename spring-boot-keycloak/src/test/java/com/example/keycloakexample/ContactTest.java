/*
 * Copyright 2022 huka.dev
 */
package com.example.keycloakexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ContactTest {

  String token =
      "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIteExjbFpIalF4QWVFTjViX2c2RGZ6YnZteWxiWlNZYXBOVlZFYzVoUjdvIn0.eyJleHAiOjE2NTY1MDczNzksImlhdCI6MTY1NjQ3MTM3OSwianRpIjoiNWI1MTY4NjMtM2I2My00MDI2LWI5MjktMDlhYThmYTBiYjM1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9WVFZjYWJTREsiLCJhdWQiOlsicmVhbG0tbWFuYWdlbWVudCIsImFjY291bnQiXSwic3ViIjoiZjQ3NDI2OTItOGI2OS00MGNhLTk3NTctMjE0NjU5ZTExNmY4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYm9zLWFwaSIsInNlc3Npb25fc3RhdGUiOiI2ZTVmMTI5MC00OTkxLTQ5MjEtODFiZi1kNzAwODRmMGUzODQiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIi8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXZ0dmNhYnNkayIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJEVktIIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsInZpZXctcmVhbG0iLCJtYW5hZ2UtaWRlbnRpdHktcHJvdmlkZXJzIiwiaW1wZXJzb25hdGlvbiIsInJlYWxtLWFkbWluIiwiY3JlYXRlLWNsaWVudCIsIm1hbmFnZS11c2VycyIsInF1ZXJ5LXJlYWxtcyIsInZpZXctYXV0aG9yaXphdGlvbiIsInF1ZXJ5LWNsaWVudHMiLCJxdWVyeS11c2VycyIsIm1hbmFnZS1ldmVudHMiLCJtYW5hZ2UtcmVhbG0iLCJ2aWV3LWV2ZW50cyIsInZpZXctdXNlcnMiLCJ2aWV3LWNsaWVudHMiLCJtYW5hZ2UtYXV0aG9yaXphdGlvbiIsIm1hbmFnZS1jbGllbnRzIiwicXVlcnktZ3JvdXBzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiI2ZTVmMTI5MC00OTkxLTQ5MjEtODFiZi1kNzAwODRmMGUzODQiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6Imh1a2FfZHZraCJ9.S2nrV4DcWgJMPSLI_Sx9DL6tK-lTPhkEyyHyVg0AtWL4KR347PHE1_H2S3nHJcX-RJzcGQSxcyYOt8dPYyfyilcjjAP_hjUXKc20Y3DznKGe0YFMO-NUJtHaaAVbZ8m_NxzWU5rmZE5fkFZcSHOWxSJcCL4TPylovdncfltrlMf6h8I79VL5S6vesdwHV9y7NfJKzydzbyqjLYTfZsqCQoSMuyK9mz4LxYJg0oY5cLGoaNemRzPDnH7Uho33fHJzjTvrZXc59rufAUs6fR8c1-aell7abx9A5LDXTXBFF6xDpQEWauwIAvSikTkEKRgRhwf_PUHhPA0Eb5xD4fAMEA";

  @Test
  void getTest() throws Exception {
    var request =
        HttpRequest.newBuilder(new URI("http://localhost:8081/contacts"))
            .header("Authorization", "Bearer " + token)
            .build();

    var client = HttpClient.newHttpClient();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void postTest() throws Exception {
    var request =
        HttpRequest.newBuilder(new URI("http://localhost:8081/contacts"))
            .header("Authorization", "Bearer " + token)
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();

    var client = HttpClient.newHttpClient();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
  }

  @Test
  void putTest() throws Exception {
    var request =
        HttpRequest.newBuilder(new URI("http://localhost:8081/contacts"))
            .header("Authorization", "Bearer " + token)
            .PUT(HttpRequest.BodyPublishers.noBody())
            .build();

    var client = HttpClient.newHttpClient();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
  }

  @Test
  void deleteTest() throws Exception {
    var request =
        HttpRequest.newBuilder(new URI("http://localhost:8081/contacts"))
            .header("Authorization", "Bearer " + token)
            .DELETE()
            .build();

    var client = HttpClient.newHttpClient();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
  }
}
