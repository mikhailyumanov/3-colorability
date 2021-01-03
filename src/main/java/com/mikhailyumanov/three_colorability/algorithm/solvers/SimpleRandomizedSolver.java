package com.mikhailyumanov.three_colorability.algorithm.solvers;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveColor;

import java.util.ArrayList;
import java.util.List;

public class SimpleRandomizedSolver extends AbstractSolver {
  public boolean hasColoring(CSPInstance instance) {
    return loop(instance);
  }

  private boolean loop(CSPInstance instance) {
    do {
      modifier.flushAppliedChanges();
      applyLemmas2to6(instance);
    } while (modifier.hasRecentlyChanged());

    if (instance.getConstraints().isEmpty()) {
      return instance.getVariables().size() == instance.getVariable_ids().size();
    }

    Constraint constraint = instance.getConstraints().get(instance.getConstraints().size() - 1);

    VarColor varColor1 = constraint.getFirst();
    VarColor varColor2 = constraint.getSecond();
    List<VarColor> possibleColors1 = getPossibleColors(instance, varColor1);
    List<VarColor> possibleColors2 = getPossibleColors(instance, varColor2);

    if (possibleColors1.size() != 2 || possibleColors2.size() != 2) {
      System.err.println(instance);
      assert false;
    }

    List<List<VarColor>> removedPairs = List.of(
        List.of(varColor1, possibleColors2.get(0)),
        List.of(varColor1, possibleColors2.get(1)),
        List.of(varColor2, possibleColors1.get(0)),
        List.of(varColor2, possibleColors1.get(1))
    );

    for (List<VarColor> pair : removedPairs) {
      VarColor fromConstraint = pair.get(0);
      VarColor other = pair.get(1);

      CSPInstance copyInstance = CSPInstance.copyFrom(instance);
      modifier.stepBy(copyInstance, new RemoveColor(copyInstance, fromConstraint));
      modifier.stepBy(copyInstance, new RemoveColor(copyInstance, other));

      if (loop(copyInstance)) {
        return true;
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
