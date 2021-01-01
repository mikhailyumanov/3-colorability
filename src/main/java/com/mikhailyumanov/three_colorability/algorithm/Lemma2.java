package com.mikhailyumanov.three_colorability.algorithm;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Difference;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lemma2 implements Reduction {
  @Override
  public Difference perform(CSPInstance instance) {
    Difference difference = new Difference();
    for (Variable variable : instance.getVariables()) {
      if (variable.getColors().size() == 2) {
        difference.union(toUpdate(instance, variable));
      }
    }

    return difference;
  }

  @Override
  public Difference toUpdate(CSPInstance instance, Variable variable) {
    VarColor varColor1 = new VarColor(variable, variable.getColors().get(0));
    VarColor varColor2 = new VarColor(variable, variable.getColors().get(1));

    List<VarColor> conflict1 = getConflict(instance, varColor1);
    List<VarColor> conflict2 = getConflict(instance, varColor2);
    CSPInstance adding = new CSPInstance();
    adding.setConstraints(getCartesianProduct(conflict1, conflict2));

    CSPInstance removing = new CSPInstance();
    removing.setVariables(List.of(variable));
    removing.setConstraints(
        instance.getConstraints().stream()
            .filter(pair -> pair.contains(varColor1) || pair.contains(varColor2))
            .collect(Collectors.toList()));

    return new Difference(adding, removing);
  }

  public List<VarColor> getConflict(CSPInstance instance, VarColor varColor) {
    return instance.getConstraints().stream()
        .filter(pair -> pair.contains(varColor))
        .map(pair -> pair.getFirst().equals(varColor) ? pair.getSecond() : pair.getFirst())
        .collect(Collectors.toList());
  }

  public List<Pair<VarColor>> getCartesianProduct(List<VarColor> l1, List<VarColor> l2) {
    ArrayList<Pair<VarColor>> tmp = new ArrayList<>();
    for (VarColor varColor1 : l1) {
      for (VarColor varColor2 : l2) {
        if (!varColor1.getVariable().equals(varColor2.getVariable())) {
          tmp.add(new Pair<>(varColor1, varColor2));
        }
      }
    }

    return tmp;
  }
}
