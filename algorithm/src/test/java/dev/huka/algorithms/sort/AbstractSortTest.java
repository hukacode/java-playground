/*
 * Copyright 2021 huka.dev
 */
package dev.huka.algorithms.sort;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public abstract class AbstractSortTest {
  @Test
  void givenEmptyArrayWhenSortThenReturnEmptyArray() {
    int[] input = {};

    Sort sort = getSort();
    int[] output = sort.sort(input);

    assertThat(output).isEqualTo(new int[] {});
  }

  @Test
  void givenArrayHasOneElementWhenSortThenReturnThatArray() {
    int[] input = {1};

    Sort sort = getSort();
    int[] output = sort.sort(input);

    assertThat(output).isEqualTo(new int[] {1});
  }

  @Test
  void givenUnsortedArrayWhenSortThenReturnSortedArray() {
    int[] input = {5, 2, 7, 4, 9, 6, 3, 8, 1};

    Sort sort = getSort();
    int[] output = sort.sort(input);

    assertThat(output).isEqualTo(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
  }

  abstract Sort getSort();
}
