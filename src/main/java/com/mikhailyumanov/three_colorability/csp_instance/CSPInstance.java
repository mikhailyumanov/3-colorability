package com.mikhailyumanov.three_colorability.csp_instance;

import com.mikhailyumanov.three_colorability.modifier.Change;

import java.util.*;
import java.util.stream.Collectors;

public class CSPInstance {
  List<Integer> variable_ids = new ArrayList<>();
  List<VarColor> varColors = new ArrayList<>();
  List<Constraint> constraints = new ArrayList<>();


  /** Constructors
   *
   */

  public CSPInstance() {}

  public CSPInstance(List<Integer> variable_ids,
                     List<VarColor> varColors,
                     List<Constraint> constraints) {
    this.variable_ids = variable_ids;
    this.varColors = varColors;
    this.constraints = constraints;
  }

  public static CSPInstance copyFrom(CSPInstance other) {
    return new CSPInstance(
        new ArrayList<>(other.variable_ids),
        new ArrayList<>(other.varColors),
        new ArrayList<>(other.constraints));
  }


  /** Get constraints incident to varColor
   *
   */

  public List<Constraint> getIncident(VarColor varColor) {
    return getConstraints().stream()
        .filter(constraint -> constraint.contains(varColor)).collect(Collectors.toList());
  }


  /** Get varColors conflicting with varColor
   *
   */

  public List<VarColor> getConflict(VarColor varColor) {
    return getIncident(varColor).stream()
        .map(pair -> pair.getFirst().equals(varColor) ? pair.getSecond() : pair.getFirst())
        .collect(Collectors.toList());
  }


  /** Compute variable by index
   *
   * @param i index of variable
   * @return ith variable
   */

  public Variable getVariable(int i) {
    return new Variable(i, varColors.stream()
        .filter(varColor -> varColor.getVariable() == i)
        .collect(Collectors.toList()));
  }


  /** Compute list of variables
   *
   * @return list of variables
   */

  public List<Variable> getVariables() {
    Map<Integer, List<VarColor>> tmp = varColors.stream().collect(Collectors.groupingBy(VarColor::getVariable));
    return tmp.entrySet().stream().map(Variable::new).collect(Collectors.toList());
  }


  /** Get union with other instance
   */

  public CSPInstance union(CSPInstance other) {
    varColors.addAll(other.getVarColors());
    varColors = varColors.stream().distinct().collect(Collectors.toList());

    constraints.addAll(other.getConstraints());
    constraints = constraints.stream().distinct().collect(Collectors.toList());

    return this;
  }


  /** Get complement with other instance
   */

  public CSPInstance complement(CSPInstance other) {
    varColors.removeAll(other.getVarColors());
    constraints.removeAll(other.getConstraints());

    return this;
  }


  /** Combination of getting union and complement
   */

  public CSPInstance withChange(Change change) {
    return this.union(change.getAdding()).complement(change.getRemoving());
  }


  /** Standard getters and setters
   */

  public List<VarColor> getVarColors() {
    return varColors;
  }

  public void setVarColors(List<VarColor> varColors) {
    this.varColors = varColors;
  }

  public List<Constraint> getConstraints() {
    return constraints;
  }

  public void setConstraints(List<Constraint> constraints) {
    this.constraints = constraints;
  }

  public List<Integer> getVariable_ids() {
    return variable_ids;
  }

  public void setVariable_ids(List<Integer> variable_ids) {
    this.variable_ids = variable_ids;
  }

  @Override
  public String toString() {
    return "CSPInstance{\n" +
        "variables=" + varColors + ", \n" +
        "constraints=" + constraints + '\n' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CSPInstance instance = (CSPInstance) o;
    return Objects.equals(new HashSet<>(varColors), new HashSet<>(instance.varColors)) &&
        Objects.equals(new HashSet<>(constraints), new HashSet<>(instance.constraints));
  }

  @Override
  public int hashCode() {
    return Objects.hash(varColors, constraints);
  }
}
