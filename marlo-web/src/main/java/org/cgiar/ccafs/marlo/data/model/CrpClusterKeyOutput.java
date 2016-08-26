package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 16, 2016 9:31:20 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpClusterKeyOutput generated by hbm2java
 */
public class CrpClusterKeyOutput implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -5642790359021262160L;


  @Expose
  private Long id;

  @Expose
  private CrpClusterOfActivity crpClusterOfActivity;

  @Expose
  private User modifiedBy;
  @Expose
  private User createdBy;
  @Expose
  private String keyOutput;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;

  private Set<Deliverable> deliverables = new HashSet<Deliverable>(0);

  public CrpClusterKeyOutput() {
  }

  public CrpClusterKeyOutput(CrpClusterOfActivity crpClusterOfActivity, User usersByModifiedBy, String keyOutput,
    boolean isActive, Date activeSince, String modificationJustification, Set<Deliverable> deliverables) {
    this.crpClusterOfActivity = crpClusterOfActivity;
    this.modifiedBy = usersByModifiedBy;
    this.keyOutput = keyOutput;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.deliverables = deliverables;
  }

  public CrpClusterKeyOutput(CrpClusterOfActivity crpClusterOfActivity, User usersByModifiedBy, User usersByCreatedBy,
    String keyOutput, boolean isActive, Date activeSince, String modificationJustification) {
    this.crpClusterOfActivity = crpClusterOfActivity;
    this.modifiedBy = usersByModifiedBy;
    this.createdBy = usersByCreatedBy;
    this.keyOutput = keyOutput;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
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
    CrpClusterKeyOutput other = (CrpClusterKeyOutput) obj;
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
    return this.activeSince;
  }


  public User getCreatedBy() {
    return this.createdBy;
  }

  public CrpClusterOfActivity getCrpClusterOfActivity() {
    return this.crpClusterOfActivity;
  }

  public Set<Deliverable> getDeliverables() {
    return deliverables;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public String getKeyOutput() {
    return this.keyOutput;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getModificationJustification() {
    return this.modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return this.modifiedBy;
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
    return this.active;
  }

  public void setActive(boolean isActive) {
    this.active = isActive;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setCreatedBy(User usersByCreatedBy) {
    this.createdBy = usersByCreatedBy;
  }

  public void setCrpClusterOfActivity(CrpClusterOfActivity crpClusterOfActivity) {
    this.crpClusterOfActivity = crpClusterOfActivity;
  }

  public void setDeliverables(Set<Deliverable> deliverables) {
    this.deliverables = deliverables;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setKeyOutput(String keyOutput) {
    this.keyOutput = keyOutput;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User usersByModifiedBy) {
    this.modifiedBy = usersByModifiedBy;
  }


}

