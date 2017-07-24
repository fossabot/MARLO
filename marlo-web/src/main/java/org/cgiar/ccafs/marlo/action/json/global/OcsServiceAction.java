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
import org.cgiar.ccafs.marlo.ocs.MarloOcsClient;
import org.cgiar.ccafs.marlo.ocs.model.AgreementOCS;
import org.cgiar.ccafs.marlo.utils.APConfig;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.HttpParameters;

/**
 * Service for get the OCS info through of the OCS Web services.
 * 
 * @author Christian Garcia - CIAT/CCAFS
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

  private AgreementOCS json;


  @Inject
  public OcsServiceAction(APConfig config, MarloOcsClient ocsClient) {
    super(config);
    this.ocsClient = ocsClient;

  }


  @Override
  /**
   * Get the OCS info an send the success result
   */
  public String execute() throws Exception {
    json = ocsClient.getagreement(ocsCode);


    return SUCCESS;
  }


  public AgreementOCS getJson() {
    return json;
  }


  @Override
  /**
   * Get the OCS code send by http request parameter.
   */
  public void prepare() throws Exception {
    HttpParameters parameters = this.getParameters();
    ocsCode = (StringUtils.trim((parameters.get(APConstants.OCS_CODE_REQUEST_ID).getValue())));
  }


  public void setJson(AgreementOCS json) {
    this.json = json;
  }


}
