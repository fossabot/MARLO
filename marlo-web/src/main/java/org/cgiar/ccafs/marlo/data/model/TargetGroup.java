package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated Jun 1, 2017 10:19:53 AM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * TargetGroups generated by hbm2java
 */
public class TargetGroup implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Expose
  private Long id;

  @Expose
  private String name;

  @Expose
  private String description;

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

  public TargetGroup() {
  }

  public TargetGroup(String name, String description, User modifiedBy, User createdBy, boolean active, Date activeSince,
    String modificationJustification) {
    this.name = name;
    this.description = description;
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    final StringBuilder sb = new StringBuilder();
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


  public String getName() {
    return this.name;
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


  public void setDescription(String description) {
    this.description = description;
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

  public void setName(String name) {
    this.name = name;
  }


}
