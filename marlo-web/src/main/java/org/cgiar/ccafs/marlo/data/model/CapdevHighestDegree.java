package org.cgiar.ccafs.marlo.data.model;


/**
 * CapdevHighestDegree generated by hbm2java
 */
public class CapdevHighestDegree implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
  private String description;
  private String acronym;

  public CapdevHighestDegree() {
  }


  public CapdevHighestDegree(Long id) {
    this.id = id;
  }

  public CapdevHighestDegree(Long id, String name, String description, String acronym) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.acronym = acronym;
  }

  public String getAcronym() {
    return this.acronym;
  }

  public String getDescription() {
    return this.description;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


}
