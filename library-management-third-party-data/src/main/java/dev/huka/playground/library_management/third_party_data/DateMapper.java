/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.library_management.third_party_data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateMapper {
  public LocalDate asDate(String date) {
    if (date == null) return null;
    var formatStrings = Arrays.asList("dd MMM yyyy", "dd MMMM yyyy", "d MMMM yyyy", "dd MMM yyyy");
    for (String format : formatStrings) {
      try {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
      } catch (Exception ignored) {
      }
    }
    return null;
  }
}
