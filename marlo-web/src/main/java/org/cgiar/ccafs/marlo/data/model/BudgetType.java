package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 5, 2016 3:46:35 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * BudgetType generated by hbm2java
 */
public class BudgetType implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 4497778782300688556L;

  @Expose
  private Long id;

  @Expose
  private String name;
  @Expose
  private String description;


  private Set<ProjectBudget> projectBudgets = new HashSet<ProjectBudget>(0);


  private Set<FundingSource> fundingSources = new HashSet<FundingSource>(0);

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
    BudgetType other = (BudgetType) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public String getDescription() {
    return description;
  }

  public Set<FundingSource> getFundingSources() {
    return fundingSources;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }

  @Override
  public String getModificationJustification() {

    return "";
  }

  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }

  public String getName() {
    return name;
  }

  public Set<ProjectBudget> getProjectBudgets() {
    return projectBudgets;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean isActive() {

    return true;
  }

  public void setDescription(String decription) {
    this.description = decription;
  }

  public void setFundingSources(Set<FundingSource> fundingSources) {
    this.fundingSources = fundingSources;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setProjectBudgets(Set<ProjectBudget> projectBudgets) {
    this.projectBudgets = projectBudgets;
  }


}

