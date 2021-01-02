package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lemma5Test {
  Lemma5 lemma5 = new Lemma5();

  List<Variable> variables = new ArrayList<>() {{
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
  }};

  List<Constraint> constraints = new ArrayList<>() {{
    add(new Constraint(
        new VarColor(variables.get(0), Color.RED),
        new VarColor(variables.get(1), Color.RED)));
    add(new Constraint(
        new VarColor(variables.get(0), Color.RED),
        new VarColor(variables.get(1), Color.GREEN)));
    add(new Constraint(
        new VarColor(variables.get(0), Color.BLUE),
        new VarColor(variables.get(1), Color.RED)));
    add(new Constraint(
        new VarColor(variables.get(0), Color.BLUE),
        new VarColor(variables.get(1), Color.GREEN)));

    add(new Constraint(
        new VarColor(variables.get(0), Color.RED),
        new VarColor(variables.get(2), Color.GREEN)));
    add(new Constraint(
        new VarColor(variables.get(0), Color.BLUE),
        new VarColor(variables.get(2), Color.GREEN)));
    add(new Constraint(
        new VarColor(variables.get(0), Color.BLUE),
        new VarColor(variables.get(2), Color.RED)));

    add(new Constraint(
        new VarColor(variables.get(1), Color.BLUE),
        new VarColor(variables.get(3), Color.BLUE)));

    add(new Constraint(
        new VarColor(variables.get(2), Color.BLUE),
        new VarColor(variables.get(3), Color.RED)));
    add(new Constraint(
        new VarColor(variables.get(2), Color.BLUE),
        new VarColor(variables.get(3), Color.GREEN)));
  }};

  CSPInstance instance = new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints));

  @Test
  void testPerform() {
    Modifier modifier = new Modifier(instance, lemma5.perform(instance));
    modifier.apply();
    CSPInstance instance_after = modifier.getInstance();

    List<Constraint> constraints_final = new ArrayList<>() {{
      add(new Constraint(
          new VarColor(variables.get(1), Color.BLUE),
          new VarColor(variables.get(3), Color.BLUE)));
      add(new Constraint(
          new VarColor(variables.get(2), Color.BLUE),
          new VarColor(variables.get(3), Color.RED)));
      add(new Constraint(
          new VarColor(variables.get(2), Color.BLUE),
          new VarColor(variables.get(3), Color.GREEN)));
    }};

    assertEquals(
        new CSPInstance(variables.subList(1, 4), constraints_final),
        instance_after
    );

    modifier.unapply();

    assertEquals(
        new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints)),
        modifier.getInstance()
    );
  }
}