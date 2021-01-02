package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;

import java.util.List;

@FunctionalInterface
public interface Reduction {
  List<ChangeInstruction> perform(CSPInstance instance);
}
