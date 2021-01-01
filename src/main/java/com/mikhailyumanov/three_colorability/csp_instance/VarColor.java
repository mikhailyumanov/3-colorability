package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.Objects;

public class VarColor {
  Variable variable;
  Color color;

  public VarColor(Variable variable, Color color) {
    this.variable = variable;
    this.color = color;
  }

  public Variable getVariable() {
    return variable;
  }

  public void setVariable(Variable variable) {
    this.variable = variable;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VarColor varColor = (VarColor) o;
    return Objects.equals(variable, varColor.variable) &&
        color == varColor.color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(variable, color);
  }

  @Override
  public String toString() {
    return "VarColor{" +
        "variable=" + variable.getId() +
        ", color=" + color +
        '}';
  }
}
