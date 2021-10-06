/*
 * Copyright 2021 huka.dev
 */
package dev.huka.algorithms.sort;

import java.util.Arrays;

public class QuickSort implements Sort {
  public int[] sort(int[] input) {
    sort(input, 0, input.length - 1);
    return input;
  }

  void sort(int[] input, int left, int right) {
    if (left < right) {
      System.out.println(Arrays.toString(input));

      int pivot = partition(input, left, right);
      sort(input, left, pivot - 1);
      sort(input, pivot + 1, right);
    }
  }

  int partition(int[] input, int left, int right) {
    int pivot = input[right];
    int index = left - 1;

    for (int i = left; i < right; i++) {
      if (input[i] < pivot) {
        index++;

        int temp = input[index];
        input[index] = input[i];
        input[i] = temp;
      }
    }

    int temp = input[index + 1];
    input[index + 1] = input[right];
    input[right] = temp;

    return index + 1;
  }
}
