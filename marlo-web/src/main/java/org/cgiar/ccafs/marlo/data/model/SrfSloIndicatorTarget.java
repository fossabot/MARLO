package org.cgiar.ccafs.marlo.data.model;
// Generated May 21, 2018 2:10:20 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * SrfSloIndicatorTarget generated by hbm2java
 */
public class SrfSloIndicatorTarget extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -8975527941728871240L;


  @Expose
  private SrfSloIndicator srfSloIndicator;


  @Expose
  private String narrative;


  @Expose
  private BigDecimal value;


  @Expose
  private int year;

  private String targetsIndicator;

  @Expose
  private SrfTargetUnit srfTargetUnit;

  private Set<ReportSynthesisCrpProgressTarget> reportSynthesisCrpProgressTargets =
    new HashSet<ReportSynthesisCrpProgressTarget>(0);

  public SrfSloIndicatorTarget() {
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
    SrfSloIndicatorTarget other = (SrfSloIndicatorTarget) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public String getComposedName() {
    return "SLO " + this.getSrfSloIndicator().getSrfSlo().getId() + " Target - " + narrative;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getNarrative() {
    return narrative;
  }


  public Set<ReportSynthesisCrpProgressTarget> getReportSynthesisCrpProgressTargets() {
    return reportSynthesisCrpProgressTargets;
  }


  public SrfSloIndicator getSrfSloIndicator() {
    return srfSloIndicator;
  }


  public SrfTargetUnit getSrfTargetUnit() {
    return srfTargetUnit;
  }


  public String getTargetsIndicator() {
    return targetsIndicator;
  }


  public BigDecimal getValue() {
    return value;
  }

  public int getYear() {
    return year;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public void setNarrative(String narrative) {
    this.narrative = narrative;
  }

  public void
    setReportSynthesisCrpProgressTargets(Set<ReportSynthesisCrpProgressTarget> reportSynthesisCrpProgressTargets) {
    this.reportSynthesisCrpProgressTargets = reportSynthesisCrpProgressTargets;
  }

  public void setSrfSloIndicator(SrfSloIndicator srfSloIndicator) {
    this.srfSloIndicator = srfSloIndicator;
  }

  public void setSrfTargetUnit(SrfTargetUnit srfTargetUnit) {
    this.srfTargetUnit = srfTargetUnit;
  }

  public void setTargetsIndicator(String targetsIndicator) {
    this.targetsIndicator = targetsIndicator;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }


  public void setYear(int year) {
    this.year = year;
  }


  @Override
  public String toString() {
    return "SrfSloIndicatorTarget [id=" + this.getId() + ", narrative=" + narrative + ", value=" + value + ", year="
      + year + "]";
  }

}

