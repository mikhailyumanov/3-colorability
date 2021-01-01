package com.mikhailyumanov.three_colorability.csp_instance;

import com.mikhailyumanov.three_colorability.algorithm.Lemma2;
import com.mikhailyumanov.three_colorability.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DifferenceTest {
  @Test
  void union() {
    List<Variable> variables_before = List.of(
        new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
        new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
        new Variable(List.of(Color.RED, Color.GREEN)),
        new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
        new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
        new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE))
    );

    List<Pair<VarColor>> constraints_before = List.of(
        new Pair<>(
            new VarColor(variables_before.get(0), Color.BLUE),
            new VarColor(variables_before.get(2), Color.RED)),
        new Pair<>(
            new VarColor(variables_before.get(1), Color.RED),
            new VarColor(variables_before.get(2), Color.RED)),
        new Pair<>(
            new VarColor(variables_before.get(3), Color.GREEN),
            new VarColor(variables_before.get(2), Color.GREEN)),
        new Pair<>(
            new VarColor(variables_before.get(4), Color.GREEN),
            new VarColor(variables_before.get(2), Color.GREEN)),
        new Pair<>(
            new VarColor(variables_before.get(5), Color.RED),
            new VarColor(variables_before.get(2), Color.GREEN))
    );

    Lemma2 lemma2 = new Lemma2();
    CSPInstance instance_before = new CSPInstance(variables_before, constraints_before);
    CSPInstance instance_after = instance_before.withDifference(lemma2.perform(instance_before));

    List<Pair<VarColor>> constraints_final = List.of(
        new Pair<>(
            new VarColor(instance_after.getVariables().get(0), Color.BLUE),
            new VarColor(instance_after.getVariables().get(2), Color.RED)),
        new Pair<>(
            new VarColor(instance_after.getVariables().get(1), Color.RED),
            new VarColor(instance_after.getVariables().get(2), Color.RED)),
        new Pair<>(
            new VarColor(instance_after.getVariables().get(3), Color.GREEN),
            new VarColor(instance_after.getVariables().get(2), Color.GREEN)),
        new Pair<>(
            new VarColor(instance_after.getVariables().get(4), Color.GREEN),
            new VarColor(instance_after.getVariables().get(2), Color.GREEN)),
        new Pair<>(
            new VarColor(instance_after.getVariables().get(5), Color.RED),
            new VarColor(instance_after.getVariables().get(2), Color.GREEN))
    );

    assertEquals(
        instance_after,
        new CSPInstance(instance_after.getVariables(), constraints_final)
    );
  }
}