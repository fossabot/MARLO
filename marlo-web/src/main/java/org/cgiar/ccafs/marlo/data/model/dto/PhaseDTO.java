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


package org.cgiar.ccafs.marlo.data.model.dto;

import org.cgiar.ccafs.marlo.data.model.Phase;

import java.io.Serializable;
import java.util.Date;


public class PhaseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private GlobalUnitDTO crp;

  private String description;

  private int year;

  private Boolean editable;

  private Boolean visible;

  private Phase next;

  private Date startDate;

  private Date endDate;


  public GlobalUnitDTO getCrp() {
    return crp;
  }


  public String getDescription() {
    return description;
  }


  public Boolean getEditable() {
    return editable;
  }


  public Date getEndDate() {
    return endDate;
  }


  public Phase getNext() {
    return next;
  }


  public Date getStartDate() {
    return startDate;
  }


  public Boolean getVisible() {
    return visible;
  }


  public int getYear() {
    return year;
  }


  public void setCrp(GlobalUnitDTO crp) {
    this.crp = crp;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public void setEditable(Boolean editable) {
    this.editable = editable;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public void setNext(Phase next) {
    this.next = next;
  }


  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }


  public void setVisible(Boolean visible) {
    this.visible = visible;
  }


  public void setYear(int year) {
    this.year = year;
  }


}
