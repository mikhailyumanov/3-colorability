package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Coloring {
  private final CSPInstance instance;
  List<VarColor> varColors = new ArrayList<>();

  public Coloring(CSPInstance instance) {
    this.instance = instance;
  }

  public void assignColor(VarColor varColor) {
    varColors.add(varColor);
  }

  public boolean isCorrect() {
    int num_variables = instance.getVariable_ids().size();
    return varColors.size() == num_variables &&
        varColors.stream().map(VarColor::getVariableId).distinct().count() == num_variables;

  }

  public List<VarColor> getVarColors() {
    return varColors;
  }

  public void setVarColors(List<VarColor> varColors) {
    this.varColors = varColors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coloring coloring = (Coloring) o;
    return Objects.equals(new HashSet<>(varColors), new HashSet<>(coloring.varColors));
  }

  @Override
  public int hashCode() {
    return Objects.hash(new HashSet<>(varColors));
  }

  @Override
  public String toString() {
    return "Coloring{" +
        "varColors=" + varColors + "\n" +
        '}' + "\n";
  }
}
