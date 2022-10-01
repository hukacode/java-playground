/*
 * Copyright 2022 huka.dev
 */
package reactive.flowapi;

import java.util.random.RandomGenerator;

public record Stock(String ticker, Double current, String currency) {
  public static final RandomGenerator generator = RandomGenerator.getDefault();

  public static Stock fetch(String ticker) {
    return new Stock(ticker, generator.nextDouble(), "USD");
  }
}
