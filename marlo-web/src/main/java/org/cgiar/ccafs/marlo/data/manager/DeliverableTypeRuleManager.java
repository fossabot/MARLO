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

import org.cgiar.ccafs.marlo.data.model.DeliverableTypeRule;

import java.util.List;


/**
 * @author Christian Garcia
 */

public interface DeliverableTypeRuleManager {


  /**
   * This method removes a specific deliverableTypeRule value from the database.
   * 
   * @param deliverableTypeRuleId is the deliverableTypeRule identifier.
   * @return true if the deliverableTypeRule was successfully deleted, false otherwise.
   */
  public void deleteDeliverableTypeRule(long deliverableTypeRuleId);


  /**
   * This method validate if the deliverableTypeRule identify with the given id exists in the system.
   * 
   * @param deliverableTypeRuleID is a deliverableTypeRule identifier.
   * @return true if the deliverableTypeRule exists, false otherwise.
   */
  public boolean existDeliverableTypeRule(long deliverableTypeRuleID);


  /**
   * This method gets a list of deliverableTypeRule that are active
   * 
   * @return a list from DeliverableTypeRule null if no exist records
   */
  public List<DeliverableTypeRule> findAll();

  /**
   * This method gets a list of deliverableTypeRule by a rule identifier
   * 
   * @return a list from DeliverableTypeRule null if no exist records
   */
  public List<DeliverableTypeRule> findDeliverableTypeRuleByRule(String rule);

  /**
   * This method gets a deliverableTypeRule object by a given deliverableTypeRule identifier.
   * 
   * @param deliverableTypeRuleID is the deliverableTypeRule identifier.
   * @return a DeliverableTypeRule object.
   */
  public DeliverableTypeRule getDeliverableTypeRuleById(long deliverableTypeRuleID);


  /**
   * This method saves the information of the given deliverableTypeRule
   * 
   * @param deliverableTypeRule - is the deliverableTypeRule object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the deliverableTypeRule was
   *         updated
   *         or -1 is some error occurred.
   */
  public DeliverableTypeRule saveDeliverableTypeRule(DeliverableTypeRule deliverableTypeRule);


}