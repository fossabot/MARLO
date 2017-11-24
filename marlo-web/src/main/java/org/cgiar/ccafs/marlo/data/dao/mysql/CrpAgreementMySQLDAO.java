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
import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.CrpAgreement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;


public class CrpAgreementMySQLDAO extends AbstractMarloDAO<CrpAgreement, Long> implements CrpAgreementDAO {


  @Inject
  public CrpAgreementMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
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
    return super.find(CrpAgreement.class, Long.valueOf(id));
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
    CrpAgreement theCrp = null;
    String query =
      "select cp.id,cp.agreement_id,cp.crp_id,cp.description,cp.percentage from crps_agreement cp where cp.agreement_id='"
        + agreement + "' and cp.crp_id=" + crpId;
    List<Map<String, Object>> list = super.findCustomQuery(query);
    if (list.size() > 0) {
      theCrp = new CrpAgreement();
      Iterator iterCrp = list.iterator();

      while (iterCrp.hasNext()) {
        Map<String, Object> mapTmp = (Map<String, Object>) iterCrp.next();

        Iterator itMap = mapTmp.entrySet().iterator();

        while (itMap.hasNext()) {
          Map.Entry record = (Map.Entry) itMap.next();

          switch (record.getKey().toString()) {
            case "id":
              theCrp.setId(Long.parseLong(record.getValue().toString()));
              break;
            case "description":
              theCrp.setDescription(record.getValue().toString());
              break;
            case "crp_id":
              theCrp.setCrpId(record.getValue().toString());
              break;
            case "agreement_id":
              Agreement theAgreement = new Agreement();
              theAgreement.setId(record.getValue().toString());
              theCrp.setAgreement(theAgreement);
              break;
            case "percentage":
              theCrp.setPercentage(Double.valueOf(record.getValue().toString()));
              break;
          }


        }


      }
    }
    return theCrp;
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
    String query = "from " + CrpAgreement.class.getName() + " agreements.id =" + codAgreement;
    List<CrpAgreement> list = super.findAll(query);
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
   * @return - the object
   */
  @Override
  public CrpAgreement save(CrpAgreement crp) {
    return super.saveEntity(crp);
  }

  /**
   * update a crp agreement
   * 
   * @author JULIANRODRIGUEZ
   * @since 20171023
   * @param crp - a object to update
   * @return - the object
   */
  @Override
  public CrpAgreement update(CrpAgreement crp) {
    return super.update(crp);
  }

}
