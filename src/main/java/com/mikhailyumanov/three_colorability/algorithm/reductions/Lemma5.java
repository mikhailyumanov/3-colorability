package com.mikhailyumanov.three_colorability.algorithm.reductions;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.AssignColor;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveVariable;

public class Lemma5 implements Reduction {
  @Override
  public void perform(Modifier modifier, CSPInstance instance) {
    variables_loop:
    for (Variable variable : instance.getVariables()) {
      for (VarColor varColor : variable.getVarColors()) {
        if (instance.getConstraints().stream().noneMatch(
            constraint -> constraint.contains(varColor))) {
          modifier.addInstruction(new RemoveVariable(instance, variable.getId()));
          modifier.addInstruction(new AssignColor(instance, varColor));
          continue variables_loop;
        }
      }
    }

    modifier.apply(instance);
  }
}
