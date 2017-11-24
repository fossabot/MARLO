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

import org.cgiar.ccafs.marlo.data.dao.CountryAgreementDAO;
import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.CountryAgreement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;


public class CountryAgreementMySQLDAO extends AbstractMarloDAO<CountryAgreement, Long> implements CountryAgreementDAO {


  @Inject
  public CountryAgreementMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * find a country agreement by its id
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param id - id of the country agreement
   * @return
   */
  @Override
  public CountryAgreement find(String id) {
    return super.find(CountryAgreement.class, Long.valueOf(id));
  }

  /**
   * find a country agreement by its code and agreement id
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param code - code of the country
   * @param agreement - id of the agreement
   * @return
   */
  @Override
  public CountryAgreement findByCodeAndAgreement(String code, String agreement) {
    CountryAgreement theCountry = null;

    String query =
      "select ca.id,ca.agreement_id,ca.code,ca.description,ca.percentage from countries_agreement ca where ca.code='"
        + code + "' and ca.agreement_id='" + agreement + "'";
    List<Map<String, Object>> list = super.findCustomQuery(query);
    if (list.size() > 0) {
      theCountry = new CountryAgreement();
      Iterator iterCountry = list.iterator();

      while (iterCountry.hasNext()) {
        Map<String, Object> mapTmp = (Map<String, Object>) iterCountry.next();

        Iterator itMap = mapTmp.entrySet().iterator();

        while (itMap.hasNext()) {
          Map.Entry record = (Map.Entry) itMap.next();

          switch (record.getKey().toString()) {
            case "id":
              theCountry.setId(Long.parseLong(record.getValue().toString()));
              break;
            case "description":
              theCountry.setDescription(record.getValue().toString());
              break;
            case "code":
              theCountry.setCode(record.getValue().toString());
              break;
            case "agreement_id":
              Agreement theAgreement = new Agreement();
              theAgreement.setId(record.getValue().toString());
              theCountry.setAgreement(theAgreement);
              break;
            case "percentage":
              theCountry.setPercentage(Double.valueOf(record.getValue().toString()));
              break;
          }


        }


      }

    }
    return theCountry;
  }

  /**
   * find all the countries agreement of an agreement
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param codAgreement - id code of the agreement
   * @return
   */
  @Override
  public List<CountryAgreement> getCountriesByAgreement(String codAgreement) {

    String query = "from " + CountryAgreement.class.getName() + " agreements.id =" + codAgreement;
    List<CountryAgreement> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  /**
   * save a new country agreement
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param country - the country agreement object
   * @return - the code of the new agreement
   */
  @Override
  public CountryAgreement save(CountryAgreement country) {
    return super.saveEntity(country);
  }


  /**
   * update a country agreement
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param country - the country agreement object
   * @return - the code of the updated agreement
   */
  @Override
  public CountryAgreement update(CountryAgreement country) {
    return super.update(country);
  }

}
