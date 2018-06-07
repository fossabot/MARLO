package org.cgiar.ccafs.marlo.data.model;
// Generated Dec 12, 2016 3:55:58 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CaseStudyProjects generated by hbm2java
 */
public class CaseStudyProject extends MarloBaseEntity implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = -1004953642319195188L;


  private CaseStudy caseStudy;

  @Expose
  private Project project;
  @Expose
  private boolean created;

  public CaseStudyProject() {
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
    CaseStudyProject other = (CaseStudyProject) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public CaseStudy getCaseStudy() {
    return caseStudy;
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


  public Project getProject() {
    return project;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  @Override
  public boolean isActive() {
    return true;
  }


  public boolean isCreated() {
    return created;
  }

  public void setCaseStudy(CaseStudy caseStudy) {
    this.caseStudy = caseStudy;
  }

  public void setCreated(boolean created) {
    this.created = created;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public String toString() {
    return "CaseStudyProject [id=" + this.getId() + ", caseStudy=" + caseStudy + ", project=" + project + "]";
  }


}

