package org.ycwearn.freemarker.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class Person {
  private String name;
  private int age;
  private Gender gender;
}
