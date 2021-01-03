package com.mikhailyumanov.three_colorability.algorithm.solvers;

import com.mikhailyumanov.three_colorability.algorithm.reductions.*;
import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.Modifier;

public class AbstractSolver {
  private final Lemma2 lemma2 = new Lemma2();
  private final Lemma3 lemma3 = new Lemma3();
  private final Lemma4 lemma4 = new Lemma4();
  private final Lemma5 lemma5 = new Lemma5();
  private final Lemma6 lemma6 = new Lemma6();

  final Modifier modifier;

  public AbstractSolver() {
    this.modifier = new Modifier();
  }

  void applyLemmas2to6(CSPInstance instance) {
    lemma2.perform(modifier, instance);
    lemma3.perform(modifier, instance);
    lemma4.perform(modifier, instance);
    lemma5.perform(modifier, instance);
    lemma6.perform(modifier, instance);
  }
}
