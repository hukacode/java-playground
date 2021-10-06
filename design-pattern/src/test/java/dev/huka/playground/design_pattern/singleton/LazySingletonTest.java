/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.design_pattern.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LazySingletonTest {

  @Test
  void getInstance() {
    var instance1 = LazySingleton.getInstance();
    var instance2 = LazySingleton.getInstance();
    assertSame(instance1, instance2);
  }
}
