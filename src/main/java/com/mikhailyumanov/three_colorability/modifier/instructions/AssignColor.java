package com.mikhailyumanov.three_colorability.modifier.instructions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.modifier.Change;

public class AssignColor extends ChangeInstruction {
  private final VarColor varColor;
  public AssignColor(CSPInstance instance, VarColor varColor) {
    super(instance);
    this.varColor = varColor;
  }

  @Override
  public Change generateChange() {
    Change change = new Change();
    change.getAdding().assignColor(varColor);
    return change;
  }
}
