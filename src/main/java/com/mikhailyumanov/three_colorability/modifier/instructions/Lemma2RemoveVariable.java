package com.mikhailyumanov.three_colorability.modifier.instructions;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.csp_instance.Constraint;
import com.mikhailyumanov.three_colorability.csp_instance.VarColor;
import com.mikhailyumanov.three_colorability.csp_instance.Variable;
import com.mikhailyumanov.three_colorability.modifier.Change;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lemma2RemoveVariable extends ChangeInstruction {
  Variable variable;

  public Lemma2RemoveVariable(CSPInstance instance, Variable variable) {
    super(instance);
    this.variable = variable;
  }

  @Override
  public Change generateChange() {
    VarColor varColor1 = variable.getVarColors().get(0);
    VarColor varColor2 = variable.getVarColors().get(1);

    List<VarColor> conflict1 = instance.getConflict(varColor1);
    List<VarColor> conflict2 = instance.getConflict(varColor2);
    CSPInstance adding = new CSPInstance();
    adding.setConstraints(getCartesianProduct(conflict1, conflict2));

    CSPInstance removing = new CSPInstance();

    removing.setVarColors(variable.getVarColors());
    removing.setConstraints(
        instance.getConstraints().stream()
            .filter(pair -> pair.contains(varColor1) || pair.contains(varColor2))
            .collect(Collectors.toList()));

    return new Change(adding, removing);
  }

  public List<Constraint> getCartesianProduct(List<VarColor> l1, List<VarColor> l2) {
    ArrayList<Constraint> tmp = new ArrayList<>();
    for (VarColor varColor1 : l1) {
      for (VarColor varColor2 : l2) {
        if (varColor1.getVariable() != varColor2.getVariable()) {
          tmp.add(new Constraint(varColor1, varColor2));
        }
      }
    }

    return tmp;
  }
}
