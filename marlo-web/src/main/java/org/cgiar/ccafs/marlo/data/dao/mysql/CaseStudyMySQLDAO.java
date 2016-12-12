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

import org.cgiar.ccafs.marlo.data.dao.CaseStudyDAO;
import org.cgiar.ccafs.marlo.data.model.CaseStudy;

import java.util.List;

import com.google.inject.Inject;

public class CaseStudyMySQLDAO implements CaseStudyDAO {

  private StandardDAO dao;

  @Inject
  public CaseStudyMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteCaseStudy(long caseStudyId) {
    CaseStudy caseStudy = this.find(caseStudyId);
    caseStudy.setActive(false);
    return this.save(caseStudy) > 0;
  }

  @Override
  public boolean existCaseStudy(long caseStudyID) {
    CaseStudy caseStudy = this.find(caseStudyID);
    if (caseStudy == null) {
      return false;
    }
    return true;

  }

  @Override
  public CaseStudy find(long id) {
    return dao.find(CaseStudy.class, id);

  }

  @Override
  public List<CaseStudy> findAll() {
    String query = "from " + CaseStudy.class.getName() + " where is_active=1";
    List<CaseStudy> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(CaseStudy caseStudy) {
    if (caseStudy.getId() == null) {
      dao.save(caseStudy);
    } else {
      dao.update(caseStudy);
    }


    return caseStudy.getId();
  }


}