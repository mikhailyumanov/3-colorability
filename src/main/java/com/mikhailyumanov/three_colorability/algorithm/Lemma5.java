package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;

public class Lemma5 implements Reduction {
  @Override
  public Difference perform(CSPInstance instance) {
    Difference difference = new Difference();

    variables_loop:
    for (Variable variable : instance.getVariables()) {
      for (Color color : variable.getColors()) {
        if (instance.getConstraints().stream().noneMatch(
            constraint -> constraint.contains(new VarColor(variable, color)))) {
          difference.union(toUpdate(instance, variable));
          continue variables_loop;
        }
      }
    }

    return difference;
  }
}
