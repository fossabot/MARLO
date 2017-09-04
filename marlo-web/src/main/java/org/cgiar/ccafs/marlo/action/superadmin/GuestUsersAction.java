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

package org.cgiar.ccafs.marlo.action.superadmin;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.manager.CrpUserManager;
import org.cgiar.ccafs.marlo.data.manager.RoleManager;
import org.cgiar.ccafs.marlo.data.manager.UserManager;
import org.cgiar.ccafs.marlo.data.manager.UserRoleManager;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.data.model.CrpUser;
import org.cgiar.ccafs.marlo.data.model.Role;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.data.model.UserRole;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.SendMailS;

import org.cgiar.ciat.auth.LDAPService;
import org.cgiar.ciat.auth.LDAPUser;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class GuestUsersAction extends BaseAction {


  private static final long serialVersionUID = 6860177996446505143L;
  private static Logger LOG = LoggerFactory.getLogger(GuestUsersAction.class);


  /**
   * Helper method to read a stream into memory.
   * 
   * @param stream
   * @return
   * @throws IOException
   */
  public static byte[] readFully(InputStream stream) throws IOException {
    byte[] buffer = new byte[8192];
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    int bytesRead;
    while ((bytesRead = stream.read(buffer)) != -1) {
      baos.write(buffer, 0, bytesRead);
    }
    return baos.toByteArray();
  }

  private UserManager userManager;


  private CrpManager crpManager;

  private UserRoleManager userRoleManager;

  private CrpUserManager crpUserManager;
  private RoleManager roleManager;

  private SendMailS sendMailS;

  private User user;

  private boolean cigarUser;


  private Boolean isNewUser;

  private List<Crp> crps;

  private long userID;


  @Inject
  public GuestUsersAction(APConfig config, UserManager userManager, CrpManager crpManager,
    CrpUserManager crpUserManager, UserRoleManager userRoleManager, SendMailS sendMailS, RoleManager roleManager) {
    super(config);
    this.userManager = userManager;
    this.crpManager = crpManager;
    this.crpUserManager = crpUserManager;
    this.userRoleManager = userRoleManager;
    this.sendMailS = sendMailS;
    this.roleManager = roleManager;
  }

  /**
   * update the method save. Optimize code and add new features
   * new features:
   * if the user is a cgiar user, search into ldap server by the username
   * the users can eliminate guest permission to crps in the user interface
   * 
   * @author JULIANRODRIGUEZ <julian.rodriguez@cgiar.org>
   * @since 30/08/2017
   */

  public String create() throws Exception {

    User newUser;
    long newUserID;
    LDAPUser userLDAP = null;
    List<CrpUser> crpsForDeleting;
    List<CrpUser> crpsInDb;


    // set the fields, depending if it's a new user or an old one
    if (isNewUser) {
      newUser = new User();
      newUser.setActiveSince(new Date());
      newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
      newUser.setUsername(user.getUsername());
      newUser.setActive(user.isActive());
      newUser.setCgiarUser(user.isCgiarUser());
      newUser.setAutoSave(user.isAutoSave());
      newUser.setEmail(user.getEmail());
      newUser.setModificationJustification(" ");
      newUser.setModifiedBy(this.getCurrentUser());

    } else {
      newUser = userManager.getUser(user.getId());

      newUser.setActive(user.isActive());
      newUser.setAutoSave(user.isAutoSave());
      newUser.setModificationJustification(" ");
      newUser.setModifiedBy(this.getCurrentUser());

    }

    // check if it's a cgiar user
    if (!user.isCgiarUser()) {
      if (!user.getPassword().isEmpty()) {
        newUser.setPassword(user.getPassword());
      }
    } else {
      userLDAP = new LDAPService(true).searchUserByEmail(user.getEmail());

      if (userLDAP != null) {
        newUser.setFirstName(userLDAP.getFirstName());
        newUser.setLastName(userLDAP.getLastName());
        newUser.setUsername(userLDAP.getLogin());
      } else {
        LOG.info("Can't find in the LDAP Server user: " + user.getEmail());
      }
    }

    // saving or update the user
    newUserID = userManager.saveUser(newUser, this.getCurrentUser());

    // if the user saved without errors
    if (newUserID != -1) {
      userID = newUserID;
      newUser = userManager.getUser(newUserID);

      // validate the crps that user has in bd and compare them with the crps in the interface
      // if any of the crps don't exits in the crps from interface. Delete that crps from database
      crpsInDb = crpUserManager.getCrpUserByUserId(newUserID);

      if (crpsInDb != null) {
        crpsForDeleting = new ArrayList<>();

        // compare the crps assigned in db
        for (CrpUser crpUserBD : crpsInDb) {
          int cont = 0;
          if (user.getCrpUser() != null) {
            for (CrpUser crpUser : user.getCrpUser()) {
              if (crpUserBD.getId().equals(crpUser.getId())) {
                cont++;
              }
            }

            if (cont == 0) {
              crpsForDeleting.add(crpUserBD);
            }
          }
        }

        for (CrpUser crpUserDelete : crpsForDeleting) {
          Crp crp = crpManager.getCrpById(crpUserDelete.getCrp().getId());
          UserRole userRole = new UserRole();

          List<Role> roles = new ArrayList<>(crp.getRoles());

          Role guestRole = roles.stream().filter(r -> r.getAcronym().equals("G")).collect(Collectors.toList()).get(0);

          userRole.setRole(guestRole);
          userRole.setUser(newUser);

          List<UserRole> rolesInDb = userRoleManager.getUserRolesByUserId(crpUserDelete.getUser().getId());

          if (rolesInDb != null) {
            for (UserRole rol : rolesInDb) {
              if (rol.getRole().getId().equals(guestRole.getId())) {
                userRoleManager.deleteUserRole(rol.getId());
              }
            }
          }

          crpUserManager.deleteCrpUser(crpUserDelete.getId());
        }


      }


      // check if the user has crp's associated
      if (user.getCrpUser() != null) {

        for (CrpUser crpUser : user.getCrpUser()) {

          if (crpUser.getId() == -1) {

            // search the crp with it's id
            Crp crp = crpManager.getCrpById(crpUser.getCrp().getId());

            CrpUser newCrpUser = new CrpUser();
            newCrpUser.setCrp(crp);
            newCrpUser.setUser(newUser);
            newCrpUser.setActiveSince(new Date());
            newCrpUser.setCreatedBy(this.getCurrentUser());
            newCrpUser.setModifiedBy(this.getCurrentUser());
            newCrpUser.setModificationJustification(" ");
            newCrpUser.setActive(true);

            // assign the user in the crp
            long newCrpUserID = crpUserManager.saveCrpUser(newCrpUser);

            // if the crp-user association saved without errors
            if (newCrpUserID != -1) {

              newCrpUser = crpUserManager.getCrpUserById(newCrpUserID);

              UserRole userRole = new UserRole();

              List<Role> roles = new ArrayList<>(crp.getRoles());

              Role guestRole =
                roles.stream().filter(r -> r.getAcronym().equals("G")).collect(Collectors.toList()).get(0);

              userRole.setRole(guestRole);
              userRole.setUser(newUser);

              long userRoleID = userRoleManager.saveUserRole(userRole);


              // if the user is new and has a rol, send a email with welcome message and instructions.
              if (isNewUser && userRoleID != -1) {
                try {
                  this.sendMailNewUser(newUser, crp);
                } catch (NoSuchAlgorithmException e) {
                  LOG.error(
                    "GuestUsersAction.save(). There was an error sending the mail to the user: " + e.getMessage());
                  throw e;
                }
              }

            }


          }

        }
      }


    }

    this.addActionMessage("message:" + this.getText("saving.saved"));


    return SUCCESS;


  }

  public List<Crp> getCrps() {
    return crps;
  }

  public Boolean getIsNewUser() {
    return isNewUser;
  }


  public User getUser() {
    return user;
  }

  public long getUserID() {
    return userID;
  }

  public boolean isCigarUser() {
    return cigarUser;
  }


  @Override
  public void prepare() throws Exception {

    try {
      userID = Long.parseLong(StringUtils.trim(this.getRequest().getParameter(APConstants.USER_ID)));
      user = userManager.getUser(userID);
      System.out.println("");
    } catch (Exception e) {
      LOG.error("There was an error in GuestUsersAction.prepare method: " + e.getMessage());
    }

    crps = new ArrayList<>(
      crpManager.findAll().stream().filter(c -> c.isActive() && c.isMarlo()).collect(Collectors.toList()));

    if (this.isHttpPost()) {
      isNewUser = null;
    }

  }

  /**
   * Send a mail to a new user.
   * update: change the e.printStackTrace() method by LOG.error() method
   * 
   * @author JULIANRODRIGUEZ <julian.rodriguez@cgiar.org>
   * @since 30/08/2017
   * @param user - the user who recives the mail
   * @param crp - the crp who the user has permissions
   * @throws NoSuchAlgorithmException
   */
  public void sendMailNewUser(User user, Crp crp) throws NoSuchAlgorithmException {
    String toEmail = user.getEmail();
    String ccEmail = null;
    String bbcEmails = this.config.getEmailNotification();
    String subject = this.getText("email.newUser.subject", new String[] {user.getFirstName()});
    // Setting the password
    String password = this.getText("email.outlookPassword");
    if (!user.isCgiarUser()) {
      password = this.user.getPassword();
      // Applying the password to the user.
      user.setPassword(password);
    }

    // get CRPAdmin contacts
    String crpAdmins = "";
    long adminRol = Long.parseLong((String) this.getSession().get(APConstants.CRP_ADMIN_ROLE));
    Role roleAdmin = roleManager.getRoleById(adminRol);
    List<UserRole> userRoles = roleAdmin.getUserRoles().stream()
      .filter(ur -> ur.getUser() != null && ur.getUser().isActive()).collect(Collectors.toList());
    for (UserRole userRole : userRoles) {
      if (crpAdmins.isEmpty()) {
        crpAdmins += userRole.getUser().getFirstName() + " (" + userRole.getUser().getEmail() + ")";
      } else {
        crpAdmins += ", " + userRole.getUser().getFirstName() + " (" + userRole.getUser().getEmail() + ")";
      }
    }

    // Building the Email message:
    StringBuilder message = new StringBuilder();
    message.append(this.getText("email.dear", new String[] {user.getFirstName()}));

    message.append(this.getText("email.newUser.part2",
      new String[] {this.getText("global.sClusterOfActivities").toLowerCase(), config.getBaseUrl(), crp.getName(),
        user.getEmail(), password, this.getText("email.support", new String[] {crpAdmins})}));
    message.append(this.getText("email.bye"));

    // Send pdf
    String contentType = "application/pdf";
    String fileName = "Introduction_To_MARLO_v2.1.pdf";
    byte[] buffer = null;
    InputStream inputStream = null;

    try {
      inputStream = this.getClass().getResourceAsStream("/manual/Introduction_To_MARLO_v2.1.pdf");
      buffer = readFully(inputStream);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      LOG.error("There was an error including the atachment: " + e.getMessage());

    } catch (IOException e) {
      // TODO Auto-generated catch block
      LOG.error("There was an error including the atachment: " + e.getMessage());
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          LOG.error("There was an error including the atachment: " + e.getMessage());
        }
      }
    }

    if (buffer != null && fileName != null && contentType != null) {
      sendMailS.send(toEmail, ccEmail, bbcEmails, subject, message.toString(), buffer, contentType, fileName, true);
    } else {
      sendMailS.send(toEmail, ccEmail, bbcEmails, subject, message.toString(), null, null, null, true);
    }

  }

  public void setCrps(List<Crp> crps) {
    this.crps = crps;
  }


  public void setIsNewUser(Boolean isNewUser) {
    this.isNewUser = isNewUser;
  }

  public void setNewUser(boolean isNewUser) {
    this.isNewUser = isNewUser;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUserID(long userID) {
    this.userID = userID;
  }

}
