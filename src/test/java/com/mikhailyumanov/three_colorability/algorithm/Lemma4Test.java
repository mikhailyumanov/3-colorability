package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Lemma4Test {
  Lemma4 lemma4 = new Lemma4();

  List<Variable> variables = new ArrayList<>() {{
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
    add(new Variable(new ArrayList<>() {{ add(Color.RED); add(Color.GREEN); add(Color.BLUE); }}));
  }};

  /**
   *  (v, R) = (0, RED)
   *  (v, B) = (0. BLUE)
   */

  VarColor varColor1 = new VarColor(variables.get(0), Color.RED);
  VarColor varColor2 = new VarColor(variables.get(0), Color.BLUE);

  List<Constraint> constraints = new ArrayList<>() {{
    //< Constraints which both (v, R) and (v, B) have
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
    //>

    //< Constraint on which there is a difference
    /// between (v, B) and (v, R)
    add(new Constraint(
        new VarColor(variables.get(0), Color.BLUE),
        new VarColor(variables.get(2), Color.RED)));
    //>

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
    Modifier modifier = new Modifier(instance, lemma4.perform(instance));
    modifier.apply();
    CSPInstance instance_after = modifier.getInstance();

    assertEquals(
        variables.size(),
        instance_after.getVariables().size()
    );

    assertNotEquals(
        variables,
        instance_after.getVariables()
    );

    assertEquals(
        variables.stream()
            .filter(v -> !v.equals(variables.get(0))).collect(Collectors.toList()),
        instance_after.getVariables().stream()
            .filter(v -> !v.equals(instance_after.getVariables().get(3))).collect(Collectors.toList())
    );

    assertEquals(
        constraints.size(),
        instance_after.getConstraints().size()
    );

    assertNotEquals(
        constraints,
        instance_after.getConstraints()
    );

    modifier.unapply();

    assertEquals(
        new CSPInstance(new ArrayList<>(variables), new ArrayList<>(constraints)),
        modifier.getInstance()
    );
  }
}