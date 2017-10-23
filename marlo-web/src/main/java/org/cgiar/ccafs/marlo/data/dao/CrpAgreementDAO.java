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

import org.cgiar.ccafs.marlo.data.dao.mysql.CrpAgreementMySQLDAO;
import org.cgiar.ccafs.marlo.data.model.CrpAgreement;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy(CrpAgreementMySQLDAO.class)
public interface CrpAgreementDAO {

  /**
   * fin a crp agreement by its id
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param id
   * @return
   */
  public CrpAgreement find(String id);

  /**
   * find a crp agreement by its crpid and the agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crpId - id of the crp
   * @param agreement - id of the agreement
   * @return - The crp agreement object
   */
  public CrpAgreement findByCrpIdAndAgreement(String crpId, String agreement);

  /**
   * get a list of crp related to the agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param codAgreement - id of the agreement
   * @return - a list with all the crps
   */
  public List<CrpAgreement> getCrpsByAgreement(String codAgreement);


  /**
   * save a new crp agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crp - a crp agreement object to save
   * @return - the id of the new agreement
   */
  public String save(CrpAgreement crp);


  /**
   * update a crp agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crp - a object to update
   * @return - the id of the updated object
   */
  public String update(CrpAgreement crp);

}
