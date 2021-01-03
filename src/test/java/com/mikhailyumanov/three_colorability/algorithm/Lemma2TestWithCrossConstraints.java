package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.algorithm.reductions.Lemma2;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lemma2TestWithCrossConstraints extends TestWrapper {
  Lemma2TestWithCrossConstraints() throws IOException {
    super(new Lemma2(),
        "src/main/resources/input/Lemma2TestWithCrossConstraints",
        "src/main/resources/reduced/Lemma2TestWithCrossConstraints");
  }

  @Test
  void testPerform() {
    assertEquals(
        test_reduced_instance,
        train_reduced_instance
    );
  }
}