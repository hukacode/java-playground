/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hukacode.logging")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
