package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.algorithm.solvers.SimpleRandomizedSolver;
import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.parser.InputParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
  @Test
  void EmptyConstraintsSet() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/EmptyConstraintsSet");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertTrue(solver.hasColoring(test_input_instance));
  }

  @Test
  void OneConstraint() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/OneConstraint");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertTrue(solver.hasColoring(test_input_instance));
  }

  @Test
  void TwoConstraints() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/TwoConstraints");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertTrue(solver.hasColoring(test_input_instance));
  }

  @Test
  void AllConstraints() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/AllConstraints");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertFalse(solver.hasColoring(test_input_instance));
  }

  @Test
  void GraphTestSolvable() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/GraphTestSolvable");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertTrue(solver.hasColoring(test_input_instance));
  }

  @Test
  void GraphTestUnsolvable() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/GraphTestUnsolvable");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertFalse(solver.hasColoring(test_input_instance));
  }

  @Test
  void GraphTestSolvable2() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/GraphTestSolvable2");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertTrue(solver.hasColoring(test_input_instance));
  }

  @Test
  void GraphTestUnsolvable2() throws IOException {
    CSPInstance test_input_instance = InputParser.parse("src/main/resources/solver/GraphTestUnsolvable2");
    SimpleRandomizedSolver solver = new SimpleRandomizedSolver();
    assertFalse(solver.hasColoring(test_input_instance));
  }


}