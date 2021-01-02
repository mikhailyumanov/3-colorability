package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.*;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lemma5Test extends TestWrapper {
  Lemma5Test() throws IOException {
    super(new Lemma5(),
        "src/main/resources/input/Lemma5Test",
        "src/main/resources/reduced/Lemma5Test");
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