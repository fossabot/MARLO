package org.cgiar.ccafs.marlo.data.model;
// Generated Jun 18, 2018 8:59:00 AM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisCrossCgiarCollaboration generated by hbm2java
 */
public class ReportSynthesisCrossCgiarCollaboration extends MarloAuditableEntity
  implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -7242774805983782350L;

  @Expose
  private GlobalUnit globalUnit;

  @Expose
  private ReportSynthesisCrossCgiar reportSynthesisCrossCgiar;

  @Expose
  private String flagship;

  @Expose
  private RepIndCollaborationType repIndCollaborationType;

  @Expose
  private Integer status;

  @Expose
  private String description;

  public ReportSynthesisCrossCgiarCollaboration() {
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
    ReportSynthesisCrossCgiarCollaboration other = (ReportSynthesisCrossCgiarCollaboration) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public String getDescription() {
    return description;
  }

  public String getFlagship() {
    return flagship;
  }


  public GlobalUnit getGlobalUnit() {
    return globalUnit;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public RepIndCollaborationType getRepIndCollaborationType() {
    return repIndCollaborationType;
  }

  public ReportSynthesisCrossCgiar getReportSynthesisCrossCgiar() {
    return reportSynthesisCrossCgiar;
  }


  public Integer getStatus() {
    return status;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public void setFlagship(String flagship) {
    this.flagship = flagship;
  }

  public void setGlobalUnit(GlobalUnit globalUnit) {
    this.globalUnit = globalUnit;
  }

  public void setRepIndCollaborationType(RepIndCollaborationType repIndCollaborationType) {
    this.repIndCollaborationType = repIndCollaborationType;
  }

  public void setReportSynthesisCrossCgiar(ReportSynthesisCrossCgiar reportSynthesisCrossCgiar) {
    this.reportSynthesisCrossCgiar = reportSynthesisCrossCgiar;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }


}

