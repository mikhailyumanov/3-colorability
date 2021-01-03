package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.algorithm.reductions.Lemma6;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lemma6Test extends TestWrapper {
  Lemma6Test() throws IOException {
    super(new Lemma6(),
        "src/main/resources/input/Lemma6Test",
        "src/main/resources/reduced/Lemma6Test");
  }

  @Test
  void testPerform() {
    assertEquals(
        test_reduced_instance,
        train_reduced_instance
    );
  }
}