/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.jigsaw.client;

import dev.huka.playground.jigsaw.api.EmployeeService;
import java.util.ServiceLoader;

public class EmployeeClient {
  public static void main(String[] args) {
    ServiceLoader.load(EmployeeService.class)
        .forEach(employeeService -> employeeService.findAll().forEach(System.out::println));
  }
}
