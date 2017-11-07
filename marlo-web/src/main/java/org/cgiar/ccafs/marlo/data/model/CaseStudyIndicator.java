package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 12, 2017 7:14:14 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CaseStudieIndicators generated by hbm2java
 */
public class CaseStudyIndicator implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -7193373165611989240L;

  @Expose
  private Long id;
  private CaseStudy caseStudy;
  @Expose
  private IpIndicator ipIndicator;

  public CaseStudyIndicator() {
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
    CaseStudyIndicator other = (CaseStudyIndicator) obj;
    if (caseStudy == null) {
      if (other.caseStudy != null) {
        return false;
      }
    } else if (!caseStudy.getId().equals(other.caseStudy.getId())) {
      return false;
    }
    if (ipIndicator == null) {
      if (other.ipIndicator != null) {
        return false;
      }
    } else if (!ipIndicator.getId().equals(other.ipIndicator.getId())) {
      return false;
    }
    return true;
  }

  public CaseStudy getCaseStudy() {
    return caseStudy;
  }

  @Override
  public Long getId() {
    return id;
  }

  public IpIndicator getIpIndicator() {
    return ipIndicator;
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


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((caseStudy == null) ? 0 : caseStudy.getId().hashCode());
    result = prime * result + ((ipIndicator == null) ? 0 : ipIndicator.getId().hashCode());
    return result;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public void setCaseStudy(CaseStudy caseStudy) {
    this.caseStudy = caseStudy;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public void setIpIndicator(IpIndicator ipIndicator) {
    this.ipIndicator = ipIndicator;
  }

  @Override
  public String toString() {
    return "CaseStudyIndicator [id=" + id + ", caseStudy=" + caseStudy + ", ipIndicator=" + ipIndicator + "]";
  }


}

