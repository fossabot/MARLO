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
import org.cgiar.ccafs.marlo.ocs.model.AgreementOCS;
import org.cgiar.ccafs.marlo.ocs.ws.MarloOcsClient;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    // AgreementDTO agreement = agreementManager.loadAgreement(ocsCode);
    json = agreementManager.loadAgreement(ocsCode);

    if (json != null) {
      /*
       * check the date of the last sync. if the date of synchronization is less than
       * the current date, call the service and save/update the new data. If not call the data
       * store in the database
       */

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
      String syncDate = format.format(json.getSyncDate());
      String today = format.format(new Date());


      if (!syncDate.equals(today)) {
        json = ocsClient.getagreement(ocsCode);

        if (json != null) {

          json.setNew(false);
          json.setSyncDate(new Date());
          // save or update the method
          agreementManager.saveAgreement(json);
        }


      }


    } else {
      json = ocsClient.getagreement(ocsCode);

      if (json != null) {

        json.setNew(true);
        json.setSyncDate(new Date());
        // save or update the method
        agreementManager.saveAgreement(json);
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


  public void setJson(AgreementOCS json) {
    this.json = json;
  }


}
