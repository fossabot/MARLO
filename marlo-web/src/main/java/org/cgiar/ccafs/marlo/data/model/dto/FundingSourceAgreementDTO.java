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


public class FundingSourceAgreementDTO {

  private String id;
  private String codAgreement;
  private String codfundingSource;

  public String getCodAgreement() {
    return codAgreement;
  }

  public String getCodfundingSource() {
    return codfundingSource;
  }

  public String getId() {
    return id;
  }

  public void setCodAgreement(String codAgreement) {
    this.codAgreement = codAgreement;
  }

  public void setCodfundingSource(String codfundingSource) {
    this.codfundingSource = codfundingSource;
  }

  public void setId(String id) {
    this.id = id;
  }


}
