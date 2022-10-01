/*
 * Copyright 2022 huka.dev
 */
package reactive.flowapi;

import java.util.concurrent.Flow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockProcessor implements Flow.Processor<Stock, Stock> {
  private Flow.Subscriber<? super Stock> subscriber;

  @Override
  public void subscribe(Flow.Subscriber<? super Stock> subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    this.subscriber.onSubscribe(subscription);
  }

  @Override
  public void onNext(Stock item) {
    log.info("Get {}", item);
    this.subscriber.onNext(new Stock(item.ticker(), item.current() * 24000, "VND"));
  }

  @Override
  public void onError(Throwable throwable) {
    this.subscriber.onError(throwable);
  }

  @Override
  public void onComplete() {
    this.subscriber.onComplete();
  }
}
