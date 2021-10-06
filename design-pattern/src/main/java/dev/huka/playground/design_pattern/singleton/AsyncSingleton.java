/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.design_pattern.singleton;

public class AsyncSingleton {
  private static AsyncSingleton INSTANCE;

  private AsyncSingleton() {}

  public static synchronized AsyncSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AsyncSingleton();
    }

    return INSTANCE;
  }
}
