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
import org.cgiar.ccafs.marlo.data.model.PlaAgreement;

import java.util.List;

import com.google.inject.Inject;


public class PlaAgreementMySQLDAO implements PlaAgreementDAO {

  private StandardDAO dao;

  @Inject
  public PlaAgreementMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public PlaAgreement find(String id) {
    return dao.find(PlaAgreement.class, id);
  }

  @Override
  public List<PlaAgreement> getPlasByAgreement(String codAgreement) {
    String query = "from " + PlaAgreement.class.getName() + "agreements.id =" + codAgreement;
    List<PlaAgreement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;
  }

  @Override
  public String save(PlaAgreement pla) {
    dao.save(pla);
    return String.valueOf(pla.getId());
  }

  @Override
  public String update(PlaAgreement pla) {
    dao.update(pla);
    return String.valueOf(pla.getId());
  }

}
