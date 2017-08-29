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

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.inject.Inject;
import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.ExcelReportUtil;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OngoingActiveProjectsSummaryAction extends BaseAction implements Summary {

  private static final long serialVersionUID = 1L;
  private static Logger LOG = LoggerFactory.getLogger(OngoingActiveProjectsSummaryAction.class);

  // Parameters
  private Crp loggedCrp;
  private int year;
  private String cycle;
  private long startTime;
  // XLSX bytes
  private byte[] bytesXLSX;
  // Streams
  InputStream inputStream;
  // Managers
  private CrpManager crpManager;


  @Inject
  public OngoingActiveProjectsSummaryAction(APConfig config, CrpManager crpManager) {
    super(config);
    this.crpManager = crpManager;
  }


  @Override
  public String execute() throws Exception {
    ClassicEngineBoot.getInstance().start();
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ResourceManager manager = new ResourceManager();
    manager.registerDefaults();

    try {
      Resource reportResource = manager
        .createDirectly(this.getClass().getResource("/pentaho/OnGoingActiveProjectsSummary.prpt"), MasterReport.class);
      MasterReport masterReport = (MasterReport) reportResource.getResource();
      Number idParam = loggedCrp.getId();

      // Get datetime
      ZonedDateTime timezone = ZonedDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d 'at' HH:mm ");
      String zone = timezone.getOffset() + "";
      if (zone.equals("Z")) {
        zone = "+0";
      }

      String currentDate = timezone.format(format) + "(GMT" + zone + ")";

      // Fill the parameters to the report

      masterReport.getParameterValues().put("i8nMainTitle", this.getText("summaries.oaprojects.mainTitle"));
      masterReport.getParameterValues().put("i8nPhaseDescription", cycle);
      masterReport.getParameterValues().put("i8nPhaseYear", String.valueOf(year));
      masterReport.getParameterValues().put("i8nCrpId", idParam.toString());
      masterReport.getParameterValues().put("center", loggedCrp.getAcronym());
      masterReport.getParameterValues().put("i8nProjectId", this.getText("summaries.oaprojects.projectId"));
      masterReport.getParameterValues().put("i8nProjectTitle", this.getText("summaries.oaprojects.projectTitle"));
      masterReport.getParameterValues().put("i8nProjectSummary", this.getText("summaries.oaprojects.projectSummary"));
      masterReport.getParameterValues().put("i8nStartDate", this.getText("summaries.oaprojects.startDate"));
      masterReport.getParameterValues().put("i8nEndDate", this.getText("summaries.oaprojects.endDate"));
      masterReport.getParameterValues().put("i8nManagementLiaison",
        this.getText("summaries.oaprojects.managementLiasion"));
      masterReport.getParameterValues().put("i8nManagementLiaisonCP",
        this.getText("summaries.oaprojects.managementLiasionContPerson"));
      masterReport.getParameterValues().put("i8nFlagship", this.getText("summaries.oaprojects.flagships"));
      masterReport.getParameterValues().put("i8nRegionalPrograms",
        this.getText("summaries.oaprojects.regionalPrograms"));
      masterReport.getParameterValues().put("i8nW1W2", this.getText("summaries.oaprojects.w1w2"));
      masterReport.getParameterValues().put("i8nW3", this.getText("summaries.oaprojects.w3"));
      masterReport.getParameterValues().put("i8nBilateral", this.getText("summaries.oaprojects.bilateral"));
      masterReport.getParameterValues().put("i8nLeadInstitution", this.getText("summaries.oaprojects.leadInstitution"));
      masterReport.getParameterValues().put("i8nProjectLeader", this.getText("summaries.oaprojects.projectLeader"));
      masterReport.getParameterValues().put("date", currentDate);


      ExcelReportUtil.createXLSX(masterReport, os);
      bytesXLSX = os.toByteArray();
      os.close();

    } catch (Exception e) {
      LOG.error("Error generating OnGoing/Active Projects Summary " + e.getMessage());
      throw e;
    }

    // Calculate time of generation
    long stopTime = System.currentTimeMillis();
    stopTime = stopTime - startTime;
    LOG.info(
      "Downloaded successfully: " + this.getFileName() + ". User: " + this.getCurrentUser().getComposedCompleteName()
        + ". CRP: " + this.loggedCrp.getAcronym() + ". Time to generate: " + stopTime + "ms.");

    return SUCCESS;
  }

  public byte[] getBytesXLSX() {
    return bytesXLSX;
  }


  @Override
  public int getContentLength() {
    // TODO Auto-generated method stub
    return bytesXLSX.length;
  }

  @Override
  public String getContentType() {
    // TODO Auto-generated method stub
    return "application/xlsx";
  }

  public String getCycle() {
    return cycle;
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
    StringBuffer fileName = new StringBuffer();
    fileName.append("OnGoingActiveProjectsSummary-");
    fileName.append(this.year + "_");
    fileName.append(new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date()));
    fileName.append(".xlsx");
    return fileName.toString();
  }


  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      inputStream = new ByteArrayInputStream(bytesXLSX);
    }
    return inputStream;
  }


  public Crp getLoggedCrp() {
    return loggedCrp;
  }


  public int getYear() {
    return year;
  }


  @Override
  public void prepare() {

    // Get loggerCrp
    try {
      loggedCrp = (Crp) this.getSession().get(APConstants.SESSION_CRP);
      loggedCrp = crpManager.getCrpById(loggedCrp.getId());
    } catch (Exception e) {
      LOG.error("Failed to get " + APConstants.SESSION_CRP + " parameter. Exception: " + e.getMessage());
    }

    // Calculate time to generate report
    startTime = System.currentTimeMillis();
    LOG.info("Start report download: " + this.getFileName() + ". User: "
      + this.getCurrentUser().getComposedCompleteName() + ". CRP: " + this.loggedCrp.getAcronym());
  }


  public void setBytesXLSX(byte[] bytesXLSX) {
    this.bytesXLSX = bytesXLSX;
  }


  public void setCycle(String cycle) {
    this.cycle = cycle;
  }


  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }


  public void setLoggedCrp(Crp loggedCrp) {
    this.loggedCrp = loggedCrp;
  }


  public void setYear(int year) {
    this.year = year;
  }


}
