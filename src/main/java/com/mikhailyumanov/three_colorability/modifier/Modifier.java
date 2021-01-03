package com.mikhailyumanov.three_colorability.modifier;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;
import com.mikhailyumanov.three_colorability.modifier.instructions.Lemma2RemoveVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Modifier {
  List<Change> changeListApply = new ArrayList<>();
  List<Change> changeListUnapply = new ArrayList<>();

  public void addInstruction(ChangeInstruction instruction) {
    changeListApply.add(instruction.generateChange());
  }

  public void applyOne(CSPInstance instance) {
    instance.withChange(changeListApply.get(0));
    changeListUnapply.add(changeListApply.remove(0));
  }

  public void stepBy(CSPInstance instance, ChangeInstruction instruction) {
    addInstruction(instruction);
    applyOne(instance);
  }

  public void apply(CSPInstance instance) {
    for (Change change : changeListApply) {
      instance.withChange(change);
    }

    changeListUnapply.addAll(changeListApply);
    changeListApply = new ArrayList<>();
  }

  public boolean hasRecentlyChanged() {
    return !changeListUnapply.isEmpty();
  }

  public void flushAppliedChanges() {
    changeListUnapply = new ArrayList<>();
  }
}
