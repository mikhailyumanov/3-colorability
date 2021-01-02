package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.Change;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.Lemma2RemoveVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lemma2 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(CSPInstance instance) {
    List<ChangeInstruction> instructions = new ArrayList<>();

    for (Variable variable : instance.getVariables()) {
      if (variable.getColors().size() == 2) {
        instructions.add(new Lemma2RemoveVariable(instance, variable));
      }
    }

    return instructions;
  }
}
