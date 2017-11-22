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

import org.cgiar.ccafs.marlo.data.dao.CrpTimelineEventsDAO;
import org.cgiar.ccafs.marlo.data.model.CrpTimelineEvents;

import java.util.List;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;

/**
 * CrpTimelineEventsMySQLDAO
 * 
 * @author avalencia - CCAFS
 * @date Nov 22, 2017
 * @time 1:48:39 PM: Creation
 */
public class CrpTimelineEventsMySQLDAO extends AbstractMarloDAO<CrpTimelineEvents, Long>
  implements CrpTimelineEventsDAO {


  @Inject
  public CrpTimelineEventsMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public void deleteCrpTimelineEvents(long CrpTimelineEventsId) {
    CrpTimelineEvents CrpTimelineEvents = this.find(CrpTimelineEventsId);
    CrpTimelineEvents.setActive(false);
    this.save(CrpTimelineEvents);
  }

  @Override
  public boolean existCrpTimelineEvents(long CrpTimelineEventsID) {
    CrpTimelineEvents CrpTimelineEvents = this.find(CrpTimelineEventsID);
    if (CrpTimelineEvents == null) {
      return false;
    }
    return true;
  }

  @Override
  public CrpTimelineEvents find(long id) {
    return super.find(CrpTimelineEvents.class, id);
  }

  @Override
  public List<CrpTimelineEvents> findAll() {
    String query = "from " + CrpTimelineEvents.class.getName() + " where is_active=1";
    List<CrpTimelineEvents> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;
  }

  @Override
  public CrpTimelineEvents save(CrpTimelineEvents CrpTimelineEvents) {
    if (CrpTimelineEvents.getId() == null) {
      super.saveEntity(CrpTimelineEvents);
    } else {
      CrpTimelineEvents = super.update(CrpTimelineEvents);
    }
    return CrpTimelineEvents;
  }

}