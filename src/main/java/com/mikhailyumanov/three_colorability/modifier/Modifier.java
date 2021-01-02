package com.mikhailyumanov.three_colorability.modifier;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.modifier.instructions.ChangeInstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Modifier {
  CSPInstance instance;
  List<Change> changeList;

  public Modifier(CSPInstance instance, List<ChangeInstruction> instructions) {
    this.instance = instance;
    this.changeList = instructions.stream()
        .map(ChangeInstruction::generateChange).collect(Collectors.toList());
  }

  public void apply() {
    for (Change change : changeList) {
      instance.withChange(change);
    }
  }

  public void unapply() {
    List<Change> reversed = new ArrayList<>(changeList);
    Collections.reverse(reversed);
    for (Change change : reversed) {
      instance.withChange(change.reversed());
    }
  }

  public CSPInstance getInstance() {
    return instance;
  }
}
