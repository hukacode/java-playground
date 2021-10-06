/*
 * Copyright 2021 huka.dev
 */
package dev.huka.algorithms.sort;

class SelectionSortTest extends AbstractSortTest {

  @Override
  Sort getSort() {
    return new SelectionSort();
  }
}
