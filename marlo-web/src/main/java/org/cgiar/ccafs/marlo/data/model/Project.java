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
// Generated Jul 13, 2016 11:45:52 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.IAuditLog;
import org.cgiar.ccafs.marlo.utils.CountryLocationLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;


/**
 * Project generated by hbm2java
 */
public class Project implements java.io.Serializable, IAuditLog {


  public static final int EMAIL_SUBJECT_IDENTIFIER = 4;


  public static final int EXCEL_IDENTIFIER_REPORT = 3;


  public static final int PDF_IDENTIFIER_REPORT = 2;


  private static final long serialVersionUID = -5737088425960023585L;


  public static final int STANDAR_IDENTIFIER = 1;

  @Expose
  private Long id;


  @Expose
  private Crp crp;

  @Expose
  private User createdBy;

  @Expose
  private boolean isActive;

  @Expose
  private User modifiedBy;
  private String flagshipValue;

  private String regionsValue;
  @Expose
  private Date activeSince;


  @Expose
  private Date createDate;


  private String customID;


  private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);


  private ProjectInfo projectInfo;


  private List<CrpClusterOfActivity> crpActivities;


  private List<CrpProgram> flagships;


  private Set<Deliverable> deliverables = new HashSet<Deliverable>(0);

  private List<ProjectLocation> locations;
  private List<CountryLocationLevel> locationsData;


  private List<ProjectOutcome> outcomes;
  private List<ProjectLocation> projectRegions;


  private String overall;


  private List<ProjectPartner> partners;


  private List<Activity> projectActivities;


  private Set<ProjectBudget> projectBudgetCofinances = new HashSet<ProjectBudget>(0);
  private Set<ProjectPhase> projectPhases = new HashSet<ProjectPhase>(0);
  private Set<ProjectBudget> projectBudgets = new HashSet<ProjectBudget>(0);
  private Set<ProjectBudgetsCluserActvity> projectBudgetsCluserActvities = new HashSet<ProjectBudgetsCluserActvity>(0);


  private Set<ProjectClusterActivity> projectClusterActivities = new HashSet<ProjectClusterActivity>(0);


  private Set<ProjectHighlight> projectHighlights = new HashSet<ProjectHighlight>(0);


  private List<ProjectHighlight> highligths;


  private ProjectComponentLesson projectComponentLesson;


  private Set<Activity> activities = new HashSet<Activity>(0);


  private List<ProjectBudget> budgets;

  private List<ProjectBudgetsCluserActvity> budgetsCluserActvities;

  private List<Activity> closedProjectActivities;

  private List<ProjectClusterActivity> clusterActivities;


  private ProjectComponentLesson projectComponentLessonPreview;
  private List<CountryFundingSources> countryFS;

  private List<CountryFundingSources> regionFS;


  private Set<ProjectComponentLesson> projectComponentLessons = new HashSet<ProjectComponentLesson>(0);


  private Set<CaseStudyProject> caseStudyProjects = new HashSet<CaseStudyProject>(0);


  private List<CaseStudy> caseStudies;


  private Set<ProjectCrpContribution> projectCrpContributions = new HashSet<ProjectCrpContribution>(0);


  private List<ProjectCrpContribution> crpContributions;


  private List<Deliverable> projectDeliverables;


  private Set<ProjectFocus> projectFocuses = new HashSet<ProjectFocus>(0);


  private Set<ProjectLocationElementType> projectLocationElementTypes = new HashSet<ProjectLocationElementType>(0);


  private Set<ProjectLocation> projectLocations = new HashSet<ProjectLocation>(0);


  private Set<ProjectOutcome> projectOutcomes = new HashSet<ProjectOutcome>(0);


  private Set<ProjectLeverage> projectLeverages = new HashSet<ProjectLeverage>(0);


  private List<ProjectLeverage> leverages;


  private List<ProjectLeverage> leveragesClosed;

  private Set<ProjectPartner> projectPartners = new HashSet<ProjectPartner>(0);

  private Set<ProjectScope> projectScopes = new HashSet<ProjectScope>(0);

  private List<CrpProgram> regions;


  private Set<OtherContribution> otherContributions = new HashSet<OtherContribution>(0);


  private List<OtherContribution> otherContributionsList;

  private List<ProjectScope> scopes;


  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);


  private Set<Submission> submissions = new HashSet<Submission>(0);


  private List<IpElement> outputs;


  private List<IpElement> mogs;


  private List<IpIndicator> indicators;


  private List<IpProjectIndicator> projectIndicators;


  private Set<IpProjectContributionOverview> ipProjectContributionOverviews =
    new HashSet<IpProjectContributionOverview>(0);


  private List<IpProjectContributionOverview> overviews;


  private Set<IpProjectContribution> ipProjectContributions = new HashSet<IpProjectContribution>(0);


  private Set<ProjectOtherContribution> projectOtherContributions = new HashSet<ProjectOtherContribution>(0);


  private List<ProjectOtherContribution> projectOtherContributionsList;


  private Set<ProjectFurtherContribution> projectFurtherContributions = new HashSet<ProjectFurtherContribution>(0);


  private Set<IpProjectIndicator> ipProjectIndicators = new HashSet<IpProjectIndicator>(0);


  private Set<ProjectFocusPrev> projectFocusPrevs = new HashSet<ProjectFocusPrev>(0);


  private Set<ProjectOutcomePandr> projectOutcomesPandr = new HashSet<ProjectOutcomePandr>(0);


  private List<ProjectOutcomePandr> outcomesPandr;

  private double coreBudget;
  private double w3Budget;
  private double bilateralBudget;


  public Project() {
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    Project other = (Project) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.getId())) {
      return false;
    }
    return true;
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public Set<Activity> getActivities() {
    return activities;
  }


  public double getBilateralBudget() {
    return bilateralBudget;
  }


  public double getBilateralBudget(int year) {

    double total = 0;
    for (ProjectBudget projectBudget : this.getProjectBudgets().stream()
      .filter(c -> c.isActive() && c.getBudgetType().getId() == 3 && c.getYear() == year)
      .collect(Collectors.toList())) {
      if (projectBudget.getAmount() != null) {
        total = total + projectBudget.getAmount();
      }
    }

    return total;
  }


  public List<ProjectBudget> getBudgets() {
    return budgets;
  }


  public List<ProjectBudgetsCluserActvity> getBudgetsCluserActvities() {
    return budgetsCluserActvities;
  }


  public List<CaseStudy> getCaseStudies() {
    return caseStudies;
  }


  public Set<CaseStudyProject> getCaseStudyProjects() {
    return caseStudyProjects;
  }


  public List<Activity> getClosedProjectActivities() {
    return closedProjectActivities;
  }


  public List<ProjectClusterActivity> getClusterActivities() {
    return clusterActivities;
  }


  /**
   * get the composed name of one phase
   * 
   * @param phase the phase we want to get the title
   * @return the composed name for a project
   */
  public String getComposedName(Phase phase) {

    ProjectInfo projectInfo = this.getProjecInfoPhase(phase);
    if (projectInfo != null) {
      return "P" + this.id + " - " + projectInfo.getTitle();
    } else {
      return "P" + this.id;
    }

  }


  /**
   * This method gets all the coordinators working for this project.
   * 
   * @return a list of PartnerPerson with the information requested.
   */
  public List<ProjectPartnerPerson> getCoordinatorPersons(Phase phase) {
    List<ProjectPartnerPerson> projectCoordinators = new ArrayList<>();
    if (partners != null) {
      for (ProjectPartner partner : partners) {
        if (partner.getPartnerPersons() != null) {
          for (ProjectPartnerPerson person : partner.getPartnerPersons()) {
            if (person.getContactType().equals("PC")) {
              projectCoordinators.add(person);
            }
          }
        }

      }
    } else {
      for (ProjectPartner partner : projectPartners.stream().filter(c -> c.isActive() && c.getPhase().equals(phase))
        .collect(Collectors.toList())) {
        if (partner.getProjectPartnerPersons() != null) {
          for (ProjectPartnerPerson person : partner.getProjectPartnerPersons()) {

            if (person.getContactType().equals("PC") && person.isActive()) {
              projectCoordinators.add(person);
            }
          }
        }

      }
    }
    return projectCoordinators;
  }


  public double getCoreBudget() {
    return coreBudget;
  }


  public double getCoreBudget(int year) {

    double total = 0;
    for (ProjectBudget projectBudget : this.getProjectBudgets().stream()
      .filter(c -> c.isActive() && c.getBudgetType().getId() == 1 && c.getYear() == year)
      .collect(Collectors.toList())) {
      if (projectBudget.getAmount() != null) {
        total = total + projectBudget.getAmount();
      }
    }

    return total;
  }


  public List<CountryFundingSources> getCountryFS() {
    return countryFS;
  }


  public Date getCreateDate() {
    return createDate;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public Crp getCrp() {
    return crp;
  }


  public List<CrpClusterOfActivity> getCrpActivities() {
    return crpActivities;
  }


  public List<ProjectCrpContribution> getCrpContributions() {
    return crpContributions;
  }


  public String getCustomID() {
    return customID;
  }


  public Set<Deliverable> getDeliverables() {
    return deliverables;
  }


  public List<CrpProgram> getFlagships() {
    return flagships;
  }


  public String getFlagshipValue() {
    return flagshipValue;
  }


  public List<ProjectHighlight> getHighligths() {
    return highligths;
  }


  @Override
  public Long getId() {
    return id;
  }


  public List<IpIndicator> getIndicators() {
    return indicators;
  }


  public Set<IpProjectContributionOverview> getIpProjectContributionOverviews() {
    return ipProjectContributionOverviews;
  }

  public Set<IpProjectContribution> getIpProjectContributions() {
    return ipProjectContributions;
  }

  public Set<IpProjectIndicator> getIpProjectIndicators() {
    return ipProjectIndicators;
  }

  public ProjectPartner getLeader() {

    if (partners != null) {
      for (ProjectPartner partner : partners) {

        if (partner.getPartnerPersons() != null) {
          for (ProjectPartnerPerson person : partner.getPartnerPersons()) {
            if (person.getContactType().equals("PL")) {
              return partner;
            }
          }
        }

      }
    } else {
      for (ProjectPartner partner : projectPartners.stream().filter(c -> c.isActive()).collect(Collectors.toList())) {
        if (partner.isActive()) {


          for (ProjectPartnerPerson person : partner.getProjectPartnerPersons()) {
            if (person.isActive()) {
              if (person.getContactType().equals("PL") && person.isActive()) {
                return partner;
              }
            }

          }
        }
      }


    }
    return null;
  }


  public ProjectPartner getLeader(Phase phase) {

    try {
      if (partners != null) {
        for (ProjectPartner partner : partners.stream().filter(c -> c.isActive()).collect(Collectors.toList())) {

          if (partner.getPartnerPersons() != null) {
            for (ProjectPartnerPerson person : partner.getPartnerPersons()) {
              if (person.getContactType().equals(APConstants.PROJECT_PARTNER_PL)) {
                return partner;
              }
            }
          }

        }
      } else {
        final Phase actualPhase;
        if (projectInfo.getPhase() != null) {
          actualPhase = projectInfo.getPhase();
        } else {
          actualPhase = phase;
        }
        for (ProjectPartner partner : projectPartners.stream()
          .filter(c -> c.isActive() && c.getPhase().equals(actualPhase)).collect(Collectors.toList())) {
          if (partner.isActive()) {


            for (ProjectPartnerPerson person : partner.getProjectPartnerPersons()) {
              if (person.isActive()) {
                if (person.getContactType().equals(APConstants.PROJECT_PARTNER_PL) && person.isActive()) {
                  return partner;
                }
              }

            }
          }
        }


      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }


  /**
   * This method returns the project partner person who is leading the project.
   * 
   * @return a PartnerPerson object with the information requested. Or null if the project doesn't have a leader.
   */
  public ProjectPartnerPerson getLeaderPerson(Phase phase) {

    if (partners != null) {
      for (ProjectPartner partner : partners) {
        if (partner.getPartnerPersons() != null) {
          for (ProjectPartnerPerson person : partner.getPartnerPersons()) {

            if (person.getContactType().equals("PL")) {
              return person;

            }

          }
        }

      }
    } else {
      for (ProjectPartner partner : projectPartners.stream().filter(c -> c.isActive() && c.getPhase().equals(phase))
        .collect(Collectors.toList())) {
        for (ProjectPartnerPerson person : partner.getProjectPartnerPersons()) {
          if (person.isActive()) {
            if (person.getContactType().equals("PL")) {
              return person;
            }
          }
        }

      }

    }

    return null;
  }


  public List<ProjectLeverage> getLeverages() {
    return leverages;
  }


  public List<ProjectLeverage> getLeveragesClosed() {
    return leveragesClosed;
  }


  public List<ProjectLocation> getLocations() {
    return locations;
  }


  public List<CountryLocationLevel> getLocationsData() {
    return locationsData;
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

  public List<IpElement> getMogs() {
    return mogs;
  }

  public Set<OtherContribution> getOtherContributions() {
    return otherContributions;
  }


  public List<OtherContribution> getOtherContributionsList() {
    return otherContributionsList;
  }

  public List<ProjectOutcome> getOutcomes() {
    return outcomes;
  }

  public List<ProjectOutcomePandr> getOutcomesPandr() {
    return outcomesPandr;
  }

  public List<IpElement> getOutputs() {
    return outputs;
  }

  public String getOverall() {
    return overall;
  }


  public List<IpProjectContributionOverview> getOverviews() {
    return overviews;
  }


  public List<ProjectPartner> getPartners() {
    return partners;
  }


  public ProjectInfo getProjecInfoPhase(Phase phase) {
    if (this.getProjectInfo() != null) {
      return this.getProjectInfo();
    } else {
      List<ProjectInfo> infos = projectInfos.stream().filter(
        c -> c.getPhase() != null && c.getPhase().getId() != null && c.getPhase().getId().longValue() == phase.getId())
        .collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setProjectInfo(infos.get(0));
        return this.getProjectInfo();
      }
    }

    return null;
  }


  public List<Activity> getProjectActivities() {
    return projectActivities;
  }


  public Set<ProjectBudget> getProjectBudgetCofinances() {
    return projectBudgetCofinances;
  }


  public Set<ProjectBudget> getProjectBudgets() {
    return projectBudgets;
  }


  public Set<ProjectBudgetsCluserActvity> getProjectBudgetsCluserActvities() {
    return projectBudgetsCluserActvities;
  }


  public Set<ProjectClusterActivity> getProjectClusterActivities() {
    return projectClusterActivities;
  }


  public ProjectComponentLesson getProjectComponentLesson() {
    return projectComponentLesson;
  }


  public ProjectComponentLesson getProjectComponentLessonPreview() {
    return projectComponentLessonPreview;
  }


  public Set<ProjectComponentLesson> getProjectComponentLessons() {
    return projectComponentLessons;
  }


  public Set<ProjectCrpContribution> getProjectCrpContributions() {
    return projectCrpContributions;
  }


  public List<Deliverable> getProjectDeliverables() {
    return projectDeliverables;
  }


  public Set<ProjectFocus> getProjectFocuses() {
    return projectFocuses;
  }


  public Set<ProjectFocusPrev> getProjectFocusPrevs() {
    return projectFocusPrevs;
  }


  public Set<ProjectFurtherContribution> getProjectFurtherContributions() {
    return projectFurtherContributions;
  }


  public Set<ProjectHighlight> getProjectHighlights() {
    return projectHighlights;
  }

  public Set<ProjectHighlight> getProjectHighligths() {
    return projectHighlights;
  }


  public List<IpProjectIndicator> getProjectIndicators() {
    return projectIndicators;
  }


  public ProjectInfo getProjectInfo() {
    return projectInfo;
  }


  public Set<ProjectInfo> getProjectInfos() {
    return projectInfos;
  }

  public Set<ProjectLeverage> getProjectLeverages() {
    return projectLeverages;
  }


  public Set<ProjectLocationElementType> getProjectLocationElementTypes() {
    return projectLocationElementTypes;
  }


  public Set<ProjectLocation> getProjectLocations() {
    return projectLocations;
  }


  public Set<ProjectOtherContribution> getProjectOtherContributions() {
    return projectOtherContributions;
  }


  public List<ProjectOtherContribution> getProjectOtherContributionsList() {
    return projectOtherContributionsList;
  }


  public Set<ProjectOutcome> getProjectOutcomes() {
    return projectOutcomes;
  }


  public Set<ProjectOutcomePandr> getProjectOutcomesPandr() {
    return projectOutcomesPandr;
  }


  public Set<ProjectPartner> getProjectPartners() {
    return projectPartners;
  }


  public Set<ProjectPhase> getProjectPhases() {
    return projectPhases;
  }


  public List<ProjectLocation> getProjectRegions() {
    return projectRegions;
  }


  public Set<ProjectScope> getProjectScopes() {
    return projectScopes;
  }


  public List<CountryFundingSources> getRegionFS() {
    return regionFS;
  }


  public List<CrpProgram> getRegions() {
    return regions;
  }


  public String getRegionsValue() {
    return regionsValue;
  }


  public List<ProjectScope> getScopes() {
    return scopes;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }


  public String getStandardIdentifier(int typeCodification) {
    StringBuilder result = new StringBuilder();

    switch (typeCodification) {
      // Standar identifier
      case Project.EMAIL_SUBJECT_IDENTIFIER:
        result.append("P" + this.getId());
        break;

      default:
        // Do nothing
        break;

    }


    return result.toString();
  }


  public Set<Submission> getSubmissions() {
    return submissions;
  }


  public double getW3Budget() {
    return w3Budget;
  }


  public double getW3Budget(int year) {
    double total = 0;
    for (ProjectBudget projectBudget : this.getProjectBudgets().stream()
      .filter(c -> c.isActive() && c.getBudgetType().getId() == 2 && c.getYear() == year)
      .collect(Collectors.toList())) {
      if (projectBudget.getAmount() != null) {
        total = total + projectBudget.getAmount();
      }
    }

    return total;
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
    return isActive;
  }


  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setActivities(Set<Activity> activities) {
    this.activities = activities;
  }


  public void setBilateralBudget(double bilateralBudget) {
    this.bilateralBudget = bilateralBudget;
  }


  public void setBudgets(List<ProjectBudget> budgets) {
    this.budgets = budgets;
  }


  public void setBudgetsCluserActvities(List<ProjectBudgetsCluserActvity> budgetsCluserActvities) {
    this.budgetsCluserActvities = budgetsCluserActvities;
  }


  public void setCaseStudies(List<CaseStudy> caseStudies) {
    this.caseStudies = caseStudies;
  }


  public void setCaseStudyProjects(Set<CaseStudyProject> caseStudyProjects) {
    this.caseStudyProjects = caseStudyProjects;
  }


  public void setClosedProjectActivities(List<Activity> closedProjectActivities) {
    this.closedProjectActivities = closedProjectActivities;
  }


  public void setClusterActivities(List<ProjectClusterActivity> clusterActivities) {
    this.clusterActivities = clusterActivities;
  }


  public void setCoreBudget(double coreBudget) {
    this.coreBudget = coreBudget;
  }


  public void setCountryFS(List<CountryFundingSources> countryFS) {
    this.countryFS = countryFS;
  }


  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setCrp(Crp crp) {
    this.crp = crp;
  }


  public void setCrpActivities(List<CrpClusterOfActivity> crpActivities) {
    this.crpActivities = crpActivities;
  }


  public void setCrpContributions(List<ProjectCrpContribution> crpContributions) {
    this.crpContributions = crpContributions;
  }

  public void setCustomID(String customID) {
    this.customID = customID;
  }


  public void setDeliverables(Set<Deliverable> deliverables) {
    this.deliverables = deliverables;
  }

  public void setFlagships(List<CrpProgram> flagships) {
    this.flagships = flagships;
  }

  public void setFlagshipValue(String flagshipValue) {
    this.flagshipValue = flagshipValue;
  }

  public void setHighligths(List<ProjectHighlight> highligths) {
    this.highligths = highligths;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIndicators(List<IpIndicator> indicators) {
    this.indicators = indicators;
  }

  public void setIpProjectContributionOverviews(Set<IpProjectContributionOverview> ipProjectContributionOverviews) {
    this.ipProjectContributionOverviews = ipProjectContributionOverviews;
  }

  public void setIpProjectContributions(Set<IpProjectContribution> ipProjectContributions) {
    this.ipProjectContributions = ipProjectContributions;
  }

  public void setIpProjectIndicators(Set<IpProjectIndicator> ipProjectIndicators) {
    this.ipProjectIndicators = ipProjectIndicators;
  }

  public void setLeverages(List<ProjectLeverage> leverages) {
    this.leverages = leverages;
  }

  public void setLeveragesClosed(List<ProjectLeverage> leveragesClosed) {
    this.leveragesClosed = leveragesClosed;
  }

  public void setLocations(List<ProjectLocation> locations) {
    this.locations = locations;
  }

  public void setLocationsData(List<CountryLocationLevel> locationsData) {
    this.locationsData = locationsData;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setMogs(List<IpElement> mogs) {
    this.mogs = mogs;
  }

  public void setOtherContributions(Set<OtherContribution> otherContributions) {
    this.otherContributions = otherContributions;
  }

  public void setOtherContributionsList(List<OtherContribution> otherContributionsList) {
    this.otherContributionsList = otherContributionsList;
  }

  public void setOutcomes(List<ProjectOutcome> outcomes) {
    this.outcomes = outcomes;
  }

  public void setOutcomesPandr(List<ProjectOutcomePandr> outcomesPandr) {
    this.outcomesPandr = outcomesPandr;
  }

  public void setOutputs(List<IpElement> outputs) {
    this.outputs = outputs;
  }

  public void setOverall(String overall) {
    this.overall = overall;
  }

  public void setOverviews(List<IpProjectContributionOverview> overviews) {
    this.overviews = overviews;
  }

  public void setPartners(List<ProjectPartner> partners) {
    this.partners = partners;
  }

  public void setProjectActivities(List<Activity> projectActivities) {
    this.projectActivities = projectActivities;
  }

  public void setProjectBudgetCofinances(Set<ProjectBudget> projectBudgetCofinances) {
    this.projectBudgetCofinances = projectBudgetCofinances;
  }

  public void setProjectBudgets(Set<ProjectBudget> projectBudgets) {
    this.projectBudgets = projectBudgets;
  }

  public void setProjectBudgetsCluserActvities(Set<ProjectBudgetsCluserActvity> projectBudgetsCluserActvities) {
    this.projectBudgetsCluserActvities = projectBudgetsCluserActvities;
  }

  public void setProjectClusterActivities(Set<ProjectClusterActivity> projectClusterActivities) {
    this.projectClusterActivities = projectClusterActivities;
  }

  public void setProjectComponentLesson(ProjectComponentLesson projectComponentLesson) {
    this.projectComponentLesson = projectComponentLesson;
  }

  public void setProjectComponentLessonPreview(ProjectComponentLesson projectComponentLessonPreview) {
    this.projectComponentLessonPreview = projectComponentLessonPreview;
  }

  public void setProjectComponentLessons(Set<ProjectComponentLesson> projectComponentLessons) {
    this.projectComponentLessons = projectComponentLessons;
  }

  public void setProjectCrpContributions(Set<ProjectCrpContribution> projectCrpContributions) {
    this.projectCrpContributions = projectCrpContributions;
  }

  public void setProjectDeliverables(List<Deliverable> projectDeliverables) {
    this.projectDeliverables = projectDeliverables;
  }

  public void setProjectFocuses(Set<ProjectFocus> projectFocuses) {
    this.projectFocuses = projectFocuses;
  }

  public void setProjectFocusPrevs(Set<ProjectFocusPrev> projectFocusPrevs) {
    this.projectFocusPrevs = projectFocusPrevs;
  }

  public void setProjectFurtherContributions(Set<ProjectFurtherContribution> projectFurtherContributions) {
    this.projectFurtherContributions = projectFurtherContributions;
  }

  public void setProjectHighlights(Set<ProjectHighlight> projectHighlights) {
    this.projectHighlights = projectHighlights;
  }

  public void setProjectIndicators(List<IpProjectIndicator> projectIndicators) {
    this.projectIndicators = projectIndicators;
  }

  public void setProjectInfo(ProjectInfo projectInfo) {
    this.projectInfo = projectInfo;
  }

  public void setProjectInfos(Set<ProjectInfo> projectInfos) {
    this.projectInfos = projectInfos;
  }

  public void setProjectLeverages(Set<ProjectLeverage> projectLeverages) {
    this.projectLeverages = projectLeverages;
  }

  public void setProjectLocationElementTypes(Set<ProjectLocationElementType> projectLocationElementTypes) {
    this.projectLocationElementTypes = projectLocationElementTypes;
  }

  public void setProjectLocations(Set<ProjectLocation> projectLocations) {
    this.projectLocations = projectLocations;
  }

  public void setProjectOtherContributions(Set<ProjectOtherContribution> projectOtherContributions) {
    this.projectOtherContributions = projectOtherContributions;
  }

  public void setProjectOtherContributionsList(List<ProjectOtherContribution> projectOtherContributionsList) {
    this.projectOtherContributionsList = projectOtherContributionsList;
  }

  public void setProjectOutcomes(Set<ProjectOutcome> projectOutcomes) {
    this.projectOutcomes = projectOutcomes;
  }

  public void setProjectOutcomesPandr(Set<ProjectOutcomePandr> projectOutcomesPandr) {
    this.projectOutcomesPandr = projectOutcomesPandr;
  }

  public void setProjectPartners(Set<ProjectPartner> projectPartners) {
    this.projectPartners = projectPartners;
  }

  public void setProjectPhases(Set<ProjectPhase> projectPhases) {
    this.projectPhases = projectPhases;
  }

  public void setProjectRegions(List<ProjectLocation> projectRegions) {
    this.projectRegions = projectRegions;
  }

  public void setProjectScopes(Set<ProjectScope> projectScopes) {
    this.projectScopes = projectScopes;
  }

  public void setRegionFS(List<CountryFundingSources> regionFS) {
    this.regionFS = regionFS;
  }

  public void setRegions(List<CrpProgram> regions) {
    this.regions = regions;
  }

  public void setRegionsValue(String regionsValue) {
    this.regionsValue = regionsValue;
  }

  public void setScopes(List<ProjectScope> scopes) {
    this.scopes = scopes;
  }


  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }


  public void setSubmissions(Set<Submission> submissions) {
    this.submissions = submissions;
  }


  public void setW3Budget(double w3Budget) {
    this.w3Budget = w3Budget;
  }


}

