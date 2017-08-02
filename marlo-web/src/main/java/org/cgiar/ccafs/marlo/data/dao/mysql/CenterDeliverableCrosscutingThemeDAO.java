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

import org.cgiar.ccafs.marlo.data.dao.ICenterDeliverableCrosscutingThemeDAO;
import org.cgiar.ccafs.marlo.data.model.CenterDeliverableCrosscutingTheme;

import java.util.List;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;

public class CenterDeliverableCrosscutingThemeDAO extends AbstractMarloDAO
  implements ICenterDeliverableCrosscutingThemeDAO {


  @Inject
  public CenterDeliverableCrosscutingThemeDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public boolean deleteDeliverableCrosscutingTheme(long deliverableCrosscutingThemeId) {
    CenterDeliverableCrosscutingTheme deliverableCrosscutingTheme = this.find(deliverableCrosscutingThemeId);
    deliverableCrosscutingTheme.setActive(false);
    return this.save(deliverableCrosscutingTheme) > 0;
  }

  @Override
  public boolean existDeliverableCrosscutingTheme(long deliverableCrosscutingThemeID) {
    CenterDeliverableCrosscutingTheme deliverableCrosscutingTheme = this.find(deliverableCrosscutingThemeID);
    if (deliverableCrosscutingTheme == null) {
      return false;
    }
    return true;

  }

  @Override
  public CenterDeliverableCrosscutingTheme find(long id) {
    return super.find(CenterDeliverableCrosscutingTheme.class, id);

  }

  @Override
  public List<CenterDeliverableCrosscutingTheme> findAll() {
    String query = "from " + CenterDeliverableCrosscutingTheme.class.getName();
    List<CenterDeliverableCrosscutingTheme> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public List<CenterDeliverableCrosscutingTheme> getDeliverableCrosscutingThemesByUserId(long userId) {
    String query = "from " + CenterDeliverableCrosscutingTheme.class.getName() + " where user_id=" + userId;
    return super.findAll(query);
  }

  @Override
  public long save(CenterDeliverableCrosscutingTheme deliverableCrosscutingTheme) {
    if (deliverableCrosscutingTheme.getId() == null) {
      super.save(deliverableCrosscutingTheme);
    } else {
      super.update(deliverableCrosscutingTheme);
    }
    return deliverableCrosscutingTheme.getId();
  }

  @Override
  public long save(CenterDeliverableCrosscutingTheme deliverableCrosscutingTheme, String actionName,
    List<String> relationsName) {
    if (deliverableCrosscutingTheme.getId() == null) {
      super.save(deliverableCrosscutingTheme, actionName, relationsName);
    } else {
      super.update(deliverableCrosscutingTheme, actionName, relationsName);
    }
    return deliverableCrosscutingTheme.getId();
  }


}