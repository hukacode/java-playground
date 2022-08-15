/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.thymeleaf;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import org.thymeleaf.context.Context;

public abstract class Base {
  protected static Context getContext() {
    var employee = new Employee("employee", LocalDate.now(), List.of("Java", "C#"));

    var ctx = new Context(Locale.US);
    ctx.setVariable("title", "Thymeleaf Demo");
    ctx.setVariable("employee", employee);

    return ctx;
  }
}
