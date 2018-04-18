package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 18, 2018 1:21:50 PM by Hibernate Tools 3.4.0.CR1

import com.google.gson.annotations.Expose;

/**
 * RepIndPhaseResearchPartnership generated by hbm2java
 */
public class RepIndPhaseResearchPartnership implements java.io.Serializable {

  private static final long serialVersionUID = -2512720215143137653L;
  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String definition;

  public RepIndPhaseResearchPartnership() {
  }

  public RepIndPhaseResearchPartnership(String name, String definition) {
    this.name = name;
    this.definition = definition;
  }

  public String getDefinition() {
    return this.definition;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


}

