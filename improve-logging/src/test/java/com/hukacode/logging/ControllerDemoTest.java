/*
 * Copyright 2020 hukacode
 */
package com.hukacode.logging;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hukacode.logging.controller.DemoController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DemoController.class)
class ControllerDemoTest {
  @Autowired private MockMvc mockMvc;

  @Test
  public void logging() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/me?input=fromTest"))
        .andExpect(status().is5xxServerError())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
  }
}
