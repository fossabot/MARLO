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


import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.dao.DeliverableDAO;
import org.cgiar.ccafs.marlo.data.dao.DeliverableInfoDAO;
import org.cgiar.ccafs.marlo.data.dao.PhaseDAO;
import org.cgiar.ccafs.marlo.data.manager.DeliverableManager;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableInfo;
import org.cgiar.ccafs.marlo.data.model.Phase;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

/**
 * @author Christian Garcia
 */
public class DeliverableManagerImpl implements DeliverableManager {

  private PhaseDAO phaseDAO;

  private DeliverableDAO deliverableDAO;
  private DeliverableInfoDAO deliverableInfoDAO;

  // Managers


  @Inject
  public DeliverableManagerImpl(DeliverableDAO deliverableDAO, PhaseDAO phaseDAO,
    DeliverableInfoDAO deliverableInfoDAO) {
    this.deliverableDAO = deliverableDAO;
    this.phaseDAO = phaseDAO;
    this.deliverableInfoDAO = deliverableInfoDAO;

  }

  @Override
  public boolean deleteDeliverable(long deliverableId) {

    return deliverableDAO.deleteDeliverable(deliverableId);
  }

  @Override
  public boolean existDeliverable(long deliverableID) {

    return deliverableDAO.existDeliverable(deliverableID);
  }

  @Override
  public List<Deliverable> findAll() {

    return deliverableDAO.findAll();

  }

  @Override
  public Deliverable getDeliverableById(long deliverableID) {

    return deliverableDAO.find(deliverableID);
  }

  @Override
  public long saveDeliverable(Deliverable deliverable) {

    return deliverableDAO.save(deliverable);
  }

  @Override
  public long saveDeliverable(Deliverable deliverable, String section, List<String> relationsName, Phase phase) {
    long resultDeliverable = deliverableDAO.save(deliverable, section, relationsName, phase);

    Phase currentPhase = phaseDAO.find(deliverable.getDeliverableInfo().getPhase().getId());
    if (currentPhase.getDescription().equals(APConstants.PLANNING)) {
      if (deliverable.getDeliverableInfo().getPhase().getNext() != null) {
        this.saveDeliverablePhase(deliverable.getDeliverableInfo().getPhase().getNext(), deliverable.getId(),
          deliverable);
      }
    }
    return resultDeliverable;
  }

  public void saveDeliverablePhase(Phase next, long deliverableID, Deliverable deliverable) {
    Phase phase = phaseDAO.find(next.getId());
    if (phase.getEditable() != null && phase.getEditable()) {
      List<DeliverableInfo> deliverablesInfo = phase.getDeliverableInfos().stream()
        .filter(c -> c.isActive() && c.getDeliverable().getId().longValue() == deliverableID)
        .collect(Collectors.toList());
      for (DeliverableInfo deliverableInfo : deliverablesInfo) {
        deliverableInfo.updateDeliverableInfo(deliverable.getDeliverableInfo());
        deliverableInfoDAO.save(deliverableInfo);
      }


    }
    if (phase.getNext() != null) {
      this.saveDeliverablePhase(phase.getNext(), deliverableID, deliverable);
    }
  }
}
