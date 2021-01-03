package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.algorithm.reductions.Lemma3;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
  }
}