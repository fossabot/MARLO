package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 9, 2017 11:49:29 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * DeliverablePrograms generated by hbm2java
 */
public class DeliverableProgram extends MarloBaseEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 283477401039556496L;

  private Deliverable deliverable;

  @Expose
  private Phase phase;
  @Expose
  private CrpProgram crpProgram;

  public DeliverableProgram() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    DeliverableProgram other = (DeliverableProgram) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public CrpProgram getCrpProgram() {
    return crpProgram;
  }


  public Deliverable getDeliverable() {
    return deliverable;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public String getModificationJustification() {

    return "";
  }

  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }

  public Phase getPhase() {
    return phase;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public void setCrpProgram(CrpProgram crpProgram) {
    this.crpProgram = crpProgram;
  }

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  @Override
  public String toString() {
    return "DeliverableProgram [id=" + this.getId() + ", deliverable=" + deliverable + ", crpProgram=" + crpProgram
      + ", phase=" + phase + "]";
  }

}

