package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.algorithm.reductions.Lemma2;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Lemma2TestWithoutCrossConstraints extends TestWrapper{
  Lemma2TestWithoutCrossConstraints() throws IOException {
    super(new Lemma2(),
        "src/main/resources/input/Lemma2TestWithoutCrossConstraints",
        "src/main/resources/reduced/Lemma2TestWithoutCrossConstraints");
  }

  @Test
  void testPerform() {
    assertEquals(
        test_reduced_instance,
        train_reduced_instance
    );
  }
}