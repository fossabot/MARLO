package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 22, 2018 1:53:05 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

/**
 * ProjectExpectedStudy generated by hbm2java
 */
public class ProjectExpectedStudy extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 8398283484560886533L;


  @Expose
  private Long phase;

  private Project project;


  @Expose
  private SrfSubIdo srfSubIdo;

  @Expose
  private String composedId;


  @Expose
  private String topicStudy;


  @Expose
  private Integer scope;


  @Expose
  private Integer type;

  @Expose
  private String otherType;

  @Expose
  private SrfSloIndicator srfSloIndicator;


  @Expose
  private String comments;


  @Expose
  private Integer year;


  private CaseStudy casesStudy;

  // Work to Table in POWB 2019
  private List<LiaisonInstitution> selectedFlahsgips;


  private Set<ExpectedStudyProject> expectedStudyProjects = new HashSet<ExpectedStudyProject>(0);


  private List<ExpectedStudyProject> projects;

  // New Reporting @HJ
  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);

  private Set<ProjectExpectedStudyInfo> projectExpectedStudyInfos = new HashSet<ProjectExpectedStudyInfo>(0);


  private Set<ProjectExpectedStudySubIdo> projectExpectedStudySubIdos = new HashSet<ProjectExpectedStudySubIdo>(0);


  private Set<ProjectExpectedStudyFlagship> projectExpectedStudyFlagships =
    new HashSet<ProjectExpectedStudyFlagship>(0);


  private Set<ProjectExpectedStudyCrp> projectExpectedStudyCrps = new HashSet<ProjectExpectedStudyCrp>(0);


  private Set<ProjectExpectedStudyInstitution> projectExpectedStudyInstitutions =
    new HashSet<ProjectExpectedStudyInstitution>(0);


  private Set<ProjectExpectedStudyCountry> projectExpectedStudyCountries = new HashSet<ProjectExpectedStudyCountry>(0);

  private Set<ProjectExpectedStudyRegion> projectExpectedStudyRegions = new HashSet<ProjectExpectedStudyRegion>(0);


  private Set<ProjectExpectedStudySrfTarget> projectExpectedStudySrfTargets =
    new HashSet<ProjectExpectedStudySrfTarget>(0);


  private ProjectExpectedStudyInfo projectExpectedStudyInfo;


  private List<ProjectExpectedStudySubIdo> subIdos;


  private List<ProjectExpectedStudyFlagship> flagships;

  private List<ProjectExpectedStudyCrp> crps;


  private List<ProjectExpectedStudyInstitution> institutions;


  private List<ProjectExpectedStudyCountry> countries;

  private List<String> countriesIds = new ArrayList<>();


  private String countriesIdsText;


  private List<ProjectExpectedStudySrfTarget> srfTargets;


  private List<ProjectExpectedStudyFlagship> regions;


  private List<ProjectExpectedStudyCountry> studyRegions;


  public ProjectExpectedStudy() {
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
    ProjectExpectedStudy other = (ProjectExpectedStudy) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public CaseStudy getCasesStudy() {
    return casesStudy;
  }

  public String getComments() {
    return comments;
  }

  public String getComposedId() {
    return composedId;
  }

  public String getComposedName() {
    String name = "S" + this.getId();
    if (this.getProjectExpectedStudyInfo() != null) {
      if (this.getProjectExpectedStudyInfo().getTitle() != null) {
        name = name + " - " + this.getProjectExpectedStudyInfo().getTitle();
      } else {
        name = name + " - Undefined";
      }
    }
    return name;
  }

  public List<ProjectExpectedStudyCountry> getCountries() {
    return countries;
  }

  public List<String> getCountriesIds() {
    return countriesIds;
  }

  public String getCountriesIdsText() {
    return countriesIdsText;
  }

  public List<ProjectExpectedStudyCrp> getCrps() {
    return crps;
  }

  public Set<ExpectedStudyProject> getExpectedStudyProjects() {
    return expectedStudyProjects;
  }

  public List<ProjectExpectedStudyFlagship> getFlagships() {
    return flagships;
  }

  public List<ProjectExpectedStudyInstitution> getInstitutions() {
    return institutions;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public String getOtherType() {
    return otherType;
  }

  public Long getPhase() {
    return phase;
  }

  public Project getProject() {
    return project;
  }


  public Set<ProjectExpectedStudyCountry> getProjectExpectedStudyCountries() {
    return projectExpectedStudyCountries;
  }

  public Set<ProjectExpectedStudyCrp> getProjectExpectedStudyCrps() {
    return projectExpectedStudyCrps;
  }

  public Set<ProjectExpectedStudyFlagship> getProjectExpectedStudyFlagships() {
    return projectExpectedStudyFlagships;
  }

  public ProjectExpectedStudyInfo getProjectExpectedStudyInfo() {
    return projectExpectedStudyInfo;
  }

  public ProjectExpectedStudyInfo getProjectExpectedStudyInfo(Phase phase) {
    if (this.getProjectExpectedStudyInfo() != null) {
      return this.getProjectExpectedStudyInfo();
    } else {
      List<ProjectExpectedStudyInfo> infos =
        projectExpectedStudyInfos.stream().filter(c -> c != null && c.getPhase() != null && c.getPhase().getId() != null
          && c.getPhase().getId().equals(phase.getId())).collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setProjectExpectedStudyInfo(infos.get(0));
        return this.getProjectExpectedStudyInfo();
      } else {
        return null;
      }
    }
  }

  public Set<ProjectExpectedStudyInfo> getProjectExpectedStudyInfos() {
    return projectExpectedStudyInfos;
  }

  public Set<ProjectExpectedStudyInstitution> getProjectExpectedStudyInstitutions() {
    return projectExpectedStudyInstitutions;
  }

  public Set<ProjectExpectedStudyRegion> getProjectExpectedStudyRegions() {
    return projectExpectedStudyRegions;
  }

  public Set<ProjectExpectedStudySrfTarget> getProjectExpectedStudySrfTargets() {
    return projectExpectedStudySrfTargets;
  }

  public Set<ProjectExpectedStudySubIdo> getProjectExpectedStudySubIdos() {
    return projectExpectedStudySubIdos;
  }

  public List<ExpectedStudyProject> getProjects() {
    return projects;
  }


  public List<ProjectExpectedStudyFlagship> getRegions() {
    return regions;
  }


  public Integer getScope() {
    return scope;
  }

  public String getScopeName() {
    if (scope == null || scope.intValue() == -1) {
      return "";
    }
    return GlobalScopeEnum.getValue(scope.intValue()).getType();

  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }

  public List<LiaisonInstitution> getSelectedFlahsgips() {
    return selectedFlahsgips;
  }


  public SrfSloIndicator getSrfSloIndicator() {
    return srfSloIndicator;
  }


  public SrfSubIdo getSrfSubIdo() {
    return srfSubIdo;
  }


  public List<ProjectExpectedStudySrfTarget> getSrfTargets() {
    return srfTargets;
  }

  public List<ProjectExpectedStudyCountry> getStudyRegions() {
    return studyRegions;
  }

  public List<ProjectExpectedStudySubIdo> getSubIdos() {
    return subIdos;
  }

  public String getTopicStudy() {
    return topicStudy;
  }

  public Integer getType() {
    return type;
  }


  public String getTypeName() {
    if (type == null || type.intValue() == -1) {
      return "";
    }
    return TypeExpectedStudiesEnum.getValue(type.intValue()).getType();

  }

  public Integer getYear() {
    return year;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public void setCasesStudy(CaseStudy casesStudy) {
    this.casesStudy = casesStudy;
  }


  public void setComments(String comments) {
    this.comments = comments;
  }


  public void setComposedId(String composedId) {
    this.composedId = composedId;
  }


  public void setCountries(List<ProjectExpectedStudyCountry> countries) {
    this.countries = countries;
  }


  public void setCountriesIds(List<String> countriesIds) {
    this.countriesIds = countriesIds;
  }


  public void setCountriesIdsText(String countriesIdsText) {
    this.countriesIdsText = countriesIdsText;
  }

  public void setCrps(List<ProjectExpectedStudyCrp> crps) {
    this.crps = crps;
  }


  public void setExpectedStudyProjects(Set<ExpectedStudyProject> expectedStudyProjects) {
    this.expectedStudyProjects = expectedStudyProjects;
  }


  public void setFlagships(List<ProjectExpectedStudyFlagship> flagships) {
    this.flagships = flagships;
  }


  public void setInstitutions(List<ProjectExpectedStudyInstitution> institutions) {
    this.institutions = institutions;
  }

  public void setOtherType(String otherType) {
    this.otherType = otherType;
  }


  public void setPhase(Long phase) {
    this.phase = phase;
  }


  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectExpectedStudyCountries(Set<ProjectExpectedStudyCountry> projectExpectedStudyCountries) {
    this.projectExpectedStudyCountries = projectExpectedStudyCountries;
  }


  public void setProjectExpectedStudyCrps(Set<ProjectExpectedStudyCrp> projectExpectedStudyCrps) {
    this.projectExpectedStudyCrps = projectExpectedStudyCrps;
  }


  public void setProjectExpectedStudyFlagships(Set<ProjectExpectedStudyFlagship> projectExpectedStudyFlagships) {
    this.projectExpectedStudyFlagships = projectExpectedStudyFlagships;
  }


  public void setProjectExpectedStudyInfo(ProjectExpectedStudyInfo projectExpectedStudyInfo) {
    this.projectExpectedStudyInfo = projectExpectedStudyInfo;
  }


  public void setProjectExpectedStudyInfos(Set<ProjectExpectedStudyInfo> projectExpectedStudyInfos) {
    this.projectExpectedStudyInfos = projectExpectedStudyInfos;
  }


  public void
    setProjectExpectedStudyInstitutions(Set<ProjectExpectedStudyInstitution> projectExpectedStudyInstitutions) {
    this.projectExpectedStudyInstitutions = projectExpectedStudyInstitutions;
  }


  public void setProjectExpectedStudyRegions(Set<ProjectExpectedStudyRegion> projectExpectedStudyRegions) {
    this.projectExpectedStudyRegions = projectExpectedStudyRegions;
  }

  public void setProjectExpectedStudySrfTargets(Set<ProjectExpectedStudySrfTarget> projectExpectedStudySrfTargets) {
    this.projectExpectedStudySrfTargets = projectExpectedStudySrfTargets;
  }

  public void setProjectExpectedStudySubIdos(Set<ProjectExpectedStudySubIdo> projectExpectedStudySubIdos) {
    this.projectExpectedStudySubIdos = projectExpectedStudySubIdos;
  }


  public void setProjects(List<ExpectedStudyProject> projects) {
    this.projects = projects;
  }


  public void setRegions(List<ProjectExpectedStudyFlagship> regions) {
    this.regions = regions;
  }


  public void setScope(Integer scope) {
    this.scope = scope;
  }


  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }


  public void setSelectedFlahsgips(List<LiaisonInstitution> selectedFlahsgips) {
    this.selectedFlahsgips = selectedFlahsgips;
  }


  public void setSrfSloIndicator(SrfSloIndicator srfSloIndicator) {
    this.srfSloIndicator = srfSloIndicator;
  }


  public void setSrfSubIdo(SrfSubIdo srfSubIdo) {
    this.srfSubIdo = srfSubIdo;
  }


  public void setSrfTargets(List<ProjectExpectedStudySrfTarget> srfTargets) {
    this.srfTargets = srfTargets;
  }

  public void setStudyRegions(List<ProjectExpectedStudyCountry> studyRegions) {
    this.studyRegions = studyRegions;
  }


  public void setSubIdos(List<ProjectExpectedStudySubIdo> subIdos) {
    this.subIdos = subIdos;
  }


  public void setTopicStudy(String topicStudy) {
    this.topicStudy = topicStudy;
  }


  public void setType(Integer type) {
    this.type = type;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "ProjectExpectedStudy [id=" + this.getId() + "]";
  }


}

