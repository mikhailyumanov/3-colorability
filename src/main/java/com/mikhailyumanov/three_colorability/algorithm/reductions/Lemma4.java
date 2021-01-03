package com.mikhailyumanov.three_colorability.algorithm.reductions;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveColor;

import java.util.List;

public class Lemma4 implements Reduction {
  @Override
  public void perform(Modifier modifier, CSPInstance instance) {
    for (Variable variable : instance.getVariables()) {
      for (VarColor varColor1 : variable.getVarColors()) {
        for (VarColor varColor2 : variable.getVarColors()) {
          if (varColor1 == varColor2) {
            continue;
          }

          List<VarColor> conflict1 = instance.getConflict(varColor1);
          List<VarColor> conflict2 = instance.getConflict(varColor2);
          if (!conflict1.isEmpty() &&
              !conflict2.isEmpty() &&
              conflict1.containsAll(conflict2)) {
            modifier.stepBy(instance, new RemoveColor(instance, varColor1));
          }
        }
      }
    }
  }
}
