package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.Objects;

public class Constraint {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Constraint constraint = (Constraint) o;
    return Objects.equals(first, constraint.first) &&
        Objects.equals(second, constraint.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return "Pair{" + '\n' +
        "first=" + first + '\n' +
        ", second=" + second + '\n' +
        '}' + '\n';
  }
}
