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

package org.cgiar.ccafs.marlo.action.projects;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.ActivityManager;
import org.cgiar.ccafs.marlo.data.manager.AuditLogManager;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableActivityManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectPartnerPersonManager;
import org.cgiar.ccafs.marlo.data.model.Activity;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableActivity;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectPartnerPerson;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.AutoSaveReader;
import org.cgiar.ccafs.marlo.validation.projects.ProjectActivitiesValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class ProjectActivitiesAction extends BaseAction {


  private static final long serialVersionUID = 2146101620783927003L;


  private ProjectActivitiesValidator activitiesValidator;


  private ActivityManager activityManager;


  private AuditLogManager auditLogManager;

  private CrpManager crpManager;

  private DeliverableActivityManager deliverableActivityManager;

  private DeliverableManager deliverableManager;

  private Crp loggedCrp;

  private List<ProjectPartnerPerson> partnerPersons;

  private Project project;

  private long projectID;

  private ProjectManager projectManager;

  private ProjectPartnerPersonManager projectPartnerPersonManager;

  private Map<String, String> status;

  private String transaction;

  @Inject
  public ProjectActivitiesAction(APConfig config, ProjectManager projectManager, CrpManager crpManager,
    ProjectPartnerPersonManager projectPartnerPersonManager, ActivityManager activityManager,
    DeliverableActivityManager deliverableActivityManager, DeliverableManager deliverableManager,
    AuditLogManager auditLogManager, ProjectActivitiesValidator activitiesValidator) {
    super(config);
    this.projectManager = projectManager;
    this.crpManager = crpManager;
    this.projectPartnerPersonManager = projectPartnerPersonManager;
    this.activityManager = activityManager;
    this.deliverableActivityManager = deliverableActivityManager;
    this.deliverableManager = deliverableManager;
    this.auditLogManager = auditLogManager;
    this.activitiesValidator = activitiesValidator;
  }


  public void activitiesNewData(List<Activity> activities) {

    for (Activity activity : activities) {
      if (activity.getId() == null || activity.getId() == -1) {

        Activity activityNew = new Activity();
        activityNew.setActive(true);
        activityNew.setCreatedBy(this.getCurrentUser());
        activityNew.setModifiedBy(this.getCurrentUser());
        activityNew.setModificationJustification("");
        activityNew.setActiveSince(new Date());

        Project project = projectManager.getProjectById(this.project.getId());

        activityNew.setProject(project);
        activityNew.setTitle(activity.getTitle());
        activityNew.setDescription(activity.getDescription());
        activityNew.setStartDate(activity.getStartDate());
        activityNew.setEndDate(activity.getEndDate());

        if (activity.getActivityStatus() != -1) {
          activityNew.setActivityStatus(activity.getActivityStatus());
        } else {
          activityNew.setActivityStatus(Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId()));
        }

        activityNew.setActivityProgress(activity.getActivityProgress());

        try {
          ProjectPartnerPerson partnerPerson =
            projectPartnerPersonManager.getProjectPartnerPersonById(activity.getProjectPartnerPerson().getId());
          activityNew.setProjectPartnerPerson(partnerPerson);
        } catch (Exception e) {
          activityNew.setProjectPartnerPerson(null);
        }


        Long activityID = activityManager.saveActivity(activityNew);

        if (activity.getDeliverables() != null) {
          for (DeliverableActivity deliverableActivity : activity.getDeliverables()) {

            DeliverableActivity deliverableActivityNew = new DeliverableActivity();

            deliverableActivityNew.setActivity(activityManager.getActivityById(activityID));
            deliverableActivityNew.setActive(true);
            deliverableActivityNew.setCreatedBy(this.getCurrentUser());
            deliverableActivityNew.setModifiedBy(this.getCurrentUser());
            deliverableActivityNew.setModificationJustification("");
            deliverableActivityNew.setActiveSince(new Date());

            Deliverable deliverable =
              deliverableManager.getDeliverableById(deliverableActivity.getDeliverable().getId());

            deliverableActivityNew.setDeliverable(deliverable);

            deliverableActivityManager.saveDeliverableActivity(deliverableActivityNew);


          }
        }
      } else {

        Activity activityUpdate = activityManager.getActivityById(activity.getId());
        activityUpdate.setActive(true);
        activityUpdate.setCreatedBy(this.getCurrentUser());
        activityUpdate.setModifiedBy(this.getCurrentUser());
        activityUpdate.setModificationJustification("");
        activityUpdate.setActiveSince(new Date());

        activityUpdate.setTitle(activity.getTitle());
        activityUpdate.setDescription(activity.getDescription());
        activityUpdate.setStartDate(activity.getStartDate());
        activityUpdate.setEndDate(activity.getEndDate());
        if (activity.getActivityStatus() != -1) {
          activityUpdate.setActivityStatus(activity.getActivityStatus());
        } else {
          activityUpdate.setActivityStatus(Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId()));
        }
        activityUpdate.setActivityProgress(activity.getActivityProgress());

        ProjectPartnerPerson partnerPerson =
          projectPartnerPersonManager.getProjectPartnerPersonById(activity.getProjectPartnerPerson().getId());

        activityUpdate.setProjectPartnerPerson(partnerPerson);

        Long activityID = activityManager.saveActivity(activityUpdate);

        activityUpdate = activityManager.getActivityById(activityID);

        if (activity.getDeliverables() != null) {

          List<DeliverableActivity> deliverableActivitiesPrew =
            activityUpdate.getDeliverableActivities().stream().filter(da -> da.isActive()).collect(Collectors.toList());

          for (DeliverableActivity deliverableActivity : deliverableActivitiesPrew) {
            if (!activity.getDeliverables().contains(deliverableActivity)) {

              deliverableActivityManager.deleteDeliverableActivity(deliverableActivity.getId());
            }
          }

          for (DeliverableActivity deliverableActivity : activity.getDeliverables()) {
            if (deliverableActivity.getId() == null || deliverableActivity.getId() == -1) {

              DeliverableActivity deliverableActivityNew = new DeliverableActivity();

              deliverableActivityNew.setActivity(activityUpdate);
              deliverableActivityNew.setActive(true);
              deliverableActivityNew.setCreatedBy(this.getCurrentUser());
              deliverableActivityNew.setModifiedBy(this.getCurrentUser());
              deliverableActivityNew.setModificationJustification("");
              deliverableActivityNew.setActiveSince(new Date());

              Deliverable deliverable =
                deliverableManager.getDeliverableById(deliverableActivity.getDeliverable().getId());

              deliverableActivityNew.setDeliverable(deliverable);

              deliverableActivityManager.saveDeliverableActivity(deliverableActivityNew);
            }
          }
        }
      }
    }

  }

  public void activitiesPreviousData(List<Activity> activities, boolean activitiesOpen) {

    List<Activity> activitiesPrew;


    if (activitiesOpen) {
      activitiesPrew = activityManager.findAll().stream()
        .filter(a -> a.isActive() && a.getProject().getId() == project.getId()
          && (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())
            || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId()))))
        .collect(Collectors.toList());
    } else {
      activitiesPrew = activityManager.findAll().stream()
        .filter(a -> a.isActive() && a.getProject().getId() == project.getId()
          && (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Cancelled.getStatusId())
            || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Complete.getStatusId()))))
        .collect(Collectors.toList());
    }


    for (Activity activity : activitiesPrew) {
      if (!activities.contains(activity)) {

        for (DeliverableActivity deliverableActivity : activity.getDeliverableActivities().stream()
          .filter(da -> da.isActive()).collect(Collectors.toList())) {
          deliverableActivityManager.deleteDeliverableActivity(deliverableActivity.getId());
        }

        activityManager.deleteActivity(activity.getId());
      }
    }

  }

  @Override
  public String cancel() {

    Path path = this.getAutoSaveFilePath();

    if (path.toFile().exists()) {

      boolean fileDeleted = path.toFile().delete();
    }

    this.setDraft(false);
    Collection<String> messages = this.getActionMessages();
    if (!messages.isEmpty()) {
      String validationMessage = messages.iterator().next();
      this.setActionMessages(null);
      this.addActionWarning(this.getText("cancel.autoSave") + validationMessage);
    } else {
      this.addActionMessage(this.getText("cancel.autoSave"));
    }
    messages = this.getActionMessages();

    return SUCCESS;
  }

  private Path getAutoSaveFilePath() {
    String composedClassName = project.getClass().getSimpleName();
    String actionFile = this.getActionName().replace("/", "_");
    String autoSaveFile = project.getId() + "_" + composedClassName + "_" + actionFile + ".json";

    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);
  }

  public Crp getLoggedCrp() {
    return loggedCrp;
  }

  public List<ProjectPartnerPerson> getPartnerPersons() {
    return partnerPersons;
  }

  public Project getProject() {
    return project;
  }

  public long getProjectID() {
    return projectID;
  }

  public Map<String, String> getStatus() {
    return status;
  }

  public String getTransaction() {
    return transaction;
  }

  @Override
  public void prepare() throws Exception {
    loggedCrp = (Crp) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getCrpById(loggedCrp.getId());

    projectID = Long.parseLong(StringUtils.trim(this.getRequest().getParameter(APConstants.PROJECT_REQUEST_ID)));

    if (this.getRequest().getParameter(APConstants.TRANSACTION_ID) != null) {

      transaction = StringUtils.trim(this.getRequest().getParameter(APConstants.TRANSACTION_ID));
      Project history = (Project) auditLogManager.getHistory(transaction);

      if (history != null) {
        project = history;
      } else {
        this.transaction = null;

        this.setTransaction("-1");
      }

    } else {
      project = projectManager.getProjectById(projectID);
    }
    if (project != null) {

      Path path = this.getAutoSaveFilePath();

      if (path.toFile().exists() && this.getCurrentUser().isAutoSave()) {

        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader(path.toFile()));

        Gson gson = new GsonBuilder().create();


        JsonObject jReader = gson.fromJson(reader, JsonObject.class);

        AutoSaveReader autoSaveReader = new AutoSaveReader();

        project = (Project) autoSaveReader.readFromJson(jReader);
        Project projectDb = projectManager.getProjectById(project.getId());
        project.setProjectEditLeader(projectDb.isProjectEditLeader());
        project.setProjectLocations(projectDb.getProjectLocations());

        // List<Activity> activities = new ArrayList<>();
        // for (Activity activity : project.getOpenProjectActivities()) {
        // List<DeliverableActivity> deliverableActivities = new ArrayList<>();
        // if (activity.getDeliverables() != null) {
        // for (DeliverableActivity deliverableActivity : activity.getDeliverables()) {
        // deliverableActivity =
        // deliverableActivityManager.getDeliverableActivityById(deliverableActivity.getId());
        // }
        // activity.setDeliverables(deliverableActivities);
        // activities.add(activity);
        // }
        // }
        //
        // project.setOpenProjectActivities(activities);

        // for (Activity activity : project.getClosedProjectActivities()) {
        // List<DeliverableActivity> deliverableActivities = new ArrayList<>();
        // if (activity.getDeliverables() != null) {
        // for (DeliverableActivity deliverableActivity : activity.getDeliverables()) {
        // deliverableActivities
        // .add(deliverableActivityManager.getDeliverableActivityById(deliverableActivity.getId()));
        // }
        // activity.setDeliverables(deliverableActivities);
        // }
        // }

        reader.close();
        this.setDraft(true);
      } else {
        this.setDraft(false);
        project
          .setOpenProjectActivities(
            new ArrayList<Activity>(project.getActivities().stream()
              .filter(a -> a.isActive()
                && ((a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())
                  || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())))))
              .collect(Collectors.toList())));

        if (project.getOpenProjectActivities() != null) {
          for (Activity openActivity : project.getOpenProjectActivities()) {
            openActivity.setDeliverables(new ArrayList<DeliverableActivity>(openActivity.getDeliverableActivities()
              .stream().filter(da -> da.isActive()).collect(Collectors.toList())));
          }
        }

        project
          .setClosedProjectActivities(
            new ArrayList<Activity>(project.getActivities().stream()
              .filter(a -> a.isActive()
                && ((a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Complete.getStatusId())
                  || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Cancelled.getStatusId())))))
              .collect(Collectors.toList())));

        if (project.getClosedProjectActivities() != null) {
          for (Activity closedActivity : project.getClosedProjectActivities()) {
            closedActivity.setDeliverables(new ArrayList<DeliverableActivity>(closedActivity.getDeliverableActivities()
              .stream().filter(da -> da.isActive()).collect(Collectors.toList())));
          }
        }
      }


      status = new HashMap<>();
      List<ProjectStatusEnum> list = Arrays.asList(ProjectStatusEnum.values());
      for (ProjectStatusEnum projectStatusEnum : list) {
        status.put(projectStatusEnum.getStatusId(), projectStatusEnum.getStatus());
      }

      project.setProjectDeliverables(new ArrayList<Deliverable>(
        project.getDeliverables().stream().filter(d -> d.isActive()).collect(Collectors.toList())));

      partnerPersons = projectPartnerPersonManager.findAll().stream()
        .filter(pp -> pp.isActive() && pp.getProjectPartner().getProject().getId() == project.getId())
        .collect(Collectors.toList());

    }

    String params[] = {loggedCrp.getAcronym(), project.getId() + ""};
    this.setBasePermission(this.getText(Permission.PROJECT_ACTIVITIES_BASE_PERMISSION, params));

    if (this.isHttpPost()) {
      if (project.getOpenProjectActivities() != null) {
        project.getOpenProjectActivities().clear();
      }

      if (project.getClosedProjectActivities() != null) {
        project.getClosedProjectActivities().clear();
      }

      if (partnerPersons != null) {
        partnerPersons.clear();
      }

      if (project.getProjectDeliverables() != null) {
        project.getProjectDeliverables().clear();
      }
    }
  }

  @Override
  public String save() {
    if (this.hasPermission("canEdit")) {

      Project projectDB = projectManager.getProjectById(project.getId());
      project.setActive(true);
      project.setCreatedBy(projectDB.getCreatedBy());
      project.setModifiedBy(this.getCurrentUser());
      project.setModificationJustification("");
      project.setActiveSince(projectDB.getActiveSince());

      this.activitiesPreviousData(project.getOpenProjectActivities(), true);
      this.activitiesNewData(project.getOpenProjectActivities());

      this.activitiesPreviousData(project.getClosedProjectActivities(), false);
      this.activitiesNewData(project.getClosedProjectActivities());

      List<String> relationsName = new ArrayList<>();
      relationsName.add(APConstants.PROJECT_ACTIVITIES_RELATION);
      project = projectManager.getProjectById(projectID);
      project.setActiveSince(new Date());
      project.setModifiedBy(this.getCurrentUser());
      projectManager.saveProject(project, this.getActionName(), relationsName);
      Path path = this.getAutoSaveFilePath();

      if (path.toFile().exists()) {
        path.toFile().delete();
      }
      Collection<String> messages = this.getActionMessages();
      if (!messages.isEmpty()) {
        String validationMessage = messages.iterator().next();
        this.setActionMessages(null);
        this.addActionWarning(this.getText("saving.saved") + validationMessage);
      } else {
        this.addActionMessage(this.getText("saving.saved"));
      }
      messages = this.getActionMessages();

      return SUCCESS;
    } else {
      return NOT_AUTHORIZED;
    }
  }

  public void setLoggedCrp(Crp loggedCrp) {
    this.loggedCrp = loggedCrp;
  }

  public void setPartnerPersons(List<ProjectPartnerPerson> partnerPersons) {
    this.partnerPersons = partnerPersons;
  }

  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectID(long projectID) {
    this.projectID = projectID;
  }

  public void setStatus(Map<String, String> status) {
    this.status = status;
  }


  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  @Override
  public void validate() {
    if (save) {
      activitiesValidator.validate(this, project, true);
    }
  }

}
