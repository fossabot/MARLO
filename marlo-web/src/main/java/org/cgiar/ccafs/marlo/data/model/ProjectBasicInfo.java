/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/


package org.cgiar.ccafs.marlo.data.model;

import java.io.Serializable;
import java.util.Date;


/**
 * This class is used for Summary purpose.
 * Check ProjectBasicInfo Summary for more detail
 * 
 * @author JULIANRODRIGUEZ <julian.rodriguez@cgiar.org>
 */
public class ProjectBasicInfo implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4702866066858792601L;

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  private Long projectId;
  private String projectTitle;
  private String projectSummary;
  private Date startDate;
  private Date endDate;
  private String managementLiaison;
  private String managementLiaisonContactPerson;
  private String flagships;
  private String regionalPrograms;
  private String w1w2;
  private String w3;
  private String bilateral;
  private String leadInstitution;

  private String projectLeader;

  public String getBilateral() {
    return bilateral;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getFlagships() {
    return flagships;
  }

  public String getLeadInstitution() {
    return leadInstitution;
  }

  public String getManagementLiaison() {
    return managementLiaison;
  }

  public String getManagementLiaisonContactPerson() {
    return managementLiaisonContactPerson;
  }

  public Long getProjectId() {
    return projectId;
  }

  public String getProjectLeader() {
    return projectLeader;
  }

  public String getProjectSummary() {
    return projectSummary;
  }

  public String getProjectTitle() {
    return projectTitle;
  }

  public String getRegionalPrograms() {
    return regionalPrograms;
  }

  public Date getStartDate() {
    return startDate;
  }

  public String getW1w2() {
    return w1w2;
  }

  public String getW3() {
    return w3;
  }

  public void setBilateral(String bilateral) {
    this.bilateral = bilateral;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setFlagships(String flagships) {
    this.flagships = flagships;
  }

  public void setLeadInstitution(String leadInstitution) {
    this.leadInstitution = leadInstitution;
  }

  public void setManagementLiaison(String managementLiaison) {
    this.managementLiaison = managementLiaison;
  }

  public void setManagementLiaisonContactPerson(String managementLiaisonContactPerson) {
    this.managementLiaisonContactPerson = managementLiaisonContactPerson;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public void setProjectLeader(String projectLeader) {
    this.projectLeader = projectLeader;
  }

  public void setProjectSummary(String projectSummary) {
    this.projectSummary = projectSummary;
  }

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  public void setRegionalPrograms(String regionalPrograms) {
    this.regionalPrograms = regionalPrograms;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setW1w2(String w1w2) {
    this.w1w2 = w1w2;
  }

  public void setW3(String w3) {
    this.w3 = w3;
  }


}
