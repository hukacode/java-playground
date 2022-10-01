/*
 * Copyright 2022 huka.dev
 */
package reactive.flowapi;

import java.util.concurrent.Flow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockSubsciber implements Flow.Subscriber<Stock> {
  private Flow.Subscription subscription;

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(Stock item) {
    log.info("Get {}", item);
    this.subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable) {
    log.error(throwable.getMessage());
  }

  @Override
  public void onComplete() {
    log.info("Completed");
  }
}
