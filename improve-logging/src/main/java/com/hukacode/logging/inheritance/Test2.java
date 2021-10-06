/*
 * Copyright 2021 hukacode
 */
package com.hukacode.logging.inheritance;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>(List.of("paris", "something", "abccde", "oman"));
    list.sort((a, b) -> b.compareTo(a));
    System.out.println(list.indexOf("oman"));
  }
}
