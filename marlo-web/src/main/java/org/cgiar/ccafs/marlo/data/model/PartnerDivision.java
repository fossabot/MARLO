package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 12, 2017 3:46:19 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * PartnerDivision generated by hbm2java
 */
public class PartnerDivision extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -1853814625671155813L;

  @Expose
  private String acronym;

  @Expose
  private String name;

  private Set<DeliverablePartnership> deliverablePartnerships = new HashSet<DeliverablePartnership>(0);

  public PartnerDivision() {
  }

  public String getAcronym() {
    return acronym;
  }

  public String getComposedName() {

    if (this.getAcronym() != null) {
      if (this.getAcronym().length() != 0) {
        try {
          return this.getAcronym() + " - " + this.getName();
        } catch (Exception e) {
          return this.getName();
        }
      }
    }
    return this.getName();
  }

  public Set<DeliverablePartnership> getDeliverablePartnerships() {
    return deliverablePartnerships;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public String getName() {
    return name;
  }


  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setDeliverablePartnerships(Set<DeliverablePartnership> deliverablePartnerships) {
    this.deliverablePartnerships = deliverablePartnerships;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "PartnerDivision [id=" + this.getId() + ", acronym=" + acronym + ", name=" + name + "]";
  }

}

