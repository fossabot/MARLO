package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 20, 2018 11:48:08 AM by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * PowbIndAssesmentRisk generated by hbm2java
 */
public class PowbIndAssesmentRisk extends MarloBaseEntity implements java.io.Serializable {


  private static final long serialVersionUID = 6593334683422687244L;

  @Expose
  private String name;


  private Set<CrpMilestone> crpMilestones = new HashSet<CrpMilestone>(0);


  public PowbIndAssesmentRisk() {
  }

  public Set<CrpMilestone> getCrpMilestones() {
    return crpMilestones;
  }

  public String getName() {
    return name;
  }

  public void setCrpMilestones(Set<CrpMilestone> crpMilestones) {
    this.crpMilestones = crpMilestones;
  }

  public void setName(String name) {
    this.name = name;
  }


}
