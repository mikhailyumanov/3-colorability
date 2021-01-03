package com.mikhailyumanov.three_colorability.algorithm.reductions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.Lemma2RemoveVariable;

public class Lemma2 implements Reduction {
  @Override
  public void perform(Modifier modifier, CSPInstance instance) {
    for (Variable variable : instance.getVariables()) {
      if (variable.getVarColors().size() == 2) {
        modifier.stepBy(instance, new Lemma2RemoveVariable(instance, variable));
      }
    }
  }
}
