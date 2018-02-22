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

import java.io.Serializable;
import java.util.Date;


public class InstitutionDTO implements Serializable {


  private static final long serialVersionUID = 1L;
  private Long id;
  private InstitutionTypeDTO institutionType;
  private String name;
  private String acronym;
  private String websiteLink;
  private Long programId;
  private Date added;

  public String getAcronym() {
    return acronym;
  }

  public Date getAdded() {
    return added;
  }

  public Long getId() {
    return id;
  }

  public InstitutionTypeDTO getInstitutionType() {
    return institutionType;
  }

  public String getName() {
    return name;
  }

  public Long getProgramId() {
    return programId;
  }

  public String getWebsiteLink() {
    return websiteLink;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setAdded(Date added) {
    this.added = added;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitutionType(InstitutionTypeDTO institutionType) {
    this.institutionType = institutionType;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setProgramId(Long programId) {
    this.programId = programId;
  }

  public void setWebsiteLink(String websiteLink) {
    this.websiteLink = websiteLink;
  }


}
