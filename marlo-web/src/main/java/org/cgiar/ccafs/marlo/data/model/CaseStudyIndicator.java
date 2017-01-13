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


}

