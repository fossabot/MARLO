package org.cgiar.ccafs.marlo.data.model;

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CapdevHighestDegree generated by hbm2java
 */
public class CapdevHighestDegree implements java.io.Serializable, IAuditLog {


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
  private String acronym;
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

  public CapdevHighestDegree() {
  }


  public CapdevHighestDegree(Long id) {
    this.id = id;
  }

  public CapdevHighestDegree(Long id, String name, String description, String acronym, User modifiedBy, User createdBy,
    boolean active, Date activeSince, String modificationJustification) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.acronym = acronym;
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }

  public String getAcronym() {
    return this.acronym;
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


  public void setAcronym(String acronym) {
    this.acronym = acronym;
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
