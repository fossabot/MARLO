package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 8, 2017 2:12:52 PM by Hibernate Tools 5.2.5.Final


/**
 * PlasAgreement generated by hbm2java
 */
public class PlaAgreement implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Long id;
  private Agreement agreements;
  private String plaId;
  private String description;
  private Double ammount;

  public PlaAgreement() {
  }


  public PlaAgreement(Agreement agreements, String plaId, String description) {
    this.agreements = agreements;
    this.plaId = plaId;
    this.description = description;
  }

  public PlaAgreement(Agreement agreements, String plaId, String description, Double ammount) {
    this.agreements = agreements;
    this.plaId = plaId;
    this.description = description;
    this.ammount = ammount;
  }

  public Agreement getAgreements() {
    return this.agreements;
  }

  public Double getAmmount() {
    return this.ammount;
  }

  public String getDescription() {
    return this.description;
  }

  public Long getId() {
    return this.id;
  }

  public String getPlaId() {
    return this.plaId;
  }

  public void setAgreements(Agreement agreements) {
    this.agreements = agreements;
  }

  public void setAmmount(Double ammount) {
    this.ammount = ammount;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPlaId(String plaId) {
    this.plaId = plaId;
  }


}

