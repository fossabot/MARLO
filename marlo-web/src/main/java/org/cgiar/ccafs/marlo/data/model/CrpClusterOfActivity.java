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
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpClusterOfActivity generated by hbm2java
 */
public class CrpClusterOfActivity implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = -6392709700346014366L;

  @Expose
  private Long id;

  @Expose
  private CrpProgram crpProgram;

  @Expose
  private String description;

  @Expose
  private boolean active;

  @Expose
  private User createdBy;

  @Expose
  private Date activeSince;

  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  private List<CrpClusterActivityLeader> leaders;

  private Set<CrpClusterActivityLeader> crpClusterActivityLeaders = new HashSet<CrpClusterActivityLeader>(0);

  public CrpClusterOfActivity() {
  }

  public CrpClusterOfActivity(Long id, CrpProgram crpProgram, String description) {
    this.id = id;
    this.crpProgram = crpProgram;
    this.description = description;
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
    CrpClusterOfActivity other = (CrpClusterOfActivity) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Date getActiveSince() {
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public Set<CrpClusterActivityLeader> getCrpClusterActivityLeaders() {
    return crpClusterActivityLeaders;
  }

  public CrpProgram getCrpProgram() {
    return this.crpProgram;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public List<CrpClusterActivityLeader> getLeaders() {
    return leaders;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
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

  public void setCrpClusterActivityLeaders(Set<CrpClusterActivityLeader> crpClusterActivityLeaders) {
    this.crpClusterActivityLeaders = crpClusterActivityLeaders;
  }

  public void setCrpProgram(CrpProgram crpProgram) {
    this.crpProgram = crpProgram;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLeaders(List<CrpClusterActivityLeader> leaders) {
    this.leaders = leaders;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


}

