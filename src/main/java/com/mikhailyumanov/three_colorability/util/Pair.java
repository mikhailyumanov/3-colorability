package com.mikhailyumanov.three_colorability.util;

import java.util.Objects;

public class Pair<T> {
  T first;
  T second;

  public Pair(T first, T second) {
    this.first = first;
    this.second = second;
  }

  public T getFirst() {
    return first;
  }

  public void setFirst(T first) {
    this.first = first;
  }

  public T getSecond() {
    return second;
  }

  public void setSecond(T second) {
    this.second = second;
  }

  public boolean contains(T t) {
    return first.equals(t) || second.equals(t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair<?> pair = (Pair<?>) o;
    return Objects.equals(first, pair.first) &&
        Objects.equals(second, pair.second);
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
