package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Difference;

@FunctionalInterface
public interface Reduction {
  Difference perform(CSPInstance instance);
}
