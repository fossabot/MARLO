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


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.CrpAgreementDAO;
import org.cgiar.ccafs.marlo.data.model.CrpAgreement;

import java.util.List;

import com.google.inject.Inject;


public class CrpAgreementMySQLDAO implements CrpAgreementDAO {

  private StandardDAO dao;

  @Inject
  public CrpAgreementMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  /**
   * fin a crp agreement by its id
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param id
   * @return
   */
  @Override
  public CrpAgreement find(String id) {
    return dao.find(CrpAgreement.class, id);
  }

  /**
   * find a crp agreement by its crpid and the agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crpId - id of the crp
   * @param agreement - id of the agreement
   * @return - The crp agreement object
   */
  @Override
  public CrpAgreement findByCrpIdAndAgreement(String crpId, String agreement) {
    String query = "from " + CrpAgreement.class.getName() + "agreements.id =" + agreement + " and crpId=" + crpId;
    List<CrpAgreement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list.iterator().next();
    }
    return null;
  }

  /**
   * get a list of crp related to the agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param codAgreement - id of the agreement
   * @return - a list with all the crps
   */
  @Override
  public List<CrpAgreement> getCrpsByAgreement(String codAgreement) {
    String query = "from " + CrpAgreement.class.getName() + "agreements.id =" + codAgreement;
    List<CrpAgreement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;
  }

  /**
   * save a new crp agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crp - a crp agreement object to save
   * @return - the id of the new agreement
   */
  @Override
  public String save(CrpAgreement crp) {
    dao.save(crp);
    return String.valueOf(crp.getId());
  }

  /**
   * update a crp agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crp - a object to update
   * @return - the id of the updated object
   */
  @Override
  public String update(CrpAgreement crp) {
    dao.update(crp);
    return String.valueOf(crp.getId());
  }

}
