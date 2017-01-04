package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * IpIndicators generated by hbm2java
 */
public class IpIndicator implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 4916518235319402584L;
  @Expose
  private Long id;
  @Expose
  private IpElement ipElement;
  @Expose
  private IpIndicator ipIndicator;
  @Expose
  private IpProgramElement ipProgramElement;
  @Expose
  private User modifiedBy;
  @Expose
  private User createdBy;
  @Expose
  private String description;
  @Expose
  private String target;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;
  private Set<OutcomeSynthesy> outcomeSynthesis = new HashSet<OutcomeSynthesy>(0);
  private Set<IpIndicator> ipIndicators = new HashSet<IpIndicator>(0);
  private Set<IpProjectIndicator> ipProjectIndicators = new HashSet<IpProjectIndicator>(0);

  public IpIndicator() {
  }


  public IpIndicator(IpElement ipElement, User usersByModifiedBy, User usersByCreatedBy, boolean isActive,
    Date activeSince, String modificationJustification) {
    this.ipElement = ipElement;
    this.modifiedBy = usersByModifiedBy;
    this.createdBy = usersByCreatedBy;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public String getDescription() {
    return description;
  }


  @Override
  public Long getId() {
    return id;
  }


  public IpElement getIpElement() {
    return ipElement;
  }


  public IpIndicator getIpIndicator() {
    return ipIndicator;
  }


  public Set<IpIndicator> getIpIndicators() {
    return ipIndicators;
  }


  public IpProgramElement getIpProgramElement() {
    return ipProgramElement;
  }


  public Set<IpProjectIndicator> getIpProjectIndicators() {
    return ipProjectIndicators;
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


  public Set<OutcomeSynthesy> getOutcomeSynthesis() {
    return outcomeSynthesis;
  }


  public String getTarget() {
    return target;
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


  public void setDescription(String description) {
    this.description = description;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIpElement(IpElement ipElement) {
    this.ipElement = ipElement;
  }


  public void setIpIndicator(IpIndicator ipIndicator) {
    this.ipIndicator = ipIndicator;
  }


  public void setIpIndicators(Set<IpIndicator> ipIndicators) {
    this.ipIndicators = ipIndicators;
  }


  public void setIpProgramElement(IpProgramElement ipProgramElement) {
    this.ipProgramElement = ipProgramElement;
  }


  public void setIpProjectIndicators(Set<IpProjectIndicator> ipProjectIndicators) {
    this.ipProjectIndicators = ipProjectIndicators;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setOutcomeSynthesis(Set<OutcomeSynthesy> outcomeSynthesis) {
    this.outcomeSynthesis = outcomeSynthesis;
  }


  public void setTarget(String target) {
    this.target = target;
  }


}

