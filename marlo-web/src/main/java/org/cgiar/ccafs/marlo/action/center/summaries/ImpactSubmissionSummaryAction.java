/*****************************************************************
 * This file is part of CCAFS Planning and Reporting Platform.
 * CCAFS P&R is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS P&R is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS P&R. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/

package org.cgiar.ccafs.marlo.action.center.summaries;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramManager;
import org.cgiar.ccafs.marlo.data.manager.ICenterCycleManager;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.ImpactSubmissionSummaryService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrés Felipe Valencia Rivera. CCAFS
 */
public class ImpactSubmissionSummaryAction extends BaseAction implements Summary {

  private static final long serialVersionUID = -624982650510682813L;
  private static Logger LOG = LoggerFactory.getLogger(ImpactSubmissionSummaryAction.class);

  // Streams
  InputStream inputStream;
  // PDF bytes
  private byte[] bytesPDF;
  // Services
  private final CrpProgramManager programService;

  private final ImpactSubmissionSummaryService impactSubmissionSummaryService;

  // Params
  private CrpProgram researchProgram;
  private long startTime;

  @Inject
  public ImpactSubmissionSummaryAction(APConfig config, CrpProgramManager programService,
    ICenterCycleManager cycleService, ImpactSubmissionSummaryService impactSubmissionSummaryService) {
    super(config);
    this.programService = programService;
    this.impactSubmissionSummaryService = impactSubmissionSummaryService;
  }

  @Override
  public String execute() throws Exception {

    bytesPDF = impactSubmissionSummaryService.execute(researchProgram, this.getActualPhase(), this.getBaseUrl());

    // Calculate time of generation
    long stopTime = System.currentTimeMillis();
    stopTime = stopTime - startTime;

    LOG.info("Downloaded successfully: " + this.getFileName() + ". User: "
      + this.getCurrentUser().getComposedCompleteName() + ". Time to generate: " + stopTime + "ms.");
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

  private File getFile(String fileName) {

    // Get file from resources folder
    ClassLoader classLoader = this.getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    return file;
  }

  @Override
  public String getFileName() {
    StringBuffer fileName = new StringBuffer();
    fileName.append("Impact_Pathway_Report-");
    // fileName.append(project.getCrp().getName() + "-");
    // fileName.append("P" + projectID + "-");
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


  public CrpProgram getResearchProgram() {
    return researchProgram;
  }

  @Override
  public void prepare() {
    long programID = -1;
    try {
      programID = Long.parseLong(StringUtils.trim(this.getRequest().getParameter(APConstants.CRP_PROGRAM_ID)));
      researchProgram = programService.getCrpProgramById(programID);
    } catch (Exception e) {
      LOG.error("Failed to get " + APConstants.CRP_PROGRAM_ID + " parameter. Parameter will be set as -1. Exception: "
        + e.getMessage());
    }
    // Calculate time to generate report
    startTime = System.currentTimeMillis();
    LOG.info(
      "Start report download: " + this.getFileName() + ". User: " + this.getCurrentUser().getComposedCompleteName());
  }

  public void setBytesPDF(byte[] bytesPDF) {
    this.bytesPDF = bytesPDF;
  }

  public void setResearchProgram(CrpProgram researchProgram) {
    this.researchProgram = researchProgram;
  }

}