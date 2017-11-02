package org.cgiar.ccafs.marlo.data.model;
// Generated May 30, 2017 9:23:45 AM by Hibernate Tools 3.4.0.CR1


import java.util.Date;

/**
 * CenterCustomParameter generated by hbm2java
 */
public class CenterCustomParameter implements java.io.Serializable {


  private static final long serialVersionUID = -3887098449470714236L;


  private Long id;

  private User modifiedBy;

  private CenterParameter centerParameter;
  private User createdBy;
  private Center researchCenter;
  private GlobalUnit globalUnit;
  private String value;
  private boolean active;
  private Date activeSince;
  private String modificationJustification;

  public CenterCustomParameter() {
  }

  public Date getActiveSince() {
    return activeSince;
  }


  public CenterParameter getCenterParameter() {
    return centerParameter;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public GlobalUnit getGlobalUnit() {
    return globalUnit;
  }


  public Long getId() {
    return id;
  }


  public String getModificationJustification() {
    return modificationJustification;
  }


  public User getModifiedBy() {
    return modifiedBy;
  }


  public Center getResearchCenter() {
    return researchCenter;
  }


  public String getValue() {
    return value;
  }


  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setCenterParameter(CenterParameter centerParameter) {
    this.centerParameter = centerParameter;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setGlobalUnit(GlobalUnit globalUnit) {
    this.globalUnit = globalUnit;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setResearchCenter(Center researchCenter) {
    this.researchCenter = researchCenter;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public String toString() {
    return "CenterCustomParameter [id=" + id + ", centerParameter=" + centerParameter + ", researchCenter="
      + researchCenter + "]";
  }


}

