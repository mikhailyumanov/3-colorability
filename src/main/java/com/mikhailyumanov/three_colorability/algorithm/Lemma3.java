package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Difference;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;

import java.util.List;
import java.util.stream.Collectors;

public class Lemma3 implements Reduction {
  @Override
  public Difference perform(CSPInstance instance) {
    Difference difference = new Difference();

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
          difference
              .union(toUpdate(instance, varColor1.getVariable()))
              .union(toUpdate(instance, varColor2.getVariable()));
        }
      }
    }

    return difference;
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
