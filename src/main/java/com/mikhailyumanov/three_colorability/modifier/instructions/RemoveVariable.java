package com.mikhailyumanov.three_colorability.modifier.instructions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Color;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Change;

public class RemoveVariable extends ChangeInstruction {
  Variable variable;

  public RemoveVariable(CSPInstance instance, Variable variable) {
    super(instance);
    this.variable = variable;
  }

  @Override
  public Change generateChange() {
    Change change = new Change();
    change.getRemoving().getVariables().add(variable);

    for (Color color : variable.getColors()) {
      change.getRemoving().getConstraints().addAll(instance.getIncident(new VarColor(variable, color)));
    }

    return change;
  }
}
