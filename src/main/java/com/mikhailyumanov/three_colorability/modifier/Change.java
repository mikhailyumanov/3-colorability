package com.mikhailyumanov.three_colorability.modifier;

import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;

import java.util.Objects;

public class Change {
  private CSPInstance adding = new CSPInstance();
  private CSPInstance removing = new CSPInstance();

  public Change() {}

  public Change(CSPInstance adding, CSPInstance removing) {
    this.adding = adding;
    this.removing = removing;
  }

  public Change reversed() {
    return new Change(removing, adding);
  }

  public CSPInstance getAdding() {
    return adding;
  }

  public void setAdding(CSPInstance adding) {
    this.adding = adding;
  }

  public CSPInstance getRemoving() {
    return removing;
  }

  public void setRemoving(CSPInstance removing) {
    this.removing = removing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Change that = (Change) o;
    return Objects.equals(adding, that.adding) &&
        Objects.equals(removing, that.removing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adding, removing);
  }

  @Override
  public String toString() {
    return "Difference{" + '\n' +
        "adding=" + adding + '\n' +
        ", removing=" + removing + '\n' +
        '}'+ '\n';
  }
}
