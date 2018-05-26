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

package org.cgiar.ccafs.marlo.action.summaries;

import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.config.MarloLocalizedTextProvider;
import org.cgiar.ccafs.marlo.data.manager.CrossCuttingScoringManager;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramManager;
import org.cgiar.ccafs.marlo.data.manager.GenderTypeManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.InstitutionManager;
import org.cgiar.ccafs.marlo.data.manager.IpElementManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectBudgetManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.RepositoryChannelManager;
import org.cgiar.ccafs.marlo.data.manager.SrfTargetUnitManager;
import org.cgiar.ccafs.marlo.data.model.CrpTargetUnit;
import org.cgiar.ccafs.marlo.data.model.GlobalUnitProject;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectInfo;
import org.cgiar.ccafs.marlo.data.model.SrfTargetUnit;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.ReportingSummaryContext;
import org.cgiar.ccafs.marlo.utils.ReportingSummaryService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.opensymphony.xwork2.LocalizedTextProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReportingSummaryAction
 * 
 * @author avalencia - CCAFS
 * @author Christian Garcia - CIAT/CCAFS
 * @date Nov 8, 2017
 * @time 10:30:10 AM: get deliverable dissemination from RepositoryChannel table
 */
public class ReportingSummaryAction extends BaseSummariesAction implements Summary {

  private static final long serialVersionUID = -624982650510682813L;

  private static Logger LOG = LoggerFactory.getLogger(ReportingSummaryAction.class);

  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  private final LocalizedTextProvider localizedTextProvider;


  // PDF bytes
  private byte[] bytesPDF;

  // Streams
  InputStream inputStream;

  // Parameters
  private long startTime;
  private HashMap<Long, String> targetUnitList;
  private Boolean hasW1W2Co;
  private Boolean hasGender;
  private Project project;
  private long projectID;
  private ProjectInfo projectInfo;


  // Managers
  private final ProjectManager projectManager;

  private final InstitutionManager institutionManager;
  private final ProjectBudgetManager projectBudgetManager;

  private final IpElementManager ipElementManager;

  private final SrfTargetUnitManager srfTargetUnitManager;

  private final ReportingSummaryService reportingSummaryService;


  @Inject
  public ReportingSummaryAction(APConfig config, GlobalUnitManager crpManager, ProjectManager projectManager,
    GenderTypeManager genderTypeManager, CrpProgramManager programManager, InstitutionManager institutionManager,
    ProjectBudgetManager projectBudgetManager, LocElementManager locElementManager, IpElementManager ipElementManager,
    SrfTargetUnitManager srfTargetUnitManager, PhaseManager phaseManager,
    RepositoryChannelManager repositoryChannelManager, LocalizedTextProvider localizedTextProvider,
    CrossCuttingScoringManager crossCuttingScoringManager, ResourceManager resourceManager,
    ReportingSummaryService reportingSummaryService) {
    super(config, crpManager, phaseManager);
    this.projectManager = projectManager;
    this.institutionManager = institutionManager;
    this.projectBudgetManager = projectBudgetManager;
    this.ipElementManager = ipElementManager;
    this.localizedTextProvider = localizedTextProvider;
    this.srfTargetUnitManager = srfTargetUnitManager;
    this.reportingSummaryService = reportingSummaryService;
  }


  @Override
  public String execute() throws Exception {
    try {
      hasGender = this.hasSpecificities(APConstants.CRP_BUDGET_GENDER);
    } catch (Exception e) {
      LOG.warn("Failed to get " + APConstants.CYCLE + " parameter. Parameter will be set as false. Exception: "
        + e.getMessage());
      hasGender = false;
    }
    // get w1w2 co
    try {
      hasW1W2Co = this.hasSpecificities(APConstants.CRP_FS_W1W2_COFINANCING);
    } catch (Exception e) {
      LOG.warn("Failed to get " + APConstants.CYCLE + " parameter. Parameter will be set as false. Exception: "
        + e.getMessage());
      hasW1W2Co = false;
    }

    // Fill target unit list
    targetUnitList = new HashMap<>();
    if (srfTargetUnitManager.findAll() != null) {
      List<SrfTargetUnit> targetUnits = new ArrayList<>();
      List<CrpTargetUnit> crpTargetUnits = new ArrayList<>(
        this.getLoggedCrp().getCrpTargetUnits().stream().filter(tu -> tu.isActive()).collect(Collectors.toList()));
      for (CrpTargetUnit crpTargetUnit : crpTargetUnits) {
        targetUnits.add(crpTargetUnit.getSrfTargetUnit());
      }
      Collections.sort(targetUnits,
        (tu1, tu2) -> tu1.getName().toLowerCase().trim().compareTo(tu2.getName().toLowerCase().trim()));
      for (SrfTargetUnit srfTargetUnit : targetUnits) {
        targetUnitList.put(srfTargetUnit.getId(), srfTargetUnit.getName());
      }
    }
    // Calculate time to generate report
    startTime = System.currentTimeMillis();
    LOG.info(
      "Start report download: " + this.getFileName() + ". User: " + this.getCurrentUser().getComposedCompleteName()
        + ". CRP: " + this.getLoggedCrp().getAcronym() + ". Cycle: " + this.getSelectedCycle());

    /** Build ReportingSummaryContext **/
    ReportingSummaryContext context = ReportingSummaryContext.builder().withHasGender(hasGender)
      .withHasW1W2Co(hasW1W2Co).withProject(project).withProjectInfo(projectInfo)
      .withSelectedCycle(this.getSelectedCycle()).withSelectedPhase(this.getSelectedPhase())
      .withSelectedYear(this.getSelectedYear()).withTargetUnitList(targetUnitList).withCrpAcronym(this.getCrpSession())
      .withActivitiesSpecifityEnabled(this.hasSpecificities(APConstants.CRP_ACTIVITES_MODULE))
      .withBudgetGenderSpecifityEnabled(this.hasSpecificities(APConstants.CRP_BUDGET_GENDER))
      .withIpOutcomeIndicatorSpecificityEnabled(this.hasSpecificities(APConstants.CRP_IP_OUTCOME_INDICATOR))
      .withHasProgramRegions(this.hasProgramnsRegions()).withLoggedCrp(this.getLoggedCrp())
      .withIsPhaseOne(this.isPhaseOne()).withIsProjectNew(this.isProjectNew(project.getId())).build();

    bytesPDF = reportingSummaryService.getReport(context);

    // Calculate time of generation
    long stopTime = System.currentTimeMillis();
    stopTime = stopTime - startTime;
    LOG.info("Downloaded successfully: " + this.getFileName() + ". User: "
      + this.getCurrentUser().getComposedCompleteName() + ". CRP: " + this.getLoggedCrp().getAcronym() + ". Cycle: "
      + this.getSelectedCycle() + ". Time to generate: " + stopTime + "ms.");
    return SUCCESS;
  }


  public byte[] getBytesPDF() {
    return bytesPDF;
  }


  @Override
  public int getContentLength() {
    return bytesPDF.length;
  }

  @Override
  public String getContentType() {
    return "application/pdf";
  }


  @SuppressWarnings("unused")
  private File getFile(String fileName) {
    // Get file from resources folder
    ClassLoader classLoader = this.getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    return file;
  }

  @Override
  public String getFileName() {
    // Get The Crp/Center/Platform where the project was created
    GlobalUnitProject globalUnitProject = project.getGlobalUnitProjects().stream()
      .filter(gu -> gu.isActive() && gu.isOrigin()).collect(Collectors.toList()).get(0);

    StringBuffer fileName = new StringBuffer();
    fileName.append("FullProjectReportSummary-");
    fileName.append(globalUnitProject.getGlobalUnit().getAcronym() + "-");
    fileName.append("P" + projectID + "-");
    fileName.append(this.getSelectedYear() + "_");
    fileName.append(new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date()));
    fileName.append(".pdf");
    return fileName.toString();
  }


  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      inputStream = new ByteArrayInputStream(bytesPDF);
    }
    return inputStream;
  }


  public Project getProject() {
    return project;
  }


  public long getProjectID() {
    return projectID;
  }

  public ProjectInfo getProjectInfo() {
    return projectInfo;
  }


  public HashMap<Long, String> getTargetUnitList() {
    return targetUnitList;
  }

  @Override
  public String getText(String aTextName) {
    String language = APConstants.CUSTOM_LAGUAGE;


    Locale locale = new Locale(language);

    return localizedTextProvider.findDefaultText(aTextName, locale);
  }

  @Override
  public String getText(String key, String[] args) {
    String language = APConstants.CUSTOM_LAGUAGE;


    Locale locale = new Locale(language);

    return localizedTextProvider.findDefaultText(key, locale, args);

  }


  public void loadProvider(Map<String, Object> session) {
    String language = APConstants.CUSTOM_LAGUAGE;
    String pathFile = APConstants.PATH_CUSTOM_FILES;
    if (session.containsKey(APConstants.CRP_LANGUAGE)) {
      language = (String) session.get(APConstants.CRP_LANGUAGE);
    }

    Locale locale = new Locale(language);

    /**
     * This is yuck to have to cast the interface to a custom implementation but I can't see a nice way to remove custom
     * properties bundles (the reason we are doing this is the scenario where a user navigates between CRPs. If we don't
     * reset the properties bundles then the user will potentially get the properties loaded from another CRP if that
     * property has not been defined by that CRP or Center.
     */
    ((MarloLocalizedTextProvider) this.localizedTextProvider).resetResourceBundles();

    this.localizedTextProvider.addDefaultResourceBundle(APConstants.CUSTOM_FILE);


    try {
      ServletActionContext.getContext().setLocale(locale);
    } catch (Exception e) {

    }

    if (session.containsKey(APConstants.SESSION_CRP)) {

      if (session.containsKey(APConstants.CRP_CUSTOM_FILE)) {
        pathFile = pathFile + session.get(APConstants.CRP_CUSTOM_FILE);
        this.localizedTextProvider.addDefaultResourceBundle(pathFile);
      } else if (session.containsKey(APConstants.CENTER_CUSTOM_FILE)) {
        pathFile = pathFile + session.get(APConstants.CENTER_CUSTOM_FILE);
        this.localizedTextProvider.addDefaultResourceBundle(pathFile);
      } else {

        this.localizedTextProvider.addDefaultResourceBundle(APConstants.CUSTOM_FILE);
      }
    }
  }

  @Override
  /**
   * Prepare the parameters of the project.
   * Note: If you add a parameter here, you must add it in the ProjectSubmissionAction class
   */
  public void prepare() {
    this.loadProvider(this.getSession());
    this.setGeneralParameters();

    // Set projectID
    try {
      this
        .setProjectID(Long.parseLong(StringUtils.trim(this.getRequest().getParameter(APConstants.PROJECT_REQUEST_ID))));
      this.setCrpSession(this.getLoggedCrp().getAcronym());
    } catch (Exception e) {
      LOG.error("Failed to get " + APConstants.PROJECT_REQUEST_ID + " parameter. Exception: " + e.getMessage());
    }
    // Get project from DB
    try {
      this.setProject(projectManager.getProjectById(this.getProjectID()));
    } catch (Exception e) {
      LOG.error("Failed to get project. Exception: " + e.getMessage());
    }
    this.setProjectInfo(project.getProjecInfoPhase(this.getSelectedPhase()));
  }

  public void setBytesPDF(byte[] bytesPDF) {
    this.bytesPDF = bytesPDF;
  }

  public void setHasW1W2Co(Boolean hasW1W2Co) {
    this.hasW1W2Co = hasW1W2Co;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void setProjectID(long projectID) {
    this.projectID = projectID;
  }

  public void setProjectInfo(ProjectInfo projectInfo) {
    this.projectInfo = projectInfo;
  }

  public void setTargetUnitList(HashMap<Long, String> targetUnitList) {
    this.targetUnitList = targetUnitList;
  }


}