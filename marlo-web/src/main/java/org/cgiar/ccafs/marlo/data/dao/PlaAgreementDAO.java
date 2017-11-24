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

import org.cgiar.ccafs.marlo.data.dao.mysql.PlaAgreementMySQLDAO;
import org.cgiar.ccafs.marlo.data.model.PlaAgreement;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy(PlaAgreementMySQLDAO.class)
public interface PlaAgreementDAO {

  /**
   * find a pla agreement by its id
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param id - id of the agreement
   * @return
   */
  public PlaAgreement find(String id);


  /**
   * find a pla agreement by its pla id and its agreement\
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param plaId - id of the pla agreement
   * @param agreement - id of the agreement
   * @return - a pla agreement object
   */

  public PlaAgreement findByPlaIdAndAgreement(String plaId, String agreement);

  /**
   * find a list of all the pla agreements related to the agreement
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param codAgreement - code of the agreement
   * @return - a list with all the pla agreement
   */
  public List<PlaAgreement> getPlasByAgreement(String codAgreement);


  /**
   * save a new pla agreement
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param pla - the pla agreement object
   * @return - the object
   */
  public PlaAgreement save(PlaAgreement pla);

  /**
   * update a pla agreement
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param pla - the pla agreement object
   * @return - the object
   */
  public PlaAgreement update(PlaAgreement pla);

}
