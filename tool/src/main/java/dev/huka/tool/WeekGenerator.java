/*
 * Copyright 2022 huka.dev
 */
package dev.huka.tool;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeekGenerator {
  public static void main(String[] args) {
    for (int i = 1; i < 54; i++) {
      var firstDayOfWeek = getFirstDayOfWeek(i);
      System.out.println(
          MessageFormat.format(
              "- [[news-{0}-{1}|News Weekly ({2}): {0} - {1}]]",
              firstDayOfWeek,
              firstDayOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)),
              i));
    }
    getAllDaysOfTheWeek(1);
  }

  static LocalDate getFirstDayOfWeek(int weekNumber) {
    return LocalDate.of(Year.now().getValue(), 2, 1)
        .with(WeekFields.ISO.getFirstDayOfWeek())
        .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNumber);
  }

  static List<LocalDate> getAllDaysOfTheWeek(int weekNumber) {
    var firstDayOfWeek = getFirstDayOfWeek(weekNumber);
    return IntStream.rangeClosed(0, 6)
        .mapToObj(firstDayOfWeek::plusDays)
        .collect(Collectors.toList());
  }
}
