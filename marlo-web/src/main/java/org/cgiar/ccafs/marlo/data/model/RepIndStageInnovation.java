package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 18, 2018 1:21:50 PM by Hibernate Tools 3.4.0.CR1

import com.google.gson.annotations.Expose;

/**
 * RepIndStageInnovation generated by hbm2java
 */
public class RepIndStageInnovation extends MarloBaseEntity implements java.io.Serializable {


  private static final long serialVersionUID = 8631221361540916292L;
  @Expose
  private String name;
  @Expose
  private String definition;

  public RepIndStageInnovation() {
  }

  public String getDefinition() {
    return this.definition;
  }

  public String getName() {
    return this.name;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public void setName(String name) {
    this.name = name;
  }


}

