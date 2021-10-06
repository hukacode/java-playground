/*
 * Copyright 2021 hukacode
 */
package com.hukacode.algorithms.sort;

import java.util.Arrays;

public class QuickSort implements Sort {
  public int[] sort(int[] input) {
    sort(input, 0, input.length - 1);
    return input;
  }

  void sort(int[] input, int low, int high) {
    if (low < high) {
      System.out.println(Arrays.toString(input));

      int pivot = partition(input, low, high);
      sort(input, low, pivot - 1);
      sort(input, pivot + 1, high);
    }
  }

  int partition(int[] input, int low, int high) {
    int pivot = input[high];
    int index = low - 1;

    for (int j = low; j < high; j++) {
      if (input[j] < pivot) {
        index++;

        int temp = input[index];
        input[index] = input[j];
        input[j] = temp;
      }
    }

    int temp = input[index + 1];
    input[index + 1] = input[high];
    input[high] = temp;

    return index + 1;
  }
}
