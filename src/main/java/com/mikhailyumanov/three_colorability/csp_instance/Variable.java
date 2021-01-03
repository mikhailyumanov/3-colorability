package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.*;

public class Variable implements CSPEntity {
  private static int counter;
  private final int id;
  private final List<VarColor> varColors;

  public Variable(Variable other) {
    this.id = other.id;
    this.varColors = other.varColors;
  }

  public Variable(int id, List<VarColor> varColors) {
    this.id = id;
    this.varColors = varColors;
  }

  public Variable(Map.Entry<Integer, List<VarColor>> integerListEntry) {
    this.id = integerListEntry.getKey();
    this.varColors = integerListEntry.getValue();
  }

  public int getId() {
    return id;
  }

  public List<VarColor> getVarColors() {
    return varColors;
  }

  @Override
  public String toString() {
    return "Variable " + id + " {" + '\n' +
        "\tvarColors=" + varColors + '\n' +
        '}';
  }


  /** WARNING: Object.equals below compares Sets, not Lists
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Variable variable = (Variable) o;
    return id == variable.id &&
        Objects.equals(new HashSet<>(varColors), new HashSet<>(variable.varColors));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, varColors);
  }
}
