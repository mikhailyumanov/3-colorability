package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  void testResultWithDifference() {
    CSPInstance instance_after = instance.withDifference(lemma5.perform(instance));

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
  }

  @Test
  void testPerform() {
    CSPInstance removing = new CSPInstance();
    removing.setVariables(List.of(variables.get(0)));
    removing.setConstraints(new ArrayList<>() {{
      add(new Constraint(
          new VarColor(variables.get(0), Color.RED),
          new VarColor(variables.get(1), Color.RED)));
      add(new Constraint(
          new VarColor(variables.get(0), Color.RED),
          new VarColor(variables.get(1), Color.GREEN)));
      add(new Constraint(
          new VarColor(variables.get(0), Color.RED),
          new VarColor(variables.get(2), Color.GREEN)));
      add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(1), Color.RED)));
      add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(1), Color.GREEN)));
      add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(2), Color.GREEN)));
      add(new Constraint(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(2), Color.RED)));
    }});

    assertEquals(
        removing,
        lemma5.perform(instance).getRemoving()
    );
  }
}