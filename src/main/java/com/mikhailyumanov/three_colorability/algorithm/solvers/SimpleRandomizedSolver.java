package com.mikhailyumanov.three_colorability.algorithm.solvers;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.instructions.Lemma2RemoveVariable;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleRandomizedSolver extends AbstractSolver {
  public boolean hasColoring(CSPInstance instance) {
    do {
      modifier.flushAppliedChanges();
      applyLemmas2to6(instance);
    } while (modifier.hasRecentlyChanged());

    return loop(instance);
  }

  private boolean loop(CSPInstance instance) {
    if (instance.getConstraints().isEmpty() || instance.getVariable_ids().size() <= 3) {
      return hasColoringBruteForce(instance);
    }

    for (int i = 0; i < instance.getVariable_ids().size(); i++) {
      for (int j = i + 1; j < instance.getVariable_ids().size(); j++) {
        int varId1 = instance.getVariable_ids().get(i);
        int varId2 = instance.getVariable_ids().get(j);
        List<Constraint> constraints = instance.getConstraints().stream()
            .filter(constraint ->
                constraint.getFirst().getVariableId() == varId1 &&
                constraint.getSecond().getVariableId() == varId2 ||
                constraint.getFirst().getVariableId() == varId2 &&
                constraint.getSecond().getVariableId() == varId1).collect(Collectors.toList());
        if (constraints.isEmpty()) {
          continue;
        }

        Constraint constraint = constraints.get(0);
        VarColor varColor1 = constraint.getFirst();
        VarColor varColor2 = getPossibleColors(instance, constraint.getSecond()).get(0);

        CSPInstance copyInstance = CSPInstance.copyFrom(instance);
        modifier.stepBy(copyInstance, new RemoveColor(copyInstance, varColor1));
        modifier.stepBy(copyInstance, new RemoveColor(copyInstance, varColor2));
        modifier.stepBy(copyInstance, new Lemma2RemoveVariable(copyInstance,
            copyInstance.getVariable(varColor1.getVariableId())));
        modifier.stepBy(copyInstance, new Lemma2RemoveVariable(copyInstance,
            copyInstance.getVariable(varColor2.getVariableId())));

        if (loop(copyInstance)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean hasColoringBruteForce(CSPInstance instance) {
    List<Variable> variables = instance.getVariables();
    if (variables.size() != instance.getVariable_ids().size()) {
      return false;
    }

    if (instance.getConstraints().isEmpty()) {
      // cases variables.size() == 0 and variables.size() == 1 are included here
      return true;
    }

    if (variables.size() == 2) {
      for (VarColor varColor1 : variables.get(0).getVarColors()) {
        for (VarColor varColor2 : variables.get(1).getVarColors()) {
          if (!instance.getConstraints().contains(new Constraint(varColor1, varColor2))) {
            return true;
          }
        }
      }
    }

    if (variables.size() == 3) {
      for (VarColor varColor1 : variables.get(0).getVarColors()) {
        for (VarColor varColor2 : variables.get(1).getVarColors()) {
          for (VarColor varColor3 : variables.get(2).getVarColors()) {
            if (!instance.getConstraints().contains(new Constraint(varColor1, varColor2)) &&
                !instance.getConstraints().contains(new Constraint(varColor1, varColor3)) &&
                !instance.getConstraints().contains(new Constraint(varColor2, varColor3))) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  private List<VarColor> getPossibleColors(CSPInstance instance, VarColor varColor) {
    Variable variable = instance.getVariable(varColor.getVariableId());
    List<VarColor> possibleColors = new ArrayList<>(variable.getVarColors());
    possibleColors.remove(varColor);
    return possibleColors;
  }
}
