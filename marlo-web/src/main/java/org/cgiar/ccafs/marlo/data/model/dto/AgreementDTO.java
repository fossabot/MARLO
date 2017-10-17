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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
  private FundingSourceAgreementDTO fundingSourcesAgreement;
  private List<PlaAgreementDTO> plasAgreements = new ArrayList<PlaAgreementDTO>(0);
  private List<CountryAgreementDTO> countriesAgreements = new ArrayList<CountryAgreementDTO>(0);

  private List<CrpAgreementDTO> crpsAgreements = new ArrayList<CrpAgreementDTO>(0);

  private boolean isNew;

  public String getContractStatus() {
    return contractStatus;
  }

  public List<CountryAgreementDTO> getCountriesAgreements() {
    return countriesAgreements;
  }

  public List<CrpAgreementDTO> getCrpsAgreements() {
    return crpsAgreements;
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


  public FundingSourceAgreementDTO getFundingSourcesAgreement() {
    return fundingSourcesAgreement;
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

  public List<PlaAgreementDTO> getPlasAgreements() {
    return plasAgreements;
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

  public boolean isNew() {
    return isNew;
  }

  public void setContractStatus(String contractStatus) {
    this.contractStatus = contractStatus;
  }

  public void setCountriesAgreements(List<CountryAgreementDTO> countriesAgreements) {
    this.countriesAgreements = countriesAgreements;
  }

  public void setCrpsAgreements(List<CrpAgreementDTO> crpsAgreements) {
    this.crpsAgreements = crpsAgreements;
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

  public void setFundingSourcesAgreement(FundingSourceAgreementDTO fundingSourcesAgreement) {
    this.fundingSourcesAgreement = fundingSourcesAgreement;
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


  public void setNew(boolean isNew) {
    this.isNew = isNew;
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


  public void setPlasAgreements(List<PlaAgreementDTO> plasAgreements) {
    this.plasAgreements = plasAgreements;
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
