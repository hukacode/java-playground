/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.design_pattern.singleton;

public class LazySingleton {
  private static LazySingleton INSTANCE;

  private LazySingleton() {}

  public static LazySingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new LazySingleton();
    }

    return INSTANCE;
  }
}
