/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.jigsaw.implementation;

import dev.huka.playground.jigsaw.api.Employee;
import dev.huka.playground.jigsaw.api.EmployeeService;
import java.util.List;

public class EmployeeHandler implements EmployeeService {
  @Override
  public List<Employee> findAll() {
    return List.of(new Employee("Huka"));
  }
}
