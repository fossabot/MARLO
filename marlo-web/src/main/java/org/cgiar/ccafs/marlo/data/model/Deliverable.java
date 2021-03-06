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

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class Deliverable extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 1867818669925473872L;


  @Expose
  private Project project;


  @Expose
  private Date createDate;


  @Expose
  private GlobalUnit crp;


  @Expose
  private Phase phase;

  @Expose
  private Boolean isPublication;

  private DeliverableInfo deliverableInfo;

  private DeliverableDissemination dissemination;


  private DeliverableQualityCheck qualityCheck;


  private Set<DeliverableGenderLevel> deliverableGenderLevels = new HashSet<DeliverableGenderLevel>(0);


  private List<DeliverableGenderLevel> genderLevels;

  private Set<DeliverablePartnership> deliverablePartnerships = new HashSet<DeliverablePartnership>(0);


  private Set<DeliverableActivity> deliverableActivities = new HashSet<DeliverableActivity>(0);


  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);


  private DeliverablePartnership responsiblePartner;


  private Set<DeliverableFundingSource> deliverableFundingSources = new HashSet<DeliverableFundingSource>(0);


  private List<DeliverableFundingSource> fundingSources;


  private List<DeliverablePartnership> otherPartners;
  private Set<DeliverableQualityCheck> deliverableQualityChecks = new HashSet<DeliverableQualityCheck>(0);
  private Set<DeliverableMetadataElement> deliverableMetadataElements = new HashSet<DeliverableMetadataElement>(0);
  private Set<DeliverableDissemination> deliverableDisseminations = new HashSet<DeliverableDissemination>(0);
  private Set<DeliverableDataSharingFile> deliverableDataSharingFiles = new HashSet<DeliverableDataSharingFile>(0);
  private Set<DeliverablePublicationMetadata> deliverablePublicationMetadatas =
    new HashSet<DeliverablePublicationMetadata>(0);
  private Set<DeliverableDataSharing> deliverableDataSharings = new HashSet<DeliverableDataSharing>(0);
  private List<DeliverableMetadataElement> metadataElements;


  private List<DeliverableDissemination> disseminations;


  private List<DeliverableDataSharingFile> dataSharingFiles;


  private List<DeliverableFile> files;


  private List<DeliverablePublicationMetadata> publicationMetadatas;


  private Set<DeliverableLocation> deliverableLocations = new HashSet<DeliverableLocation>(0);


  private List<String> countriesIds = new ArrayList<>();


  private List<DeliverableLocation> countries;


  private String countriesIdsText;

  private DeliverablePublicationMetadata publication;
  private List<DeliverableDataSharing> dataSharing;
  private Set<DeliverableProgram> deliverablePrograms = new HashSet<DeliverableProgram>(0);
  private Set<DeliverableLeader> deliverableLeaders = new HashSet<DeliverableLeader>(0);

  private List<DeliverableProgram> programs;
  private List<DeliverableProgram> regions;


  private String flagshipValue;


  private String regionsValue;


  private List<DeliverableLeader> leaders;


  private List<MetadataElement> metadata = new ArrayList<>();


  private Set<DeliverableCrp> deliverableCrps = new HashSet<DeliverableCrp>(0);


  private List<DeliverableCrp> crps;


  private Set<DeliverableUser> deliverableUsers = new HashSet<DeliverableUser>(0);


  private Set<DeliverableInfo> deliverableInfos = new HashSet<DeliverableInfo>(0);

  private List<DeliverableUser> users;
  private Set<DeliverableIntellectualAsset> deliverableIntellectualAssets =
    new HashSet<DeliverableIntellectualAsset>(0);
  private DeliverableIntellectualAsset intellectualAsset;
  private Set<DeliverableParticipant> deliverableParticipants = new HashSet<DeliverableParticipant>(0);
  private DeliverableParticipant deliverableParticipant;
  private Set<DeliverableGeographicRegion> deliverableGeographicRegions = new HashSet<DeliverableGeographicRegion>(0);
  private List<DeliverableGeographicRegion> deliverableRegions;

  public Deliverable() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    Deliverable other = (Deliverable) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public String getComposedName() {
    if (this.getDeliverableInfo() != null) {
      try {
        return "<b> (D" + this.getId() + ") " + this.getDeliverableInfo().getDeliverableType().getName() + "</b> - "
          + this.getDeliverableInfo().getTitle();
      } catch (Exception e) {
        return "<b> (D" + this.getId() + ") </b> - " + this.getDeliverableInfo().getTitle();

      }
    }
    return null;

  }

  public List<DeliverableLocation> getCountries() {
    return countries;
  }

  public List<String> getCountriesIds() {
    return countriesIds;
  }

  public String getCountriesIdsText() {
    return countriesIdsText;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public GlobalUnit getCrp() {
    return crp;
  }

  public List<DeliverableCrp> getCrps() {
    return crps;
  }

  public List<DeliverableDataSharing> getDataSharing() {
    return dataSharing;
  }

  public List<DeliverableDataSharingFile> getDataSharingFiles() {
    return dataSharingFiles;
  }

  public Set<DeliverableActivity> getDeliverableActivities() {
    return deliverableActivities;
  }

  public Set<DeliverableCrp> getDeliverableCrps() {
    return deliverableCrps;
  }

  public Set<DeliverableDataSharingFile> getDeliverableDataSharingFiles() {
    return deliverableDataSharingFiles;
  }


  public Set<DeliverableDataSharing> getDeliverableDataSharings() {
    return deliverableDataSharings;
  }


  public Set<DeliverableDissemination> getDeliverableDisseminations() {
    return deliverableDisseminations;
  }


  public Set<DeliverableFundingSource> getDeliverableFundingSources() {
    return deliverableFundingSources;
  }


  public Set<DeliverableGenderLevel> getDeliverableGenderLevels() {
    return deliverableGenderLevels;
  }

  public Set<DeliverableGeographicRegion> getDeliverableGeographicRegions() {
    return deliverableGeographicRegions;
  }


  public DeliverableInfo getDeliverableInfo() {
    return deliverableInfo;
  }

  public DeliverableInfo getDeliverableInfo(Phase phase) {
    if (this.getDeliverableInfo() != null) {
      return this.getDeliverableInfo();
    } else {
      List<DeliverableInfo> infos =
        this.getDeliverableInfos().stream().filter(c -> c.getPhase() != null && c.getPhase().getId() != null
          && c.getPhase().getId().longValue() == phase.getId() && c.isActive()).collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setDeliverableInfo(infos.get(0));
        return this.getDeliverableInfo();
      }
    }

    return null;
  }

  public Set<DeliverableInfo> getDeliverableInfos() {
    return deliverableInfos;
  }

  public Set<DeliverableIntellectualAsset> getDeliverableIntellectualAssets() {
    return deliverableIntellectualAssets;
  }

  public Set<DeliverableLeader> getDeliverableLeaders() {
    return deliverableLeaders;
  }

  public Set<DeliverableLocation> getDeliverableLocations() {
    return deliverableLocations;
  }

  public Set<DeliverableMetadataElement> getDeliverableMetadataElements() {
    return deliverableMetadataElements;
  }

  public DeliverableParticipant getDeliverableParticipant() {
    return deliverableParticipant;
  }

  public Set<DeliverableParticipant> getDeliverableParticipants() {
    return deliverableParticipants;
  }

  public Set<DeliverablePartnership> getDeliverablePartnerships() {
    return deliverablePartnerships;
  }


  public Set<DeliverableProgram> getDeliverablePrograms() {
    return deliverablePrograms;
  }

  public Set<DeliverablePublicationMetadata> getDeliverablePublicationMetadatas() {
    return deliverablePublicationMetadatas;
  }

  public Set<DeliverableQualityCheck> getDeliverableQualityChecks() {
    return deliverableQualityChecks;
  }

  public List<DeliverableGeographicRegion> getDeliverableRegions() {
    return deliverableRegions;
  }

  public Set<DeliverableUser> getDeliverableUsers() {
    return deliverableUsers;
  }

  public DeliverableDissemination getDissemination() {
    return dissemination;
  }

  public List<DeliverableDissemination> getDisseminations() {
    return disseminations;
  }

  public List<DeliverableFile> getFiles() {
    return files;
  }

  public String getFlagshipValue() {
    return flagshipValue;
  }

  public List<DeliverableFundingSource> getFundingSources() {
    return fundingSources;
  }


  public List<DeliverableGenderLevel> getGenderLevels() {
    return genderLevels;
  }


  public long getID(int metadataID) {

    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              return dmetadata.getId().longValue();
            }
          }
        }


      }

    }

    return -1;
  }

  public DeliverableIntellectualAsset getIntellectualAsset() {
    return intellectualAsset;
  }

  public Boolean getIsPublication() {
    return isPublication;
  }


  public List<DeliverableLeader> getLeaders() {
    return leaders;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public long getMElementID(int metadataID) {
    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              return dmetadata.getId().longValue();
            }
          }
        }
      }
    }
    return -1;
  }


  public List<MetadataElement> getMetadata() {
    return metadata;
  }

  public DeliverableMetadataElement getMetadata(long metadataID) {
    String value = "";
    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              return dmetadata;
            }
          }
        }


      }
    }
    return null;
  }


  public List<DeliverableMetadataElement> getMetadataElements() {
    return metadataElements;
  }


  public long getMetadataID(String metadataName) {
    for (MetadataElement mData : metadata) {
      if (mData != null) {
        if (mData.getEcondedName().equals(metadataName)) {
          return mData.getId();
        }
      }
    }
    return -1;
  }


  public int getMetadataIndex(String metadataName) {
    int c = 0;
    for (MetadataElement mData : metadata) {
      if (mData != null) {
        if (mData.getEcondedName().equals(metadataName)) {
          return c;
        }
      }

      c++;
    }
    return -1;
  }


  public String getMetadataValue(long metadataID) {
    String value = "";
    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              value = dmetadata.getElementValue();
            }
          }
        }


      }
    }
    return value;
  }


  public String getMetadataValue(String metadataName) {
    if (metadataElements != null) {
      for (DeliverableMetadataElement mData : metadataElements) {
        if (mData != null) {
          if (mData.getMetadataElement() != null) {
            if (mData.getMetadataElement().getElement().equals(metadataName)) {
              return mData.getElementValue();
            }
          }
        }

      }
    }
    return "";
  }


  public String getMetadataValueByEncondedName(String metadataName) {
    if (metadataElements != null) {
      for (DeliverableMetadataElement mData : metadataElements) {
        if (mData != null) {
          if (mData.getMetadataElement() != null) {
            if (mData.getMetadataElement().getEcondedName().equals(metadataName)) {
              return mData.getElementValue();
            }
          }
        }

      }
    }
    return "";
  }


  public List<DeliverablePartnership> getOtherPartners() {
    return otherPartners;
  }


  public Phase getPhase() {
    return phase;
  }

  public List<DeliverableProgram> getPrograms() {
    return programs;
  }

  // Data List from Data Sharing


  public Project getProject() {
    return project;
  }

  public DeliverablePublicationMetadata getPublication() {
    return publication;
  }


  public List<DeliverablePublicationMetadata> getPublicationMetadatas() {
    return publicationMetadatas;
  }


  public DeliverableQualityCheck getQualityCheck() {
    return qualityCheck;
  }


  public List<DeliverableProgram> getRegions() {
    return regions;
  }


  public String getRegionsValue() {
    return regionsValue;
  }


  public DeliverablePartnership getResponsiblePartner() {
    return responsiblePartner;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }


  public List<DeliverableUser> getUsers() {
    return users;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }


  public void setCountries(List<DeliverableLocation> countries) {
    this.countries = countries;
  }

  public void setCountriesIds(List<String> countriesIds) {
    this.countriesIds = countriesIds;
  }

  public void setCountriesIdsText(String countriesIdsText) {
    this.countriesIdsText = countriesIdsText;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }

  public void setCrps(List<DeliverableCrp> crps) {
    this.crps = crps;
  }

  public void setDataSharing(List<DeliverableDataSharing> dataSharing) {
    this.dataSharing = dataSharing;
  }

  public void setDataSharingFiles(List<DeliverableDataSharingFile> dataSharingFiles) {
    this.dataSharingFiles = dataSharingFiles;
  }

  public void setDeliverableActivities(Set<DeliverableActivity> deliverableActivities) {
    this.deliverableActivities = deliverableActivities;
  }


  public void setDeliverableCrps(Set<DeliverableCrp> deliverableCrps) {
    this.deliverableCrps = deliverableCrps;
  }

  public void setDeliverableDataSharingFiles(Set<DeliverableDataSharingFile> deliverableDataSharingFiles) {
    this.deliverableDataSharingFiles = deliverableDataSharingFiles;
  }

  public void setDeliverableDataSharings(Set<DeliverableDataSharing> deliverableDataSharings) {
    this.deliverableDataSharings = deliverableDataSharings;
  }

  public void setDeliverableDisseminations(Set<DeliverableDissemination> deliverableDisseminations) {
    this.deliverableDisseminations = deliverableDisseminations;
  }

  public void setDeliverableFundingSources(Set<DeliverableFundingSource> deliverableFundingSources) {
    this.deliverableFundingSources = deliverableFundingSources;
  }


  public void setDeliverableGenderLevels(Set<DeliverableGenderLevel> deliverableGenderLevels) {
    this.deliverableGenderLevels = deliverableGenderLevels;
  }

  public void setDeliverableGeographicRegions(Set<DeliverableGeographicRegion> deliverableGeographicRegions) {
    this.deliverableGeographicRegions = deliverableGeographicRegions;
  }

  public void setDeliverableInfo(DeliverableInfo deliverableInfo) {
    this.deliverableInfo = deliverableInfo;
  }


  public void setDeliverableInfos(Set<DeliverableInfo> deliverableInfos) {
    this.deliverableInfos = deliverableInfos;
  }

  public void setDeliverableIntellectualAssets(Set<DeliverableIntellectualAsset> deliverableIntellectualAssets) {
    this.deliverableIntellectualAssets = deliverableIntellectualAssets;
  }


  public void setDeliverableLeaders(Set<DeliverableLeader> deliverableLeaders) {
    this.deliverableLeaders = deliverableLeaders;
  }


  public void setDeliverableLocations(Set<DeliverableLocation> deliverableLocations) {
    this.deliverableLocations = deliverableLocations;
  }


  public void setDeliverableMetadataElements(Set<DeliverableMetadataElement> deliverableMetadataElements) {
    this.deliverableMetadataElements = deliverableMetadataElements;
  }


  public void setDeliverableParticipant(DeliverableParticipant deliverableParticipant) {
    this.deliverableParticipant = deliverableParticipant;
  }


  public void setDeliverableParticipants(Set<DeliverableParticipant> deliverableParticipants) {
    this.deliverableParticipants = deliverableParticipants;
  }


  public void setDeliverablePartnerships(Set<DeliverablePartnership> deliverablePartnerships) {
    this.deliverablePartnerships = deliverablePartnerships;
  }

  public void setDeliverablePrograms(Set<DeliverableProgram> deliverablePrograms) {
    this.deliverablePrograms = deliverablePrograms;
  }

  public void setDeliverablePublicationMetadatas(Set<DeliverablePublicationMetadata> deliverablePublicationMetadatas) {
    this.deliverablePublicationMetadatas = deliverablePublicationMetadatas;
  }


  public void setDeliverableQualityChecks(Set<DeliverableQualityCheck> deliverableQualityChecks) {
    this.deliverableQualityChecks = deliverableQualityChecks;
  }


  public void setDeliverableRegions(List<DeliverableGeographicRegion> deliverableRegions) {
    this.deliverableRegions = deliverableRegions;
  }


  public void setDeliverableUsers(Set<DeliverableUser> deliverableUsers) {
    this.deliverableUsers = deliverableUsers;
  }

  public void setDissemination(DeliverableDissemination dissemination) {
    this.dissemination = dissemination;
  }


  public void setDisseminations(List<DeliverableDissemination> disseminations) {
    this.disseminations = disseminations;
  }


  public void setFiles(List<DeliverableFile> files) {
    this.files = files;
  }


  public void setFlagshipValue(String flagshipValue) {
    this.flagshipValue = flagshipValue;
  }


  public void setFundingSources(List<DeliverableFundingSource> fundingSources) {
    this.fundingSources = fundingSources;
  }

  public void setGenderLevels(List<DeliverableGenderLevel> genderLevels) {
    this.genderLevels = genderLevels;
  }

  public void setIntellectualAsset(DeliverableIntellectualAsset intellectualAsset) {
    this.intellectualAsset = intellectualAsset;
  }

  public void setIsPublication(Boolean isPublication) {
    this.isPublication = isPublication;
  }

  public void setLeaders(List<DeliverableLeader> leaders) {
    this.leaders = leaders;
  }

  public void setMetadata(List<MetadataElement> metadata) {
    this.metadata = metadata;
  }

  public void setMetadataElements(List<DeliverableMetadataElement> metadataElements) {
    this.metadataElements = metadataElements;
  }

  public void setOtherPartners(List<DeliverablePartnership> otherPartners) {
    this.otherPartners = otherPartners;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setPrograms(List<DeliverableProgram> programs) {
    this.programs = programs;
  }


  public void setProject(Project project) {
    this.project = project;
  }

  public void setPublication(DeliverablePublicationMetadata publication) {
    this.publication = publication;
  }

  public void setPublicationMetadatas(List<DeliverablePublicationMetadata> publicationMetadatas) {
    this.publicationMetadatas = publicationMetadatas;
  }


  public void setQualityCheck(DeliverableQualityCheck qualityCheck) {
    this.qualityCheck = qualityCheck;
  }


  public void setRegions(List<DeliverableProgram> regions) {
    this.regions = regions;
  }


  public void setRegionsValue(String regionsValue) {
    this.regionsValue = regionsValue;
  }


  public void setResponsiblePartner(DeliverablePartnership responsiblePartner) {
    this.responsiblePartner = responsiblePartner;
  }


  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setUsers(List<DeliverableUser> users) {
    this.users = users;
  }


  @Override
  public String toString() {
    return "Deliverable [id=" + this.getId() + ", project=" + project + ", active=" + this.isActive() + ", phase="
      + phase + ", isPublication=" + isPublication + ", deliverableInfo=" + deliverableInfo + "]";
  }


}
