package com.mikhailyumanov.three_colorability.csp_instance;

import com.mikhailyumanov.three_colorability.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSPInstance {
  List<Variable> variables = new ArrayList<>();
  List<Pair<VarColor, VarColor>> constraints = new ArrayList<>();

  public CSPInstance() {}

  public CSPInstance(List<Variable> variables,
                     List<Pair<VarColor, VarColor>> constraints) {
    this.variables = variables;
    this.constraints = constraints;
  }

  public List<Variable> getVariables() {
    return variables;
  }

  public void setVariables(List<Variable> variables) {
    this.variables = variables;
  }

  public List<Pair<VarColor, VarColor>> getConstraints() {
    return constraints;
  }

  public void setConstraints(List<Pair<VarColor, VarColor>> constraints) {
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
    CSPInstance tmp = new CSPInstance();
    tmp.variables.addAll(getVariables());
    tmp.variables.addAll(other.getVariables());

    tmp.constraints.addAll(getConstraints());
    tmp.constraints.addAll(other.getConstraints());

    return tmp;
  }

  public CSPInstance complement(CSPInstance other) {
    CSPInstance tmp = new CSPInstance();
    tmp.variables.addAll(getVariables());
    tmp.variables.removeAll(other.getVariables());

    tmp.constraints.addAll(getConstraints());
    tmp.constraints.removeAll(other.getConstraints());

    return tmp;
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
