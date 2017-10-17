package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 8, 2017 2:12:52 PM by Hibernate Tools 5.2.5.Final


/**
 * CountriesAgreement generated by hbm2java
 */
public class CountryAgreement implements java.io.Serializable {


  private Long id;
  private Agreement agreement;
  private String code;
  private String description;
  private Double percentage;

  public CountryAgreement() {
  }


  public CountryAgreement(Agreement agreements, String code, String description) {
    this.agreement = agreements;
    this.code = code;
    this.description = description;
  }

  public CountryAgreement(Agreement agreements, String code, String description, Double percentage) {
    this.agreement = agreements;
    this.code = code;
    this.description = description;
    this.percentage = percentage;
  }

  public Agreement getAgreements() {
    return this.agreement;
  }

  public String getCode() {
    return this.code;
  }

  public String getDescription() {
    return this.description;
  }

  public Long getId() {
    return this.id;
  }

  public Double getPercentage() {
    return this.percentage;
  }

  public void setAgreements(Agreement agreements) {
    this.agreement = agreements;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPercentage(Double percentage) {
    this.percentage = percentage;
  }


}

