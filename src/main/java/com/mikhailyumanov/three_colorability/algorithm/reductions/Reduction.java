package com.mikhailyumanov.three_colorability.algorithm.reductions;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;

@FunctionalInterface
public interface Reduction {
  void perform(Modifier modifier, CSPInstance instance);
}
