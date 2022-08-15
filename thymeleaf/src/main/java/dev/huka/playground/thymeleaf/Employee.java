/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.thymeleaf;

import java.time.LocalDate;
import java.util.List;

public record Employee(String name, LocalDate birthDate, List<String> skills) {}
