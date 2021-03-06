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
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CrpAssumption generated by hbm2java
 */
public class CrpAssumption extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = 5392601449095678314L;

  @Expose
  private CrpOutcomeSubIdo crpOutcomeSubIdo;

  @Expose
  private String description;

  public CrpAssumption() {
  }

  public CrpAssumption(CrpOutcomeSubIdo crpOutcomeSubIdo, String description) {
    this.crpOutcomeSubIdo = crpOutcomeSubIdo;
    this.description = description;
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
      return false;
    }
    CrpAssumption other = (CrpAssumption) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public CrpOutcomeSubIdo getCrpOutcomeSubIdo() {
    return this.crpOutcomeSubIdo;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public void setCrpOutcomeSubIdo(CrpOutcomeSubIdo crpOutcomeSubIdo) {
    this.crpOutcomeSubIdo = crpOutcomeSubIdo;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "CrpAssumption [id=" + this.getId() + ", crpOutcomeSubIdo=" + crpOutcomeSubIdo + ", description="
      + description + "]";
  }


}

