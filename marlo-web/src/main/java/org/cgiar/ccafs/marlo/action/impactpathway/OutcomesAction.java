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

package org.cgiar.ccafs.marlo.action.impactpathway;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.AuditLogManager;
import org.cgiar.ccafs.marlo.data.manager.CrpAssumptionManager;
import org.cgiar.ccafs.marlo.data.manager.CrpMilestoneManager;
import org.cgiar.ccafs.marlo.data.manager.CrpOutcomeSubIdoManager;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramManager;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramOutcomeIndicatorManager;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramOutcomeManager;
import org.cgiar.ccafs.marlo.data.manager.FileDBManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.PowbIndAssesmentRiskManager;
import org.cgiar.ccafs.marlo.data.manager.PowbIndFollowingMilestoneManager;
import org.cgiar.ccafs.marlo.data.manager.PowbIndMilestoneRiskManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndGenderYouthFocusLevelManager;
import org.cgiar.ccafs.marlo.data.manager.SrfIdoManager;
import org.cgiar.ccafs.marlo.data.manager.SrfSubIdoManager;
import org.cgiar.ccafs.marlo.data.manager.SrfTargetUnitManager;
import org.cgiar.ccafs.marlo.data.manager.UserManager;
import org.cgiar.ccafs.marlo.data.model.CrpAssumption;
import org.cgiar.ccafs.marlo.data.model.CrpMilestone;
import org.cgiar.ccafs.marlo.data.model.CrpOutcomeSubIdo;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.CrpProgramLeader;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcome;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcomeIndicator;
import org.cgiar.ccafs.marlo.data.model.CrpTargetUnit;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.PowbIndAssesmentRisk;
import org.cgiar.ccafs.marlo.data.model.PowbIndFollowingMilestone;
import org.cgiar.ccafs.marlo.data.model.PowbIndMilestoneRisk;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.RepIndGenderYouthFocusLevel;
import org.cgiar.ccafs.marlo.data.model.SrfIdo;
import org.cgiar.ccafs.marlo.data.model.SrfSubIdo;
import org.cgiar.ccafs.marlo.data.model.SrfTargetUnit;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.AutoSaveReader;
import org.cgiar.ccafs.marlo.utils.HistoryComparator;
import org.cgiar.ccafs.marlo.utils.HistoryDifference;
import org.cgiar.ccafs.marlo.validation.impactpathway.OutcomeValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 * @author Christian Garcia - CIAT/CCAFS
 */
public class OutcomesAction extends BaseAction {


  private static final long serialVersionUID = -793652591843623397L;


  private AuditLogManager auditLogManager;


  private CrpAssumptionManager crpAssumptionManager;


  private HistoryComparator historyComparator;


  // GlobalUnit Manager
  private GlobalUnitManager crpManager;

  private CrpProgramOutcomeIndicatorManager crpProgramOutcomeIndicatorManager;


  private CrpMilestoneManager crpMilestoneManager;


  private CrpOutcomeSubIdoManager crpOutcomeSubIdoManager;


  private long crpProgramID;


  private FileDBManager fileDBManager;


  private CrpProgramManager crpProgramManager;

  private CrpProgramOutcomeManager crpProgramOutcomeManager;


  private HashMap<Long, String> idoList;

  private GlobalUnit loggedCrp;


  private List<Integer> milestoneYears;


  private List<CrpProgramOutcome> outcomes;
  private List<CrpProgram> programs;
  private CrpProgram selectedProgram;

  private SrfIdoManager srfIdoManager;
  private List<SrfIdo> srfIdos;
  private SrfSubIdoManager srfSubIdoManager;

  private SrfTargetUnitManager srfTargetUnitManager;
  private HashMap<Long, String> targetUnitList;
  private String transaction;
  private UserManager userManager;
  private OutcomeValidator validator;
  // POWB 2019 Milestones New List
  private List<PowbIndAssesmentRisk> assessmentRisks;
  private List<RepIndGenderYouthFocusLevel> focusLevels;
  private List<PowbIndMilestoneRisk> milestoneRisks;
  private PowbIndAssesmentRiskManager powbIndAssesmentRiskManager;
  private RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager;
  private PowbIndMilestoneRiskManager powbIndMilestoneRiskManager;
  private List<PowbIndFollowingMilestone> followingMilestones;
  private PowbIndFollowingMilestoneManager powbIndFollowingMilestoneManager;

  @Inject
  public OutcomesAction(APConfig config, SrfTargetUnitManager srfTargetUnitManager, SrfIdoManager srfIdoManager,
    CrpProgramOutcomeManager crpProgramOutcomeManager, CrpMilestoneManager crpMilestoneManager,
    CrpProgramManager crpProgramManager, OutcomeValidator validator, CrpOutcomeSubIdoManager crpOutcomeSubIdoManager,
    CrpAssumptionManager crpAssumptionManager, GlobalUnitManager crpManager, UserManager userManager,
    HistoryComparator historyComparator, AuditLogManager auditLogManager, FileDBManager fileDBManager,
    CrpProgramOutcomeIndicatorManager crpProgramOutcomeIndicator, SrfSubIdoManager srfSubIdoManager,
    PowbIndAssesmentRiskManager powbIndAssesmentRiskManager,
    RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager,
    PowbIndMilestoneRiskManager powbIndMilestoneRiskManager,
    PowbIndFollowingMilestoneManager powbIndFollowingMilestoneManager) {
    super(config);
    this.srfTargetUnitManager = srfTargetUnitManager;
    this.srfIdoManager = srfIdoManager;
    this.crpProgramOutcomeManager = crpProgramOutcomeManager;
    this.crpMilestoneManager = crpMilestoneManager;
    this.crpProgramManager = crpProgramManager;
    this.fileDBManager = fileDBManager;
    this.historyComparator = historyComparator;
    this.validator = validator;
    this.crpOutcomeSubIdoManager = crpOutcomeSubIdoManager;
    this.crpManager = crpManager;
    this.userManager = userManager;
    this.crpAssumptionManager = crpAssumptionManager;
    this.auditLogManager = auditLogManager;
    this.srfSubIdoManager = srfSubIdoManager;
    this.crpProgramOutcomeIndicatorManager = crpProgramOutcomeIndicator;
    this.powbIndAssesmentRiskManager = powbIndAssesmentRiskManager;
    this.repIndGenderYouthFocusLevelManager = repIndGenderYouthFocusLevelManager;
    this.powbIndMilestoneRiskManager = powbIndMilestoneRiskManager;
    this.powbIndFollowingMilestoneManager = powbIndFollowingMilestoneManager;
  }

  @Override
  public String cancel() {

    Path path = this.getAutoSaveFilePath();

    if (path.toFile().exists()) {
      path.toFile().delete();
    }

    this.setDraft(false);
    Collection<String> messages = this.getActionMessages();
    if (!messages.isEmpty()) {
      String validationMessage = messages.iterator().next();
      this.setActionMessages(null);
      this.addActionMessage("draft:" + this.getText("cancel.autoSave"));
    } else {
      this.addActionMessage("draft:" + this.getText("cancel.autoSave"));
    }
    messages = this.getActionMessages();

    return SUCCESS;
  }

  public boolean canEditMileStone(CrpMilestone crpMilestone) {
    if (crpMilestone.getYear() == null) {
      return true;
    }
    if (crpMilestone.getYear().intValue() == -1) {
      return true;
    }
    if (crpMilestone.getYear().intValue() >= this.getActualPhase().getYear()) {
      return true;
    }
    return false;
  }

  public List<PowbIndAssesmentRisk> getAssessmentRisks() {
    return assessmentRisks;
  }

  private Path getAutoSaveFilePath() {
    String composedClassName = selectedProgram.getClass().getSimpleName();
    String actionFile = this.getActionName().replace("/", "_");
    String autoSaveFile = selectedProgram.getId() + "_" + composedClassName + "_" + this.getActualPhase().getName()
      + "_" + this.getActualPhase().getYear() + "_" + actionFile + ".json";

    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);
  }

  public String getBaseLineFileURL(String outcomeID) {
    String path = config.getDownloadURL() + "/" + this.getBaseLineFileUrlPath(outcomeID).replace('\\', '/');
    return path;
  }

  public String getBaseLineFileUrlPath(String outcomeID) {
    return config.getProjectsBaseFolder(this.getCrpSession()) + File.separator + outcomeID + File.separator + "baseLine"
      + File.separator;
  }

  public long getCrpProgramID() {
    return crpProgramID;
  }

  public List<RepIndGenderYouthFocusLevel> getFocusLevels() {
    return focusLevels;
  }

  public List<PowbIndFollowingMilestone> getFollowingMilestones() {
    return followingMilestones;
  }

  public HashMap<Long, String> getIdoList() {
    return idoList;
  }

  public GlobalUnit getLoggedCrp() {
    return loggedCrp;
  }

  public List<PowbIndMilestoneRisk> getMilestoneRisks() {
    return milestoneRisks;
  }

  public List<Integer> getMilestoneYears() {
    return milestoneYears;
  }


  public List<CrpProgramOutcome> getOutcomes() {
    return outcomes;
  }

  public PowbIndAssesmentRiskManager getPowbIndAssesmentRiskManager() {
    return powbIndAssesmentRiskManager;
  }

  public PowbIndMilestoneRiskManager getPowbIndMilestoneRiskManager() {
    return powbIndMilestoneRiskManager;
  }

  public List<CrpProgram> getPrograms() {
    return programs;
  }

  public RepIndGenderYouthFocusLevelManager getRepIndGenderYouthFocusLevelManager() {
    return repIndGenderYouthFocusLevelManager;
  }


  public CrpProgram getSelectedProgram() {
    return selectedProgram;
  }


  public List<SrfIdo> getSrfIdos() {
    return srfIdos;
  }

  public HashMap<Long, String> getTargetUnitList() {
    return targetUnitList;
  }

  public List<Integer> getTargetYears() {
    List<Integer> targetYears = new ArrayList<>();

    Date date = new Date();

    Calendar calendarStart = new GregorianCalendar(2017, 0, 10, 01, 10, 30);

    Calendar calendarEnd = Calendar.getInstance();
    calendarEnd.set(Calendar.YEAR, APConstants.END_YEAR);

    while (calendarStart.get(Calendar.YEAR) <= calendarEnd.get(Calendar.YEAR)) {

      // Adding the year to the list.
      if (calendarStart.get(Calendar.YEAR) >= this.getActualPhase().getYear()) {
        targetYears.add(calendarStart.get(Calendar.YEAR));
      }

      // Adding a year (365 days) to the start date.
      calendarStart.add(Calendar.YEAR, 1);
    }

    return targetYears;
  }

  public String getTransaction() {
    return transaction;
  }


  public void loadInfo() {
    for (CrpProgramOutcome crpProgramOutcome : outcomes) {

      crpProgramOutcome.setMilestones(
        crpProgramOutcome.getCrpMilestones().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

      crpProgramOutcome.setIndicators(crpProgramOutcome.getCrpProgramOutcomeIndicators().stream()
        .filter(c -> c.isActive()).collect(Collectors.toList()));
      crpProgramOutcome.setSubIdos(
        crpProgramOutcome.getCrpOutcomeSubIdos().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      if (crpProgramOutcome.getFile() != null) {
        if (crpProgramOutcome.getFile().getId() != null) {
          crpProgramOutcome.setFile(fileDBManager.getFileDBById(crpProgramOutcome.getFile().getId()));
        } else {
          crpProgramOutcome.setFile(null);
        }
      }

      for (CrpOutcomeSubIdo crpOutcomeSubIdo : crpProgramOutcome.getSubIdos()) {
        List<CrpAssumption> assumptions =
          crpOutcomeSubIdo.getCrpAssumptions().stream().filter(c -> c.isActive()).collect(Collectors.toList());
        crpOutcomeSubIdo.setAssumptions(assumptions);
        HashMap<Long, String> mapSubidos = new HashMap<>();
        try {
          for (SrfSubIdo srfSubIdo : crpOutcomeSubIdo.getSrfSubIdo().getSrfIdo().getSrfSubIdos().stream()
            .filter(c -> c.isActive()).collect(Collectors.toList())) {

            if (srfSubIdo.getSrfIdo().isIsCrossCutting()) {
              mapSubidos.put(srfSubIdo.getId(), "CrossCutting:" + srfSubIdo.getDescription());
            } else {
              mapSubidos.put(srfSubIdo.getId(), srfSubIdo.getDescription());
            }


          }
        } catch (Exception e) {

        }
        crpOutcomeSubIdo.setSubIdoList(mapSubidos);
      }

    }
  }


  @Override
  public void prepare() throws Exception {


    // IAuditLog ia = auditLogManager.getHistory(4);
    loggedCrp = (GlobalUnit) this.getSession().get(APConstants.SESSION_CRP);
    outcomes = new ArrayList<CrpProgramOutcome>();
    loggedCrp = crpManager.getGlobalUnitById(loggedCrp.getId());
    targetUnitList = new HashMap<>();
    if (srfTargetUnitManager.findAll() != null) {

      List<SrfTargetUnit> targetUnits = new ArrayList<>();

      List<CrpTargetUnit> crpTargetUnits = new ArrayList<>(
        loggedCrp.getCrpTargetUnits().stream().filter(tu -> tu.isActive()).collect(Collectors.toList()));

      for (CrpTargetUnit crpTargetUnit : crpTargetUnits) {
        targetUnits.add(crpTargetUnit.getSrfTargetUnit());
      }

      Collections.sort(targetUnits,
        (tu1, tu2) -> tu1.getName().toLowerCase().trim().compareTo(tu2.getName().toLowerCase().trim()));

      for (SrfTargetUnit srfTargetUnit : targetUnits) {
        targetUnitList.put(srfTargetUnit.getId(), srfTargetUnit.getName());
      }

      // TODO
      targetUnitList = this.sortByComparator(targetUnitList);
    }

    if (this.getRequest().getParameter(APConstants.TRANSACTION_ID) != null) {


      transaction = StringUtils.trim(this.getRequest().getParameter(APConstants.TRANSACTION_ID));
      CrpProgram history = (CrpProgram) auditLogManager.getHistory(transaction);
      if (history != null) {
        crpProgramID = history.getId();
        selectedProgram = history;
        outcomes.addAll(history.getCrpProgramOutcomes().stream()
          .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList()));

        this.setEditable(false);
        this.setCanEdit(false);
        programs = new ArrayList<>();
        this.loadInfo();
        programs.add(history);

        List<HistoryDifference> differences = new ArrayList<>();
        Map<String, String> specialList = new HashMap<>();
        int i = 0;
        int j = 0;
        Collections.sort(outcomes, (lc1, lc2) -> lc1.getId().compareTo(lc2.getId()));
        for (CrpProgramOutcome crpProgramOutcome : outcomes) {
          int[] index = new int[1];
          index[0] = i;
          differences.addAll(historyComparator.getDifferencesList(crpProgramOutcome, transaction, specialList,
            "outcomes[" + i + "]", "outcomes", 1));
          for (CrpMilestone crpMilestone : crpProgramOutcome.getMilestones()) {
            differences.addAll(historyComparator.getDifferencesList(crpMilestone, transaction, specialList,
              "outcomes[" + i + "].milestones[" + j + "]", "outcomes", 2));


            j++;
          }
          j = 0;
          for (CrpOutcomeSubIdo crpOutcomeSubIdo : crpProgramOutcome.getSubIdos()) {
            differences.addAll(historyComparator.getDifferencesList(crpOutcomeSubIdo, transaction, specialList,
              "outcomes[" + i + "].subIdos[" + j + "]", "outcomes", 2));
            j++;
            int k = 0;


            for (CrpAssumption crpAssumption : crpOutcomeSubIdo.getAssumptions()) {
              differences.addAll(historyComparator.getDifferencesList(crpAssumption, transaction, specialList,
                "outcomes[" + i + "].subIdos[" + j + "].assumptions[" + k + "]", "outcomes", 3));
              k++;
            }
          }
          i++;
        }

        i = 0;


        this.setDifferences(differences);
      } else {
        programs = new ArrayList<>();
        this.transaction = null;

        this.setTransaction("-1");
      }

      Collections.sort(outcomes, (lc1, lc2) -> lc1.getId().compareTo(lc2.getId()));
    } else {

      List<CrpProgram> allPrograms = loggedCrp.getCrpPrograms().stream()
        .filter(c -> c.getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue() && c.isActive())
        .collect(Collectors.toList());
      allPrograms.sort((p1, p2) -> p1.getAcronym().compareTo(p2.getAcronym()));
      crpProgramID = -1;
      if (allPrograms != null) {

        this.programs = allPrograms;
        try {
          crpProgramID = Long.parseLong(StringUtils.trim(this.getRequest().getParameter(APConstants.CRP_PROGRAM_ID)));
        } catch (Exception e) {

          User user = userManager.getUser(this.getCurrentUser().getId());
          List<CrpProgramLeader> userLeads = user.getCrpProgramLeaders().stream()
            .filter(c -> c.isActive() && c.getCrpProgram().isActive() && c.getCrpProgram() != null

              && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
            .collect(Collectors.toList());
          if (!userLeads.isEmpty()) {
            crpProgramID = userLeads.get(0).getCrpProgram().getId();
          } else {
            if (!this.programs.isEmpty()) {
              crpProgramID = this.programs.get(0).getId();
            }
          }

        }
      } else {
        programs = new ArrayList<>();
      }

      if (crpProgramID != -1) {
        selectedProgram = crpProgramManager.getCrpProgramById(crpProgramID);
        outcomes.addAll(selectedProgram.getCrpProgramOutcomes().stream()
          .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList()));

      }

      if (selectedProgram != null) {

        milestoneYears = this.getTargetYears();

        Path path = this.getAutoSaveFilePath();

        if (path.toFile().exists() && this.getCurrentUser().isAutoSave()) {

          BufferedReader reader = null;

          reader = new BufferedReader(new FileReader(path.toFile()));

          Gson gson = new GsonBuilder().create();


          JsonObject jReader = gson.fromJson(reader, JsonObject.class);
          reader.close();


          AutoSaveReader autoSaveReader = new AutoSaveReader();

          selectedProgram = (CrpProgram) autoSaveReader.readFromJson(jReader);
          outcomes = selectedProgram.getOutcomes();
          selectedProgram.setAcronym(crpProgramManager.getCrpProgramById(selectedProgram.getId()).getAcronym());
          selectedProgram.setBaseLine(crpProgramManager.getCrpProgramById(selectedProgram.getId()).getBaseLine());

          selectedProgram.setCrp(loggedCrp);
          if (outcomes == null) {
            outcomes = new ArrayList<>();
          }
          for (CrpProgramOutcome outcome : outcomes) {

            if (outcome.getSubIdos() != null) {
              for (CrpOutcomeSubIdo subIdo : outcome.getSubIdos()) {
                if (subIdo.getSrfSubIdo() != null && subIdo.getSrfSubIdo().getId() != null) {
                  subIdo.setSrfSubIdo(srfSubIdoManager.getSrfSubIdoById(subIdo.getSrfSubIdo().getId()));
                }
              }
            }
            if (outcome.getFile() != null) {
              if (outcome.getFile().getId() != null) {
                outcome.setFile(fileDBManager.getFileDBById(outcome.getFile().getId()));
              } else {
                outcome.setFile(null);
              }
            }
          }


          this.setDraft(true);
        } else {
          this.loadInfo();
          this.setDraft(false);
        }

        String params[] = {loggedCrp.getAcronym(), selectedProgram.getId().toString()};
        this.setBasePermission(this.getText(Permission.IMPACT_PATHWAY_BASE_PERMISSION, params));
        if (!selectedProgram.getSubmissions().stream()
          .filter(c -> c.getYear() == this.getActualPhase().getYear() && c.getCycle() != null
            && c.getCycle().equals(this.getActualPhase().getDescription())
            && (c.isUnSubmit() == null || !c.isUnSubmit()))
          .collect(Collectors.toList()).isEmpty()) {
          if (!(this.canAccessSuperAdmin() || this.canAcessCrpAdmin())) {
            this.setCanEdit(false);
            this.setEditable(false);
          }


          this.setSubmission(selectedProgram
            .getSubmissions().stream().filter(c -> c.getYear() == this.getActualPhase().getYear()
              && c.getCycle() != null && c.getCycle().equals(this.getActualPhase().getDescription()))
            .collect(Collectors.toList()).get(0));
        }

      }

      if (this.isHttpPost()) {
        outcomes.clear();
      }
    }

    /** POWB 2019 List */
    assessmentRisks = new ArrayList<>();
    assessmentRisks = powbIndAssesmentRiskManager.findAll();

    focusLevels = new ArrayList<>();
    focusLevels = repIndGenderYouthFocusLevelManager.findAll();

    milestoneRisks = new ArrayList<>();
    milestoneRisks = powbIndMilestoneRiskManager.findAll();

    followingMilestones = new ArrayList<>();
    followingMilestones = powbIndFollowingMilestoneManager.findAll();

    /** */

    idoList = new HashMap<>();
    srfIdos = new ArrayList<>();
    for (SrfIdo srfIdo : srfIdoManager.findAll().stream().filter(c -> c.isActive()).collect(Collectors.toList())) {
      idoList.put(srfIdo.getId(), srfIdo.getDescription());

      srfIdo.setSubIdos(srfIdo.getSrfSubIdos().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      srfIdos.add(srfIdo);
    }


  }

  @Override
  public String save() {
    if (this.hasPermission("canEdit")) {
      /*
       * Removing outcomes
       */
      selectedProgram = crpProgramManager.getCrpProgramById(crpProgramID);
      this.saveCrpProgramOutcome();


      selectedProgram = crpProgramManager.getCrpProgramById(crpProgramID);
      selectedProgram.setAction(this.getActionName());
      List<String> relationsName = new ArrayList<>();
      relationsName.add(APConstants.PROGRAM_OUTCOMES_RELATION);
      /**
       * The following is required because we need to update something on the @CrpProgram if we want a row created in
       * the auditlog table.
       */
      this.setModificationJustification(selectedProgram);
      crpProgramManager.saveCrpProgram(selectedProgram, this.getActionName(), relationsName, this.getActualPhase());

      Path path = this.getAutoSaveFilePath();

      if (path.toFile().exists()) {
        path.toFile().delete();
      }


      if (this.getUrl() == null || this.getUrl().isEmpty()) {
        Collection<String> messages = this.getActionMessages();
        if (!this.getInvalidFields().isEmpty()) {
          this.setActionMessages(null);
          // this.addActionMessage(Map.toString(this.getInvalidFields().toArray()));
          List<String> keys = new ArrayList<String>(this.getInvalidFields().keySet());
          for (String key : keys) {
            this.addActionMessage(key + ": " + this.getInvalidFields().get(key));
          }

        } else {
          this.addActionMessage("message:" + this.getText("saving.saved"));
        }
        return SUCCESS;
      } else {
        this.addActionMessage("");
        return REDIRECT;
      }
    } else {
      this.setActionMessages(null);
      this.setActionMessages(null);
      return NOT_AUTHORIZED;
    }


  }

  public void saveAssumptions(CrpOutcomeSubIdo crpOutcomeSubIdoDB, CrpOutcomeSubIdo crpOutcomeSubIdoDetached) {

    /*
     * Delete assupmtions
     */
    for (CrpAssumption crpAssumptionDB : crpOutcomeSubIdoDB.getCrpAssumptions().stream().filter(c -> c.isActive())
      .collect(Collectors.toList())) {
      if (crpOutcomeSubIdoDetached.getAssumptions() != null) {
        if (!crpOutcomeSubIdoDetached.getAssumptions().contains(crpAssumptionDB)) {
          crpAssumptionManager.deleteCrpAssumption(crpAssumptionDB.getId());
        }
      } else {
        crpAssumptionManager.deleteCrpAssumption(crpAssumptionDB.getId());
      }


    }


    if (crpOutcomeSubIdoDetached.getAssumptions() != null) {
      for (CrpAssumption crpAssumptionDetached : crpOutcomeSubIdoDetached.getAssumptions()) {
        CrpAssumption crpAssumptionDB = null;
        if (crpAssumptionDetached.getId() == null) {
          crpAssumptionDB = new CrpAssumption();
        } else {
          crpAssumptionDB = crpAssumptionManager.getCrpAssumptionById(crpAssumptionDetached.getId());
        }

        crpAssumptionDB.setCrpOutcomeSubIdo(crpOutcomeSubIdoDB);

        // Weird - assume this is to bypass a mandatory field - why not throw an exception instead?
        if (crpAssumptionDetached.getDescription() == null) {
          crpAssumptionDB.setDescription(new String());
        } else {
          crpAssumptionDB.setDescription(crpAssumptionDetached.getDescription());
        }
        crpAssumptionDB = crpAssumptionManager.saveCrpAssumption(crpAssumptionDB);
      }
    }
  }


  public void saveCrpProgramOutcome() {

    /**
     * Delete removed CrpProgramOutcomes and their child entity collections
     */
    for (CrpProgramOutcome crpProgramOutcomeDB : selectedProgram.getCrpProgramOutcomes().stream()
      .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())) {
      if (!outcomes.contains(crpProgramOutcomeDB)) {
        for (CrpMilestone crpMilestone : crpProgramOutcomeDB.getCrpMilestones()) {
          crpMilestoneManager.deleteCrpMilestone(crpMilestone.getId());

        }
        for (CrpOutcomeSubIdo crpOutcomeSubIdo : crpProgramOutcomeDB.getCrpOutcomeSubIdos()) {
          for (CrpAssumption assumption : crpOutcomeSubIdo.getCrpAssumptions()) {
            crpAssumptionManager.deleteCrpAssumption(assumption.getId());
          }
          crpOutcomeSubIdoManager.deleteCrpOutcomeSubIdo(crpOutcomeSubIdo.getId());
        }
        // if (crpProgramOutcome.getCrpMilestones().isEmpty() && crpProgramOutcome.getCrpOutcomeSubIdos().isEmpty()) {
        crpProgramOutcomeManager.deleteCrpProgramOutcome(crpProgramOutcomeDB.getId());
      }

    }
    /*
     * Save outcomes
     */
    for (CrpProgramOutcome crpProgramOutcomeDetached : outcomes) {

      CrpProgramOutcome crpProgramOutcomeDB = null;

      if (crpProgramOutcomeDetached.getId() == null) {

        crpProgramOutcomeDB = new CrpProgramOutcome();
        crpProgramOutcomeDB.setPhase(this.getActualPhase());
      } else {
        crpProgramOutcomeDB = crpProgramOutcomeManager.getCrpProgramOutcomeById(crpProgramOutcomeDetached.getId());
      }

      crpProgramOutcomeDB.setDescription(crpProgramOutcomeDetached.getDescription());
      crpProgramOutcomeDB.setIndicator(crpProgramOutcomeDetached.getIndicator());
      crpProgramOutcomeDB.setSrfTargetUnit(crpProgramOutcomeDetached.getSrfTargetUnit());
      crpProgramOutcomeDB.setValue(crpProgramOutcomeDetached.getValue());
      crpProgramOutcomeDB.setYear(crpProgramOutcomeDetached.getYear());
      crpProgramOutcomeDB.setPhase(this.getActualPhase());
      crpProgramOutcomeDB.setCrpProgram(selectedProgram);
      if (crpProgramOutcomeDetached.getFile() != null && crpProgramOutcomeDetached.getFile().getId() == null) {
        crpProgramOutcomeDetached.setFile(null);
      }
      crpProgramOutcomeDB.setFile(crpProgramOutcomeDetached.getFile());
      crpProgramOutcomeDB.setIndicators(crpProgramOutcomeDetached.getIndicators());
      crpProgramOutcomeDB.setMilestones(crpProgramOutcomeDetached.getMilestones());
      crpProgramOutcomeDB.setSubIdos(crpProgramOutcomeDetached.getSubIdos());

      crpProgramOutcomeDB = crpProgramOutcomeManager.saveCrpProgramOutcome(crpProgramOutcomeDB);

      this.saveIndicators(crpProgramOutcomeDB, crpProgramOutcomeDetached);
      this.saveMilestones(crpProgramOutcomeDB, crpProgramOutcomeDetached);
      this.saveSubIdo(crpProgramOutcomeDB, crpProgramOutcomeDetached);

    }

  }

  public void saveIndicators(CrpProgramOutcome crpProgramOutcomeDB, CrpProgramOutcome crpProgramOutcomeDetached) {

    /*
     * Delete Indicators
     */
    for (CrpProgramOutcomeIndicator crpProgramOutcomeIndicator : crpProgramOutcomeDB.getCrpProgramOutcomeIndicators()
      .stream().filter(c -> c.isActive()).collect(Collectors.toList())) {
      if (crpProgramOutcomeDetached.getIndicators() != null) {
        if (!crpProgramOutcomeDetached.getIndicators().contains(crpProgramOutcomeIndicator)) {
          crpProgramOutcomeIndicatorManager.deleteCrpProgramOutcomeIndicator(crpProgramOutcomeIndicator.getId());
        }
      } else {
        crpProgramOutcomeIndicatorManager.deleteCrpProgramOutcomeIndicator(crpProgramOutcomeIndicator.getId());
      }
    }

    /*
     * Save Milestones
     */
    if (crpProgramOutcomeDetached.getIndicators() != null) {
      for (CrpProgramOutcomeIndicator crpProgramOutcomeIndicatorDetached : crpProgramOutcomeDetached.getIndicators()) {
        CrpProgramOutcomeIndicator crpProgramOutcomeIndicatorDB = null;
        if (crpProgramOutcomeIndicatorDetached.getId() == null) {
          crpProgramOutcomeIndicatorDB = new CrpProgramOutcomeIndicator();
          crpProgramOutcomeIndicatorDB.setComposeID(crpProgramOutcomeIndicatorDetached.getComposeID());
        } else {
          crpProgramOutcomeIndicatorDB = crpProgramOutcomeIndicatorManager
            .getCrpProgramOutcomeIndicatorById(crpProgramOutcomeIndicatorDetached.getId());
        }

        crpProgramOutcomeIndicatorDB.setCrpProgramOutcome(crpProgramOutcomeDB);
        crpProgramOutcomeIndicatorDB.setIndicator(crpProgramOutcomeIndicatorDetached.getIndicator());

        crpProgramOutcomeIndicatorDB =
          crpProgramOutcomeIndicatorManager.saveCrpProgramOutcomeIndicator(crpProgramOutcomeIndicatorDB);
      }
    }

  }


  public void saveMilestones(CrpProgramOutcome crpProgramOutcomeDB, CrpProgramOutcome crpProgramOutcomeDetached) {

    /*
     * Delete Milestones
     */
    for (CrpMilestone crpMilestone : crpProgramOutcomeDB.getCrpMilestones().stream().filter(c -> c.isActive())
      .collect(Collectors.toList())) {
      if (crpProgramOutcomeDetached.getMilestones() != null) {
        if (!crpProgramOutcomeDetached.getMilestones().contains(crpMilestone)) {
          crpMilestoneManager.deleteCrpMilestone(crpMilestone.getId());
        }
      } else {
        crpMilestoneManager.deleteCrpMilestone(crpMilestone.getId());
      }
    }

    /*
     * Save Milestones
     */
    if (crpProgramOutcomeDetached.getMilestones() != null) {
      for (CrpMilestone crpMilestoneDetached : crpProgramOutcomeDetached.getMilestones()) {
        CrpMilestone crpMilestoneDB = null;
        if (crpMilestoneDetached.getId() == null) {
          crpMilestoneDB = new CrpMilestone();
          crpMilestoneDB.setComposeID(crpMilestoneDetached.getComposeID());

        } else {
          crpMilestoneDB = crpMilestoneManager.getCrpMilestoneById(crpMilestoneDetached.getId());
        }

        crpMilestoneDB.setCrpProgramOutcome(crpProgramOutcomeDB);
        crpMilestoneDB.setIndex(crpMilestoneDetached.getIndex());
        crpMilestoneDB.setSrfTargetUnit(crpMilestoneDetached.getSrfTargetUnit());
        crpMilestoneDB.setTitle(crpMilestoneDetached.getTitle());
        crpMilestoneDB.setValue(crpMilestoneDetached.getValue());
        crpMilestoneDB.setYear(crpMilestoneDetached.getYear());

        /* POWB 2019 New Milestones Fileds */

        crpMilestoneDB.setPowbMilestoneOtherRisk(crpMilestoneDetached.getPowbMilestoneOtherRisk());
        crpMilestoneDB.setPowbMilestoneVerification(crpMilestoneDetached.getPowbMilestoneVerification());

        if (crpMilestoneDetached.getPowbIndAssesmentRisk() != null) {
          if (crpMilestoneDetached.getPowbIndAssesmentRisk().getId() != null) {
            PowbIndAssesmentRisk powbIndAssesmentRisk = powbIndAssesmentRiskManager
              .getPowbIndAssesmentRiskById(crpMilestoneDetached.getPowbIndAssesmentRisk().getId());
            crpMilestoneDB.setPowbIndAssesmentRisk(powbIndAssesmentRisk);
          }
        }

        if (crpMilestoneDetached.getPowbIndMilestoneRisk() != null) {
          if (crpMilestoneDetached.getPowbIndMilestoneRisk().getId() != null) {
            PowbIndMilestoneRisk powbIndMilestoneRisk = powbIndMilestoneRiskManager
              .getPowbIndMilestoneRiskById(crpMilestoneDetached.getPowbIndMilestoneRisk().getId());
            crpMilestoneDB.setPowbIndMilestoneRisk(powbIndMilestoneRisk);
          }
        }

        if (crpMilestoneDetached.getPowbIndFollowingMilestone() != null) {
          if (crpMilestoneDetached.getPowbIndFollowingMilestone().getId() != null) {
            PowbIndFollowingMilestone powbIndFollowingMilestone = powbIndFollowingMilestoneManager
              .getPowbIndFollowingMilestoneById(crpMilestoneDetached.getPowbIndFollowingMilestone().getId());
            crpMilestoneDB.setPowbIndFollowingMilestone(powbIndFollowingMilestone);
          }
        }

        if (crpMilestoneDetached.getYouthFocusLevel() != null) {
          if (crpMilestoneDetached.getYouthFocusLevel().getId() != null) {
            RepIndGenderYouthFocusLevel repIndGenderYouthFocusLevel = repIndGenderYouthFocusLevelManager
              .getRepIndGenderYouthFocusLevelById(crpMilestoneDetached.getYouthFocusLevel().getId());
            crpMilestoneDB.setYouthFocusLevel(repIndGenderYouthFocusLevel);
          }
        }

        if (crpMilestoneDetached.getClimateFocusLevel() != null) {
          if (crpMilestoneDetached.getClimateFocusLevel().getId() != null) {
            RepIndGenderYouthFocusLevel repIndGenderYouthFocusLevel = repIndGenderYouthFocusLevelManager
              .getRepIndGenderYouthFocusLevelById(crpMilestoneDetached.getClimateFocusLevel().getId());
            crpMilestoneDB.setClimateFocusLevel(repIndGenderYouthFocusLevel);
          }
        }

        if (crpMilestoneDetached.getCapdevFocusLevel() != null) {
          if (crpMilestoneDetached.getCapdevFocusLevel().getId() != null) {
            RepIndGenderYouthFocusLevel repIndGenderYouthFocusLevel = repIndGenderYouthFocusLevelManager
              .getRepIndGenderYouthFocusLevelById(crpMilestoneDetached.getCapdevFocusLevel().getId());
            crpMilestoneDB.setCapdevFocusLevel(repIndGenderYouthFocusLevel);
          }
        }

        if (crpMilestoneDetached.getGenderFocusLevel() != null) {
          if (crpMilestoneDetached.getGenderFocusLevel().getId() != null) {
            RepIndGenderYouthFocusLevel repIndGenderYouthFocusLevel = repIndGenderYouthFocusLevelManager
              .getRepIndGenderYouthFocusLevelById(crpMilestoneDetached.getGenderFocusLevel().getId());
            crpMilestoneDB.setGenderFocusLevel(repIndGenderYouthFocusLevel);
          }
        }

        crpMilestoneDB = crpMilestoneManager.saveCrpMilestone(crpMilestoneDB);
      }
    }

  }

  public void saveSubIdo(CrpProgramOutcome crpProgramOutcomeDB, CrpProgramOutcome crpProgramOutcomeDetached) {

    /*
     * Delete SubIDOS
     */

    for (CrpOutcomeSubIdo crpOutcomeSubIdoDB : crpProgramOutcomeDB.getCrpOutcomeSubIdos().stream()
      .filter(c -> c.isActive()).collect(Collectors.toList())) {
      if (crpProgramOutcomeDetached.getSubIdos() != null) {
        if (!crpProgramOutcomeDetached.getSubIdos().contains(crpOutcomeSubIdoDB)) {
          for (CrpAssumption assumption : crpOutcomeSubIdoDB.getCrpAssumptions()) {
            crpAssumptionManager.deleteCrpAssumption(assumption.getId());
          }
          crpOutcomeSubIdoManager.deleteCrpOutcomeSubIdo(crpOutcomeSubIdoDB.getId());

        }
        // All subIdos have been deleted
      } else {
        if (crpOutcomeSubIdoDB.getCrpAssumptions().isEmpty()) {
          for (CrpAssumption assumption : crpOutcomeSubIdoDB.getCrpAssumptions()) {
            crpAssumptionManager.deleteCrpAssumption(assumption.getId());
          }
          crpOutcomeSubIdoManager.deleteCrpOutcomeSubIdo(crpOutcomeSubIdoDB.getId());
        }
      }
    }

    /*
     * Save CrpOutcomeSubIdo
     */

    if (crpProgramOutcomeDetached.getSubIdos() != null) {
      for (CrpOutcomeSubIdo crpOutcomeSubIdoDetached : crpProgramOutcomeDetached.getSubIdos()) {
        CrpOutcomeSubIdo crpOutcomeSubIdoDB = null;
        if (crpOutcomeSubIdoDetached.getId() == null) {

          crpOutcomeSubIdoDB = new CrpOutcomeSubIdo();
        } else {
          crpOutcomeSubIdoDB = crpOutcomeSubIdoManager.getCrpOutcomeSubIdoById(crpOutcomeSubIdoDetached.getId());
        }

        crpOutcomeSubIdoDB.setContribution(crpOutcomeSubIdoDetached.getContribution());
        crpOutcomeSubIdoDB.setCrpProgramOutcome(crpProgramOutcomeDB);
        if (crpOutcomeSubIdoDetached.getSrfSubIdo() == null || crpOutcomeSubIdoDetached.getSrfSubIdo().getId() == null
          || crpOutcomeSubIdoDetached.getSrfSubIdo().getId() == -1) {
          crpOutcomeSubIdoDB.setSrfSubIdo(null);
        } else {
          crpOutcomeSubIdoDB.setSrfSubIdo(crpOutcomeSubIdoDetached.getSrfSubIdo());
        }

        boolean save = true;
        /**
         * We need a comment here to understand what the original author is trying to achieve here
         */
        if (crpOutcomeSubIdoDetached.getSrfSubIdo() != null && crpOutcomeSubIdoDetached.getId() == null) {
          List<CrpOutcomeSubIdo> subidos = crpProgramOutcomeDB.getCrpOutcomeSubIdos().stream()
            .filter(c -> c.isActive()
              && c.getSrfSubIdo().getId().longValue() == crpOutcomeSubIdoDetached.getSrfSubIdo().getId().longValue())
            .collect(Collectors.toList());
          if (subidos.size() > 0) {
            save = false;
          }
        }
        if (save) {
          crpOutcomeSubIdoDB = crpOutcomeSubIdoManager.saveCrpOutcomeSubIdo(crpOutcomeSubIdoDB);
        }
        this.saveAssumptions(crpOutcomeSubIdoDB, crpOutcomeSubIdoDetached);

      }
    }
  }

  public void setAssessmentRisks(List<PowbIndAssesmentRisk> assessmentRisks) {
    this.assessmentRisks = assessmentRisks;
  }

  public void setCrpProgramID(long crpProgramID) {
    this.crpProgramID = crpProgramID;
  }

  public void setFocusLevels(List<RepIndGenderYouthFocusLevel> focusLevels) {
    this.focusLevels = focusLevels;
  }


  public void setFollowingMilestones(List<PowbIndFollowingMilestone> followingMilestones) {
    this.followingMilestones = followingMilestones;
  }


  public void setIdoList(HashMap<Long, String> idoList) {
    this.idoList = idoList;
  }

  public void setLoggedCrp(GlobalUnit loggedCrp) {
    this.loggedCrp = loggedCrp;
  }

  public void setMilestoneRisks(List<PowbIndMilestoneRisk> milestoneRisks) {
    this.milestoneRisks = milestoneRisks;
  }


  public void setMilestoneYears(List<Integer> milestoneYears) {
    this.milestoneYears = milestoneYears;
  }

  public void setOutcomes(List<CrpProgramOutcome> outcomes) {
    this.outcomes = outcomes;
  }

  public void setPowbIndAssesmentRiskManager(PowbIndAssesmentRiskManager powbIndAssesmentRiskManager) {
    this.powbIndAssesmentRiskManager = powbIndAssesmentRiskManager;
  }


  public void setPowbIndMilestoneRiskManager(PowbIndMilestoneRiskManager powbIndMilestoneRiskManager) {
    this.powbIndMilestoneRiskManager = powbIndMilestoneRiskManager;
  }


  public void setPrograms(List<CrpProgram> programs) {
    this.programs = programs;
  }


  public void
    setRepIndGenderYouthFocusLevelManager(RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager) {
    this.repIndGenderYouthFocusLevelManager = repIndGenderYouthFocusLevelManager;
  }

  public void setSelectedProgram(CrpProgram selectedProgram) {
    this.selectedProgram = selectedProgram;
  }

  public void setSrfIdos(List<SrfIdo> srfIdos) {
    this.srfIdos = srfIdos;
  }


  public void setTargetUnitList(HashMap<Long, String> targetUnitList) {
    this.targetUnitList = targetUnitList;
  }

  public void setTransaction(String transactionId) {
    this.transaction = transactionId;
  }

  private HashMap<Long, String> sortByComparator(HashMap<Long, String> unsortMap) {

    // Convert Map to List
    List<HashMap.Entry<Long, String>> list = new LinkedList<HashMap.Entry<Long, String>>(unsortMap.entrySet());

    // Sort list with comparator, to compare the Map values
    Collections.sort(list, new Comparator<HashMap.Entry<Long, String>>() {

      @Override
      public int compare(HashMap.Entry<Long, String> o1, HashMap.Entry<Long, String> o2) {
        return (o1.getValue().toLowerCase().trim()).compareTo(o2.getValue().toLowerCase().trim());
      }
    });

    // Convert sorted map back to a Map
    HashMap<Long, String> sortedMap = new LinkedHashMap<Long, String>();
    for (Iterator<HashMap.Entry<Long, String>> it = list.iterator(); it.hasNext();) {
      HashMap.Entry<Long, String> entry = it.next();
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }

  @Override
  public void validate() {
    if (save) {
      validator.validate(this, outcomes, selectedProgram, true);
    }
  }

}