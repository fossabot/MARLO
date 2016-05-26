/*****************************************************************
 * This file is part of CCAFS Planning and Reporting Platform.
 * CCAFS P&R is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS P&R is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS P&R. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.manager.impl.CrpAssumptionManagerImpl;
import org.cgiar.ccafs.marlo.data.model.CrpAssumption;
import org.cgiar.ccafs.marlo.data.model.User;

import com.google.inject.ImplementedBy;

/**
 * @author Christian Garcia
 */
@ImplementedBy(CrpAssumptionManagerImpl.class)
public interface CrpAssumptionManager {


  /**
   * This method removes a specific crpAssumption value from the database.
   * 
   * @param crpAssumptionId is the crpAssumption identifier.
   * @param user - the user that is deleting the crpAssumption.
   * @param justification - the justification statement.
   * @return true if the crpAssumption was successfully deleted, false otherwise.
   */
  public boolean deleteCrpAssumption(long crpAssumptionId, User user, String justification);


  /**
   * This method validate if the crpAssumption identify with the given id exists in the system.
   * 
   * @param crpAssumptionID is a crpAssumption identifier.
   * @return true if the crpAssumption exists, false otherwise.
   */
  public boolean existCrpAssumption(long crpAssumptionID);

  /**
   * This method gets a crpAssumption object by a given crpAssumption identifier.
   * 
   * @param crpAssumptionID is the crpAssumption identifier.
   * @return a CrpAssumption object.
   */
  public CrpAssumption getCrpAssumptionById(long crpAssumptionID);


  /**
   * This method saves the information of the given crpAssumption
   * 
   * @param crpAssumption - is the crpAssumption object with the new information to be added/updated.
   * @param user - is the user that is making the change.
   * @param justification - is the justification statement.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the crpAssumption was
   *         updated
   *         or -1 is some error occurred.
   */
  public long saveCrpAssumption(CrpAssumption crpAssumption, User user, String justification);


}
