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

package org.cgiar.ccafs.marlo.utils;

import org.cgiar.ccafs.marlo.data.manager.ICenterCycleManager;
import org.cgiar.ccafs.marlo.data.model.CenterCycle;
import org.cgiar.ccafs.marlo.data.model.CenterImpact;
import org.cgiar.ccafs.marlo.data.model.CenterImpactObjective;
import org.cgiar.ccafs.marlo.data.model.CenterOutcome;
import org.cgiar.ccafs.marlo.data.model.CenterOutput;
import org.cgiar.ccafs.marlo.data.model.CenterOutputsOutcome;
import org.cgiar.ccafs.marlo.data.model.CenterSubmission;
import org.cgiar.ccafs.marlo.data.model.CenterTopic;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.ImpactPathwayCyclesEnum;
import org.cgiar.ccafs.marlo.data.model.Phase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.pentaho.reporting.engine.classic.core.Band;
import org.pentaho.reporting.engine.classic.core.CompoundDataFactory;
import org.pentaho.reporting.engine.classic.core.Element;
import org.pentaho.reporting.engine.classic.core.ItemBand;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportFooter;
import org.pentaho.reporting.engine.classic.core.SubReport;
import org.pentaho.reporting.engine.classic.core.TableDataFactory;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.util.TypedTableModel;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

/**
 * A service to help move the shared logic out of the struts2 Actions as this was causing
 * struts2 actions to have dependencies on other struts2 actions.
 * 
 * @author GrantL
 */
@Lazy
@Named
public class ImpactSubmissionSummaryService {

  private final ResourceManager resourceManager;

  private final ICenterCycleManager cycleService;

  private final Logger LOG = LoggerFactory.getLogger(ImpactSubmissionSummaryService.class);

  @Inject
  public ImpactSubmissionSummaryService(ResourceManager resourceManager, ICenterCycleManager cycleService) {
    this.resourceManager = resourceManager;
    this.cycleService = cycleService;
  }

  public byte[] execute(CrpProgram researchProgram, Phase actualPhase, String baseUrl) throws Exception {
    byte[] bytesPDF;
    try {
      ByteArrayOutputStream os = new ByteArrayOutputStream();

      String res = this.getClass().getResource("/pentaho/center/ImpactPathway.prpt").toString();

      Resource reportResource = resourceManager
        .createDirectly(this.getClass().getResource("/pentaho/center/ImpactPathway.prpt"), MasterReport.class);

      // Get main report
      MasterReport masterReport = (MasterReport) reportResource.getResource();

      // Get program from DB
      // project = projectManager.getProjectById(projectID);


      // Get details band
      ItemBand masteritemBand = masterReport.getItemBand();
      // Create new empty subreport hash map
      HashMap<String, Element> hm = new HashMap<String, Element>();
      // method to get all the subreports in the prpt and store in the HashMap
      this.getAllSubreports(hm, masteritemBand);
      // Uncomment to see which Subreports are detecting the method getAllSubreports
      System.out.println("Pentaho SubReports: " + hm);

      // Set Main_Query
      String masterQueryName = "main";
      CompoundDataFactory cdf = CompoundDataFactory.normalize(masterReport.getDataFactory());
      TableDataFactory sdf = (TableDataFactory) cdf.getDataFactoryForQuery(masterQueryName);
      TypedTableModel model = this.getMasterTableModel(researchProgram, actualPhase, baseUrl);
      sdf.addTable(masterQueryName, model);
      masterReport.setDataFactory(cdf);

      // Start Setting Subreports

      // Subreport Description
      this.fillSubreport((SubReport) hm.get("program_impact"), "program_impact", researchProgram);
      // Hiden section
      // this.fillSubreport((SubReport) hm.get("research_topics"), "research_topics");
      this.fillSubreport((SubReport) hm.get("outcomes"), "outcomes", researchProgram);
      this.fillSubreport((SubReport) hm.get("outputs"), "outputs", researchProgram);

      PdfReportUtil.createPDF(masterReport, os);

      bytesPDF = os.toByteArray();

      os.close();
    } catch (Exception e) {
      LOG.error("Error generating PDF " + e.getMessage());
      throw e;
    }

    return bytesPDF;

  }

  private void fillSubreport(SubReport subReport, String query, CrpProgram researchProgram) {
    CompoundDataFactory cdf = CompoundDataFactory.normalize(subReport.getDataFactory());
    TableDataFactory sdf = (TableDataFactory) cdf.getDataFactoryForQuery(query);
    TypedTableModel model = null;
    switch (query) {
      case "program_impact":
        model = this.getProgramImpactTableModel(researchProgram);
        break;
      // case "research_topics":
      // model = this.getResearchTopicsTableModel();
      // break;
      case "outcomes":
        model = this.getOutcomesTableModel(researchProgram);
        break;
      case "outputs":
        model = this.getOutputsTableModel(researchProgram);
        break;

    }
    sdf.addTable(query, model);
    subReport.setDataFactory(cdf);
  }

  /**
   * Get all subreports and store then in a hash map.
   * If it encounters a band, search subreports in the band
   * 
   * @param hm List to populate with subreports found
   * @param itemBand details section in pentaho
   */
  private void getAllSubreports(HashMap<String, Element> hm, ItemBand itemBand) {
    int elementCount = itemBand.getElementCount();
    for (int i = 0; i < elementCount; i++) {
      Element e = itemBand.getElement(i);
      // verify if the item is a SubReport
      if (e instanceof SubReport) {
        hm.put(e.getName(), e);
        if (((SubReport) e).getElementCount() != 0) {
          this.getAllSubreports(hm, ((SubReport) e).getItemBand());
          // If report footer is not null check for subreports
          if (((SubReport) e).getReportFooter().getElementCount() != 0) {
            this.getFooterSubreports(hm, ((SubReport) e).getReportFooter());
          }
        }
      }
      // If is a band, find the subreport if exist
      if (e instanceof Band) {
        this.getBandSubreports(hm, (Band) e);
      }
    }
  }

  /**
   * Get all subreports in the band.
   * If it encounters a band, search subreports in the band
   * 
   * @param hm
   * @param band
   */
  private void getBandSubreports(HashMap<String, Element> hm, Band band) {
    int elementCount = band.getElementCount();
    for (int i = 0; i < elementCount; i++) {
      Element e = band.getElement(i);
      if (e instanceof SubReport) {
        hm.put(e.getName(), e);
        // If report footer is not null check for subreports
        if (((SubReport) e).getReportFooter().getElementCount() != 0) {
          this.getFooterSubreports(hm, ((SubReport) e).getReportFooter());
        }
      }
      if (e instanceof Band) {
        this.getBandSubreports(hm, (Band) e);
      }
    }
  }


  private void getFooterSubreports(HashMap<String, Element> hm, ReportFooter reportFooter) {

    int elementCount = reportFooter.getElementCount();
    for (int i = 0; i < elementCount; i++) {
      Element e = reportFooter.getElement(i);
      if (e instanceof SubReport) {
        hm.put(e.getName(), e);
        if (((SubReport) e).getElementCount() != 0) {
          this.getAllSubreports(hm, ((SubReport) e).getItemBand());

        }
      }
      if (e instanceof Band) {
        this.getBandSubreports(hm, (Band) e);
      }
    }
  }

  /**
   * Get the main information of the report
   * 
   * @return
   */
  private TypedTableModel getMasterTableModel(CrpProgram researchProgram, Phase actualPhase, String baseUrl) {
    // Initialization of Model
    TypedTableModel model = new TypedTableModel(new String[] {"title", "current_date", "impact_submission", "imageUrl"},
      new Class[] {String.class, String.class, String.class, String.class});
    String title = "";
    String currentDate = "";
    String impactSubmission = "";

    // Get title composed by center-area-program
    if (researchProgram.getResearchArea() != null) {
      title += researchProgram.getResearchArea().getName() + " - ";
      if (researchProgram.getComposedName() != null) {
        title += researchProgram.getName();
      }
    }

    // Get datetime
    ZonedDateTime timezone = ZonedDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d 'at' HH:mm ");
    currentDate = timezone.format(format) + TimeZone.getDefault().getDisplayName();

    // Get submission
    CenterCycle researchCycle = cycleService.getResearchCycleById(ImpactPathwayCyclesEnum.IMPACT_PATHWAY.getId());

    // Filling submission
    List<CenterSubmission> submissions = new ArrayList<>();
    for (CenterSubmission submission : researchProgram.getCenterSubmissions().stream()
      .filter(s -> s.getResearchCycle().getId() == researchCycle.getId() && s.getYear() == actualPhase.getYear())
      .collect(Collectors.toList())) {
      submissions.add(submission);
    }


    if (!submissions.isEmpty()) {
      if (submissions.size() > 1) {
        LOG.error("More than one submission was found, the report will retrieve the first one");
      }
      CenterSubmission fisrtSubmission = submissions.get(0);
      String submissionDate = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm").format(fisrtSubmission.getDateTime());

      impactSubmission = "Submitted on " + submissionDate + " (" + fisrtSubmission.getResearchCycle().getName()
        + " cycle " + fisrtSubmission.getYear() + ")";
    } else {
      impactSubmission =
        "Center Submission for " + researchCycle.getName() + " cycle " + actualPhase.getYear() + ": &lt;pending&gt;";
    }

    // Get CIAT imgage URL from repo
    String imageUrl = baseUrl + "/global/images/centers/CIAT.png";

    model.addRow(new Object[] {title, currentDate, impactSubmission, imageUrl});
    return model;
  }

  private TypedTableModel getOutcomesTableModel(CrpProgram researchProgram) {
    // Initialization of Model
    TypedTableModel model = new TypedTableModel(
      new String[] {"shortName", "statement", "research_topic", "program_impact", "target_unit", "target_value",
        "target_year", "showResearchTopic", "id"},
      new Class[] {String.class, String.class, String.class, String.class, String.class, String.class, String.class,
        Boolean.class, Long.class});


    List<CenterTopic> researchTopics = researchProgram.getResearchTopics().stream()
      .filter(rt -> rt.isActive() && rt.getResearchOutcomes().size() > 0).collect(Collectors.toList());

    Collections.sort(researchTopics, (ra1, ra2) -> ra1.getOrder().compareTo(ra2.getOrder()));

    // Get research topics and then outcomes
    for (CenterTopic researchTopic : researchTopics) {
      String researchTopicTitle = "";
      Boolean showResearchTopic;
      int countOutcome = 0;
      for (CenterOutcome researchOutcome : researchTopic.getResearchOutcomes().stream().filter(ro -> ro.isActive())
        .collect(Collectors.toList())) {
        if (countOutcome == 0) {
          if (researchTopic.getResearchTopic() != null) {
            researchTopicTitle = researchTopic.getResearchTopic();
          }
          if (researchTopicTitle.isEmpty()) {
            researchTopicTitle = "&lt;Not Defined&gt;";
          }
          showResearchTopic = true;
        } else {
          showResearchTopic = false;
        }


        String shortName = "";
        String statement = "";

        String programImpact = "";
        String targetUnit = "";
        String targetValue = "";
        String targetYear = "";
        Long id = researchOutcome.getId();

        if (researchOutcome.getShortName() != null) {
          shortName = researchOutcome.getShortName();
        }
        if (shortName.isEmpty()) {
          shortName = "&lt;Not Defined&gt;";
        }

        if (researchOutcome.getDescription() != null) {
          statement = researchOutcome.getDescription();
        }
        if (statement.isEmpty()) {
          statement = "&lt;Not Defined&gt;";
        }

        if (researchOutcome.getResearchImpact() != null) {
          programImpact = researchOutcome.getResearchImpact().getDescription();
        }
        if (programImpact.isEmpty()) {
          programImpact = "&lt;Not Defined&gt;";
        }
        if (researchOutcome.getTargetUnit() != null) {
          targetUnit = researchOutcome.getSrfTargetUnit().getName();
        }
        if (targetUnit.isEmpty()) {
          targetUnit = "&lt;Not Defined&gt;";
        }

        if (targetUnit.equals("Not Applicable")) {
          targetValue = "Not Applicable";
        } else {
          if (researchOutcome.getValue() != null) {
            targetValue = researchOutcome.getValue().toString();
          }
        }

        if (targetValue == null || targetValue.isEmpty()) {
          targetValue = "&lt;Not Defined&gt;";
        }
        if (researchOutcome.getTargetYear() != null) {
          if (researchOutcome.getTargetYear() != -1) {
            targetYear = researchOutcome.getTargetYear().toString();
          } else {
            targetYear = "&lt;Not Defined&gt;";
          }
        }
        if (targetYear.isEmpty()) {
          targetYear = "&lt;Not Defined&gt;";
        }

        countOutcome++;
        model.addRow(new Object[] {shortName, statement, researchTopicTitle, programImpact, targetUnit, targetValue,
          targetYear, showResearchTopic, id});
      }

    }


    return model;
  }

  private TypedTableModel getOutputsTableModel(CrpProgram researchProgram) {
    // Initialization of Model
    TypedTableModel model =
      new TypedTableModel(new String[] {"shortName", "title", "research_topic", "outcome", "showResearchTopic", "id"},
        new Class[] {String.class, String.class, String.class, String.class, Boolean.class, Long.class});
    List<CenterTopic> researchTopics = researchProgram.getResearchTopics().stream()
      .filter(rt -> rt.isActive() && rt.getResearchOutcomes().size() > 0).collect(Collectors.toList());
    Collections.sort(researchTopics, (ra1, ra2) -> ra1.getOrder().compareTo(ra2.getOrder()));
    for (CenterTopic researchTopic : researchTopics) {
      String researchTopicTitle = "";
      Boolean showResearchTopic;
      int countOutcome = 0;
      for (CenterOutcome researchOutcome : researchTopic.getResearchOutcomes().stream().filter(ro -> ro.isActive())
        .collect(Collectors.toList())) {

        List<CenterOutput> outputs = new ArrayList<>();
        List<CenterOutputsOutcome> centerOutputsOutcomes = new ArrayList<>(
          researchOutcome.getCenterOutputsOutcomes().stream().filter(ro -> ro.isActive()).collect(Collectors.toList()));
        for (CenterOutputsOutcome centerOutputsOutcome : centerOutputsOutcomes) {
          outputs.add(centerOutputsOutcome.getCenterOutput());
        }

        for (CenterOutput researchOutput : outputs) {
          Long id = researchOutput.getId();
          if (countOutcome == 0) {
            if (researchTopic.getResearchTopic() != null) {
              researchTopicTitle = researchTopic.getResearchTopic();
            }
            if (researchTopicTitle.isEmpty()) {
              researchTopicTitle = "&lt;Not Defined&gt;";
            }
            showResearchTopic = true;
          } else {
            showResearchTopic = false;
          }

          String shortName = "";
          String title = "";
          String outcome = "";

          if (researchOutput.getShortName() != null) {
            shortName = researchOutput.getShortName();
          }
          if (shortName.isEmpty()) {
            shortName = "&lt;Not Defined&gt;";
          }
          if (researchOutput.getTitle() != null) {
            title = researchOutput.getTitle();
          }
          if (title.isEmpty()) {
            title = "&lt;Not Defined&gt;";
          }


          if (researchOutcome.getDescription() != null) {
            outcome = researchOutcome.getDescription();
          }
          if (outcome.isEmpty()) {
            outcome = "&lt;Not Defined&gt;";
          }
          countOutcome++;

          model.addRow(new Object[] {shortName, title, researchTopicTitle, outcome, showResearchTopic, id});
        }
      }
    }


    return model;
  }

  /**
   * Get the program impact information
   * 
   * @return model with program impact information
   */
  private TypedTableModel getProgramImpactTableModel(CrpProgram researchProgram) {
    // Initialization of Model
    TypedTableModel model =
      new TypedTableModel(new String[] {"title", "objectives", "program_id", "research_impact_id", "shortName"},
        new Class[] {String.class, String.class, Long.class, Long.class, String.class});
    for (CenterImpact researchImpact : researchProgram.getResearchImpacts().stream().filter(rp -> rp.isActive())
      .collect(Collectors.toList())) {
      String title = "";
      String objectives = "";
      long programId = 0;
      if (researchImpact.getDescription() == null || researchImpact.getDescription().isEmpty()) {
        title = "&lt;Not Defined&gt;";
      } else {
        title = researchImpact.getDescription();
      }

      if (researchImpact.getResearchImpactObjectives() != null
        && researchImpact.getResearchImpactObjectives().size() > 0) {
        for (CenterImpactObjective researchImpactObjective : researchImpact.getResearchImpactObjectives().stream()
          .filter(rio -> rio.isActive() && rio.getResearchObjective() != null).collect(Collectors.toList())) {
          objectives += "&#9679" + researchImpactObjective.getResearchObjective().getObjective() + "<br>";
        }
      } else {
        objectives = "&lt;Not Defined&gt;";
      }
      if (objectives.isEmpty()) {
        objectives = null;
      }
      if (researchProgram != null) {
        programId = researchProgram.getId();
      }

      String shortName = "";
      if (researchImpact.getShortName() == null || researchImpact.getShortName().isEmpty()) {
        shortName = "&lt;Not Defined&gt;";
      } else {
        shortName = researchImpact.getShortName();
      }


      model.addRow(new Object[] {title, objectives, programId, researchImpact.getId(), shortName});
    }
    return model;
  }

}
