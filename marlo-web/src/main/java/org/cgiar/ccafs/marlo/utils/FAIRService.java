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

package org.cgiar.ccafs.marlo.utils;

import org.cgiar.ccafs.marlo.data.manager.DeliverableManager;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableDissemination;
import org.cgiar.ccafs.marlo.data.model.LicensesTypeEnum;
import org.cgiar.ccafs.marlo.data.model.Phase;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FAIRService {

  private final DeliverableManager deliverableManager;

  @Inject
  public FAIRService(DeliverableManager deliverableManager) {
    this.deliverableManager = deliverableManager;
  }


  public Boolean isA(long deliverableID) {
    try {
      Deliverable deliverableBD = deliverableManager.getDeliverableById(deliverableID);
      this.loadDissemination(deliverableBD);

      if (deliverableBD.getDissemination().getIsOpenAccess() != null
        && deliverableBD.getDissemination().getIsOpenAccess().booleanValue()) {
        return true;
      }

      if (deliverableBD.getDissemination().getIsOpenAccess() == null) {
        return null;
      }
      return false;
    } catch (Exception e) {
      return null;
    }
  }

  public Boolean isF(long deliverableID) {


    try {
      Deliverable deliverableBD = deliverableManager.getDeliverableById(deliverableID);
      this.loadDissemination(deliverableBD);
      if (deliverableBD.getDissemination().getAlreadyDisseminated() != null
        && deliverableBD.getDissemination().getAlreadyDisseminated().booleanValue()) {
        return true;
      }
      if (deliverableBD.getDissemination().getAlreadyDisseminated() == null) {
        return null;
      }

      return false;
    } catch (Exception e) {
      return null;
    }

  }


  public Boolean isI(long deliverableID) {
    try {
      Deliverable deliverableBD = deliverableManager.getDeliverableById(deliverableID);
      this.loadDissemination(deliverableBD);
      if (deliverableBD.getDissemination().getAlreadyDisseminated() != null
        && deliverableBD.getDissemination().getAlreadyDisseminated().booleanValue()) {


        String channel = deliverableBD.getDissemination().getDisseminationChannel();
        String link = deliverableBD.getDissemination().getDisseminationUrl().replaceAll(" ", "%20");;
        if (channel == null || channel.equals("-1")) {
          return null;
        }
        if (link == null || link.equals("-1") || link.isEmpty()) {
          return null;
        }

        // If the deliverable is synced
        if ((deliverableBD.getDissemination().getSynced() != null)
          && (deliverableBD.getDissemination().getSynced().booleanValue())) {
          return true;
        }

        switch (channel) {
          case "cgspace":
            if (!this.validURL(link)) {
              return null;
            }
            if ((link.contains("cgspace")) || (link.contains("hdl")) || (link.contains("handle"))) {
              return true;
            }
            break;
          case "dataverse":
            if (!link.contains("dataverse.harvard.edu")) {
              if (!this.validURL(link)) {
                return null;
              }
              return null;
            }
            break;
          case "other":
            return null;

          default:
            return null;

        }


        return true;
      }
      if (deliverableBD.getDissemination().getAlreadyDisseminated() == null) {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
    return null;


  }

  public Boolean isR(long deliverableID, Phase actualPhase) {
    try {
      Deliverable deliverableBD = deliverableManager.getDeliverableById(deliverableID);
      if (deliverableBD.getDeliverableInfo(actualPhase).getAdoptedLicense() == null) {
        return null;
      }
      if (deliverableBD.getDeliverableInfo(actualPhase).getAdoptedLicense()) {
        if (deliverableBD.getDeliverableInfo(actualPhase).getLicense() == null) {
          return false;
        } else {
          if (!(deliverableBD.getDeliverableInfo(actualPhase).getLicense().equals(LicensesTypeEnum.OTHER.getValue())
            || deliverableBD.getDeliverableInfo(actualPhase).getLicense().equals(LicensesTypeEnum.CC_BY_ND.getValue())
            || deliverableBD.getDeliverableInfo(actualPhase).getLicense()
              .equals(LicensesTypeEnum.CC_BY_NC_ND.getValue()))) {
            return true;
          } else {
            if (deliverableBD.getDeliverableInfo(actualPhase).getAllowModifications() == null
              || !deliverableBD.getDeliverableInfo(actualPhase).getAllowModifications().booleanValue()) {
              return false;
            }
            if (deliverableBD.getDeliverableInfo(actualPhase).getOtherLicense() == null
              || deliverableBD.getDeliverableInfo(actualPhase).getOtherLicense().isEmpty()) {
              return false;
            }
            return true;
          }

        }
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  private void loadDissemination(Deliverable deliverableBD) {

    if (deliverableBD.getDeliverableDisseminations() != null) {
      deliverableBD.setDisseminations(new ArrayList<>(deliverableBD.getDeliverableDisseminations()));
      if (deliverableBD.getDeliverableDisseminations().size() > 0) {
        deliverableBD.setDissemination(deliverableBD.getDisseminations().get(0));
      } else {
        deliverableBD.setDissemination(new DeliverableDissemination());
      }

    }
  }

  private boolean validURL(String URL) {
    try {
      java.net.URL url = new java.net.URL(URL);
      url.toURI();
      return true;
    } catch (MalformedURLException e) {

      return false;
    } catch (URISyntaxException e) {

      return false;
    }

  }


}
