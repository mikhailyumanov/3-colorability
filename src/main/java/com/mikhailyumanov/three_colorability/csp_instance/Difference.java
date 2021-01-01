package com.mikhailyumanov.three_colorability.csp_instance;

import java.util.Objects;

public class Difference {
  private CSPInstance adding = new CSPInstance();
  private CSPInstance removing = new CSPInstance();

  public Difference() {}

  public Difference(CSPInstance adding, CSPInstance removing) {
    this.adding = adding;
    this.removing = removing;
  }

  public Difference union(Difference other) {
    adding.union(other.adding);
    removing.union(other.removing);

    return this;
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
    Difference that = (Difference) o;
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
