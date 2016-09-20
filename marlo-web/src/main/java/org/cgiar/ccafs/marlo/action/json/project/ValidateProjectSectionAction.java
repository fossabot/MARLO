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

package org.cgiar.ccafs.marlo.action.json.project;


import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.SectionStatusManager;
import org.cgiar.ccafs.marlo.data.model.Activity;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableActivity;
import org.cgiar.ccafs.marlo.data.model.DeliverablePartnership;
import org.cgiar.ccafs.marlo.data.model.DeliverablePartnershipTypeEnum;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectClusterActivity;
import org.cgiar.ccafs.marlo.data.model.ProjectFocus;
import org.cgiar.ccafs.marlo.data.model.ProjectOutcome;
import org.cgiar.ccafs.marlo.data.model.ProjectPartner;
import org.cgiar.ccafs.marlo.data.model.ProjectScope;
import org.cgiar.ccafs.marlo.data.model.ProjectSectionStatusEnum;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.data.model.SectionStatus;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.validation.projects.DeliverableValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectActivitiesValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectBudgetsCoAValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectBudgetsValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectDescriptionValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectLocationValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectOutcomeValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectPartnersValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class ValidateProjectSectionAction extends BaseAction {


  private static final long serialVersionUID = 2334147747892988744L;


  // Logger
  private static final Logger LOG = LoggerFactory.getLogger(ValidateProjectSectionAction.class);

  // Model
  private boolean existProject;
  private Crp loggedCrp;
  private boolean validSection;

  private String sectionName;
  private Long projectID;
  private SectionStatus sectionStatus;
  private Map<String, Object> section;
  // Managers
  @Inject
  private SectionStatusManager sectionStatusManager;
  @Inject
  private ProjectManager projectManager;
  @Inject
  ProjectLocationValidator locationValidator;

  @Inject
  ProjectBudgetsValidator projectBudgetsValidator;

  @Inject
  DeliverableValidator deliverableValidator;
  @Inject
  ProjectOutcomeValidator projectOutcomeValidator;

  @Inject
  ProjectBudgetsCoAValidator projectBudgetsCoAValidator;


  @Inject
  ProjectDescriptionValidator descriptionValidator;
  @Inject
  ProjectPartnersValidator projectPartnerValidator;
  @Inject
  ProjectActivitiesValidator projectActivitiesValidator;
  @Inject
  private CrpManager crpManager;

  @Inject
  public ValidateProjectSectionAction(APConfig config) {
    super(config);
  }

  @Override
  public String execute() throws Exception {
    if (existProject && validSection) {
      // getting the current section status.
      switch (ProjectSectionStatusEnum.value(sectionName.toUpperCase())) {
        case LOCATIONS:
          this.validateProjectLocations();
          break;
        case DESCRIPTION:
          this.validateProjectDescription();
          break;
        case ACTIVITIES:
          this.validateProjectActivities();
          break;
        case PARTNERS:
          this.validateProjectParnters();
        case BUDGET:
          this.validateProjectBudgets();
          break;
        case BUDGETBYCOA:
          this.validateProjectBudgetsCoAs();
          break;

        case DELIVERABLES:
          this.validateProjectDeliverables();
          break;

        case OUTCOMES:
          this.validateProjectOutcomes();
          break;

        default:
          break;
      }
    }
    String cycle = "";
    if (this.isPlanningActive()) {
      cycle = APConstants.PLANNING;
    } else {
      cycle = APConstants.REPORTING;
    }
    Project project = projectManager.getProjectById(projectID);
    switch (ProjectSectionStatusEnum.value(sectionName.toUpperCase())) {
      case OUTCOMES:
        section = new HashMap<String, Object>();
        section.put("sectionName", ProjectSectionStatusEnum.OUTCOMES);
        section.put("missingFields", "");

        List<ProjectOutcome> projectOutcomes =
          project.getProjectOutcomes().stream().filter(c -> c.isActive()).collect(Collectors.toList());


        project.setOutcomes(projectOutcomes);
        for (ProjectOutcome projectOutcome : project.getOutcomes()) {
          sectionStatus = sectionStatusManager.getSectionStatusByProjectOutcome(projectOutcome.getId(), cycle,
            this.getCurrentCycleYear(), sectionName);
          if (sectionStatus.getMissingFields().length() > 0) {
            section.put("missingFields", section.get("missingFields") + "-" + sectionStatus.getMissingFields());

          }
        }


        break;

      case DELIVERABLES:
        section = new HashMap<String, Object>();
        section.put("sectionName", ProjectSectionStatusEnum.DELIVERABLES);
        section.put("missingFields", "");


        for (Deliverable deliverable : project.getDeliverables().stream().filter(d -> d.isActive())
          .collect(Collectors.toList())) {
          sectionStatus = sectionStatusManager.getSectionStatusByDeliverable(deliverable.getId(), cycle,
            this.getCurrentCycleYear(), sectionName);

          if (sectionStatus.getMissingFields().length() > 0) {
            section.put("missingFields", section.get("missingFields") + "-" + sectionStatus.getMissingFields());

          }


        }


        break;


      default:
        sectionStatus =
          sectionStatusManager.getSectionStatusByProject(projectID, cycle, this.getCurrentCycleYear(), sectionName);
        section = new HashMap<String, Object>();
        section.put("sectionName", sectionStatus.getSectionName());
        section.put("missingFields", sectionStatus.getMissingFields());
        break;
    }


    // Thread.sleep(500);
    return SUCCESS;
  }

  public Map<String, Object> getSection() {
    return section;
  }

  public List<DeliverablePartnership> otherPartners(Deliverable deliverable) {
    try {
      List<DeliverablePartnership> list = deliverable.getDeliverablePartnerships().stream()
        .filter(dp -> dp.isActive() && dp.getPartnerType().equals(DeliverablePartnershipTypeEnum.OTHER.getValue()))
        .collect(Collectors.toList());
      return list;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void prepare() throws Exception {
    Map<String, Object> parameters = this.getParameters();
    loggedCrp = (Crp) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getCrpById(loggedCrp.getId());
    sectionName = StringUtils.trim(((String[]) parameters.get(APConstants.SECTION_NAME))[0]);

    projectID = Long.parseLong(StringUtils.trim(((String[]) parameters.get(APConstants.PROJECT_REQUEST_ID))[0]));
    // Validate if project exists.
    existProject = projectManager.existProject(projectID);

    // Validate if the section exists.
    validSection = ProjectSectionStatusEnum.value(sectionName) != null;


  }

  private DeliverablePartnership responsiblePartner(Deliverable deliverable) {
    try {
      DeliverablePartnership partnership = deliverable.getDeliverablePartnerships().stream()
        .filter(
          dp -> dp.isActive() && dp.getPartnerType().equals(DeliverablePartnershipTypeEnum.RESPONSIBLE.getValue()))
        .collect(Collectors.toList()).get(0);
      return partnership;
    } catch (Exception e) {
      return null;
    }
  }

  public void setSection(Map<String, Object> section) {
    this.section = section;
  }

  private void validateProjectActivities() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);

    project.setOpenProjectActivities(new ArrayList<Activity>(project.getActivities().stream()
      .filter(a -> a.isActive() && ((a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())
        || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())))))
      .collect(Collectors.toList())));


    if (project.getOpenProjectActivities() != null) {
      for (Activity openActivity : project.getOpenProjectActivities()) {
        openActivity.setDeliverables(new ArrayList<DeliverableActivity>(
          openActivity.getDeliverableActivities().stream().filter(da -> da.isActive()).collect(Collectors.toList())));
      }
    }

    project.setClosedProjectActivities(new ArrayList<Activity>(project.getActivities().stream()
      .filter(a -> a.isActive() && ((a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Complete.getStatusId())
        || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Cancelled.getStatusId())))))
      .collect(Collectors.toList())));

    if (project.getClosedProjectActivities() != null) {
      for (Activity closedActivity : project.getClosedProjectActivities()) {
        closedActivity.setDeliverables(new ArrayList<DeliverableActivity>(
          closedActivity.getDeliverableActivities().stream().filter(da -> da.isActive()).collect(Collectors.toList())));
      }
    }
    projectActivitiesValidator.validate(this, project, false);
  }


  private void validateProjectBudgets() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    if (!project.isBilateralProject()) {
      project.setBudgets(project.getProjectBudgets().stream()
        .filter(c -> c.isActive() && (c.getBudgetType().getId() != 3 || c.getBudgetType().getId() != 2)
          && c.getProjectBilateralCofinancing() == null)
        .collect(Collectors.toList()));

      project.setBudgetsCofinancing(project.getProjectBudgets().stream()
        .filter(c -> c.isActive() && (c.getBudgetType().getId() == 3 || c.getBudgetType().getId() == 2)
          && c.getProjectBilateralCofinancing() != null)
        .collect(Collectors.toList()));
    } else {
      project.setBudgets(project.getProjectBudgets().stream().filter(c -> c.isActive()).collect(Collectors.toList()));


    }
    projectBudgetsValidator.validate(this, project, false);
  }

  private void validateProjectBudgetsCoAs() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    project.setBudgetsCluserActvities(
      project.getProjectBudgetsCluserActvities().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

    projectBudgetsCoAValidator.validate(this, project, false);
  }


  private void validateProjectDeliverables() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    for (Deliverable deliverable : project.getDeliverables().stream().filter(d -> d.isActive())
      .collect(Collectors.toList())) {
      deliverable.setResponsiblePartner(this.responsiblePartner(deliverable));
      deliverable.setOtherPartners(this.otherPartners(deliverable));
      deliverableValidator.validate(this, deliverable, false);
    }

  }

  private void validateProjectDescription() {
    Project project = projectManager.getProjectById(projectID);
    List<CrpProgram> programs = new ArrayList<>();
    for (ProjectFocus projectFocuses : project.getProjectFocuses().stream()
      .filter(c -> c.isActive() && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList())) {
      programs.add(projectFocuses.getCrpProgram());
    }

    List<CrpProgram> regions = new ArrayList<>();
    for (ProjectFocus projectFocuses : project.getProjectFocuses().stream()
      .filter(c -> c.isActive() && c.getCrpProgram().getProgramType() == ProgramType.REGIONAL_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList())) {
      regions.add(projectFocuses.getCrpProgram());
    }

    List<ProjectClusterActivity> projectClusterActivities = new ArrayList<>();
    for (ProjectClusterActivity projectClusterActivity : project.getProjectClusterActivities().stream()
      .filter(c -> c.isActive()).collect(Collectors.toList())) {

      projectClusterActivity.getCrpClusterOfActivity().setLeaders(projectClusterActivity.getCrpClusterOfActivity()
        .getCrpClusterActivityLeaders().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      projectClusterActivities.add(projectClusterActivity);
    }

    List<ProjectScope> projectLocations = new ArrayList<>();
    for (ProjectScope projectLocation : project.getProjectScopes().stream().filter(c -> c.isActive())
      .collect(Collectors.toList())) {

      projectLocations.add(projectLocation);
    }
    project.setClusterActivities(projectClusterActivities);
    project.setFlagships(programs);
    project.setRegions(regions);
    project.setScopes(projectLocations);

    descriptionValidator.validate(this, project, false);
  }

  private void validateProjectLocations() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    project.setLocations(
      new ArrayList<>(project.getProjectLocations().stream().filter(pl -> pl.isActive()).collect(Collectors.toList())));

    locationValidator.validate(this, project, false);
  }


  private void validateProjectOutcomes() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    List<ProjectOutcome> projectOutcomes =
      project.getProjectOutcomes().stream().filter(c -> c.isActive()).collect(Collectors.toList());


    project.setOutcomes(projectOutcomes);
    for (ProjectOutcome projectOutcome : project.getOutcomes()) {
      projectOutcome.setMilestones(
        projectOutcome.getProjectMilestones().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

      projectOutcome.setCommunications(
        projectOutcome.getProjectCommunications().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      projectOutcome.setNextUsers(
        projectOutcome.getProjectNextusers().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

      projectOutcomeValidator.validate(this, projectOutcome, false);
    }

  }

  private void validateProjectParnters() {
    Project project = projectManager.getProjectById(projectID);
    project.setPartners(project.getProjectPartners().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
    for (ProjectPartner projectPartner : project.getPartners()) {
      projectPartner.setPartnerPersons(
        projectPartner.getProjectPartnerPersons().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
    }
    if (this.isLessonsActive()) {
      this.loadLessons(loggedCrp, project, ProjectSectionStatusEnum.PARTNERS.getStatus());
    }
    projectPartnerValidator.validate(this, project, false);

  }

}
