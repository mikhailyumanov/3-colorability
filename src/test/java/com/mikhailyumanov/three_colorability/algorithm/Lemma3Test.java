package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.Change;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lemma3Test {
  Lemma3 lemma3 = new Lemma3();

  List<Variable> variables = new ArrayList<>() {{
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
    add(new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)));
  }};

  /**
   *  (v, X) = (0, RED)
   *  (w, Y) = (1. GREEN)
   */
  VarColor varColor1 = new VarColor(variables.get(0), Color.RED);
  VarColor varColor2 = new VarColor(variables.get(1), Color.GREEN);

  List<Constraint> constraints = new ArrayList<>() {{
    add(new Constraint(
          new VarColor(variables.get(0), Color.RED),
          new VarColor(variables.get(1), Color.RED)));
    add(new Constraint(
          new VarColor(variables.get(0), Color.RED),
          new VarColor(variables.get(1), Color.BLUE)));
    add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(1), Color.GREEN)));
    add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(2), Color.RED)));
    add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(2), Color.GREEN)));
    add(new Constraint(
          new VarColor(variables.get(1), Color.RED),
          new VarColor(variables.get(3), Color.BLUE)));
    add(new Constraint(
          new VarColor(variables.get(1), Color.BLUE),
          new VarColor(variables.get(3), Color.GREEN)));

    /// Constrains remaining after lemma3
    add(new Constraint(
          new VarColor(variables.get(2), Color.RED),
          new VarColor(variables.get(3), Color.GREEN)));
    add(new Constraint(
          new VarColor(variables.get(2), Color.RED),
          new VarColor(variables.get(3), Color.BLUE)));
    add(new Constraint(
          new VarColor(variables.get(2), Color.GREEN),
          new VarColor(variables.get(3), Color.GREEN)));
    add(new Constraint(
          new VarColor(variables.get(2), Color.GREEN),
          new VarColor(variables.get(3), Color.BLUE)));
    ///

  }};

  CSPInstance instance = new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints));
  
  @Test
  void testPerform() {
    Modifier modifier = new Modifier(instance, lemma3.perform(instance));
    modifier.apply();

    List<Constraint> constraints_final = new ArrayList<>() {{
      add(new Constraint(
          new VarColor(variables.get(2), Color.RED),
          new VarColor(variables.get(3), Color.GREEN)));
      add(new Constraint(
          new VarColor(variables.get(2), Color.RED),
          new VarColor(variables.get(3), Color.BLUE)));
      add(new Constraint(
          new VarColor(variables.get(2), Color.GREEN),
          new VarColor(variables.get(3), Color.GREEN)));
      add(new Constraint(
          new VarColor(variables.get(2), Color.GREEN),
          new VarColor(variables.get(3), Color.BLUE)));
    }};

    assertEquals(
        new CSPInstance(variables.subList(2, 4), constraints_final),
        modifier.getInstance()
    );

    modifier.unapply();

    assertEquals(
        new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints)),
        modifier.getInstance()
    );
  }
}