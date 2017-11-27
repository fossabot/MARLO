package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * IpProgramElements generated by hbm2java
 */
public class IpProgramElement implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -9147641495865825269L;

  @Expose
  private Long id;


  @Expose
  private IpElement ipElement;

  @Expose
  private IpProgramElementRelationType ipProgramElementRelationType;

  @Expose
  private IpProgram ipProgram;

  private Set<IpIndicator> ipIndicators = new HashSet<IpIndicator>(0);


  public IpProgramElement() {
  }


  public IpProgramElement(IpElement ipElement, IpProgram ipProgram) {
    this.ipElement = ipElement;
    this.ipProgram = ipProgram;
  }


  @Override
  public Long getId() {
    return id;
  }


  public IpElement getIpElement() {
    return ipElement;
  }


  public Set<IpIndicator> getIpIndicators() {
    return ipIndicators;
  }


  public IpProgram getIpProgram() {
    return ipProgram;
  }

  public IpProgramElementRelationType getIpProgramElementRelationType() {
    return ipProgramElementRelationType;
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

  public void setId(Long id) {
    this.id = id;
  }


  public void setIpElement(IpElement ipElement) {
    this.ipElement = ipElement;
  }

  public void setIpIndicators(Set<IpIndicator> ipIndicators) {
    this.ipIndicators = ipIndicators;
  }

  public void setIpProgram(IpProgram ipProgram) {
    this.ipProgram = ipProgram;
  }

  public void setIpProgramElementRelationType(IpProgramElementRelationType ipProgramElementRelationType) {
    this.ipProgramElementRelationType = ipProgramElementRelationType;
  }


  @Override
  public String toString() {
    return "IpProgramElement [id=" + id + ", ipElement=" + ipElement + ", ipProgramElementRelationType="
      + ipProgramElementRelationType + ", ipProgram=" + ipProgram + "]";
  }

}

