/*
 * Copyright 2022 huka.dev
 */
package reactive.flowapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class StockSubscription implements Flow.Subscription {
  private static final ExecutorService executor = Executors.newSingleThreadExecutor();
  private final Flow.Subscriber<? super Stock> subscriber;
  private final String ticker;

  public StockSubscription(Flow.Subscriber<? super Stock> subscriber, String ticker) {
    this.subscriber = subscriber;
    this.ticker = ticker;
  }

  @Override
  public void request(long n) {
    executor.submit(
        () -> {
          for (long i = 0L; i < n; i++) {
            try {
              Thread.sleep(1000);
              this.subscriber.onNext(Stock.fetch(ticker));
            } catch (Exception e) {
              this.subscriber.onError(e);
              break;
            }
          }
        });
  }

  @Override
  public void cancel() {
    this.subscriber.onComplete();
  }
}
