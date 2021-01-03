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
  private final VarColor varColor;
  
  public RemoveColor(CSPInstance instance, VarColor varColor) {
    super(instance);
    this.varColor = varColor;
  }

  @Override
  public Change generateChange() {
    Change change = new Change();
    change.getRemoving().getVarColors().add(varColor);
    change.getRemoving().setConstraints(instance.getIncident(varColor));

    return change;
  }
}
