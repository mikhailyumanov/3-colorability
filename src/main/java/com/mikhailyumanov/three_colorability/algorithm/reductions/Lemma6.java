package com.mikhailyumanov.three_colorability.algorithm.reductions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveColor;

public class Lemma6 implements Reduction {
  @Override
  public void perform(Modifier modifier, CSPInstance instance) {
    for (VarColor varColor : instance.getVarColors()) {
      for (Variable variable : instance.getVariables()) {
        if (varColor.getVariableId() == variable.getId()) {
          continue;
        }

        if (instance.getConflict(varColor).containsAll(variable.getVarColors())) {
          modifier.addInstruction(new RemoveColor(instance, varColor));
        }
      }
    }

    modifier.apply(instance);
  }
}
