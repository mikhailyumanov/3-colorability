package com.mikhailyumanov.three_colorability.modifier.instructions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Change;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveColor extends ChangeInstruction {
  VarColor varColor;
  
  public RemoveColor(CSPInstance instance, VarColor varColor) {
    super(instance);
    this.varColor = varColor;
  }

  @Override
  public Change generateChange() {
    Change change = new RemoveVariable(instance, varColor.getVariable()).generateChange();

    Variable variable = new Variable();
    variable.setColors(varColor.getVariable().getColors().stream()
        .filter(c -> c != varColor.getColor()).collect(Collectors.toList()));

    change.getAdding().setVariables(new ArrayList<>() {{ add(variable); }});

    List<Constraint> new_constraints = new ArrayList<>();
    for (Constraint constraint : change.getRemoving().getConstraints()) {
      VarColor first = constraint.getFirst();
      VarColor second = constraint.getSecond();
      if (first.getVariable().equals(varColor.getVariable())) {
        new_constraints.add(new Constraint(new VarColor(variable, first.getColor()), second));
      } else if (second.getVariable().equals(varColor.getVariable())) {
        new_constraints.add(new Constraint(first, new VarColor(variable, second.getColor())));
      }
    }

    change.getAdding().setConstraints(new_constraints);

    return change;
  }
}
