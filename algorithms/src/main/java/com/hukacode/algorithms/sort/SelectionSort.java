/*
 * Copyright 2021 hukacode
 */
package com.hukacode.algorithms.sort;

import java.util.Arrays;

public class SelectionSort implements Sort {
  public int[] sort(int[] input) {
    for (int i = 0; i < input.length - 1; i++) {
      int indexOfMin = i;

      for (int j = i; j < input.length; j++) {
        if (input[indexOfMin] > input[j]) {
          indexOfMin = j;
        }
      }

      int temp = input[indexOfMin];
      input[indexOfMin] = input[i];
      input[i] = temp;

      System.out.println(Arrays.toString(input));
    }

    return input;
  }
}
