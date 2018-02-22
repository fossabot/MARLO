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


public class FundingSourceInstitutionDTO implements Serializable {


  private static final long serialVersionUID = 1L;

  private Long id;

  private FundingSourceDTO fundingSource;

  private InstitutionDTO institution;

  private PhaseDTO phase;


  public FundingSourceDTO getFundingSource() {
    return fundingSource;
  }


  public Long getId() {
    return id;
  }


  public InstitutionDTO getInstitution() {
    return institution;
  }


  public PhaseDTO getPhase() {
    return phase;
  }


  public void setFundingSource(FundingSourceDTO fundingSource) {
    this.fundingSource = fundingSource;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setInstitution(InstitutionDTO institution) {
    this.institution = institution;
  }


  public void setPhase(PhaseDTO phase) {
    this.phase = phase;
  }


}
