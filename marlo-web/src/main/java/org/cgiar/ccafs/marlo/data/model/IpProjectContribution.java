package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * IpProjectContributions generated by hbm2java
 */
public class IpProjectContribution implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1353643495837176430L;
  @Expose
  private Long id;
  @Expose
  private IpElement ipElementByMogId;
  @Expose
  private IpElement ipElementByMidOutcomeId;
  private Project project;
  @Expose
  private User modifiedBy;
  @Expose
  private User createdBy;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;


  public IpProjectContribution() {
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  @Override
  public Long getId() {
    return id;
  }


  public IpElement getIpElementByMidOutcomeId() {
    return ipElementByMidOutcomeId;
  }


  public IpElement getIpElementByMogId() {
    return ipElementByMogId;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  @Override
  public String getModificationJustification() {
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public Project getProject() {
    return project;
  }


  @Override
  public boolean isActive() {
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIpElementByMidOutcomeId(IpElement ipElementByMidOutcomeId) {
    this.ipElementByMidOutcomeId = ipElementByMidOutcomeId;
  }

  public void setIpElementByMogId(IpElement ipElementByMogId) {
    this.ipElementByMogId = ipElementByMogId;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setProject(Project project) {
    this.project = project;
  }


}

