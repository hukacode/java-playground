/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.design_pattern.singleton;

public class SimpleSingleton {
  public static SimpleSingleton INSTANCE = new SimpleSingleton();

  private SimpleSingleton() {}
}
