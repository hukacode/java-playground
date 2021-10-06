/*
 * Copyright 2021 huka.dev
 */
package dev.huka.algorithms.sort;

import java.util.Arrays;

public class InsertionSort implements Sort {
  public int[] sort(int[] input) {
    for (int i = 0; i < input.length - 1; i++) {
      for (int j = i; j > 1; j--) {
        if (input[j] < input[j - 1]) {
          int temp = input[j - 1];
          input[j - 1] = input[j];
          input[j] = temp;
        } else {
          break;
        }
        System.out.println(Arrays.toString(input));
      }
    }
    return input;
  }
}
