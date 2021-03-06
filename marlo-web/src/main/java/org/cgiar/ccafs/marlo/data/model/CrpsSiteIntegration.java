/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.ccafs.marlo.data.model;
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpsSiteIntegration generated by hbm2java
 */
public class CrpsSiteIntegration extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -7674530393075404476L;


  @Expose
  private GlobalUnit crp;


  @Expose
  private LocElement locElement;


  private Set<CrpSitesLeader> crpSitesLeaders = new HashSet<CrpSitesLeader>(0);

  @Expose
  private boolean regional;


  private List<String> programName;

  private List<CrpSitesLeader> siteLeaders;

  private List<Project> projects;

  private List<FundingSource> fundingSources;


  public CrpsSiteIntegration() {
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
    CrpsSiteIntegration other = (CrpsSiteIntegration) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public GlobalUnit getCrp() {
    return crp;
  }

  public Set<CrpSitesLeader> getCrpSitesLeaders() {
    return this.crpSitesLeaders;
  }

  public List<FundingSource> getFundingSources() {
    return fundingSources;
  }

  public LocElement getLocElement() {
    return this.locElement;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public List<String> getProgramName() {
    return programName;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public List<CrpSitesLeader> getSiteLeaders() {
    return siteLeaders;
  }


  public boolean isRegional() {
    return regional;
  }

  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }

  public void setCrpSitesLeaders(Set<CrpSitesLeader> crpSitesLeaders) {
    this.crpSitesLeaders = crpSitesLeaders;
  }

  public void setFundingSources(List<FundingSource> flagships) {
    this.fundingSources = flagships;
  }

  public void setLocElement(LocElement locElements) {
    this.locElement = locElements;
  }

  public void setProgramName(List<String> programName) {
    this.programName = programName;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public void setRegional(boolean regional) {
    this.regional = regional;
  }

  public void setSiteLeaders(List<CrpSitesLeader> siteLeaders) {
    this.siteLeaders = siteLeaders;
  }

  @Override
  public String toString() {
    return "CrpsSiteIntegration [id=" + this.getId() + ", crp=" + crp + ", locElement=" + locElement + ", regional="
      + regional + ", programName=" + programName + "]";
  }

}

