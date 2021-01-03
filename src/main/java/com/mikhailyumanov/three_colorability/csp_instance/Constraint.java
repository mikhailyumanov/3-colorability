package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Constraint implements CSPEntity {
  VarColor first;
  VarColor second;

  public Constraint(VarColor first, VarColor second) {
    this.first = first;
    this.second = second;
  }

  public VarColor getFirst() {
    return first;
  }

  public void setFirst(VarColor first) {
    this.first = first;
  }

  public VarColor getSecond() {
    return second;
  }

  public void setSecond(VarColor second) {
    this.second = second;
  }

  public boolean contains(VarColor t) {
    return first.equals(t) || second.equals(t);
  }


  /** WARNING: Object.equals below compares constraints as <u>unordered</u> pairs
   *
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Constraint constraint = (Constraint) o;
    return Objects.equals(first, constraint.first) &&
        Objects.equals(second, constraint.second) ||

        Objects.equals(first, constraint.second) &&
            Objects.equals(second, constraint.first);
  }


  /** WARNING: Object.equals below compares constraints as <u>unordered</u> pairs
   *
   */

  @Override
  public int hashCode() {
    return Objects.hash(Set.of(first, second));
  }

  @Override
  public String toString() {
    return "Pair{" + '\n' +
        "first=" + first + '\n' +
        ", second=" + second + '\n' +
        '}' + '\n';
  }
}
