package com.mikhailyumanov.three_colorability.algorithm.reductions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.modifier.instructions.AssignColor;
import com.mikhailyumanov.three_colorability.modifier.instructions.RemoveVariable;

import java.util.ArrayList;
import java.util.List;

public class Lemma3 implements Reduction {
  @Override
  public void perform(Modifier modifier, CSPInstance instance) {
    List<VarColor> varColors = instance.getVarColors();
    for (VarColor varColor1 : varColors) {
      for (VarColor varColor2 : varColors) {
        if (varColor1.getVariableId() == varColor2.getVariableId()) {
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
          modifier.addInstruction(new RemoveVariable(instance, varColor1.getVariableId()));
          modifier.addInstruction(new RemoveVariable(instance, varColor2.getVariableId()));
          modifier.addInstruction(new AssignColor(instance, varColor1));
          modifier.addInstruction(new AssignColor(instance, varColor2));
        }
      }
    }

    modifier.apply(instance);
  }

  public List<Constraint> getSuitableConstraints(CSPInstance instance, VarColor varColor1, VarColor varColor2) {
    List<Constraint> list = new ArrayList<>();
    for (Constraint pair : instance.getIncident(varColor1)) {
      if ((pair.getFirst().equals(varColor1) &&
          pair.getSecond().getVariableId() == varColor2.getVariableId() &&
          !pair.getSecond().getColor().equals(varColor2.getColor())) ||
          (pair.getSecond().equals(varColor1) &&
              pair.getFirst().getVariableId() == varColor2.getVariableId() &&
              !pair.getFirst().getColor().equals(varColor2.getColor()))) {
        list.add(pair);
      }
    }
    return list;
  }
}
