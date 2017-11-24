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

import org.cgiar.ccafs.marlo.data.dao.FundingSourceAgreementDAO;
import org.cgiar.ccafs.marlo.data.model.FundingSourceAgreement;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;


public class FundingSourceAgreementMySQLDAO extends AbstractMarloDAO<FundingSourceAgreement, Long>
  implements FundingSourceAgreementDAO {


  @Inject
  public FundingSourceAgreementMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }


  @Override
  public FundingSourceAgreement find(String codAgreement, String idFunding) {
    String query = "from " + FundingSourceAgreement.class.getName() + "agreements.id =" + codAgreement
      + " and fundingSourceId=" + idFunding;
    return super.findSingleResult(FundingSourceAgreement.class, query);
  }

  @Override
  public FundingSourceAgreement save(FundingSourceAgreement fsAgreement) {
    return super.saveEntity(fsAgreement);
  }

  @Override
  public FundingSourceAgreement update(FundingSourceAgreement fsAgreement) {
    return super.update(fsAgreement);
  }

}
