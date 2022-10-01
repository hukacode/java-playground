/*
 * Copyright 2022 huka.dev
 */
package reactive.flowapi;

import java.util.concurrent.Flow;

public class ReactiveApp {
  public static void main(String[] args) {
    Flow.Publisher<Stock> stockPublisher =
        subscriber -> {
          var stockProcessor = new StockProcessor();
          stockProcessor.subscribe(subscriber);
          stockProcessor.onSubscribe(new StockSubscription(stockProcessor, "ABC"));
        };
    stockPublisher.subscribe(new StockSubsciber());
  }
}
