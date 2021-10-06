/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {
  @GetMapping("/api/me")
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String currentUserName(@RequestParam String input, HttpServletRequest request) {
    // FOR TESTING
    var authReq = new UsernamePasswordAuthenticationToken("admin", "pass");
    SecurityContextHolder.getContext().setAuthentication(authReq);

    log.trace("This is a TRACE log");
    log.debug("This is a DEBUG log");
    log.info("This is a INFO log");
    log.warn("This is a WARN log");
    log.error("This is a ERROR log");

    throw new InternalException("Error");
  }
}
