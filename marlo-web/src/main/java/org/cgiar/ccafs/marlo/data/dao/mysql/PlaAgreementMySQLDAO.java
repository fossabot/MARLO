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

import org.cgiar.ccafs.marlo.data.dao.PlaAgreementDAO;
import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.PlaAgreement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;


public class PlaAgreementMySQLDAO extends AbstractMarloDAO<PlaAgreement, Long> implements PlaAgreementDAO {


  @Inject
  public PlaAgreementMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * find a pla agreement by its id
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param id - id of the agreement
   * @return
   */
  @Override
  public PlaAgreement find(String id) {
    return super.find(PlaAgreement.class, Long.valueOf(id));
  }

  /**
   * find a pla agreement by its pla id and its agreement\
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param plaId - id of the pla agreement
   * @param agreement - id of the agreement
   * @return - a pla agreement object
   */
  @Override
  public PlaAgreement findByPlaIdAndAgreement(String plaId, String agreement) {
    PlaAgreement thePla = null;
    String query =
      "select pa.id,pa.agreement_id,pa.pla_id,pa.description,pa.ammount from plas_agreement pa where pa.pla_id='"
        + plaId + "' and pa.agreement_id='" + agreement + "'";
    List<Map<String, Object>> list = super.findCustomQuery(query);
    if (list.size() > 0) {
      thePla = new PlaAgreement();
      Iterator iterPla = list.iterator();

      while (iterPla.hasNext()) {
        Map<String, Object> mapTmp = (Map<String, Object>) iterPla.next();

        Iterator itMap = mapTmp.entrySet().iterator();

        while (itMap.hasNext()) {
          Map.Entry record = (Map.Entry) itMap.next();

          switch (record.getKey().toString()) {
            case "id":
              thePla.setId(Long.parseLong(record.getValue().toString()));
              break;
            case "description":
              thePla.setDescription(record.getValue().toString());
              break;
            case "pla_id":
              thePla.setPlaId(record.getValue().toString());
              break;
            case "agreement_id":
              Agreement theAgreement = new Agreement();
              theAgreement.setId(record.getValue().toString());
              thePla.setAgreement(theAgreement);
              break;
            case "ammount":
              thePla.setAmmount(Double.valueOf(record.getValue().toString()));
              break;
          }


        }
      }
    }
    return thePla;
  }

  /**
   * find a list of all the pla agreements related to the agreement
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param codAgreement - code of the agreement
   * @return - a list with all the pla agreement
   */
  @Override
  public List<PlaAgreement> getPlasByAgreement(String codAgreement) {
    String query = "from " + PlaAgreement.class.getName() + " agreements.id =" + codAgreement;
    List<PlaAgreement> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;
  }

  /**
   * save a new pla agreement
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param pla - the pla agreement object
   * @return - the id of the agreement
   */
  @Override
  public PlaAgreement save(PlaAgreement pla) {
    return super.saveEntity(pla);
  }

  /**
   * update a pla agreement
   * 
   * @author JULIANRODRIGUEZ\
   * @since 20171023
   * @param pla - the pla agreement object
   * @return - the id of the updated pla agreement
   */
  @Override
  public PlaAgreement update(PlaAgreement pla) {
    return super.update(pla);
  }

}
