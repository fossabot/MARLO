package org.cgiar.ccafs.marlo.data.model;
// Generated Dec 5, 2016 1:23:27 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectHighligthsCountry generated by hbm2java
 */
public class ProjectHighlightCountry extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 3040210263258419226L;

  private ProjectHighlight projectHighlight;

  @Expose
  private LocElement locElement;

  @Expose
  private Phase phase;


  public ProjectHighlightCountry() {
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
    ProjectHighlightCountry other = (ProjectHighlightCountry) obj;
    if (locElement == null) {
      if (other.locElement != null) {
        return false;
      }
    } else if (!locElement.getId().equals(other.locElement.getId())) {
      return false;
    }
    return true;
  }


  public LocElement getLocElement() {
    return locElement;
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

  public Phase getPhase() {
    return phase;
  }

  public ProjectHighlight getProjectHighligth() {
    return projectHighlight;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((locElement == null) ? 0 : locElement.hashCode());
    return result;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }

  @Override
  public void setModifiedBy(User modifiedBy) {

  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setProjectHighligth(ProjectHighlight projectHighlight) {
    this.projectHighlight = projectHighlight;
  }

  @Override
  public String toString() {
    return "ProjectHighlightCountry [id=" + this.getId() + ", projectHighlight=" + projectHighlight + ", locElement="
      + locElement + "]";
  }


}

