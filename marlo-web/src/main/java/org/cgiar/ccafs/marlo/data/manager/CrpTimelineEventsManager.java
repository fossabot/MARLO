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
package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.manager.impl.CrpTimelineEventsManagerImpl;
import org.cgiar.ccafs.marlo.data.model.CrpTimelineEvents;

import java.util.List;

import com.google.inject.ImplementedBy;


/**
 * CrpTimelineEventsManager
 * 
 * @author avalencia - CCAFS
 * @date Nov 22, 2017
 * @time 1:30:45 PM: Creation
 */
@ImplementedBy(CrpTimelineEventsManagerImpl.class)
public interface CrpTimelineEventsManager {


  /**
   * This method removes a specific CrpTimelineEvents value from the database.
   * 
   * @param CrpTimelineEventsId is the CrpTimelineEvents identifier.
   * @return true if the CrpTimelineEvents was successfully deleted, false otherwise.
   */
  public void deleteCrpTimelineEvents(long CrpTimelineEventsId);


  /**
   * This method validate if the CrpTimelineEvents identify with the given id exists in the system.
   * 
   * @param CrpTimelineEventsID is a CrpTimelineEvents identifier.
   * @return true if the CrpTimelineEvents exists, false otherwise.
   */
  public boolean existCrpTimelineEvents(long CrpTimelineEventsID);


  /**
   * This method gets a list of CrpTimelineEvents that are active
   * 
   * @return a list from CrpTimelineEvents null if no exist records
   */
  public List<CrpTimelineEvents> findAll();

  /**
   * This method gets a CrpTimelineEvents object by a given CrpTimelineEvents identifier.
   * 
   * @param CrpTimelineEventsID is the CrpTimelineEvents identifier.
   * @return a CrpTimelineEvents object.
   */
  public CrpTimelineEvents getCrpTimelineEventsById(long CrpTimelineEventsID);

  /**
   * This method saves the information of the given CrpTimelineEvents
   * 
   * @param CrpTimelineEvents - is the CrpTimelineEvents object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the CrpTimelineEvents was
   *         updated
   *         or -1 is some error occurred.
   */
  public CrpTimelineEvents saveCrpTimelineEvents(CrpTimelineEvents CrpTimelineEvents);
}
