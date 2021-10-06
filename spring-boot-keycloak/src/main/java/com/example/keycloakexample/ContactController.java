/*
 * Copyright 2022 huka.dev
 */
package com.example.keycloakexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ContactController {
  @GetMapping("/contacts")
  public String get() {
    return "From GetMapping contacts";
  }

  @PostMapping("/contacts")
  public String post() {
    return "From PostMapping contacts";
  }

  @PutMapping("/contacts")
  public String put() {
    return "From PutMapping contacts";
  }

  @DeleteMapping("/contacts")
  public String delete() {
    return "From DeleteMapping contacts";
  }
}
