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


package org.cgiar.ccafs.marlo.data.model.dto;

import java.util.Date;

/**
 * a simple DTO class for Agreement
 * 
 * @author Julián Rodríguez CCAFS/CIAT
 * @date 20171011
 */
public class AgreementDTO {

  private String id;
  private String description;
  private String shortTitle;
  private String objectives;
  private String donorId;
  private String donor;
  private String originalDonorId;
  private String originalDonor;
  private String researchId;
  private String reasearchName;
  private String grantAmmount;
  private Date startDate;
  private Date endDate;
  private Date extensionDate;
  private String contractStatus;
  private String fundingType;
  private Date syncDate;

  public String getContractStatus() {
    return contractStatus;
  }

  public String getDescription() {
    return description;
  }

  public String getDonor() {
    return donor;
  }

  public String getDonorId() {
    return donorId;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Date getExtensionDate() {
    return extensionDate;
  }

  public String getFundingType() {
    return fundingType;
  }

  public String getGrantAmmount() {
    return grantAmmount;
  }

  public String getId() {
    return id;
  }

  public String getObjectives() {
    return objectives;
  }

  public String getOriginalDonor() {
    return originalDonor;
  }

  public String getOriginalDonorId() {
    return originalDonorId;
  }

  public String getReasearchName() {
    return reasearchName;
  }

  public String getResearchId() {
    return researchId;
  }

  public String getShortTitle() {
    return shortTitle;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getSyncDate() {
    return syncDate;
  }

  public void setContractStatus(String contractStatus) {
    this.contractStatus = contractStatus;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDonor(String donor) {
    this.donor = donor;
  }

  public void setDonorId(String donorId) {
    this.donorId = donorId;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setExtensionDate(Date extensionDate) {
    this.extensionDate = extensionDate;
  }

  public void setFundingType(String fundingType) {
    this.fundingType = fundingType;
  }

  public void setGrantAmmount(String grantAmmount) {
    this.grantAmmount = grantAmmount;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setObjectives(String objectives) {
    this.objectives = objectives;
  }

  public void setOriginalDonor(String originalDonor) {
    this.originalDonor = originalDonor;
  }

  public void setOriginalDonorId(String originalDonorId) {
    this.originalDonorId = originalDonorId;
  }

  public void setReasearchName(String reasearchName) {
    this.reasearchName = reasearchName;
  }

  public void setResearchId(String researchId) {
    this.researchId = researchId;
  }

  public void setShortTitle(String shortTitle) {
    this.shortTitle = shortTitle;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setSyncDate(Date syncDate) {
    this.syncDate = syncDate;
  }


}
