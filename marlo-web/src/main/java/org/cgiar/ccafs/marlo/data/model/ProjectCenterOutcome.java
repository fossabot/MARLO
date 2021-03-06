package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 11, 2018 10:23:20 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectCenterOutcome generated by hbm2java
 */
public class ProjectCenterOutcome extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -6667846324243117944L;


  @Expose
  private CenterOutcome centerOutcome;


  @Expose
  private Phase phase;


  @Expose
  private Project project;


  public ProjectCenterOutcome() {
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
    ProjectCenterOutcome other = (ProjectCenterOutcome) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public CenterOutcome getCenterOutcome() {
    return centerOutcome;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public Phase getPhase() {
    return phase;
  }

  public Project getProject() {
    return project;
  }

  public void setCenterOutcome(CenterOutcome centerOutcome) {
    this.centerOutcome = centerOutcome;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProject(Project project) {
    this.project = project;
  }


}

