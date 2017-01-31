package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 26, 2017 10:34:59 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * DeliverableCrps generated by hbm2java
 */
public class DeliverableCrp implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 3934374948729277454L;


  @Expose
  private Long id;


  @Expose
  private CrpPandr crpPandr;


  @Expose
  private IpProgram ipProgram;

  @Expose
  private Deliverable deliverable;

  public DeliverableCrp() {
  }

  public DeliverableCrp(Deliverable deliverable) {

    this.deliverable = deliverable;
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
    DeliverableCrp other = (DeliverableCrp) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public CrpPandr getCrpPandr() {
    return crpPandr;
  }

  public Deliverable getDeliverable() {
    return deliverable;
  }

  @Override
  public Long getId() {
    return id;
  }

  public IpProgram getIpProgram() {
    return ipProgram;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public void setCrpPandr(CrpPandr crpPandr) {
    this.crpPandr = crpPandr;
  }


  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIpProgram(IpProgram ipProgram) {
    this.ipProgram = ipProgram;
  }


}

