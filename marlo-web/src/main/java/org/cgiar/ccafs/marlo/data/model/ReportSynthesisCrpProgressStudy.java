package org.cgiar.ccafs.marlo.data.model;
// Generated May 21, 2018 2:10:20 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisCrpProgressStudy generated by hbm2java
 */
public class ReportSynthesisCrpProgressStudy extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 5731871790828462667L;

  @Expose
  private ProjectExpectedStudy projectExpectedStudy;

  @Expose
  private ReportSynthesisCrpProgress reportSynthesisCrpProgress;

  public ReportSynthesisCrpProgressStudy() {
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ReportSynthesisCrpProgressStudy other = (ReportSynthesisCrpProgressStudy) obj;
    if (this.getProjectExpectedStudy() == null) {
      if (other.getProjectExpectedStudy() != null) {
        return false;
      }
    } else if (!this.getProjectExpectedStudy().equals(other.getProjectExpectedStudy())) {
      return false;
    }
    if (this.getReportSynthesisCrpProgress() == null) {
      if (other.getReportSynthesisCrpProgress() != null) {
        return false;
      }
    } else if (!this.getReportSynthesisCrpProgress().getId().equals(other.getReportSynthesisCrpProgress().getId())) {
      return false;
    }
    return true;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public ProjectExpectedStudy getProjectExpectedStudy() {
    return projectExpectedStudy;
  }


  public ReportSynthesisCrpProgress getReportSynthesisCrpProgress() {
    return reportSynthesisCrpProgress;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    result = prime * result + ((reportSynthesisCrpProgress == null) ? 0 : reportSynthesisCrpProgress.hashCode());
    return result;
  }

  public void setProjectExpectedStudy(ProjectExpectedStudy projectExpectedStudy) {
    this.projectExpectedStudy = projectExpectedStudy;
  }


  public void setReportSynthesisCrpProgress(ReportSynthesisCrpProgress reportSynthesisCrpProgress) {
    this.reportSynthesisCrpProgress = reportSynthesisCrpProgress;
  }


}
