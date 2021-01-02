package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.modifier.Change;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.parser.InputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lemma3Test extends TestWrapper {
  Lemma3Test() throws IOException {
    super(new Lemma3(),
        "src/main/resources/input/Lemma3Test",
        "src/main/resources/reduced/Lemma3Test");
  }

  @Test
  void testPerform() {
    assertEquals(
        test_reduced_instance,
        train_reduced_instance
    );

    modifier.unapply();

    assertEquals(
        test_input_instance,
        modifier.getInstance()
    );

  }
}