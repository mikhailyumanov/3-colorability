package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Variable {
  private static int counter;
  private final int id;
  List<Color> colors = new ArrayList<>();

  public Variable() {
    this.id = counter++;
  }

  public Variable(List<Color> colors) {
    this.id = counter++;
    this.colors = colors;
  }

  public List<Color> getColors() {
    return colors;
  }

  public void setColors(List<Color> colors) {
    this.colors = colors;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Variable " + id + " {" + '\n' +
        "\tcolors=" + colors + '\n' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Variable variable = (Variable) o;
    return id == variable.id &&
        Objects.equals(colors, variable.colors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, colors);
  }
}
