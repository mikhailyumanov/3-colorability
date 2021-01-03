package com.mikhailyumanov.three_colorability.modifier.instructions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.Change;

public abstract class ChangeInstruction {
  final CSPInstance instance;

  public ChangeInstruction(CSPInstance instance) {
    this.instance = instance;
  }

  public abstract Change generateChange();
}
