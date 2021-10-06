/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.design_pattern.singleton;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class SimpleSingletonTest {

  @Test
  void test() {
    var instance1 = SimpleSingleton.INSTANCE;
    var instance2 = SimpleSingleton.INSTANCE;
    assertSame(instance1, instance2);
  }
}
