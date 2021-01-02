package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lemma4 implements Reduction {
  @Override
  public Difference perform(CSPInstance instance) {
    Difference difference = new Difference();

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
            Difference new_difference = toUpdate(instance, variable);
            updateVariable(new_difference, varColor1);
            difference.union(new_difference);

            continue variables_loop;
          }
        }
      }
    }

    return difference;
  }

  private void updateVariable(Difference difference, VarColor to_remove) {
    Variable variable = new Variable();
    variable.setColors(to_remove.getVariable().getColors().stream()
        .filter(c -> c != to_remove.getColor()).collect(Collectors.toList()));

    difference.getAdding().setVariables(new ArrayList<>() {{ add(variable); }});

    List<Constraint> new_constraints = new ArrayList<>();
    for (Constraint constraint : difference.getRemoving().getConstraints()) {
      VarColor first = constraint.getFirst();
      VarColor second = constraint.getSecond();
      if (first.getVariable().equals(to_remove.getVariable())) {
        new_constraints.add(new Constraint(new VarColor(variable, first.getColor()), second));
      } else if (second.getVariable().equals(to_remove.getVariable())) {
        new_constraints.add(new Constraint(first, new VarColor(variable, second.getColor())));
      }
    }

    difference.getAdding().setConstraints(new_constraints);
  }
}
