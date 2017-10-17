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

package org.cgiar.ccafs.marlo.action.json.global;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.AgreementManager;
import org.cgiar.ccafs.marlo.data.model.dto.AgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.CountryAgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.CrpAgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.PlaAgreementDTO;
import org.cgiar.ccafs.marlo.ocs.model.AgreementOCS;
import org.cgiar.ccafs.marlo.ocs.model.CountryOCS;
import org.cgiar.ccafs.marlo.ocs.model.CrpOCS;
import org.cgiar.ccafs.marlo.ocs.model.DonorOCS;
import org.cgiar.ccafs.marlo.ocs.model.PlaOCS;
import org.cgiar.ccafs.marlo.ocs.model.ResearcherOCS;
import org.cgiar.ccafs.marlo.ocs.ws.MarloOcsClient;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Christian Garcia - CIAT/CCAFS
 * @author Julián Rodríguez - CIAT/CCAFS
 */
public class OcsServiceAction extends BaseAction {

  /**
   * 
   */
  private static final long serialVersionUID = -6338578372277010087L;
  /**
   * 
   */
  private String ocsCode;
  private MarloOcsClient ocsClient;
  private AgreementManager agreementManager;

  private AgreementOCS json;


  @Inject
  public OcsServiceAction(APConfig config, MarloOcsClient ocsClient, AgreementManager agreementManager) {
    super(config);
    this.ocsClient = ocsClient;
    this.agreementManager = agreementManager;

  }


  @Override
  public String execute() throws Exception {
    /*
     * search an agreement into the database. If the agreement doesn't exits
     * call the service and save the data into the database. If the agreement exits
     * validate the date of the agreement and return the object of the database instead
     * the object of the service.
     */
    AgreementDTO agreement = agreementManager.loadAgreement(ocsCode);

    if (agreement != null) {
      /*
       * check the date of the last sync. if the date of synchronization is less than
       * the current date, call the service and save/update the new data. If not call the data
       * store in the database
       */

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
      String syncDate = format.format(agreement.getSyncDate());
      String today = format.format(new Date());


      if (!syncDate.equals(today)) {
        json = ocsClient.getagreement(ocsCode);

        if (json != null) {
          AgreementDTO theAgreement = this.returnAgreement(json);
          theAgreement.setNew(false);
          theAgreement.setSyncDate(new Date());
          // save or update the method
          agreementManager.saveAgreement(theAgreement);
        }


      } else {
        // map the json object from the DTO
        json = this.returnOCS(agreement);
      }


    } else {
      json = ocsClient.getagreement(ocsCode);

      if (json != null) {
        AgreementDTO theAgreement = this.returnAgreement(json);
        theAgreement.setNew(true);
        theAgreement.setSyncDate(new Date());
        // save or update the method
        agreementManager.saveAgreement(theAgreement);
      }

    }


    return SUCCESS;
  }


  public AgreementOCS getJson() {
    return json;
  }


  @Override
  public void prepare() throws Exception {
    Map<String, Object> parameters = this.getParameters();
    ocsCode = (StringUtils.trim(((String[]) parameters.get(APConstants.OCS_CODE_REQUEST_ID))[0]));
  }


  /**
   * return an Agreement Object Type given an AgreementOCS type
   * 
   * @author Julián Rodríguez CCAFS/CIAT
   * @param agreementOCS this is the object from the service
   * @since 20171012
   * @return an AgreementDTO Object
   */
  private AgreementDTO returnAgreement(AgreementOCS agreementOCS) {

    AgreementDTO agreement = new AgreementDTO();
    agreement.setId(agreementOCS.getId());
    agreement.setDescription(agreementOCS.getDescription());
    agreement.setShortTitle(agreementOCS.getShortTitle());
    agreement.setObjectives(agreementOCS.getObjectives());
    agreement.setGrantAmmount(agreementOCS.getGrantAmount());
    agreement.setStartDate(agreementOCS.getStartDate());
    agreement.setEndDate(agreementOCS.getEndDate());
    agreement.setExtensionDate(agreementOCS.getExtensionDate());
    agreement.setContractStatus(agreementOCS.getContractStatus());
    agreement.setFundingType(agreementOCS.getFundingType());
    agreement.setOriginalDonorId(agreementOCS.getOriginalDonor().getId());
    agreement.setOriginalDonor(agreementOCS.getOriginalDonor().getName());
    agreement.setDonorId(agreementOCS.getDirectDonor().getId());
    agreement.setDonor(agreementOCS.getDirectDonor().getName());
    agreement.setResearchId(agreementOCS.getResearcher().getId());
    agreement.setReasearchName(agreementOCS.getResearcher().getName());

    List<CountryAgreementDTO> countries = new ArrayList<>();
    for (CountryOCS country : agreementOCS.getCountries()) {
      CountryAgreementDTO countryDTO = new CountryAgreementDTO();
      countryDTO.setAgreement(agreement);
      countryDTO.setCode(country.getCode());
      countryDTO.setDescription(country.getDescription());
      countryDTO.setPercentage(country.getPercentage());
      countries.add(countryDTO);
    }

    agreement.setCountriesAgreements(countries);

    List<PlaAgreementDTO> plas = new ArrayList<>();
    for (PlaOCS pla : agreementOCS.getPlas()) {
      PlaAgreementDTO plaDTO = new PlaAgreementDTO();
      plaDTO.setPlaId(pla.getId());
      plaDTO.setDescription(pla.getDescription());
      plaDTO.setAmmount(pla.getAmmount());
      plaDTO.setAgreement(agreement);
      plas.add(plaDTO);
    }

    agreement.setPlasAgreements(plas);

    List<CrpAgreementDTO> crps = new ArrayList<>();
    for (CrpOCS crp : agreementOCS.getCrps()) {
      CrpAgreementDTO crpDTO = new CrpAgreementDTO();
      crpDTO.setAgreement(agreement);
      crpDTO.setCrpId(crp.getId());
      crpDTO.setDescription(crp.getDescription());
      crpDTO.setPercentage(crp.getPercentage());
      crps.add(crpDTO);
    }
    agreement.setCrpsAgreements(crps);


    return agreement;

  }

  /**
   * return an object type AgreementOCS given an AgreementDTO type
   * 
   * @author Julián Rodríguez CCAFS/CIAT
   * @param agreement this is the object store in Database
   * @since 20171012
   * @return an AgreementOCS
   */
  private AgreementOCS returnOCS(AgreementDTO agreement) {

    AgreementOCS agreementOCS = new AgreementOCS();
    agreementOCS.setId(agreement.getId());
    agreementOCS.setDescription(agreement.getDescription());
    agreementOCS.setShortTitle(agreement.getShortTitle());
    agreementOCS.setObjectives(agreement.getObjectives());
    agreementOCS.setGrantAmount(agreement.getGrantAmmount());
    agreementOCS.setStartDate(agreement.getStartDate());
    agreementOCS.setEndDate(agreement.getEndDate());
    agreementOCS.setExtensionDate(agreement.getExtensionDate());
    agreementOCS.setContractStatus(agreement.getContractStatus());
    agreementOCS.setFundingType(agreement.getFundingType());

    DonorOCS originalDonor = new DonorOCS();
    originalDonor.setId(agreement.getOriginalDonorId());
    originalDonor.setName(agreement.getOriginalDonor());

    DonorOCS donor = new DonorOCS();
    donor.setId(agreement.getDonorId());
    donor.setName(agreement.getDonor());

    agreementOCS.setOriginalDonor(originalDonor);
    agreementOCS.setDirectDonor(donor);

    ResearcherOCS researcher = new ResearcherOCS();
    researcher.setId(agreement.getResearchId());
    researcher.setName(agreement.getReasearchName());
    agreementOCS.setResearcher(researcher);


    List<CountryOCS> countries = new ArrayList<>();
    for (CountryAgreementDTO country : agreement.getCountriesAgreements()) {
      CountryOCS countryOCS = new CountryOCS();
      countryOCS.setCode(country.getCode());
      countryOCS.setDescription(country.getDescription());
      countryOCS.setPercentage(country.getPercentage());
      countries.add(countryOCS);
    }

    agreementOCS.setCountries(countries);

    List<PlaOCS> plas = new ArrayList<>();
    for (PlaAgreementDTO pla : agreement.getPlasAgreements()) {
      PlaOCS plaOCS = new PlaOCS();
      plaOCS.setId(pla.getPlaId());
      plaOCS.setDescription(pla.getDescription());
      plaOCS.setAmmount(pla.getAmmount());

      plas.add(plaOCS);
    }

    agreementOCS.setPlas(plas);

    List<CrpOCS> crps = new ArrayList<>();
    for (CrpAgreementDTO crp : agreement.getCrpsAgreements()) {
      CrpOCS crpOCS = new CrpOCS();
      crpOCS.setId(crp.getCrpId());
      crpOCS.setDescription(crp.getDescription());
      crpOCS.setPercentage(crp.getPercentage());
      crps.add(crpOCS);
    }
    agreementOCS.setCrps(crps);


    return agreementOCS;
  }

  public void setJson(AgreementOCS json) {
    this.json = json;
  }


}
