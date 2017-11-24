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


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * LiaisonInstitution generated by hbm2java
 */
public class LiaisonInstitution implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -1466921023839583918L;
  @Expose
  private Long id;
  @Expose
  private CrpProgram crpProgram;
  @Expose
  private Institution institution;

  @Expose
  private String name;

  @Expose
  private String acronym;
  @Expose
  private Crp crp;
  @Expose
  private boolean active;
  private Set<LiaisonUser> liaisonUsers = new HashSet<LiaisonUser>(0);
  private Set<Project> projects = new HashSet<Project>(0);

  public LiaisonInstitution() {
  }

  public LiaisonInstitution(CrpProgram crpProgram, Institution institution, String name, String acronym,
    Set<LiaisonUser> liaisonUserses, Set<Project> projectses) {
    this.crpProgram = crpProgram;
    this.institution = institution;
    this.name = name;
    this.acronym = acronym;
    this.liaisonUsers = liaisonUserses;
    this.projects = projectses;
  }


  public LiaisonInstitution(Institution institution, String name) {
    this.institution = institution;
    this.name = name;
  }


  public String getAcronym() {
    return this.acronym;
  }

  public String getComposedName() {
    if (this.getAcronym() != null) {
      if (this.getAcronym().length() != 0) {

        return this.getAcronym() + " - " + this.getName();


      }
    }

    return this.getName();

  }

  public Crp getCrp() {
    return crp;
  }

  public CrpProgram getCrpProgram() {
    return crpProgram;
  }


  @Override
  public Long getId() {
    return this.id;
  }

  public Institution getInstitution() {
    return institution;
  }


  public Set<LiaisonUser> getLiaisonUsers() {
    return liaisonUsers;
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
    User user = new User();
    user.setId(new Long(3));
    return user;
  }


  public String getName() {
    return this.name;
  }

  public Set<Project> getProjects() {
    return projects;
  }


  @Override
  public boolean isActive() {
    return active;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setCrp(Crp crp) {
    this.crp = crp;
  }

  public void setCrpProgram(CrpProgram crpProgram) {
    this.crpProgram = crpProgram;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  public void setLiaisonUsers(Set<LiaisonUser> liaisonUserses) {
    this.liaisonUsers = liaisonUserses;
  }

  public void setName(String name) {
    this.name = name;
  }


  public void setProjects(Set<Project> projects) {
    this.projects = projects;
  }


  @Override
  public String toString() {
    return "LiaisonInstitution [id=" + id + ", crpProgram=" + crpProgram + ", institution=" + institution + ", name="
      + name + ", acronym=" + acronym + ", crp=" + crp + "]";
  }

}

