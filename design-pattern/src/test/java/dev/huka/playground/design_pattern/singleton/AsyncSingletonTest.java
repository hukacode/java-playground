/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.design_pattern.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;

class AsyncSingletonTest {

  @Test
  void getInstance() {
    Callable<AsyncSingleton> callable = AsyncSingleton::getInstance;

    var executorService = Executors.newWorkStealingPool();
    var futures = new HashSet<Future<AsyncSingleton>>();
    for (int i = 0; i < 10; i++) {
      futures.add(executorService.submit(callable));
    }

    var out = new HashSet<AsyncSingleton>();
    futures.forEach(
        asyncSingletonFuture -> {
          try {
            out.add(asyncSingletonFuture.get());
          } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
          }
        });

    executorService.shutdown();
    assertThat(out).hasSize(1);
  }
}
