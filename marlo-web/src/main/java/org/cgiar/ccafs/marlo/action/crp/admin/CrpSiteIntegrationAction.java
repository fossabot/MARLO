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

package org.cgiar.ccafs.marlo.action.crp.admin;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.manager.CrpSitesLeaderManager;
import org.cgiar.ccafs.marlo.data.manager.CrpsSiteIntegrationManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementManager;
import org.cgiar.ccafs.marlo.data.manager.RoleManager;
import org.cgiar.ccafs.marlo.data.manager.UserRoleManager;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.data.model.CrpSitesLeader;
import org.cgiar.ccafs.marlo.data.model.CrpsSiteIntegration;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.Role;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class CrpSiteIntegrationAction extends BaseAction {


  private static final long serialVersionUID = 1323996683605051647L;


  private CrpManager crpManager;


  private LocElementManager locElementManager;


  private CrpsSiteIntegrationManager crpsSiteIntegrationManager;


  private CrpSitesLeaderManager crpSitesLeaderManager;

  private RoleManager roleManager;

  private UserRoleManager userRoleManager;


  private Crp loggedCrp;
  private List<LocElement> countriesList;
  private Long slRoleid;
  private Role slRole;

  @Inject
  public CrpSiteIntegrationAction(APConfig config, CrpManager crpManager, LocElementManager locElementManager,
    CrpsSiteIntegrationManager crpsSiteIntegrationManager, CrpSitesLeaderManager crpSitesLeaderManager,
    RoleManager roleManager, UserRoleManager userRoleManager) {
    super(config);
    this.crpManager = crpManager;
    this.locElementManager = locElementManager;
    this.crpsSiteIntegrationManager = crpsSiteIntegrationManager;
    this.crpSitesLeaderManager = crpSitesLeaderManager;
    this.roleManager = roleManager;
    this.userRoleManager = userRoleManager;
  }

  public List<LocElement> getCountriesList() {
    return countriesList;
  }

  public Crp getLoggedCrp() {
    return loggedCrp;
  }

  public Role getSlRole() {
    return slRole;
  }

  public Long getSlRoleid() {
    return slRoleid;
  }

  @Override
  public void prepare() throws Exception {
    loggedCrp = (Crp) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getCrpById(loggedCrp.getId());
    slRoleid = Long.parseLong((String) this.getSession().get(APConstants.CRP_SL_ROLE));
    slRole = roleManager.getRoleById(slRoleid);

    if (loggedCrp.getCrpsSitesIntegrations() != null) {
      loggedCrp.setSiteIntegrations(new ArrayList<CrpsSiteIntegration>(loggedCrp.getCrpsSitesIntegrations()));

      for (int i = 0; i < loggedCrp.getSiteIntegrations().size(); i++) {
        loggedCrp.getSiteIntegrations().get(i)
          .setSiteLeaders(new ArrayList<CrpSitesLeader>(loggedCrp.getSiteIntegrations().get(i).getCrpSitesLeaders()));
      }
    }

    countriesList = locElementManager.findAll().stream().filter(le -> le.getLocElementType().getId() == 2)
      .collect(Collectors.toList());
    Collections.sort(countriesList, (lc1, lc2) -> lc1.getName().compareTo(lc2.getName()));


    String params[] = {loggedCrp.getAcronym()};
    this.setBasePermission(this.getText(Permission.CRP_ADMIN_BASE_PERMISSION, params));

    if (this.isHttpPost()) {
      loggedCrp.getSiteIntegrations().clear();
    }

  }

  @Override
  public String save() {

    if (this.hasPermission("*")) {

      List<CrpsSiteIntegration> siteIntegrationPrew;

      if (crpsSiteIntegrationManager.findAll() != null) {
        siteIntegrationPrew = crpsSiteIntegrationManager.findAll().stream().filter(si -> si.getCrp().equals(loggedCrp))
          .collect(Collectors.toList());

        for (CrpsSiteIntegration crpsSiteIntegration : siteIntegrationPrew) {
          if (!loggedCrp.getSiteIntegrations().contains(crpsSiteIntegration)) {

            for (CrpSitesLeader crpSitesLeader : crpsSiteIntegration.getCrpSitesLeaders()) {
              crpSitesLeaderManager.deleteCrpSitesLeader(crpSitesLeader.getId());
            }

            crpsSiteIntegrationManager.deleteCrpsSiteIntegration(crpsSiteIntegration.getId());
          }
        }
      }


      List<CrpsSiteIntegration> siteIntegrationsPrew = crpsSiteIntegrationManager.findAll().stream()
        .filter(si -> si.getCrp().equals(loggedCrp)).collect(Collectors.toList());

      if (loggedCrp.getCrpsSitesIntegrations() != null) {
        loggedCrp.setSiteIntegrations(new ArrayList<CrpsSiteIntegration>(loggedCrp.getCrpsSitesIntegrations()));

        for (int i = 0; i < loggedCrp.getSiteIntegrations().size(); i++) {
          loggedCrp.getSiteIntegrations().get(i).setSiteLeaders(new ArrayList<CrpSitesLeader>());
        }
      }
    }


    return null;
  }


  public void setCountriesList(List<LocElement> countriesList) {
    this.countriesList = countriesList;
  }

  public void setLoggedCrp(Crp loggedCrp) {
    this.loggedCrp = loggedCrp;
  }

  public void setSlRole(Role slRole) {
    this.slRole = slRole;
  }


  public void setSlRoleid(Long slRoleid) {
    this.slRoleid = slRoleid;
  }

}
