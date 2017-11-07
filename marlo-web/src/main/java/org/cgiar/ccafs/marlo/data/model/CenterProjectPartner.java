package org.cgiar.ccafs.marlo.data.model;
// Generated Mar 28, 2017 11:24:30 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CenterProjectPartner generated by hbm2java
 */
public class CenterProjectPartner implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -564182537522358471L;

  @Expose
  private Long id;


  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;

  @Expose
  private Institution institution;


  @Expose
  private CenterProject project;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  private Set<CenterProjectPartnerPerson> projectPartnerPersons = new HashSet<CenterProjectPartnerPerson>(0);

  private List<CenterProjectPartnerPerson> users;

  public CenterProjectPartner() {
  }

  public CenterProjectPartner(Institution institution, CenterProject project, boolean active) {
    this.institution = institution;
    this.project = project;
    this.active = active;
  }

  public CenterProjectPartner(User modifiedBy, User createdBy, Institution institution, CenterProject project,
    boolean active, Date activeSince, String modificationJustification,

    Set<CenterProjectPartnerPerson> projectPartnerPersons) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.institution = institution;
    this.project = project;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.projectPartnerPersons = projectPartnerPersons;
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
    CenterProjectPartner other = (CenterProjectPartner) obj;
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


  @Override
  public Long getId() {
    return id;
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
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public CenterProject getProject() {
    return project;
  }


  public Set<CenterProjectPartnerPerson> getProjectPartnerPersons() {
    return projectPartnerPersons;
  }


  public List<CenterProjectPartnerPerson> getUsers() {
    return users;
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

  public void setProject(CenterProject project) {
    this.project = project;
  }


  public void setProjectPartnerPersons(Set<CenterProjectPartnerPerson> projectPartnerPersons) {
    this.projectPartnerPersons = projectPartnerPersons;
  }

  public void setUsers(List<CenterProjectPartnerPerson> users) {
    this.users = users;
  }


  @Override
  public String toString() {
    return "CenterProjectPartner [id=" + id + ", institution=" + institution + ", project=" + project + "]";
  }

}

