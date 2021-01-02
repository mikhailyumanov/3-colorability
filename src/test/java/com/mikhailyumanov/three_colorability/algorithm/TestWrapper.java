package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.Modifier;
import com.mikhailyumanov.three_colorability.parser.InputParser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWrapper {
  Reduction reduction;
  final CSPInstance test_input_instance;
  final CSPInstance test_reduced_instance;
  final CSPInstance train_reduced_instance;
  final Modifier modifier;


  TestWrapper(Reduction reduction, String input, String reduced) throws IOException {
    test_input_instance = InputParser.parse(input);
    test_reduced_instance = InputParser.parse(reduced);

    CSPInstance instance = CSPInstance.copyFrom(test_input_instance);
    modifier = new Modifier(instance);
    modifier.setChangeList(reduction.perform(modifier, instance));
    modifier.apply();
    train_reduced_instance = modifier.getInstance();
  }
}
