package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lemma2TestWithCrossConstraints {
  Lemma2 lemma2 = new Lemma2();

  List<Variable> variables = List.of(
      new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
      new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
      new Variable(List.of(Color.RED, Color.GREEN)),
      new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
      new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE)),
      new Variable(List.of(Color.RED, Color.GREEN, Color.BLUE))
  );

  List<Pair<VarColor>> constraints = List.of(
      new Pair<>(
          new VarColor(variables.get(0), Color.BLUE),
          new VarColor(variables.get(2), Color.RED)),
      new Pair<>(
          new VarColor(variables.get(1), Color.RED),
          new VarColor(variables.get(2), Color.RED)),

      /// Extra constraint
      new Pair<>(
          new VarColor(variables.get(1), Color.GREEN),
          new VarColor(variables.get(2), Color.GREEN)),
      ///

      new Pair<>(
          new VarColor(variables.get(3), Color.GREEN),
          new VarColor(variables.get(2), Color.GREEN)),
      new Pair<>(
          new VarColor(variables.get(4), Color.GREEN),
          new VarColor(variables.get(2), Color.GREEN)),
      new Pair<>(
          new VarColor(variables.get(5), Color.RED),
          new VarColor(variables.get(2), Color.GREEN))
  );

  CSPInstance instance = new CSPInstance(variables, constraints);

  @Test
  void testPerform() {
  }

  @Test
  void testToEliminate() {
    CSPInstance adding = new CSPInstance();
    adding.setConstraints(List.of(
        new Pair<>(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(1), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(3), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(4), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(5), Color.RED)),
        new Pair<>(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(3), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(4), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(5), Color.RED))
    ));

    CSPInstance removing = new CSPInstance();
    removing.setVariables(List.of(variables.get(2)));
    removing.setConstraints(List.of(
        new Pair<>(
            new VarColor(variables.get(0), Color.BLUE),
            new VarColor(variables.get(2), Color.RED)),
        new Pair<>(
            new VarColor(variables.get(1), Color.RED),
            new VarColor(variables.get(2), Color.RED)),
        new Pair<>(
            new VarColor(variables.get(1), Color.GREEN),
            new VarColor(variables.get(2), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(3), Color.GREEN),
            new VarColor(variables.get(2), Color.GREEN)),
        new Pair<>(
            new VarColor(variables.get(4), Color.GREEN),
            new VarColor(variables.get(2), Color.GREEN)),
        new Pair<>(
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
            new VarColor(variables.get(1), Color.GREEN),
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
        new VarColor(variables.get(1), Color.RED)
    );

    List<VarColor> l2 = List.of(
        new VarColor(variables.get(1), Color.GREEN),
        new VarColor(variables.get(3), Color.GREEN),
        new VarColor(variables.get(4), Color.GREEN),
        new VarColor(variables.get(5), Color.RED));

    assertEquals(
        List.of(
            new Pair<>(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(1), Color.GREEN)),
            new Pair<>(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(3), Color.GREEN)),
            new Pair<>(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(4), Color.GREEN)),
            new Pair<>(
                new VarColor(variables.get(0), Color.BLUE),
                new VarColor(variables.get(5), Color.RED)),
            new Pair<>(
                new VarColor(variables.get(1), Color.RED),
                new VarColor(variables.get(3), Color.GREEN)),
            new Pair<>(
                new VarColor(variables.get(1), Color.RED),
                new VarColor(variables.get(4), Color.GREEN)),
            new Pair<>(
                new VarColor(variables.get(1), Color.RED),
                new VarColor(variables.get(5), Color.RED))
        ),
        lemma2.getCartesianProduct(l1, l2)
    );
  }
}