package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated Jul 12, 2017 2:20:41 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CapdevPartners generated by hbm2java
 */
public class CapdevPartners extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Expose
  private Institution institution;

  @Expose
  private CapacityDevelopment capacityDevelopment;


  public CapdevPartners() {
  }


  public CapacityDevelopment getCapacityDevelopment() {
    return this.capacityDevelopment;
  }

  public Institution getInstitution() {
    return this.institution;
  }


  @Override
  public String getLogDeatil() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public void setCapacityDevelopment(CapacityDevelopment capacityDevelopment) {
    this.capacityDevelopment = capacityDevelopment;
  }


  public void setInstitution(Institution institution) {
    this.institution = institution;
  }


}

