package com.mikhailyumanov.three_colorability.modifier.instructions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Color;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Change;

public class RemoveVariable extends ChangeInstruction {
  private final int variable;

  public RemoveVariable(CSPInstance instance, int variable) {
    super(instance);
    this.variable = variable;
  }

  @Override
  public Change generateChange() {
    Change change = new Change();

    Variable variable = instance.getVariable(this.variable);
    change.getRemoving().getVariable_ids().add(this.variable);
    change.getRemoving().getVarColors().addAll(variable.getVarColors());

    for (VarColor varColor : variable.getVarColors()) {
      change.getRemoving().getConstraints().addAll(instance.getIncident(varColor));
    }

    return change;
  }
}
