package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.parser.InputParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Lemma4Test extends TestWrapper {
  Lemma4Test() throws IOException {
    super(new Lemma4(),
        "src/main/resources/input/Lemma4Test",
        "src/main/resources/reduced/Lemma4Test");
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