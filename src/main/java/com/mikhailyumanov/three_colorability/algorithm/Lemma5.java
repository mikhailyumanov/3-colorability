package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Change;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveVariable;

import java.util.ArrayList;
import java.util.List;

public class Lemma5 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(CSPInstance instance) {
    List<ChangeInstruction> instructions = new ArrayList<>();

    variables_loop:
    for (Variable variable : instance.getVariables()) {
      for (Color color : variable.getColors()) {
        if (instance.getConstraints().stream().noneMatch(
            constraint -> constraint.contains(new VarColor(variable, color)))) {
          instructions.add(new RemoveVariable(instance, variable));
          continue variables_loop;
        }
      }
    }

    return instructions;
  }
}
