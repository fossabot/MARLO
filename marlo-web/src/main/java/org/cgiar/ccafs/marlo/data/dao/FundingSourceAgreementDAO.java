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


package org.cgiar.ccafs.marlo.data.dao;

import org.cgiar.ccafs.marlo.data.model.FundingSourceAgreement;

public interface FundingSourceAgreementDAO {

  /**
   * find a FundingSourceAgreement by code and idFunding
   * 
   * @param codAgreement
   * @param idFunding
   * @return the FundingSourceAgreement object
   */
  public FundingSourceAgreement find(String codAgreement, String idFunding);


  /**
   * save a new fundingSourceAgreement object
   * 
   * @param fsAgreement
   * @return the object
   */
  public FundingSourceAgreement save(FundingSourceAgreement fsAgreement);

  /**
   * update a existing FundingSourceAgreement
   * 
   * @param fsAgreement
   * @return the object
   */
  public FundingSourceAgreement update(FundingSourceAgreement fsAgreement);

}
