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
import org.cgiar.ccafs.marlo.data.dao.CountryAgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.CrpAgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.FundingSourceAgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.PlaAgreementDAO;
import org.cgiar.ccafs.marlo.data.manager.AgreementManager;
import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.CountryAgreement;
import org.cgiar.ccafs.marlo.data.model.CrpAgreement;
import org.cgiar.ccafs.marlo.data.model.PlaAgreement;
import org.cgiar.ccafs.marlo.mappers.AgreementMapper;
import org.cgiar.ccafs.marlo.mappers.CountryAgreementMapper;
import org.cgiar.ccafs.marlo.mappers.CrpAgreementMapper;
import org.cgiar.ccafs.marlo.mappers.PlaAgreementMapper;
import org.cgiar.ccafs.marlo.ocs.model.AgreementOCS;
import org.cgiar.ccafs.marlo.ocs.model.CountryOCS;
import org.cgiar.ccafs.marlo.ocs.model.CrpOCS;
import org.cgiar.ccafs.marlo.ocs.model.PlaOCS;

import com.google.inject.Inject;


public class AgreementManagerImpl implements AgreementManager {

  private AgreementDAO agreementDAO;
  private CountryAgreementDAO countryAgreement;
  private CrpAgreementDAO crpAgreement;
  private PlaAgreementDAO plaAgreement;
  private FundingSourceAgreementDAO fsAgreement;


  @Inject
  public AgreementManagerImpl(AgreementDAO agreementDAO, CountryAgreementDAO countryAgreement,
    CrpAgreementDAO crpAgreement, PlaAgreementDAO plaAgreement) {
    this.agreementDAO = agreementDAO;
    this.countryAgreement = countryAgreement;
    this.crpAgreement = crpAgreement;
    this.plaAgreement = plaAgreement;
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
  public AgreementOCS loadAgreement(String codAgreement) {
    AgreementOCS agreementDTO = null;
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
  public String saveAgreement(AgreementOCS agreement) {
    String codAgreement = null;

    Agreement agreementDB = AgreementMapper.INSTANCE.agreementDTOToAgreement(agreement);
    // save or update the agreement
    if (agreement.isNew()) {
      codAgreement = this.agreementDAO.save(agreementDB);
    } else {
      /**
       * this is necessary because the mapper don't map the list, and when the object
       * updates there is a problem with the data types of the list.So putting in null
       * solves the problem. But we need to understand how to make the map wit MapStructs
       * in this kind of cases.
       */
      agreementDB.setCountriesAgreements(null);
      agreementDB.setPlasAgreements(null);
      agreementDB.setCrpsAgreements(null);
      codAgreement = this.agreementDAO.update(agreementDB);
    }


    // Saving the countries of the agreement
    for (CountryOCS country : agreement.getCountries()) {
      CountryAgreement theCountry = CountryAgreementMapper.INSTANCE.countryAgreementDTOToCountryAgreement(country);
      theCountry.setAgreement(agreementDB);

      if (agreement.isNew()) {
        this.countryAgreement.save(theCountry);
      } else {
        CountryAgreement countryDB = countryAgreement.findByCodeAndAgreement(theCountry.getCode(), agreement.getId());
        theCountry.setId(countryDB.getId());
        this.countryAgreement.update(theCountry);
      }

    }

    for (CrpOCS crp : agreement.getCrps()) {
      CrpAgreement theCrp = CrpAgreementMapper.INSTANCE.crpAgreementDTOToCrpAgreement(crp);
      theCrp.setAgreement(agreementDB);

      if (agreement.isNew()) {
        this.crpAgreement.save(theCrp);
      } else {
        CrpAgreement crpAgreementDB = this.crpAgreement.findByCrpIdAndAgreement(theCrp.getCrpId(), agreement.getId());
        theCrp.setId(crpAgreementDB.getId());
        this.crpAgreement.update(theCrp);
      }

    }


    // saving the plas of the agreement

    for (PlaOCS pla : agreement.getPlas()) {
      PlaAgreement thePla = PlaAgreementMapper.INSTANCE.plaAgreementDTOToPlaAgreement(pla);
      thePla.setAgreement(agreementDB);

      if (agreement.isNew()) {
        this.plaAgreement.save(thePla);
      } else {
        PlaAgreement plaAgreementDB = this.plaAgreement.findByPlaIdAndAgreement(thePla.getPlaId(), agreement.getId());
        thePla.setId(plaAgreementDB.getId());
        this.plaAgreement.update(thePla);
      }

    }

    return codAgreement;

  }


}
