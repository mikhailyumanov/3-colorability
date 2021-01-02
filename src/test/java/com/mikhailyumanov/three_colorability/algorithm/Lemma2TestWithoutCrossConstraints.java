package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lemma2TestWithoutCrossConstraints {
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

  CSPInstance instance = new CSPInstance(variables, constraints);

  @Test
  void testResultWithDifference() {
    CSPInstance instance_after = instance.withDifference(lemma2.perform(instance));

    List<Constraint> constraints_final = List.of(
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(3), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(4), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(5), Color.RED)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(3), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(4), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(5), Color.RED))
    );

    assertEquals(
        new CSPInstance(instance_after.getVariables(), constraints_final),
        instance_after
    );
  }

  @Test
  void testToEliminate() {
    CSPInstance adding = new CSPInstance();
    adding.setConstraints(List.of(
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(3), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(4), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(5), Color.RED)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(3), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(4), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(5), Color.RED))
    ));

    CSPInstance removing = new CSPInstance();
    removing.setVariables(List.of(variables.get(2)));
    removing.setConstraints(List.of(
        new Constraint(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(2), Color.RED)),
        new Constraint(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(2), Color.RED)),
        new Constraint(
            new VarColor(variables.get(3), Color.GREEN),
            new VarColor(variables.get(2), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(4), Color.GREEN),
            new VarColor(variables.get(2), Color.GREEN)),
        new Constraint(
            new VarColor(variables.get(5), Color.RED),
            new VarColor(variables.get(2), Color.GREEN))
    ));

    assertEquals(
        new Difference(adding, removing),
        lemma2.toUpdate(instance, variables.get(2))
    );
  }

  @Test
  void testGetConflict() {
    assertEquals(
        List.of(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(1), Color.RED)
        ),
        lemma2.getConflict(instance, new VarColor(variables.get(2), Color.RED))
    );
    assertEquals(
        List.of(
            new VarColor(variables.get(3), Color.GREEN),
            new VarColor(variables.get(4), Color.GREEN),
            new VarColor(variables.get(5), Color.RED)
        ),
        lemma2.getConflict(instance, new VarColor(variables.get(2), Color.GREEN))
    );
  }

  @Test
  void testGetCartesianProduct() {
    List<VarColor> l1 = List.of(
        new VarColor(variables.get(0), Color.BLUE),
        new VarColor(variables.get(1), Color.RED));

    List<VarColor> l2 = List.of(
        new VarColor(variables.get(3), Color.GREEN),
        new VarColor(variables.get(4), Color.GREEN),
        new VarColor(variables.get(5), Color.RED));

    assertEquals(
        List.of(
            new Constraint(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(3), Color.GREEN)),
            new Constraint(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(4), Color.GREEN)),
            new Constraint(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(5), Color.RED)),
            new Constraint(
                new VarColor(variables.get(1), Color.RED),
                new VarColor(variables.get(3), Color.GREEN)),
            new Constraint(
                new VarColor(variables.get(1), Color.RED),
                new VarColor(variables.get(4), Color.GREEN)),
            new Constraint(
                new VarColor(variables.get(1), Color.RED),
                new VarColor(variables.get(5), Color.RED))
        ),
        lemma2.getCartesianProduct(l1, l2)
    );
  }
}