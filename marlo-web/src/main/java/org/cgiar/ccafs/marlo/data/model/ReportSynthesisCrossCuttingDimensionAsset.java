package org.cgiar.ccafs.marlo.data.model;
// Generated May 31, 2018 4:07:34 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisCrossCuttingDimensionAsset generated by hbm2java
 */
public class ReportSynthesisCrossCuttingDimensionAsset extends MarloAuditableEntity
  implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 2009701301682650387L;

  @Expose
  private DeliverableIntellectualAsset deliverableIntellectualAsset;

  @Expose
  private ReportSynthesisCrossCuttingDimension reportSynthesisCrossCuttingDimension;

  public ReportSynthesisCrossCuttingDimensionAsset() {
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
    ReportSynthesisCrossCuttingDimensionAsset other = (ReportSynthesisCrossCuttingDimensionAsset) obj;
    if (this.getDeliverableIntellectualAsset() == null) {
      if (other.getDeliverableIntellectualAsset() != null) {
        return false;
      }
    } else if (!this.getDeliverableIntellectualAsset().equals(other.getDeliverableIntellectualAsset())) {
      return false;
    }
    if (this.getReportSynthesisCrossCuttingDimension() == null) {
      if (other.getReportSynthesisCrossCuttingDimension() != null) {
        return false;
      }
    } else if (!this.getReportSynthesisCrossCuttingDimension()
      .equals(other.getReportSynthesisCrossCuttingDimension())) {
      return false;
    }
    return true;
  }

  public DeliverableIntellectualAsset getDeliverableIntellectualAsset() {
    return deliverableIntellectualAsset;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public ReportSynthesisCrossCuttingDimension getReportSynthesisCrossCuttingDimension() {
    return reportSynthesisCrossCuttingDimension;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((deliverableIntellectualAsset == null) ? 0 : deliverableIntellectualAsset.hashCode());
    result = prime * result
      + ((reportSynthesisCrossCuttingDimension == null) ? 0 : reportSynthesisCrossCuttingDimension.hashCode());
    return result;
  }

  public void setDeliverableIntellectualAsset(DeliverableIntellectualAsset deliverableIntellectualAsset) {
    this.deliverableIntellectualAsset = deliverableIntellectualAsset;
  }

  public void
    setReportSynthesisCrossCuttingDimension(ReportSynthesisCrossCuttingDimension reportSynthesisCrossCuttingDimension) {
    this.reportSynthesisCrossCuttingDimension = reportSynthesisCrossCuttingDimension;
  }


}

