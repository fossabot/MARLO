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

  @Override
  public CrpAgreement find(String id) {
    return dao.find(CrpAgreement.class, id);
  }

  @Override
  public List<CrpAgreement> getCrpsByAgreement(String codAgreement) {
    String query = "from " + CrpAgreement.class.getName() + "agreements.id =" + codAgreement;
    List<CrpAgreement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;
  }

  @Override
  public String save(CrpAgreement crp) {
    dao.save(crp);
    return String.valueOf(crp.getId());
  }

  @Override
  public String update(CrpAgreement crp) {
    dao.update(crp);
    return String.valueOf(crp.getId());
  }

}
