/*
 * Copyright 2021 hukacode
 */
package com.hukacode.logging.inheritance;

public class Test {
  private int sum;

  int dosome() {
    int i = 0;
    while (i < 3) {
      sum += ++i;
    }
    return sum / 4;
  }

  public static void main(String[] args) {
    Test t = new Test();
    int sum = t.dosome();
    sum = t.dosome();
    t.dosome();
    System.out.println(sum);
  }
}
