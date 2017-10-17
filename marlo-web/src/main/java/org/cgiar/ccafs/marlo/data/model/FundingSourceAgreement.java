package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 8, 2017 2:12:52 PM by Hibernate Tools 5.2.5.Final


/**
 * FundingSourcesAgreements generated by hbm2java
 */
public class FundingSourceAgreement implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Long id;
  private Agreement agreement;
  private long fundingSourceId;

  public FundingSourceAgreement() {
  }

  public FundingSourceAgreement(Agreement agreements, long fundingSourceId) {
    this.agreement = agreements;
    this.fundingSourceId = fundingSourceId;
  }

  public Agreement getAgreements() {
    return this.agreement;
  }

  public long getFundingSourceId() {
    return this.fundingSourceId;
  }

  public Long getId() {
    return this.id;
  }

  public void setAgreements(Agreement agreements) {
    this.agreement = agreements;
  }

  public void setFundingSourceId(long fundingSourceId) {
    this.fundingSourceId = fundingSourceId;
  }

  public void setId(Long id) {
    this.id = id;
  }


}

