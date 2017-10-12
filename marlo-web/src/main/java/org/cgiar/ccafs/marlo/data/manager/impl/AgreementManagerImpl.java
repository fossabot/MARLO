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


package org.cgiar.ccafs.marlo.data.manager.impl;

import org.cgiar.ccafs.marlo.data.dao.AgreementDAO;
import org.cgiar.ccafs.marlo.data.manager.AgreementManager;
import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.dto.AgreementDTO;
import org.cgiar.ccafs.marlo.mappers.AgreementMapper;

import com.google.inject.Inject;


public class AgreementManagerImpl implements AgreementManager {

  private AgreementDAO agreementDAO;


  @Inject
  public AgreementManagerImpl(AgreementDAO agreementDAO) {
    this.agreementDAO = agreementDAO;
  }


  /**
   * this method load an agreement form database given its code
   * 
   * @author Julián Rodríguez
   * @date 20171011
   * @param codAgreement - the code of the agreement
   * @return an AgreementDTO object
   */
  @Override
  public AgreementDTO loadAgreement(String codAgreement) {
    AgreementDTO agreementDTO = null;
    Agreement agreement = this.agreementDAO.find(codAgreement);

    if (agreement != null) {
      agreementDTO = AgreementMapper.INSTANCE.agreementToAgreementDTO(agreement);
    }

    return agreementDTO;
  }


  /**
   * this method saves or updates a new agreement into the database
   * 
   * @author Julián Rodríguez
   * @date 20171011
   * @param agreement - this is an AgreementDTO object
   * @return an string with the id of the agreement into the database
   */
  @Override
  public String saveAgreement(AgreementDTO agreement) {
    String response = null;
    Agreement agreementDB = AgreementMapper.INSTANCE.agreementDTOToAgreement(agreement);

    if (agreement.isNew()) {
      response = this.agreementDAO.save(agreementDB);
    } else {
      response = this.agreementDAO.update(agreementDB);
    }

    return response;
  }


}
