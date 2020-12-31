package com.mikhailyumanov.three_colorability.csp_instance;

import com.mikhailyumanov.three_colorability.util.Pair;

import java.util.List;

public interface CSPInstance {
  List<Variable> variables = null;
  List<Pair<VarColor, VarColor>> constraints = null;
}
