package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * IpProjectContributions generated by hbm2java
 */
public class IpProjectContribution extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = 1353643495837176430L;

  @Expose
  private IpElement ipElementByMogId;
  @Expose
  private IpElement ipElementByMidOutcomeId;
  private Project project;

  public IpProjectContribution() {
  }


  public IpElement getIpElementByMidOutcomeId() {
    return ipElementByMidOutcomeId;
  }


  public IpElement getIpElementByMogId() {
    return ipElementByMogId;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public Project getProject() {
    return project;
  }


  public void setIpElementByMidOutcomeId(IpElement ipElementByMidOutcomeId) {
    this.ipElementByMidOutcomeId = ipElementByMidOutcomeId;
  }


  public void setIpElementByMogId(IpElement ipElementByMogId) {
    this.ipElementByMogId = ipElementByMogId;
  }

  public void setProject(Project project) {
    this.project = project;
  }


  @Override
  public String toString() {
    return "IpProjectContribution [id=" + this.getId() + ", ipElementByMogId=" + ipElementByMogId
      + ", ipElementByMidOutcomeId=" + ipElementByMidOutcomeId + ", project=" + project + "]";
  }


}

