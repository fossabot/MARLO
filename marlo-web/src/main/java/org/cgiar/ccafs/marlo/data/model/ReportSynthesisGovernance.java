package org.cgiar.ccafs.marlo.data.model;
// Generated May 24, 2018 5:28:55 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisGovernance generated by hbm2java
 */
public class ReportSynthesisGovernance extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 7243533984625495024L;

  private ReportSynthesis reportSynthesis;

  @Expose
  private String description;

  public ReportSynthesisGovernance() {
  }

  public String getDescription() {
    return description;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public ReportSynthesis getReportSynthesis() {
    return reportSynthesis;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public void setReportSynthesis(ReportSynthesis reportSynthesis) {
    this.reportSynthesis = reportSynthesis;
  }


}

