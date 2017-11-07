package org.cgiar.ccafs.marlo.data.model;
// Generated Dec 5, 2016 1:23:27 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectHighligthsCountry generated by hbm2java
 */
public class ProjectHighlightCountry implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 3040210263258419226L;

  @Expose
  private Integer id;

  private ProjectHighlight projectHighlight;

  @Expose
  private LocElement locElement;

  public ProjectHighlightCountry() {
  }

  public ProjectHighlightCountry(ProjectHighlight projectHighlight, LocElement locElement) {
    this.projectHighlight = projectHighlight;
    this.locElement = locElement;
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

  @Override
  public Integer getId() {
    return this.id;
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


  public void setId(Integer id) {
    this.id = id;
  }

  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }

  public void setProjectHighligth(ProjectHighlight projectHighlight) {
    this.projectHighlight = projectHighlight;
  }

  @Override
  public String toString() {
    return "ProjectHighlightCountry [id=" + id + ", projectHighlight=" + projectHighlight + ", locElement=" + locElement
      + "]";
  }


}

