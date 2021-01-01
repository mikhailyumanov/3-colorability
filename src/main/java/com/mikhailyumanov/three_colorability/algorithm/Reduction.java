package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;

@FunctionalInterface
public interface Reduction {
  Difference perform(CSPInstance instance);

  default Difference toUpdate(CSPInstance instance, Variable variable) {
    Difference difference = new Difference();
    difference.getRemoving().getVariables().add(variable);

    for (Color color : variable.getColors()) {
      difference.getRemoving().getConstraints().addAll(instance.getIncident(new VarColor(variable, color)));
    }

    return difference;
  }
}
