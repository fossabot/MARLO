package org.cgiar.ccafs.marlo.data.model;
// Generated Nov 24, 2016 11:37:14 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CenterImpactBeneficiary generated by hbm2java
 */
public class CenterImpactBeneficiary extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -6926942149840809770L;

  @Expose
  private CenterImpact researchImpact;

  @Expose
  private CenterBeneficiary beneficiary;

  @Expose
  private CenterRegion researchRegion;


  public CenterImpactBeneficiary() {
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
    CenterImpactBeneficiary other = (CenterImpactBeneficiary) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public CenterBeneficiary getBeneficiary() {
    return beneficiary;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public CenterImpact getResearchImpact() {
    return researchImpact;
  }

  public CenterRegion getResearchRegion() {
    return researchRegion;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public void setBeneficiary(CenterBeneficiary beneficiary) {
    this.beneficiary = beneficiary;
  }

  public void setResearchImpact(CenterImpact researchImpact) {
    this.researchImpact = researchImpact;
  }

  public void setResearchRegion(CenterRegion researchRegion) {
    this.researchRegion = researchRegion;
  }

  @Override
  public String toString() {
    return "CenterImpactBeneficiary [id=" + this.getId() + ", researchImpact=" + researchImpact + ", beneficiary="
      + beneficiary + "]";
  }

}

