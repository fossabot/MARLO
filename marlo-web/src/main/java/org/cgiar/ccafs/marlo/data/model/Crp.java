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
// Generated May 17, 2016 9:53:46 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * Crps generated by hbm2java
 */
public class Crp implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 6299077992797380051L;


  @Expose
  private Long id;


  @Expose
  private String name;


  @Expose
  private String acronym;

  private Set<CrpUser> crpUsers = new HashSet<CrpUser>(0);


  private Set<Role> roles = new HashSet<Role>(0);


  private Set<CrpProgram> crpPrograms = new HashSet<CrpProgram>(0);

  private Set<CrpsSiteIntegration> crpsSitesIntegrations = new HashSet<CrpsSiteIntegration>(0);


  private Set<LocElementType> locElementTypes = new HashSet<LocElementType>(0);


  private Set<LiaisonUser> liasonUsers = new HashSet<LiaisonUser>(0);


  private Set<LiaisonInstitution> liaisonInstitutions = new HashSet<LiaisonInstitution>(0);

  private Set<CustomParameter> customParameters = new HashSet<CustomParameter>(0);
  private List<CustomParameter> parameters;


  private Set<CrpSubIdosContribution> crpSubIdosContributions = new HashSet<CrpSubIdosContribution>(0);


  private Set<FundingSource> fundingSources = new HashSet<FundingSource>(0);

  private Set<CrpTargetUnit> crpTargetUnits = new HashSet<CrpTargetUnit>(0);


  private Set<Deliverable> deliverables = new HashSet<Deliverable>(0);


  private Set<CrpLocElementType> crpLocElementTypes = new HashSet<CrpLocElementType>(0);


  private List<Deliverable> deliverablesList;

  private Set<GenderType> genderTypes = new HashSet<GenderType>(0);


  @Expose
  private boolean active;

  @Expose
  private boolean marlo;


  @Expose
  private User createdBy;

  @Expose
  private Date activeSince;


  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  private Set<CrpPpaPartner> crpPpaPartners = new HashSet<CrpPpaPartner>(0);


  private Set<LocElement> locElements = new HashSet<LocElement>(0);


  private List<UserRole> programManagmenTeam;


  private List<CrpPpaPartner> crpInstitutionsPartners;


  private List<CrpsSiteIntegration> siteIntegrations;

  private List<LocElementType> locationElementTypes;

  private List<LocElementType> locationCustomElementTypes;

  private Set<Project> projects = new HashSet<Project>(0);

  private boolean hasRegions;

  private List<TargetUnitSelect> targetUnits;

  private List<CustomLevelSelect> customLevels;


  public Crp() {
  }

  public Crp(String name) {
    this.name = name;
  }


  public Crp(String name, String acronym, Set<CrpUser> crpUsers, Set<Role> roles) {
    this.name = name;
    this.acronym = acronym;
    this.crpUsers = crpUsers;
    this.roles = roles;
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
    Crp other = (Crp) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public String getAcronym() {
    return acronym;
  }


  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public List<CrpPpaPartner> getCrpInstitutionsPartners() {
    return crpInstitutionsPartners;
  }

  public Set<CrpLocElementType> getCrpLocElementTypes() {
    return crpLocElementTypes;
  }

  public Set<CrpPpaPartner> getCrpPpaPartners() {
    return crpPpaPartners;
  }

  public Set<CrpProgram> getCrpPrograms() {
    return crpPrograms;
  }


  public Set<CrpsSiteIntegration> getCrpsSitesIntegrations() {
    return crpsSitesIntegrations;
  }


  public Set<CrpSubIdosContribution> getCrpSubIdosContributions() {
    return crpSubIdosContributions;
  }


  public Set<CrpTargetUnit> getCrpTargetUnits() {
    return crpTargetUnits;
  }


  public Set<CrpUser> getCrpUsers() {
    return this.crpUsers;
  }

  public List<CustomLevelSelect> getCustomLevels() {
    return customLevels;
  }


  public Set<CustomParameter> getCustomParameters() {
    return customParameters;
  }

  public Set<Deliverable> getDeliverables() {
    return deliverables;
  }

  public List<Deliverable> getDeliverablesList() {
    return deliverablesList;
  }

  public Set<FundingSource> getFundingSources() {
    return fundingSources;
  }

  public Set<GenderType> getGenderTypes() {
    return genderTypes;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public Set<LiaisonInstitution> getLiaisonInstitutions() {
    return liaisonInstitutions;
  }

  public Set<LiaisonUser> getLiasonUsers() {
    return liasonUsers;
  }

  public List<LocElementType> getLocationCustomElementTypes() {
    return locationCustomElementTypes;
  }

  public List<LocElementType> getLocationElementTypes() {
    return locationElementTypes;
  }

  public Set<LocElement> getLocElements() {
    return locElements;
  }

  public Set<LocElementType> getLocElementTypes() {
    return locElementTypes;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId()).append("Name : ").append(this.getName());
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


  public String getName() {
    return this.name;
  }


  public List<CustomParameter> getParameters() {
    return parameters;
  }

  public List<UserRole> getProgramManagmenTeam() {
    return programManagmenTeam;
  }


  public Set<Project> getProjects() {
    return projects;
  }

  public Set<Role> getRoles() {
    return this.roles;
  }

  public List<CrpsSiteIntegration> getSiteIntegrations() {
    return siteIntegrations;
  }

  public List<TargetUnitSelect> getTargetUnits() {
    return targetUnits;
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

  public boolean isHasRegions() {
    return hasRegions;
  }


  public boolean isMarlo() {
    return marlo;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
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

  public void setCrpInstitutionsPartners(List<CrpPpaPartner> crpInstitutionsPartners) {
    this.crpInstitutionsPartners = crpInstitutionsPartners;
  }


  public void setCrpLocElementTypes(Set<CrpLocElementType> crpLocElementTypes) {
    this.crpLocElementTypes = crpLocElementTypes;
  }

  public void setCrpPpaPartners(Set<CrpPpaPartner> crpPpaPartners) {
    this.crpPpaPartners = crpPpaPartners;
  }


  public void setCrpPrograms(Set<CrpProgram> crpPrograms) {
    this.crpPrograms = crpPrograms;
  }

  public void setCrpsSitesIntegrations(Set<CrpsSiteIntegration> crpsSitesIntegrations) {
    this.crpsSitesIntegrations = crpsSitesIntegrations;
  }

  public void setCrpSubIdosContributions(Set<CrpSubIdosContribution> crpSubIdosContributions) {
    this.crpSubIdosContributions = crpSubIdosContributions;
  }

  public void setCrpTargetUnits(Set<CrpTargetUnit> crpTargetUnits) {
    this.crpTargetUnits = crpTargetUnits;
  }

  public void setCrpUsers(Set<CrpUser> crpUsers) {
    this.crpUsers = crpUsers;
  }


  public void setCustomLevels(List<CustomLevelSelect> customLevels) {
    this.customLevels = customLevels;
  }


  public void setCustomParameters(Set<CustomParameter> customParameters) {
    this.customParameters = customParameters;
  }


  public void setDeliverables(Set<Deliverable> deliverables) {
    this.deliverables = deliverables;
  }


  public void setDeliverablesList(List<Deliverable> deliverablesList) {
    this.deliverablesList = deliverablesList;
  }

  public void setFundingSources(Set<FundingSource> fundingSources) {
    this.fundingSources = fundingSources;
  }

  public void setGenderTypes(Set<GenderType> genderTypes) {
    this.genderTypes = genderTypes;
  }


  public void setHasRegions(boolean hasRegions) {
    this.hasRegions = hasRegions;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setLiaisonInstitutions(Set<LiaisonInstitution> liasonInstituions) {
    this.liaisonInstitutions = liasonInstituions;
  }


  public void setLiasonUsers(Set<LiaisonUser> liasonUsers) {
    this.liasonUsers = liasonUsers;
  }


  public void setLocationCustomElementTypes(List<LocElementType> locationCustomElementTypes) {
    this.locationCustomElementTypes = locationCustomElementTypes;
  }


  public void setLocationElementTypes(List<LocElementType> locationElementTypes) {
    this.locationElementTypes = locationElementTypes;
  }


  public void setLocElements(Set<LocElement> locElementses) {
    this.locElements = locElementses;
  }


  public void setLocElementTypes(Set<LocElementType> locElementTypes) {
    this.locElementTypes = locElementTypes;
  }


  public void setMarlo(boolean marlo) {
    this.marlo = marlo;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setName(String name) {
    this.name = name;
  }


  public void setParameters(List<CustomParameter> parameters) {
    this.parameters = parameters;
  }

  public void setProgramManagmenTeam(List<UserRole> programManagmenTeam) {
    this.programManagmenTeam = programManagmenTeam;
  }

  public void setProjects(Set<Project> projects) {
    this.projects = projects;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }


  public void setSiteIntegrations(List<CrpsSiteIntegration> siteIntegrations) {
    this.siteIntegrations = siteIntegrations;
  }

  public void setTargetUnits(List<TargetUnitSelect> targetUnits) {
    this.targetUnits = targetUnits;
  }


  @Override
  public String toString() {
    return id.toString();
  }

}

