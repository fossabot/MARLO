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

// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final

import com.google.gson.annotations.Expose;

/**
 * CrpProgramLeader generated by hbm2java
 */
public class CenterLeader extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 7345327536072331541L;

  @Expose
  private CenterProgram researchProgram;


  @Expose
  private GlobalUnit researchCenter;

  @Expose
  private CenterArea researchArea;


  @Expose
  private User user;

  @Expose
  private CenterAllTypes type;


  public CenterLeader() {
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
    }
    CenterLeader other = (CenterLeader) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  /**
   * @return the researchArea
   */
  public CenterArea getResearchArea() {
    return researchArea;
  }

  public GlobalUnit getResearchCenter() {
    return researchCenter;
  }

  /**
   * @return the researchProgram
   */
  public CenterProgram getResearchProgram() {
    return researchProgram;
  }


  public CenterAllTypes getType() {
    return type;
  }


  public User getUser() {
    return this.user;
  }


  /**
   * @param researchArea the researchArea to set
   */
  public void setResearchArea(CenterArea researchArea) {
    this.researchArea = researchArea;
  }


  public void setResearchCenter(GlobalUnit researchCenter) {
    this.researchCenter = researchCenter;
  }


  /**
   * @param researchProgram the researchProgram to set
   */
  public void setResearchProgram(CenterProgram researchProgram) {
    this.researchProgram = researchProgram;
  }


  public void setType(CenterAllTypes type) {
    this.type = type;
  }


  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "CenterLeader [id = " + this.getId() + ", user = " + user + "]";
  }

}
