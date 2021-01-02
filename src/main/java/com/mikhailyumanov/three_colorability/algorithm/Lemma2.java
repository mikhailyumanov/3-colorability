package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.Lemma2RemoveVariable;

import java.util.ArrayList;
import java.util.List;

public class Lemma2 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(Modifier modifier, CSPInstance instance) {
    List<ChangeInstruction> instructions = new ArrayList<>();

    for (Variable variable : instance.getVariables()) {
      if (variable.getVarColors().size() == 2) {
        instructions.add(new Lemma2RemoveVariable(instance, variable));
      }
    }

    return instructions;
  }
}
