package org.cgiar.ccafs.marlo.data.model;

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

// Generated Feb 9, 2017 11:49:29 AM by Hibernate Tools 4.3.1.Final


/**
 * DeliverableLeaders generated by hbm2java
 */
public class DeliverableLeader extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -5863315494457225665L;

  private Deliverable deliverable;


  @Expose
  private Institution institution;
  @Expose
  private Phase phase;

  public DeliverableLeader() {
  }

  public DeliverableLeader(Deliverable deliverable, Institution institution) {
    this.deliverable = deliverable;
    this.institution = institution;
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
    DeliverableLeader other = (DeliverableLeader) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public Deliverable getDeliverable() {
    return deliverable;
  }

  public Institution getInstitution() {
    return institution;
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

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  @Override
  public String toString() {
    return "DeliverableLeader [id=" + id + ", deliverable=" + deliverable + ", institution=" + institution + ", phase="
      + phase + "]";
  }


}

