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


package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.manager.impl.AgreementManagerImpl;
import org.cgiar.ccafs.marlo.data.model.dto.AgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.FundingSourceAgreementDTO;

import com.google.inject.ImplementedBy;

/**
 * Manager for the agrements
 * 
 * @author Julián Rodríguez - CCAFS/CIAT
 * @date 20171011
 */
@ImplementedBy(AgreementManagerImpl.class)
public interface AgreementManager {


  /**
   * this method load an agreement form database given its code
   * 
   * @author Julián Rodríguez
   * @date 20171011
   * @param codAgreement - the code of the agreement
   * @return an AgreementDTO object
   */
  public AgreementDTO loadAgreement(String codAgreement);


  /**
   * load an agreement relationship with the funding source id and the code of
   * the agreement
   * 
   * @author Julián Rodríguez Calderón CCAFS/CIAT
   * @since 20171018
   * @param codAgreement code of the agreement
   * @param idFundingSource id of the funding source
   * @return a DTO object
   */
  public FundingSourceAgreementDTO loadFSAgreement(String codAgreement, String idFundingSource);


  /**
   * this method saves a new agreement into the database
   * 
   * @author Julián Rodríguez
   * @date 20171011
   * @param agreement - this is an AgreementDTO object
   * @return an string with the id of the agreement into the database
   */
  public String saveAgreement(AgreementDTO agreement);

  /**
   * this method saves or update a new funding source agreement into the database
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171018
   * @param fsAgreement an DTO object to save
   * @return String with the id of the object
   */
  public String saveFSAgreement(FundingSourceAgreementDTO fsAgreement);


}
