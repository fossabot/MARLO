package org.cgiar.ccafs.marlo.data.model;
// Generated May 24, 2018 5:28:55 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ReportSynthesisIndicatorGeneral generated by hbm2java
 */
public class ReportSynthesisIndicatorGeneral extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -4912856496234834922L;

  private ReportSynthesis reportSynthesis;

  private Set<ReportSynthesisIndicator> reportSynthesisIndicators = new HashSet<ReportSynthesisIndicator>(0);

  private List<ReportSynthesisIndicator> synthesisIndicators;


  public ReportSynthesisIndicatorGeneral() {
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


  public Set<ReportSynthesisIndicator> getReportSynthesisIndicators() {
    return reportSynthesisIndicators;
  }

  public List<ReportSynthesisIndicator> getSynthesisIndicators() {
    return synthesisIndicators;
  }

  public void setReportSynthesis(ReportSynthesis reportSynthesis) {
    this.reportSynthesis = reportSynthesis;
  }

  public void setReportSynthesisIndicators(Set<ReportSynthesisIndicator> reportSynthesisIndicators) {
    this.reportSynthesisIndicators = reportSynthesisIndicators;
  }

  public void setSynthesisIndicators(List<ReportSynthesisIndicator> synthesisIndicators) {
    this.synthesisIndicators = synthesisIndicators;
  }

  @Override
  public String toString() {
    return "ReportSynthesisIndicatorGeneral [reportSynthesis=" + reportSynthesis + "]";
  }


}

