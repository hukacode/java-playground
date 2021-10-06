/*
 * Copyright 2021 hukacode
 */
package com.hukacode.logging.inheritance;

import java.util.function.Predicate;

public class Main {
  public static void main(String[] args) {
    Student student1 = new Student();
    Person person1 = new Student(); // Don't need to cast when assign child to parent
    Person person2 = new Person();
    Student student2 = (Student) person1; // Need to cast when assign parent to child
    Student student3 = (Student) person2; // runtime java.lang.ClassCastException
    System.out.println(student3.studentId);
    //    Teacher teacher = (Teacher) person1; // cannot cast sibling. runtime
    // java.lang.ClassCastException

    Predicate a =
        new Predicate() {

          @Override
          public boolean test(Object o) {
            return false;
          }
        };
  }
}
