package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveColor;

import java.util.ArrayList;
import java.util.List;

public class Lemma6 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(Modifier modifier, CSPInstance instance) {
    for (VarColor varColor : instance.getVarColors()) {
      for (Variable variable : instance.getVariables()) {
        if (varColor.getVariable() == variable.getId()) {
          continue;
        }

        if (instance.getConflict(varColor).containsAll(variable.getVarColors())) {
          modifier.addInstruction(new RemoveColor(instance, varColor));
        }
      }
    }

    return new ArrayList<>();
  }
}
