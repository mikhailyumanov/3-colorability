package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveVariable;

import java.util.ArrayList;
import java.util.List;

public class Lemma5 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(Modifier modifier, CSPInstance instance) {
    List<ChangeInstruction> instructions = new ArrayList<>();

    variables_loop:
    for (Variable variable : instance.getVariables()) {
      for (VarColor varColor : variable.getVarColors()) {
        if (instance.getConstraints().stream().noneMatch(
            constraint -> constraint.contains(varColor))) {
          instructions.add(new RemoveVariable(instance, variable.getId()));
          continue variables_loop;
        }
      }
    }

    return instructions;
  }
}
