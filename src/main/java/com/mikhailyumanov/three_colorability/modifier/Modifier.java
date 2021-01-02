package com.mikhailyumanov.three_colorability.modifier;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Modifier {
  CSPInstance instance;
  List<Change> changeListApply = new ArrayList<>();
  List<Change> changeListUnapply = new ArrayList<>();
  List<ChangeInstruction> instructions = new ArrayList<>();

  public Modifier(CSPInstance instance) {
    this.instance = instance;
  }

  public void setChangeList(List<ChangeInstruction> instructions) {
    this.changeListApply = instructions.stream()
        .map(ChangeInstruction::generateChange).collect(Collectors.toList());
  }

  public void addInstruction(ChangeInstruction instruction) {
    changeListApply.add(instruction.generateChange());
  }

  public void applyOne() {
    instance.withChange(changeListApply.get(0));
    changeListUnapply.add(changeListApply.remove(0));
  }

  public void apply() {
    for (Change change : changeListApply) {
      instance.withChange(change);
    }

    changeListUnapply.addAll(changeListApply);
    changeListApply = new ArrayList<>();
  }

  public void unapply() {
    List<Change> reversed = new ArrayList<>(changeListUnapply);
    for (Change change : reversed) {
      instance.withChange(change.reversed());
    }

    changeListUnapply.addAll(changeListApply);
    Collections.reverse(changeListUnapply);
    changeListApply = changeListUnapply;
    changeListUnapply = new ArrayList<>();
  }

  public CSPInstance getInstance() {
    return instance;
  }

  public void addChangeList(List<ChangeInstruction> perform) {
    changeListApply.addAll(perform.stream()
        .map(ChangeInstruction::generateChange).collect(Collectors.toList()));
  }
}
