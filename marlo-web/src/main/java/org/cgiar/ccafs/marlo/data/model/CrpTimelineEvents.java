package org.cgiar.ccafs.marlo.data.model;
// Generated Oct 27, 2016 3:39:29 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CrpTimelineEvents
 * 
 * @author avalencia - CCAFS
 * @date Nov 21, 2017
 * @time 10:37:06 AM: Creation of model
 */
public class CrpTimelineEvents implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 7236090753934711367L;

  @Expose
  private Long id;

  @Expose
  private Date startDate;

  @Expose
  private Date endDate;

  @Expose
  private String description;

  @Expose
  private String responsible;

  @Expose
  private Crp crp;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private User createdBy;

  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  public CrpTimelineEvents() {
  }


  public CrpTimelineEvents(User modifiedBy, boolean active, Date activeSince, String modificationJustification) {
    this.modifiedBy = modifiedBy;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }


  public CrpTimelineEvents(User modifiedBy, User createdBy, String description, String responsible, Date startDate,
    Date endDate, boolean active, Date activeSince, String modificationJustification, Crp crp) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.description = description;
    this.responsible = responsible;
    this.startDate = startDate;
    this.endDate = endDate;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.crp = crp;
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
    CrpTimelineEvents other = (CrpTimelineEvents) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }


  public Crp getCrp() {
    return crp;
  }


  public String getDescription() {
    return description;
  }


  public Date getEndDate() {
    return endDate;
  }

  @Override
  public Long getId() {
    return id;
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

  public String getResponsible() {
    return responsible;
  }

  public Date getStartDate() {
    return startDate;
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

  public void setCrp(Crp crp) {
    this.crp = crp;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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

  public void setResponsible(String responsible) {
    this.responsible = responsible;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Override
  public String toString() {
    return "CrpTimelineEvents [id=" + id + ", start_date=" + startDate + ", end_date=" + endDate + ", description="
      + description + ", responsible=" + responsible + ", CRP=" + crp + "]";
  }
}

