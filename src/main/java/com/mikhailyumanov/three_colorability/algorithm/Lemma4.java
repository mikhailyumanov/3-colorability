package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.Change;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveColor;

import java.util.ArrayList;
import java.util.List;

public class Lemma4 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(CSPInstance instance) {
    List<ChangeInstruction> instructions = new ArrayList<>();

    variables_loop:
    for (Variable variable : instance.getVariables()) {
      for (Color color1 : variable.getColors()) {
        for (Color color2 : variable.getColors()) {
          if (color1 == color2) {
            continue;
          }

          VarColor varColor1 = new VarColor(variable, color1);
          VarColor varColor2 = new VarColor(variable, color2);
          List<Constraint> incident1 = instance.getIncident(varColor1);
          List<Constraint> incident2 = instance.getIncident(varColor2);
          if (incident1.containsAll(incident2)) {
            instructions.add(new RemoveColor(instance, varColor1));

            continue variables_loop;
          }
        }
      }
    }

    return instructions;
  }
}
