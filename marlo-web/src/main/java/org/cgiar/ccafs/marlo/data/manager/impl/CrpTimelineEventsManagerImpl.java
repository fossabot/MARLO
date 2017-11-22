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
package org.cgiar.ccafs.marlo.data.manager.impl;


import org.cgiar.ccafs.marlo.data.dao.CrpTimelineEventsDAO;
import org.cgiar.ccafs.marlo.data.manager.CrpTimelineEventsManager;
import org.cgiar.ccafs.marlo.data.model.CrpTimelineEvents;

import java.util.List;

import com.google.inject.Inject;


/**
 * CrpTimelineEventsManagerImpl
 * 
 * @author avalencia - CCAFS
 * @date Nov 22, 2017
 * @time 1:42:16 PM: Creation
 */
public class CrpTimelineEventsManagerImpl implements CrpTimelineEventsManager {


  private CrpTimelineEventsDAO CrpTimelineEventsDAO;
  // Managers


  @Inject
  public CrpTimelineEventsManagerImpl(CrpTimelineEventsDAO CrpTimelineEventsDAO) {
    this.CrpTimelineEventsDAO = CrpTimelineEventsDAO;
  }

  @Override
  public void deleteCrpTimelineEvents(long CrpTimelineEventsId) {
    CrpTimelineEventsDAO.deleteCrpTimelineEvents(CrpTimelineEventsId);
  }

  @Override
  public boolean existCrpTimelineEvents(long CrpTimelineEventsID) {
    return CrpTimelineEventsDAO.existCrpTimelineEvents(CrpTimelineEventsID);
  }

  @Override
  public List<CrpTimelineEvents> findAll() {
    return CrpTimelineEventsDAO.findAll();
  }

  @Override
  public CrpTimelineEvents getCrpTimelineEventsById(long CrpTimelineEventsID) {
    return CrpTimelineEventsDAO.find(CrpTimelineEventsID);
  }

  @Override
  public CrpTimelineEvents saveCrpTimelineEvents(CrpTimelineEvents CrpTimelineEvents) {
    return CrpTimelineEventsDAO.save(CrpTimelineEvents);
  }

}
