package org.cgiar.ccafs.marlo.data.model;
// Generated Oct 27, 2016 3:39:29 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

/**
 * FundingSource generated by hbm2java
 */
public class FundingSource implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -3854119067580692258L;

  @Expose
  private Long id;

  @Expose
  private Crp crp;

  @Expose
  private boolean active;

  @Expose
  private User createdBy;

  @Expose
  private User modifiedBy;

  @Expose
  private Date activeSince;

  private List<FundingSourceBudget> budgets;


<<<<<<< HEAD
=======
  private List<FundingSourceInstitution> institutions;

  @Expose
  private BudgetType budgetType;


  @Expose
  private Boolean w1w2;

  @Expose
  private String contactPersonEmail;


  @Expose
  private String contactPersonName;


  @Expose
  private User createdBy;

  @Expose
  private Crp crp;


  @Expose
  private String description;

  @Expose
  private Boolean synced;
  @Expose
  private Date syncedDate;

  @Expose
  private Date extensionDate;

  @Expose
  private Double grantAmount;


  @Expose
  private FileDB file;

  @Expose
  private PartnerDivision partnerDivision;

  @Expose
  private String financeCode;


>>>>>>> refs/remotes/origin/staging
  private Set<FundingSourceBudget> fundingSourceBudgets = new HashSet<FundingSourceBudget>(0);


  private Set<FundingSource> fundingSources = new HashSet<FundingSource>(0);

  private Set<FundingSourceInstitution> fundingSourceInstitutions = new HashSet<FundingSourceInstitution>(0);


  /*
   * Funding Source Locations
   */
  private Set<FundingSourceLocation> fundingSourceLocations = new HashSet<FundingSourceLocation>(0);

  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);

  private List<FundingSourceLocation> fundingRegions;

  private List<FundingSourceLocation> fundingCountry;
<<<<<<< HEAD
=======

  private boolean global;


  @Expose
  private Long id;

  @Expose
  private Institution institution;


  @Expose
  private Institution directDonor;


  @Expose
  private User modifiedBy;
>>>>>>> refs/remotes/origin/staging


  private Set<ProjectBudget> projectBudgets = new HashSet<ProjectBudget>(0);

<<<<<<< HEAD
=======
  @Expose
  private String modificationJustification;

>>>>>>> refs/remotes/origin/staging
  private List<ProjectBudget> projectBudgetsList;
<<<<<<< HEAD

  private FundingSourceInfo fundingSourceInfo;

  private Set<FundingSourceInfo> fundingSourceInfos = new HashSet<FundingSourceInfo>(0);
=======
  @Expose
  private Date startDate;


  @Expose
  private Date endDate;

  private Integer status;


  @Expose
  private String title;
>>>>>>> refs/remotes/origin/staging


  public FundingSource() {
  }


  public FundingSource(User modifiedBy, boolean active, Date activeSince) {
    this.modifiedBy = modifiedBy;
    this.active = active;
    this.activeSince = activeSince;
<<<<<<< HEAD
=======
    this.modificationJustification = modificationJustification;
  }


  public FundingSource(User modifiedBy, User createdBy, Institution institution, String description, Date startDate,
    Date endDate, String financeCode, String contactPersonName, String contactPersonEmail, BudgetType type,
    boolean active, Date activeSince, String modificationJustification, Set<FundingSourceBudget> fundingSourceBudgets,
    Set<ProjectBudget> projectBudgets, Crp crp) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.institution = institution;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.financeCode = financeCode;
    this.contactPersonName = contactPersonName;
    this.contactPersonEmail = contactPersonEmail;

    this.budgetType = type;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.fundingSourceBudgets = fundingSourceBudgets;
    this.projectBudgets = projectBudgets;
    this.crp = crp;
>>>>>>> refs/remotes/origin/staging
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    FundingSource other = (FundingSource) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }



  public Date getActiveSince() {
    return activeSince;
  }

  public List<FundingSourceBudget> getBudgets() {
    return budgets;
  }

<<<<<<< HEAD
=======
  public BudgetType getBudgetType() {
    return budgetType;
  }

>>>>>>> refs/remotes/origin/staging

  public String getComposedName() {
    try {
      return "<b> (FS" + this.id + ") " + this.getFundingSourceInfo().getBudgetType().getName() + "</b> - "
        + this.getFundingSourceInfo().getTitle();
    } catch (Exception e) {
      return "<b> (FS" + this.id + ") </b> - " + this.getFundingSourceInfo().getTitle();
    }
<<<<<<< HEAD
=======
  }


  public String getContactPersonEmail() {
    return contactPersonEmail;
  }

  public String getContactPersonName() {
    return contactPersonName;
>>>>>>> refs/remotes/origin/staging
  }


  public User getCreatedBy() {
    return createdBy;
  }



  public Crp getCrp() {
    return crp;
<<<<<<< HEAD
=======
  }


  public String getDescription() {
    return description;
  }


  public Institution getDirectDonor() {
    return directDonor;
  }

  public Date getEndDate() {
    return endDate;
  }


  public Date getExtensionDate() {
    return extensionDate;
  }


  public FileDB getFile() {
    return file;
  }

  public String getFinanceCode() {
    return financeCode;
>>>>>>> refs/remotes/origin/staging
  }


  public List<FundingSourceLocation> getFundingCountry() {
    return fundingCountry;
  }

  public List<FundingSourceLocation> getFundingRegions() {
    return fundingRegions;
  }


  public Set<FundingSourceBudget> getFundingSourceBudgets() {
    return fundingSourceBudgets;
  }

<<<<<<< HEAD
  public FundingSourceInfo getFundingSourceInfo() {
    return fundingSourceInfo;
  }

  public FundingSourceInfo getFundingSourceInfo(Phase phase) {
    if (this.getFundingSourceInfo() != null) {
      return this.getFundingSourceInfo();
    } else {
      List<FundingSourceInfo> infos =
        fundingSourceInfos.stream().filter(c -> c.getPhase() != null && c.getPhase().getId() != null
          && c.getPhase().getId().longValue() == phase.getId().longValue()).collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setFundingSourceInfo(infos.get(0));
        return this.getFundingSourceInfo();
      }
    }

    return null;

  }


  public Set<FundingSourceInfo> getFundingSourceInfos() {
    return fundingSourceInfos;
  }

=======
>>>>>>> refs/remotes/origin/staging
  public Set<FundingSourceInstitution> getFundingSourceInstitutions() {
    return fundingSourceInstitutions;
  }

  public Set<FundingSourceLocation> getFundingSourceLocations() {
    return fundingSourceLocations;
  }


  public Set<FundingSource> getFundingSources() {
    return fundingSources;
  }

  public Double getGrantAmount() {
    return grantAmount;
  }

  @Override
  public Long getId() {
    return id;
  }



  public List<FundingSourceInstitution> getInstitutions() {
    return institutions;
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
    return modifiedBy;
  }

<<<<<<< HEAD
=======

  public PartnerDivision getPartnerDivision() {
    return partnerDivision;
  }

>>>>>>> refs/remotes/origin/staging
  public Set<ProjectBudget> getProjectBudgets() {
    return projectBudgets;
  }

  public List<ProjectBudget> getProjectBudgetsList() {
    return projectBudgetsList;
  }

  public double getRemaining(int year, Phase phase) {
    double used = 0;

    double total = 0;
    for (FundingSourceBudget fundingSourceBudget : this.getFundingSourceBudgets().stream()
      .filter(c -> c.isActive() && c.getYear() != null && c.getYear().intValue() == year && c.getPhase().equals(phase))
      .collect(Collectors.toList())) {
      if (fundingSourceBudget.getBudget() != null) {
        total = total + fundingSourceBudget.getBudget().doubleValue();
      }

    }
    for (ProjectBudget projectBudget : this.getProjectBudgets().stream()
      .filter(c -> c.isActive() && c.getPhase() != null && c.getYear() == year && c.getPhase().equals(phase))
      .collect(Collectors.toList())) {
      used = used + projectBudget.getAmount().doubleValue();
    }
    return total - used;

  }


  /**
   * this budget is excluded from the calculation
   * 
   * @param year the year to review
   * @param budgetID the budget id to exclude
   * @return
   */
  public double getRemainingExcludeBudget(int year, long budgetID, Phase phase) {
    double used = 0;
    double total = 0;
    for (FundingSourceBudget fundingSourceBudget : this.getFundingSourceBudgets().stream()
      .filter(c -> c.isActive() && c.getYear() != null && c.getYear().intValue() == year && c.getPhase().equals(phase))
      .collect(Collectors.toList())) {
      if (fundingSourceBudget.getBudget() != null) {
        total = total + fundingSourceBudget.getBudget().doubleValue();
      }

    }
    for (ProjectBudget projectBudget : this.getProjectBudgets().stream().filter(c -> c.isActive() && c.getYear() == year
      && c.getPhase() != null && c.getId().longValue() != budgetID && c.getPhase().equals(phase))
      .collect(Collectors.toList())) {
      used = used + projectBudget.getAmount().doubleValue();
    }
    return total - used;

  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }

<<<<<<< HEAD
=======

  public Date getStartDate() {
    return startDate;
  }

  public Integer getStatus() {
    return status;
  }

>>>>>>> refs/remotes/origin/staging
  public String getStatusName() {
    if (this.getFundingSourceInfo().getStatus() != null && this.getFundingSourceInfo().getStatus().intValue() != -1) {
      AgreementStatusEnum statusEnum = AgreementStatusEnum.getValue(this.getFundingSourceInfo().getStatus().intValue());
      if (statusEnum != null) {
        return statusEnum.getStatus();
      }
    }
    return "";
<<<<<<< HEAD
=======

  }


  public Boolean getSynced() {
    return synced;
  }

  public Date getSyncedDate() {
    return syncedDate;
  }

  public String getTitle() {
    return title;
  }

  public Boolean getW1w2() {
    return w1w2;
>>>>>>> refs/remotes/origin/staging
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

<<<<<<< HEAD
  public boolean hasInstitution(long institutionID, long idPhase) {
=======

  public boolean hasInstitution(long institutionID) {
>>>>>>> refs/remotes/origin/staging

    for (FundingSourceInstitution fundingSourceInstitution : fundingSourceInstitutions) {
      if (fundingSourceInstitution.getInstitution().getId().longValue() == institutionID
        && fundingSourceInstitution.getPhase().getId().longValue() == idPhase) {
        return true;
      }
    }
    return false;
  }


  @Override
  public boolean isActive() {
    return active;
<<<<<<< HEAD
=======
  }


  public boolean isGlobal() {
    return global;
>>>>>>> refs/remotes/origin/staging
  }

  public void setActive(boolean active) {
    this.active = active;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setBudgets(List<FundingSourceBudget> budgets) {
    this.budgets = budgets;
  }

<<<<<<< HEAD
=======

  public void setBudgetType(BudgetType budgetType) {
    this.budgetType = budgetType;
  }

  public void setContactPersonEmail(String contactPersonEmail) {
    this.contactPersonEmail = contactPersonEmail;
  }

  public void setContactPersonName(String contactPersonName) {
    this.contactPersonName = contactPersonName;
  }
>>>>>>> refs/remotes/origin/staging

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrp(Crp crp) {
    this.crp = crp;
<<<<<<< HEAD
=======
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDirectDonor(Institution directDonor) {
    this.directDonor = directDonor;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setExtensionDate(Date extensionDate) {
    this.extensionDate = extensionDate;
  }


  public void setFile(FileDB file) {
    this.file = file;
  }


  public void setFinanceCode(String financeCode) {
    this.financeCode = financeCode;
>>>>>>> refs/remotes/origin/staging
  }


  public void setFundingCountry(List<FundingSourceLocation> fundingCountry) {
    this.fundingCountry = fundingCountry;
  }


  public void setFundingRegions(List<FundingSourceLocation> fundingRegions) {
    this.fundingRegions = fundingRegions;
  }


  public void setFundingSourceBudgets(Set<FundingSourceBudget> fundingSourceBudgets) {
    this.fundingSourceBudgets = fundingSourceBudgets;
  }


  public void setFundingSourceInfo(FundingSourceInfo fundingSourceInfo) {
    this.fundingSourceInfo = fundingSourceInfo;
  }


  public void setFundingSourceInfos(Set<FundingSourceInfo> fundingSourceInfos) {
    this.fundingSourceInfos = fundingSourceInfos;
  }


  public void setFundingSourceInstitutions(Set<FundingSourceInstitution> fundingSourceInstitutions) {
    this.fundingSourceInstitutions = fundingSourceInstitutions;
  }

  public void setFundingSourceLocations(Set<FundingSourceLocation> fundingSourceLocations) {
    this.fundingSourceLocations = fundingSourceLocations;
  }

  public void setFundingSources(Set<FundingSource> fundingSources) {
    this.fundingSources = fundingSources;
  }


  public void setGrantAmount(Double grantAmount) {
    this.grantAmount = grantAmount;
  }


  public void setId(Long id) {
    this.id = id;
  }

<<<<<<< HEAD
=======

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }


>>>>>>> refs/remotes/origin/staging
  public void setInstitutions(List<FundingSourceInstitution> institutions) {
    this.institutions = institutions;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }
<<<<<<< HEAD
=======

  public void setPartnerDivision(PartnerDivision partnerDivision) {
    this.partnerDivision = partnerDivision;
  }
>>>>>>> refs/remotes/origin/staging

  public void setProjectBudgets(Set<ProjectBudget> projectBudgets) {
    this.projectBudgets = projectBudgets;
  }

  public void setProjectBudgetsList(List<ProjectBudget> projectBudgetsList) {
    this.projectBudgetsList = projectBudgetsList;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

<<<<<<< HEAD
=======
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setSynced(Boolean synced) {
    this.synced = synced;
  }

  public void setSyncedDate(Date syncedDate) {
    this.syncedDate = syncedDate;
  }


  public void setTitle(String title) {
    this.title = title;
  }

  public void setW1w2(Boolean w1w2) {
    this.w1w2 = w1w2;
  }

>>>>>>> refs/remotes/origin/staging

}
