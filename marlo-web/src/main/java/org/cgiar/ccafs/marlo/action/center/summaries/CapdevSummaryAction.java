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

package org.cgiar.ccafs.marlo.action.center.summaries;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.action.center.capdev.CapacityDevelopmentAction;
import org.cgiar.ccafs.marlo.data.manager.ICapacityDevelopmentService;
import org.cgiar.ccafs.marlo.data.model.CapacityDevelopment;
import org.cgiar.ccafs.marlo.data.model.CapdevDiscipline;
import org.cgiar.ccafs.marlo.data.model.CapdevParticipant;
import org.cgiar.ccafs.marlo.data.model.CapdevTargetgroup;
import org.cgiar.ccafs.marlo.data.model.Discipline;
import org.cgiar.ccafs.marlo.data.model.Institution;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.Participant;
import org.cgiar.ccafs.marlo.data.model.TargetGroup;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.pentaho.reporting.engine.classic.core.Band;
import org.pentaho.reporting.engine.classic.core.CompoundDataFactory;
import org.pentaho.reporting.engine.classic.core.Element;
import org.pentaho.reporting.engine.classic.core.ItemBand;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportFooter;
import org.pentaho.reporting.engine.classic.core.SubReport;
import org.pentaho.reporting.engine.classic.core.TableDataFactory;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.ExcelReportUtil;
import org.pentaho.reporting.engine.classic.core.util.TypedTableModel;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapdevSummaryAction extends BaseAction implements Summary {

  private static final long serialVersionUID = 1L;
  private static Logger LOG = LoggerFactory.getLogger(CapacityDevelopmentAction.class);

  private InputStream inputStream;
  // XLS bytes
  private byte[] bytesXLS;

  private List<CapacityDevelopment> capDevs = new ArrayList<>();
  private final ICapacityDevelopmentService capdevService;
  private final ResourceManager resourceManager;
  private int totalParticipants = 0;
  private int totalMen = 0;
  private int totalWomen = 0;
  private int totalOther = 0;
  private int year;

  @Inject
  public CapdevSummaryAction(APConfig config, ICapacityDevelopmentService capdevService,
    ResourceManager resourceManager) {
    super(config);
    this.capdevService = capdevService;
    this.resourceManager = resourceManager;
  }


  @Override
  public String execute() throws Exception {

    final ByteArrayOutputStream os = new ByteArrayOutputStream();

    final Resource reportResource =
      resourceManager.createDirectly(this.getClass().getResource("/pentaho/center/Capdev.prpt"), MasterReport.class);

    final MasterReport masterReport = (MasterReport) reportResource.getResource();
    String center = this.getCurrentCrp().getAcronym();

    // Get datetime
    ZonedDateTime timezone = ZonedDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d 'at' HH:mm ");
    String zone = timezone.getOffset() + "";
    if (zone.equals("Z")) {
      zone = "+0";
    }
    String currentDate = timezone.format(format) + "(GMT" + zone + ")";

    // Set Main_Query
    CompoundDataFactory cdf = CompoundDataFactory.normalize(masterReport.getDataFactory());
    String masterQueryName = "main";
    TableDataFactory sdf = (TableDataFactory) cdf.getDataFactoryForQuery(masterQueryName);
    TypedTableModel model = this.getMasterTableModel(center, currentDate);
    sdf.addTable(masterQueryName, model);
    masterReport.setDataFactory(cdf);

    // Get details band
    final ItemBand masteritemBand = masterReport.getItemBand();
    // Create new empty subreport hash map
    final HashMap<String, Element> hm = new HashMap<String, Element>();
    // method to get all the subreports in the prpt and store in the HashMap
    this.getAllSubreports(hm, masteritemBand);
    // Uncomment to see which Subreports are detecting the method getAllSubreports


    // Subreport list of capdev
    this.fillSubreport((SubReport) hm.get("capdev_interventions"), "capdev_interventions");
    this.fillSubreport((SubReport) hm.get("capdev_participants"), "capdev_participants");
    this.fillSubreport((SubReport) hm.get("individual_types"), "individual_types");
    this.fillSubreport((SubReport) hm.get("groupTypes"), "groupTypes");
    this.fillSubreport((SubReport) hm.get("disciplines"), "disciplines");
    this.fillSubreport((SubReport) hm.get("target_groups"), "target_groups");
    this.fillSubreport((SubReport) hm.get("programs"), "programs");
    this.fillSubreport((SubReport) hm.get("crp"), "crp");
    this.fillSubreport((SubReport) hm.get("capdevSummary"), "capdevSummary");
    this.fillSubreport((SubReport) hm.get("citizenship"), "citizenship");
    this.fillSubreport((SubReport) hm.get("funding_type"), "funding_type");
    this.fillSubreport((SubReport) hm.get("institution"), "institution");
    this.fillSubreport((SubReport) hm.get("institutions_type"), "institutions_type");


    ExcelReportUtil.createXLSX(masterReport, os);
    bytesXLS = os.toByteArray();
    os.close();

    return SUCCESS;
  }

  private void fillSubreport(SubReport subReport, String query) {
    final CompoundDataFactory cdf = CompoundDataFactory.normalize(subReport.getDataFactory());
    final TableDataFactory sdf = (TableDataFactory) cdf.getDataFactoryForQuery(query);
    TypedTableModel model = null;
    switch (query) {
      case "capdev_interventions":
        model = this.getCapDevInterventionsTableModel();
        break;
      case "capdev_participants":
        model = this.getCapDevParticipantsTableModel();
        break;
      case "individual_types":
        model = this.getIndividualTypesTableModel();
        break;
      case "capdevSummary":
        model = this.getCapDevSummaryTableModel();
        break;
      case "groupTypes":
        model = this.getGroupTypeTableModel();
        break;
      case "disciplines":
        model = this.getDisciplinesTableModel();
        break;
      case "target_groups":
        model = this.getTargetGroupsTableModel();
        break;
      case "programs":
        model = this.getProgramsTableModel();
        break;
      case "crp":
        model = this.getCrpsTableModel();
        break;
      case "citizenship":
        model = this.getcitizenshipTableModel();
        break;
      case "funding_type":
        model = this.getFundingTypeTableModel();
        break;
      case "outputs":
        model = this.getOutputTypeTableModel();
        break;
      case "institution":
        model = this.getInstitutionsTableModel();
        break;
      case "institutions_type":
        model = this.getInstitutionsTypeTableModel();
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
    final int elementCount = itemBand.getElementCount();
    for (int i = 0; i < elementCount; i++) {
      final Element e = itemBand.getElement(i);
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
    final int elementCount = band.getElementCount();
    for (int i = 0; i < elementCount; i++) {
      final Element e = band.getElement(i);
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

  public byte[] getBytesXLS() {
    return bytesXLS;
  }


  private TypedTableModel getCapDevInterventionsTableModel() {
    // Initialization of Model
    TypedTableModel model = new TypedTableModel(
      new String[] {"capdevId", "title", "type", "category", "numParticipants", "numMen", "numWomen", "numOther",
        "researchArea", "researchProgram", "startDate", "duration", "duration_unit", "num_supporting_docs"},
      new Class[] {String.class, String.class, String.class, String.class, String.class, String.class, String.class,
        String.class, String.class, String.class, Date.class, String.class, String.class, Integer.class});

    for (CapacityDevelopment capdev : capDevs) {
      String capdev_id = capdev.getId().toString();
      String title = null;
      if (capdev.getTitle() != null && !capdev.getTitle().isEmpty()) {
        title = capdev.getTitle();
      }

      String type = null;
      if (capdev.getCapdevType() != null) {
        type = capdev.getCapdevType().getName();
      }

      String category = null;
      if (capdev.getCategory() == 1) {
        category = "Individual";
      }
      if (capdev.getCategory() == 2) {
        category = "Group";
      }

      String numParticipants = null;
      if (capdev.getNumParticipants() != null) {
        numParticipants = capdev.getNumParticipants().toString();
        totalParticipants = totalParticipants + capdev.getNumParticipants();
      }

      String numMen = null;
      if (capdev.getNumMen() != null) {
        numMen = capdev.getNumMen().toString();
        totalMen = totalMen + capdev.getNumMen();
      }

      String numWomen = null;
      if (capdev.getNumWomen() != null) {
        numWomen = capdev.getNumWomen().toString();
        totalWomen = totalWomen + capdev.getNumWomen();
      }

      String numOther = null;
      if (capdev.getNumOther() != null) {
        numOther = capdev.getNumOther().toString();
        totalOther = totalOther + capdev.getNumOther();
      }

      String researcharea = null;
      if (capdev.getResearchArea() != null) {
        researcharea = capdev.getResearchArea().getName();
      }

      String researchProgram = null;
      if (capdev.getResearchProgram() != null) {
        researchProgram = capdev.getResearchProgram().getName();
      }

      Date startDate = null;
      if (capdev.getStartDate() != null) {
        startDate = capdev.getStartDate();
      }

      String duration = null;

      if (capdev.getDuration() != null) {
        duration = capdev.getDuration().toString();
      }

      String durationUnit = null;
      if (capdev.getDurationUnit() != null && !capdev.getDurationUnit().equals("-1")
        && !capdev.getDurationUnit().isEmpty()) {
        durationUnit = capdev.getDurationUnit();
      }

      Integer numSupportingDocs = null;
      if (capdev.getDeliverables() != null) {
        numSupportingDocs = capdev.getDeliverables().stream()
          .filter(s -> s.isActive() && s.getYear() != null && s.getYear().intValue() == this.year)
          .collect(Collectors.toList()).size();
      }

      model.addRow(new Object[] {capdev_id, title, type, category, numParticipants, numMen, numWomen, numOther,
        researcharea, researchProgram, startDate, duration, durationUnit, numSupportingDocs});
    }
    return model;
  }


  private TypedTableModel getCapDevParticipantsTableModel() {
    // Initialization of Model
    TypedTableModel model = new TypedTableModel(
      new String[] {"capdevId", "name", "gender", "age", "citizenship", "institution", "country_institution",
        "personal_email", "job_email", "supervisor"},
      new Class[] {String.class, String.class, String.class, String.class, String.class, String.class, String.class,
        String.class, String.class, String.class});
    for (CapacityDevelopment capdev : capDevs) {

      if (capdev.getCapdevParticipant() != null) {
        List<CapdevParticipant> participants =
          capdev.getCapdevParticipant().stream().filter(p -> p.isActive()).collect(Collectors.toList());
        if (participants != null) {
          for (CapdevParticipant capdev_participant : participants) {
            String capdev_id = capdev_participant.getCapacityDevelopment().getId().toString();
            Participant participant = capdev_participant.getParticipant();
            List<String> nameList = new ArrayList<>();
            String name = null;
            if (participant.getName() != null && !participant.getName().isEmpty()) {
              nameList.add(participant.getName());
            }
            if (participant.getLastName() != null && !participant.getLastName().isEmpty()) {
              nameList.add(participant.getLastName());
            }
            if (nameList != null && !nameList.isEmpty()) {
              name = String.join(" ", nameList);
              if (name == null || name.isEmpty()) {
                name = null;
              }
            }

            String gender = null;
            if (participant.getGender() != null && !participant.getGender().isEmpty()) {
              gender = participant.getGender();
            }
            String age = null;
            if (participant.getAge() != null && participant.getAge().getRange() != null
              && !participant.getAge().getRange().isEmpty()) {
              age = participant.getAge().getRange();
            }
            String citizenship = null;
            if (participant.getLocElementsByCitizenship() != null) {
              citizenship = participant.getLocElementsByCitizenship().getName();
            }
            String institution = null;
            if (participant.getInstitutions() != null) {
              institution = participant.getInstitutions().getName();
            }
            String country_institution = null;
            if (participant.getLocElementsByCountryOfInstitucion() != null) {
              country_institution = participant.getLocElementsByCountryOfInstitucion().getName();
            }
            String personal_email = null;
            if (participant.getPersonalEmail() != null && !participant.getPersonalEmail().isEmpty()) {
              personal_email = participant.getPersonalEmail();
            }
            String job_email = null;
            if (participant.getEmail() != null && !participant.getEmail().isEmpty()) {
              job_email = participant.getEmail();
            }
            String supervisor = null;
            if (participant.getSupervisor() != null && !participant.getSupervisor().isEmpty()) {
              supervisor = participant.getSupervisor();
            }

            model.addRow(new Object[] {capdev_id, name, gender, age, citizenship, institution, country_institution,
              personal_email, personal_email, job_email, supervisor});
          }
        }
      }
    }

    return model;
  }


  public List<CapacityDevelopment> getCapDevs() {
    return capDevs;
  }

  private TypedTableModel getCapDevSummaryTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(
      new String[] {"numMen", "numWomen", "total_participants", "namePercentage", "countCategory", "programName",
        "quantity"},
      new Class[] {Integer.class, Integer.class, Integer.class, String.class, Integer.class, String.class,
        Integer.class});

    // Gender summary
    model.addRow(new Object[] {totalMen, totalWomen, totalParticipants, "", 0});
    model.addRow(new Object[] {0, 0, 0, "Male", totalMen});
    model.addRow(new Object[] {0, 0, 0, "Female", totalWomen});
    model.addRow(new Object[] {0, 0, 0, "Other", totalOther});


    return model;
  }

  private TypedTableModel getcitizenshipTableModel() {
    // Initialization of Model
    final TypedTableModel model =
      new TypedTableModel(new String[] {"country_name", "quantity"}, new Class[] {String.class, Integer.class,});
    final Map<LocElement, Integer> countries = new HashMap<>();
    for (final CapacityDevelopment capdev : capDevs) {
      for (final CapdevParticipant participant : capdev.getCapdevParticipant()) {
        if (participant.getParticipant().getLocElementsByCitizenship() != null) {
          if (countries.containsKey(participant.getParticipant().getLocElementsByCitizenship())) {
            final int quantity = countries.get(participant.getParticipant().getLocElementsByCitizenship()) + 1;
            countries.put(participant.getParticipant().getLocElementsByCitizenship(), quantity);
          } else {

            countries.put(participant.getParticipant().getLocElementsByCitizenship(), 1);
          }
        }
      }
    }

    for (final Map.Entry<LocElement, Integer> entry : countries.entrySet()) {
      model.addRow(new Object[] {entry.getKey().getName(), entry.getValue()});
    }

    return model;
  }


  @Override
  public int getContentLength() {
    return bytesXLS.length;
  }


  @Override
  public String getContentType() {
    return "application/excel";
  }


  private TypedTableModel getCrpsTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"crp_name", "quantity", "crp"},
      new Class[] {String.class, Integer.class, String.class});
    for (final CapacityDevelopment capdev : capDevs) {
      if (capdev.getCrp() != null) {
        model.addRow(new Object[] {capdev.getCrp().getName(), 1, capdev.getCrp().getName()});
      }
    }


    return model;
  }


  public TypedTableModel getDisciplinesTableModel() {
    // Initialization of Model
    final TypedTableModel model =
      new TypedTableModel(new String[] {"discipline_name", "quantity"}, new Class[] {String.class, Integer.class,});

    final Map<Discipline, Integer> disciplinas = new HashMap<>();
    for (final CapacityDevelopment capdev : capDevs) {
      for (final CapdevDiscipline discipline : capdev.getCapdevDiscipline()) {
        if (disciplinas.containsKey(discipline.getDiscipline())) {
          final int quantity = disciplinas.get(discipline.getDiscipline()) + 1;
          disciplinas.put(discipline.getDiscipline(), quantity);
        } else {

          disciplinas.put(discipline.getDiscipline(), 1);
        }
      }
    }

    for (final Map.Entry<Discipline, Integer> entry : disciplinas.entrySet()) {
      model.addRow(new Object[] {entry.getKey().getName(), entry.getValue()});
    }

    return model;
  }


  @SuppressWarnings("unused")
  private File getFile(String fileName) {
    // Get file from resources folder
    final ClassLoader classLoader = this.getClass().getClassLoader();
    final File file = new File(classLoader.getResource(fileName).getFile());
    return file;
  }

  @Override
  public String getFileName() {
    final StringBuffer fileName = new StringBuffer();
    fileName.append("CapDevSummary-CIAT-");
    fileName.append(new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date()));
    fileName.append(".xlsx");
    return fileName.toString();
  }

  private void getFooterSubreports(HashMap<String, Element> hm, ReportFooter reportFooter) {
    final int elementCount = reportFooter.getElementCount();
    for (int i = 0; i < elementCount; i++) {
      final Element e = reportFooter.getElement(i);
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

  private TypedTableModel getFundingTypeTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"funding_type", "quantity", "fundingTypes"},
      new Class[] {String.class, Integer.class, String.class});

    for (CapacityDevelopment capdev : capDevs) {
      if (capdev.getCapdevParticipant() != null) {
        List<CapdevParticipant> participants =
          capdev.getCapdevParticipant().stream().filter(p -> p.isActive()).collect(Collectors.toList());
        if (participants != null) {
          for (CapdevParticipant capdev_participant : participants) {
            if (capdev_participant.getParticipant().getFellowship() != null) {
              model.addRow(new Object[] {capdev_participant.getParticipant().getFellowship().getName(), 1,
                capdev_participant.getParticipant().getFellowship().getName()});
            }
          }
        }
      }
    }

    return model;
  }

  private TypedTableModel getGroupTypeTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"groupType", "groupQuantity", "group_types"},
      new Class[] {String.class, Integer.class, String.class});
    final List<CapacityDevelopment> groups =
      capDevs.stream().filter(i -> i.getCategory() == 2).collect(Collectors.toList());
    for (final CapacityDevelopment capdev : groups) {
      if (capdev.getCapdevType() != null) {
        model.addRow(new Object[] {capdev.getCapdevType().getName(), 1, capdev.getCapdevType().getName()});
      }
    }
    return model;
  }


  private TypedTableModel getIndividualTypesTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"interventionType", "quantity", "individual_types"},
      new Class[] {String.class, Integer.class, String.class});

    final List<CapacityDevelopment> individuals =
      capDevs.stream().filter(i -> i.getCategory() == 1).collect(Collectors.toList());

    for (final CapacityDevelopment capdev : individuals) {
      if (capdev.getCapdevType() != null) {
        model.addRow(new Object[] {capdev.getCapdevType().getName(), 1, capdev.getCapdevType().getName()});
      }
    }

    return model;
  }


  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      inputStream = new ByteArrayInputStream(bytesXLS);
    }
    return inputStream;
  }

  private TypedTableModel getInstitutionsTableModel() {
    // Initialization of Model
    TypedTableModel model = new TypedTableModel(new String[] {"institution_name", "institution_type", "quantity"},
      new Class[] {String.class, String.class, Integer.class});

    Map<Institution, Integer> institucions = new HashMap<>();
    for (CapacityDevelopment capdev : capDevs) {
      for (CapdevParticipant participant : capdev.getCapdevParticipant()) {
        if (participant.getParticipant().getInstitutions() != null) {
          if (institucions.containsKey(participant.getParticipant().getInstitutions())) {
            int quantity = institucions.get(participant.getParticipant().getInstitutions()) + 1;
            institucions.put(participant.getParticipant().getInstitutions(), quantity);
          } else {
            institucions.put(participant.getParticipant().getInstitutions(), 1);
          }
        }
      }
    }

    for (Map.Entry<Institution, Integer> entry : institucions.entrySet()) {
      model.addRow(
        new Object[] {entry.getKey().getName(), entry.getKey().getInstitutionType().getName(), entry.getValue()});
    }

    return model;
  }


  private TypedTableModel getInstitutionsTypeTableModel() {
    // Initialization of Model
    TypedTableModel model =
      new TypedTableModel(new String[] {"institution_type", "quantity"}, new Class[] {String.class, Integer.class});


    Map<String, Integer> institucions = new HashMap<>();
    for (CapacityDevelopment capdev : capDevs) {
      for (CapdevParticipant participant : capdev.getCapdevParticipant()) {
        if (participant.getParticipant().getInstitutions() != null) {
          if (institucions.containsKey(participant.getParticipant().getInstitutions().getInstitutionType().getName())) {
            int quantity =
              institucions.get(participant.getParticipant().getInstitutions().getInstitutionType().getName()) + 1;
            institucions.put(participant.getParticipant().getInstitutions().getInstitutionType().getName(), quantity);
          } else {
            institucions.put(participant.getParticipant().getInstitutions().getInstitutionType().getName(), 1);
          }
        }
      }
    }

    for (Map.Entry<String, Integer> entry : institucions.entrySet()) {
      model.addRow(new Object[] {entry.getKey(), entry.getValue()});
    }

    return model;
  }


  private TypedTableModel getMasterTableModel(String center, String date) {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"center", "date", "year"},
      new Class[] {String.class, String.class, String.class});
    model.addRow(new Object[] {center, date, this.year});
    return model;
  }

  private TypedTableModel getOutputTypeTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(
      new String[] {"numMen", "numWomen", "total_participants", "namePercentage", "countCategory", "programName",
        "quantity"},
      new Class[] {Integer.class, Integer.class, Integer.class, String.class, Integer.class, String.class,
        Integer.class});

    return model;
  }


  private TypedTableModel getProgramsTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"program_name", "quantity", "programs_name"},
      new Class[] {String.class, Integer.class, String.class});
    for (final CapacityDevelopment capdev : capDevs) {
      if (capdev.getResearchProgram() != null) {
        model.addRow(new Object[] {capdev.getResearchProgram().getName(), 1, capdev.getResearchProgram().getName()});
      }
    }

    return model;
  }


  private TypedTableModel getTargetGroupsTableModel() {
    // Initialization of Model
    final TypedTableModel model = new TypedTableModel(new String[] {"group_name", "quantity", "target_group"},
      new Class[] {String.class, String.class, String.class});
    final Map<TargetGroup, Integer> targetGroupsMap = new HashMap<>();
    for (final CapacityDevelopment capdev : capDevs) {
      for (final CapdevTargetgroup targetGroup : capdev.getCapdevTargetgroup()) {
        if (targetGroup.isActive()) {
          if (targetGroupsMap.containsKey(targetGroup.getTargetGroups())) {
            final int quantity = targetGroupsMap.get(targetGroup.getTargetGroups()) + 1;
            targetGroupsMap.put(targetGroup.getTargetGroups(), quantity);
          } else {
            targetGroupsMap.put(targetGroup.getTargetGroups(), 1);
          }
        }
      }
    }

    for (final Map.Entry<TargetGroup, Integer> entry : targetGroupsMap.entrySet()) {
      model.addRow(new Object[] {entry.getKey().getName(), entry.getValue(), entry.getKey().getName()});
    }

    return model;
  }


  public int getYear() {
    return year;
  }


  @Override
  public void prepare() throws Exception {
    try {
      if (this.getRequest().getParameter("year") != null) {
        year = Integer.parseInt(StringUtils.trim(this.getRequest().getParameter("year")));
      }
    } catch (Exception e) {
      LOG.error("Failed to get capdev from Database. Exception: " + e.getMessage());
      throw e;
    }
    if (capdevService.findAll() != null) {
      capDevs = capdevService.findAll().stream().filter(cdl -> cdl.isActive()).collect(Collectors.toList());
      Collections.sort(capDevs, (ra1, ra2) -> ra1.getId().compareTo(ra2.getId()));
    }

  }


  public void setBytesXLS(byte[] bytesXLS) {
    this.bytesXLS = bytesXLS;
  }


  public void setCapDevs(List<CapacityDevelopment> capDevs) {
    this.capDevs = capDevs;
  }


  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }


  public void setYear(int year) {
    this.year = year;
  }


}
