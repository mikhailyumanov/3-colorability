package com.mikhailyumanov.three_colorability.parser;

import com.mikhailyumanov.three_colorability.csp_instance.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
  public static CSPInstance parse(String stringPath) throws IOException {
    Path path = Paths.get(stringPath);
    Scanner scanner = new Scanner(path);

    List<Integer> variable_ids = new ArrayList<>();
    List<VarColor> varColors = new ArrayList<>();
    List<Constraint> constraints = new ArrayList<>();

    int num_variables = scanner.nextInt();
    for (int i = 0; i < num_variables; i++) {
      int variable = scanner.nextInt();
      variable_ids.add(variable);

      int num_colors = scanner.nextInt();
      for (int j = 0; j < num_colors; j++) {
        varColors.add(new VarColor(variable, Color.fromInteger(scanner.nextInt())));
      }
    }

    int num_constraints = scanner.nextInt();
    for (int i = 0; i < num_constraints; i++) {
      VarColor varColor1 = new VarColor(scanner.nextInt(), Color.fromInteger(scanner.nextInt()));
      VarColor varColor2 = new VarColor(scanner.nextInt(), Color.fromInteger(scanner.nextInt()));
      constraints.add(new Constraint(varColor1, varColor2));
    }

    CSPInstance instance = new CSPInstance(variable_ids, varColors, constraints);

    int num_colored_variables = scanner.nextInt();
    for (int i = 0; i < num_colored_variables; i++) {
      VarColor varColor = new VarColor(scanner.nextInt(), Color.fromInteger(scanner.nextInt()));
      instance.getColoring().assignColor(varColor);
    }


    return instance;
  }
}
