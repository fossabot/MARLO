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
  private Agreement agreement;
  private String plaId;
  private String description;
  private Double ammount;


  public PlaAgreement() {
  }


  public PlaAgreement(Agreement agreement, String plaId, String description) {
    this.agreement = agreement;
    this.plaId = plaId;
    this.description = description;
  }

  public PlaAgreement(Agreement agreement, String plaId, String description, Double ammount) {
    this.agreement = agreement;
    this.plaId = plaId;
    this.description = description;
    this.ammount = ammount;
  }

  public Agreement getAgreement() {
    return this.agreement;
  }

  public Double getAmmount() {
    return this.ammount;
  }

  public String getDescription() {
    return this.description;
  }

  public Long getId() {
    try {
      return this.id;
    } catch (Exception e) {
      return (long) 0;
    }
  }

  public String getPlaId() {
    return this.plaId;
  }

  public void setAgreement(Agreement agreement) {
    this.agreement = agreement;
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

