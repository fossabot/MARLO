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

/**
 * a simple DTO class for FundingSourceAgreement
 * 
 * @author Julián Rodríguez CCAFS/CIAT
 * @date 20171012
 */

public class FundingSourceAgreementDTO {

  private Long id;
  private AgreementDTO agreement;
  private long fundingSourceId;

  public AgreementDTO getAgreement() {
    return agreement;
  }

  public long getFundingSourceId() {
    return fundingSourceId;
  }

  public Long getId() {
    return id;
  }

  public void setAgreement(AgreementDTO agreement) {
    this.agreement = agreement;
  }

  public void setFundingSourceId(long fundingSourceId) {
    this.fundingSourceId = fundingSourceId;
  }

  public void setId(Long id) {
    this.id = id;
  }


}
