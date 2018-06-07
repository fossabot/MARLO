package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 18, 2017 3:03:15 PM by Hibernate Tools 5.2.3.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;


/**
 * FundingSourceInfo generated by hbm2java
 */
public class PowbSynthesis extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 3684598637198995229L;

  @Expose
  private Phase phase;

  @Expose
  private LiaisonInstitution liaisonInstitution;


  @Expose
  private PowbToc powbToc;


  @Expose
  private PowbFlagshipPlans powbFlagshipPlans;


  @Expose
  private PowbEvidence powbEvidence;


  @Expose
  private PowbCrossCuttingDimension powbCrossCuttingDimension;


  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);


  private Set<PowbExpectedCrpProgress> powbExpectedCrpProgresses = new HashSet<PowbExpectedCrpProgress>(0);


  private List<PowbExpectedCrpProgress> expectedCrpProgresses;


  @Expose
  private PowbMonitoringEvaluationLearning powbMonitoringEvaluationLearning;


  @Expose
  private PowbCrpStaffing crpStaffing;


  private Set<PowbSynthesisCrpStaffingCategory> powbSynthesisCrpStaffingCategory =
    new HashSet<PowbSynthesisCrpStaffingCategory>(0);


  private List<PowbSynthesisCrpStaffingCategory> powbSynthesisCrpStaffingCategoryList;


  @Expose
  private PowbManagementRisk powbManagementRisk;


  @Expose
  private PowbManagementGovernance powbManagementGovernance;


  @Expose
  private PowbFinancialPlan financialPlan;


  @Expose
  private PowbCollaboration collaboration;


  private Set<PowbFinancialExpenditure> powbFinancialExpenditures = new HashSet<PowbFinancialExpenditure>(0);


  private List<PowbFinancialExpenditure> powbFinancialExpendituresList;


  private Set<PowbFinancialPlannedBudget> powbFinancialPlannedBudget = new HashSet<PowbFinancialPlannedBudget>(0);

  private Set<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnits = new HashSet<PowbCollaborationGlobalUnit>(0);

  private List<PowbFinancialPlannedBudget> powbFinancialPlannedBudgetList;

  private List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitsList;


  private Set<Submission> submissions = new HashSet<Submission>(0);
  private Set<PowbCollaborationRegion> powbCollaborationRegions = new HashSet<PowbCollaborationRegion>(0);

  private List<PowbCollaborationRegion> regions;


  private String flagshipSummarize;


  private String flagshipAssets;


  public PowbSynthesis() {
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
    PowbSynthesis other = (PowbSynthesis) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public PowbCollaboration getCollaboration() {
    return collaboration;
  }

  public PowbCrpStaffing getCrpStaffing() {
    return crpStaffing;
  }

  public List<PowbExpectedCrpProgress> getExpectedCrpProgresses() {
    return expectedCrpProgresses;
  }

  public PowbFinancialPlan getFinancialPlan() {
    return financialPlan;
  }


  public String getFlagshipAssets() {
    return flagshipAssets;
  }


  public String getFlagshipSummarize() {
    return flagshipSummarize;
  }

  public LiaisonInstitution getLiaisonInstitution() {
    return liaisonInstitution;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public Phase getPhase() {
    return phase;
  }


  public Set<PowbCollaborationGlobalUnit> getPowbCollaborationGlobalUnits() {
    return powbCollaborationGlobalUnits;
  }


  public List<PowbCollaborationGlobalUnit> getPowbCollaborationGlobalUnitsList() {
    return powbCollaborationGlobalUnitsList;
  }


  public Set<PowbCollaborationRegion> getPowbCollaborationRegions() {
    return powbCollaborationRegions;
  }


  public PowbCrossCuttingDimension getPowbCrossCuttingDimension() {
    return powbCrossCuttingDimension;
  }


  public PowbEvidence getPowbEvidence() {
    return powbEvidence;
  }


  public Set<PowbExpectedCrpProgress> getPowbExpectedCrpProgresses() {
    return powbExpectedCrpProgresses;
  }


  public Set<PowbFinancialExpenditure> getPowbFinancialExpenditures() {
    return powbFinancialExpenditures;
  }


  public List<PowbFinancialExpenditure> getPowbFinancialExpendituresList() {
    return powbFinancialExpendituresList;
  }


  public Set<PowbFinancialPlannedBudget> getPowbFinancialPlannedBudget() {
    return powbFinancialPlannedBudget;
  }


  public List<PowbFinancialPlannedBudget> getPowbFinancialPlannedBudgetList() {
    return powbFinancialPlannedBudgetList;
  }

  public PowbFlagshipPlans getPowbFlagshipPlans() {
    return powbFlagshipPlans;
  }

  public PowbManagementGovernance getPowbManagementGovernance() {
    return powbManagementGovernance;
  }

  public PowbManagementRisk getPowbManagementRisk() {
    return powbManagementRisk;
  }

  public PowbMonitoringEvaluationLearning getPowbMonitoringEvaluationLearning() {
    return powbMonitoringEvaluationLearning;
  }

  public Set<PowbSynthesisCrpStaffingCategory> getPowbSynthesisCrpStaffingCategory() {
    return powbSynthesisCrpStaffingCategory;
  }


  public List<PowbSynthesisCrpStaffingCategory> getPowbSynthesisCrpStaffingCategoryList() {
    return powbSynthesisCrpStaffingCategoryList;
  }


  public PowbToc getPowbToc() {
    return powbToc;
  }

  public List<PowbCollaborationRegion> getRegions() {
    return regions;
  }

  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }

  public Set<Submission> getSubmissions() {
    return submissions;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public void setCollaboration(PowbCollaboration collaboration) {
    this.collaboration = collaboration;
  }

  public void setCrpStaffing(PowbCrpStaffing crpStaffing) {
    this.crpStaffing = crpStaffing;
  }

  public void setExpectedCrpProgresses(List<PowbExpectedCrpProgress> expectedCrpProgresses) {
    this.expectedCrpProgresses = expectedCrpProgresses;
  }

  public void setFinancialPlan(PowbFinancialPlan financialPlan) {
    this.financialPlan = financialPlan;
  }


  public void setFlagshipAssets(String flagshipAssets) {
    this.flagshipAssets = flagshipAssets;
  }

  public void setFlagshipSummarize(String flagshipSummarize) {
    this.flagshipSummarize = flagshipSummarize;
  }

  public void setLiaisonInstitution(LiaisonInstitution liaisonInstitution) {
    this.liaisonInstitution = liaisonInstitution;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setPowbCollaborationGlobalUnits(Set<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnits) {
    this.powbCollaborationGlobalUnits = powbCollaborationGlobalUnits;
  }


  public void setPowbCollaborationGlobalUnitsList(List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitsList) {
    this.powbCollaborationGlobalUnitsList = powbCollaborationGlobalUnitsList;
  }


  public void setPowbCollaborationRegions(Set<PowbCollaborationRegion> powbCollaborationRegions) {
    this.powbCollaborationRegions = powbCollaborationRegions;
  }

  public void setPowbCrossCuttingDimension(PowbCrossCuttingDimension powbCrossCuttingDimension) {
    this.powbCrossCuttingDimension = powbCrossCuttingDimension;
  }

  public void setPowbEvidence(PowbEvidence powbEvidence) {
    this.powbEvidence = powbEvidence;
  }

  public void setPowbExpectedCrpProgresses(Set<PowbExpectedCrpProgress> powbExpectedCrpProgresses) {
    this.powbExpectedCrpProgresses = powbExpectedCrpProgresses;
  }


  public void setPowbFinancialExpenditures(Set<PowbFinancialExpenditure> powbFinancialExpenditures) {
    this.powbFinancialExpenditures = powbFinancialExpenditures;
  }


  public void setPowbFinancialExpendituresList(List<PowbFinancialExpenditure> powbFinancialExpendituresList) {
    this.powbFinancialExpendituresList = powbFinancialExpendituresList;
  }

  public void setPowbFinancialPlannedBudget(Set<PowbFinancialPlannedBudget> powbFinancialPlannedBudget) {
    this.powbFinancialPlannedBudget = powbFinancialPlannedBudget;
  }

  public void setPowbFinancialPlannedBudgetList(List<PowbFinancialPlannedBudget> powbFinancialPlannedBudgetList) {
    this.powbFinancialPlannedBudgetList = powbFinancialPlannedBudgetList;
  }

  public void setPowbFlagshipPlans(PowbFlagshipPlans powbFlagshipPlans) {
    this.powbFlagshipPlans = powbFlagshipPlans;
  }

  public void setPowbManagementGovernance(PowbManagementGovernance powbManagementGovernance) {
    this.powbManagementGovernance = powbManagementGovernance;
  }

  public void setPowbManagementRisk(PowbManagementRisk powbManagementRisk) {
    this.powbManagementRisk = powbManagementRisk;
  }


  public void setPowbMonitoringEvaluationLearning(PowbMonitoringEvaluationLearning powbMonitoringEvaluationLearning) {
    this.powbMonitoringEvaluationLearning = powbMonitoringEvaluationLearning;
  }


  public void
    setPowbSynthesisCrpStaffingCategory(Set<PowbSynthesisCrpStaffingCategory> powbSynthesisCrpStaffingCategory) {
    this.powbSynthesisCrpStaffingCategory = powbSynthesisCrpStaffingCategory;
  }

  public void setPowbSynthesisCrpStaffingCategoryList(
    List<PowbSynthesisCrpStaffingCategory> powbSynthesisCrpStaffingCategoryList) {
    this.powbSynthesisCrpStaffingCategoryList = powbSynthesisCrpStaffingCategoryList;
  }


  public void setPowbToc(PowbToc powbToc) {
    this.powbToc = powbToc;
  }


  public void setRegions(List<PowbCollaborationRegion> regions) {
    this.regions = regions;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }


  public void setSubmissions(Set<Submission> submissions) {
    this.submissions = submissions;
  }

  @Override
  public String toString() {
    return "PowbSynthesis [id=" + this.getId() + ", phase=" + phase + ", liaisonInstitution=" + liaisonInstitution
      + "]";
  }


}