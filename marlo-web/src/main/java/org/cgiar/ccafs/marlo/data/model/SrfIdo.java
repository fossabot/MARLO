/*****************************************************************
 * This file is part of CCAFS Planning and Reporting Platform.
 * CCAFS P&R is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS P&R is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS P&R. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.ccafs.marlo.data.model;
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * SrfIdo generated by hbm2java
 */
public class SrfIdo implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 8353586687845581811L;

  @Expose
  private Long id;

  @Expose
  private SrfCrossCuttingIssue srfCrossCuttingIssue;

  @Expose
  private String description;

  @Expose
  private boolean isCrossCutting;


  private Set<SrfSloIdo> srfSloIdos = new HashSet<SrfSloIdo>(0);

  private Set<SrfSubIdo> srfSubIdos = new HashSet<SrfSubIdo>(0);

  private boolean active;

  private User createdBy;

  private Date activeSince;
  private User modifiedBy;
  private String modificationJustification;

  public SrfIdo() {
  }

  public SrfIdo(SrfCrossCuttingIssue srfCrossCuttingIssues, String description, boolean isCrossCutting,
    Set<SrfSloIdo> srfSloIdos, Set<SrfSubIdo> srfSubIdos) {
    this.srfCrossCuttingIssue = srfCrossCuttingIssues;
    this.description = description;
    this.isCrossCutting = isCrossCutting;
    this.srfSloIdos = srfSloIdos;
    this.srfSubIdos = srfSubIdos;
  }

  public SrfIdo(String description, boolean isCrossCutting) {
    this.description = description;
    this.isCrossCutting = isCrossCutting;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getModificationJustification() {
    return modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public SrfCrossCuttingIssue getSrfCrossCuttingIssue() {
    return this.srfCrossCuttingIssue;
  }

  public Set<SrfSloIdo> getSrfSloIdos() {
    return this.srfSloIdos;
  }


  public Set<SrfSubIdo> getSrfSubIdos() {
    return this.srfSubIdos;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public boolean isIsCrossCutting() {
    return this.isCrossCutting;
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

  public void setIsCrossCutting(boolean isCrossCutting) {
    this.isCrossCutting = isCrossCutting;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setSrfCrossCuttingIssue(SrfCrossCuttingIssue srfCrossCuttingIssues) {
    this.srfCrossCuttingIssue = srfCrossCuttingIssues;
  }

  public void setSrfSloIdos(Set<SrfSloIdo> srfSloIdos) {
    this.srfSloIdos = srfSloIdos;
  }

  public void setSrfSubIdos(Set<SrfSubIdo> srfSubIdos) {
    this.srfSubIdos = srfSubIdos;
  }


}

