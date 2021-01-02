package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lemma3 implements Reduction {
  @Override
  public List<ChangeInstruction> perform(CSPInstance instance) {
    List<ChangeInstruction> instructions = new ArrayList<>();

    List<VarColor> varColors = instance.getVarColors();
    for (VarColor varColor1 : varColors) {
      for (VarColor varColor2 : varColors) {
        if (varColor1.getVariable().equals(varColor2.getVariable())) {
          continue;
        }

        List<Constraint> involving1 = getSuitableConstraints(instance, varColor1, varColor2);
        List<Constraint> involving2 = getSuitableConstraints(instance, varColor2, varColor1);

        if (involving1.isEmpty() || involving2.isEmpty()) {
          // 'Equivalent' to getIncident(varColor1).isEmpty() || getIncident(varColor2).isEmpty()
          // in this case.
          continue;
        }

        if (instance.getIncident(varColor1).size() == involving1.size() &&
            instance.getIncident(varColor2).size() == involving2.size()) {
          instructions.add(new RemoveVariable(instance, varColor1.getVariable()));
          instructions.add(new RemoveVariable(instance, varColor2.getVariable()));
        }
      }
    }

    return instructions;
  }

  public List<Constraint> getSuitableConstraints(CSPInstance instance, VarColor varColor1, VarColor varColor2) {
    return instance.getConstraints().stream()
        .filter(pair -> pair.contains(varColor1))
        .filter(pair ->
            (pair.getFirst().equals(varColor1) &&
                pair.getSecond().getVariable().equals(varColor2.getVariable()) &&
                !pair.getSecond().getColor().equals(varColor2.getColor())) ||
                (pair.getSecond().equals(varColor1) &&
                    pair.getFirst().getVariable().equals(varColor2.getVariable()) &&
                    !pair.getFirst().getColor().equals(varColor2.getColor())))
        .collect(Collectors.toList());
  }
}
