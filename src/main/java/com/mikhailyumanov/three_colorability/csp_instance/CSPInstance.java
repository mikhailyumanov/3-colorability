package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CSPInstance {
  List<Variable> variables = new ArrayList<>();
  List<Constraint> constraints = new ArrayList<>();

  public CSPInstance() {}

  public CSPInstance(List<Variable> variables,
                     List<Constraint> constraints) {
    this.variables = variables;
    this.constraints = constraints;
  }

  public List<VarColor> getVarColors() {
    List<VarColor> tmp = new ArrayList<>();
    for (Variable variable : variables) {
      for (Color color : variable.getColors()) {
        tmp.add(new VarColor(variable, color));
      }
    }

    return tmp;
  }

  public List<Constraint> getIncident(VarColor varColor) {
    return getConstraints().stream()
        .filter(constraint -> constraint.contains(varColor)).collect(Collectors.toList());
  }

  public List<Variable> getVariables() {
    return variables;
  }

  public void setVariables(List<Variable> variables) {
    this.variables = variables;
  }

  public List<Constraint> getConstraints() {
    return constraints;
  }

  public void setConstraints(List<Constraint> constraints) {
    this.constraints = constraints;
  }

  @Override
  public String toString() {
    return "CSPInstance{\n" +
        "variables=" + variables + ", \n" +
        "constraints=" + constraints + '\n' +
        '}';
  }

  public CSPInstance union(CSPInstance other) {
    variables.addAll(other.getVariables());
    variables = variables.stream().distinct().collect(Collectors.toList());

    constraints.addAll(other.getConstraints());
    constraints = constraints.stream().distinct().collect(Collectors.toList());

    return this;
  }

  public CSPInstance complement(CSPInstance other) {
    variables.removeAll(other.getVariables());
    constraints.removeAll(other.getConstraints());

    return this;
  }

  public CSPInstance withDifference(Difference difference) {
    return this.union(difference.getAdding()).complement(difference.getRemoving());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CSPInstance instance = (CSPInstance) o;
    return Objects.equals(variables, instance.variables) &&
        Objects.equals(constraints, instance.constraints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(variables, constraints);
  }
}
