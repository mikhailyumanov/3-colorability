package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.Change;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lemma2TestWithCrossConstraints {
  Lemma2 lemma2 = new Lemma2();

  List<Variable> variables = new ArrayList<>() {{
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
  }};

  List<Constraint> constraints = new ArrayList<>() {{
    add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(2), Color.RED)));
    add(new Constraint(
          new VarColor(variables.get(1), Color.RED),
          new VarColor(variables.get(2), Color.RED)));

      /// Extra constraint
    add(new Constraint(
          new VarColor(variables.get(1), Color.GREEN),
          new VarColor(variables.get(2), Color.GREEN)));
      ///

    add(new Constraint(
          new VarColor(variables.get(3), Color.GREEN),
          new VarColor(variables.get(2), Color.GREEN)));
    add(new Constraint(
          new VarColor(variables.get(4), Color.GREEN),
          new VarColor(variables.get(2), Color.GREEN)));
    add(new Constraint(
          new VarColor(variables.get(5), Color.RED),
          new VarColor(variables.get(2), Color.GREEN)));
  }};

  CSPInstance instance = new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints));

  @Test
  void testPerform() {
    Modifier modifier = new Modifier(instance, lemma2.perform(instance));
    modifier.apply();
    CSPInstance instance_after = modifier.getInstance();

    List<Constraint> constraints_final = new ArrayList<>() {{
      add(new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(1), Color.GREEN)));
      add(new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(3), Color.GREEN)));
      add(new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(4), Color.GREEN)));
      add(new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(5), Color.RED)));
      add(new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(3), Color.GREEN)));
      add(new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(4), Color.GREEN)));
      add(new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(5), Color.RED)));
    }};

    assertEquals(
        new CSPInstance(instance_after.getVariables(), constraints_final),
        instance_after
    );

    modifier.unapply();

    assertEquals(
        new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints)),
        modifier.getInstance()
    );

  }
}