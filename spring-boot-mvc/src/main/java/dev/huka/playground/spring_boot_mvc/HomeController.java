/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.spring_boot_mvc;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
  public String home() {
    return "index";
  }
}
