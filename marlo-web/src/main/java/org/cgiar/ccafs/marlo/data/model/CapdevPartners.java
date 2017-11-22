package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated Jul 12, 2017 2:20:41 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CapdevPartners generated by hbm2java
 */
public class CapdevPartners implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Expose
  private Long id;

  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;

  @Expose
  private Institution institution;

  @Expose
  private CapacityDevelopment capacityDevelopment;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  public CapdevPartners() {
  }


  public CapdevPartners(CapacityDevelopment capacityDevelopment, boolean active) {
    this.capacityDevelopment = capacityDevelopment;
    this.active = active;
  }

  public CapdevPartners(User modifiedBy, User createdBy, Institution institution,
    CapacityDevelopment capacityDevelopment, boolean active, Date activeSince, String modificationJustification) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.institution = institution;
    this.capacityDevelopment = capacityDevelopment;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }

  public Date getActiveSince() {
    return this.activeSince;
  }

  public CapacityDevelopment getCapacityDevelopment() {
    return this.capacityDevelopment;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  @Override
  public Long getId() {
    return this.id;
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

  @Override
  public String getModificationJustification() {
    return this.modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  @Override
  public boolean isActive() {
    return this.active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setCapacityDevelopment(CapacityDevelopment capacityDevelopment) {
    this.capacityDevelopment = capacityDevelopment;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setInstitution(Institution institution) {
    this.institution = institution;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


}
