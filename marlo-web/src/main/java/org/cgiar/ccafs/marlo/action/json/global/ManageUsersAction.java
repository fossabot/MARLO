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

package org.cgiar.ccafs.marlo.action.json.global;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 * @author Hernán David Carvajal
 * @author Héctor F. Tobón R. - CIAT/CCAFS
 * @author Julián Rodríguez C. - CIAT/CCAFS
 */
public class ManageUsersAction extends BaseAction {


  private static final long serialVersionUID = 281018603716118132L;


  private static Logger LOG = LoggerFactory.getLogger(ManageUsersAction.class);

  private static String PARAM_FIRST_NAME = "firstName";
  private static String PARAM_LAST_NAME = "lastName";

  private static String PARAM_EMAIL = "email";
  private static String PARAM_IS_ACTIVE = "isActive";

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
  private List<Crp> crps;

  private List<Map<String, Object>> users;
  private User newUser;
  private User user;
  private String message;

  private String warningMessage;


  private String successMessage;


  private boolean cigarUser;

  private Boolean isNewUser;
  private long userID;
  private SendMailS sendMail;

  private String actionName;
  private String queryParameter;

  @Inject
  public ManageUsersAction(APConfig config, UserManager userManager, CrpManager crpManager,
    CrpUserManager crpUserManager, UserRoleManager userRoleManager, RoleManager roleManager, SendMailS sendMail) {
    super(config);
    this.userManager = userManager;
    this.sendMail = sendMail;
    this.crpManager = crpManager;
    this.crpUserManager = crpUserManager;
    this.userRoleManager = userRoleManager;
    this.roleManager = roleManager;
  }

  /**
   * Add a new user into the database;
   * 
   * @return true if the user was successfully added, false otherwise.
   */
  private boolean addUser() {
    newUser.setModificationJustification("User created in MARLO " + actionName.replace("/", "-"));
    newUser.setActiveSince(new Date());
    newUser.setModifiedBy(this.getCurrentUser());
    newUser.setActive(false);
    newUser.setAutoSave(true);
    newUser.setId(null);
    newUser.setCreatedBy(this.getCurrentUser());

    Long id = userManager.saveUser(newUser, this.getCurrentUser());
    // If successfully added.
    if (id > 0) {
      newUser = userManager.getUser(id);

      this.users = new ArrayList<>();
      Map<String, Object> userMap = new HashMap<>();
      userMap.put("id", newUser.getId());
      userMap.put("composedName", newUser.getComposedName());
      userMap.put("name", newUser.getComposedCompleteName());
      userMap.put("email", newUser.getEmail());
      this.users.add(userMap);

      return true;
    } else {
      // If some error occurred.
      return false;
    }
  }

  /**
   * check if the user has CRPs assigned in database.
   * compare those crps with the crps assigned in the interface
   * if the method detects that if a crp is delete it from interaface
   * delete that crp in database and its permissions
   * 
   * @author Julián Rodríguez C. - CIAT/CCAFS
   * @param userId
   * @param user
   */
  public void checkCRPsInDB(long userId, User user) {
    List<CrpUser> crpsForDeleting;
    List<CrpUser> crpsInDb;

    // validate the crps that user has in bd and compare them with the crps in the interface
    // if any of the crps don't exits in the crps from interface. Delete that crps from database
    crpsInDb = crpUserManager.getCrpUserByUserId(userId);

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


        if (crpUserDelete.isAdmin()) {
          Role adminRole =
            roles.stream().filter(r -> r.getAcronym().equals("CRP-Admin")).collect(Collectors.toList()).get(0);

          userRole.setRole(adminRole);
          userRole.setUser(newUser);

          List<UserRole> rolesInDb = userRoleManager.getUserRolesByUserId(crpUserDelete.getUser().getId());

          if (rolesInDb != null) {
            for (UserRole rol : rolesInDb) {
              if (rol.getRole().getId().equals(adminRole.getId())) {
                userRoleManager.deleteUserRole(rol.getId());
              }
            }
          }

          crpUserManager.deleteCrpUser(crpUserDelete.getId());
        } else {
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


    }

  }


  /**
   * The method detect if there is a new crp that has a new role
   * and create the user into the crp and assign permissions.
   * 
   * @param user
   * @param newUser
   */
  public void checkNewCRPs(User user, User newUser) {
    // check if the user has crp's associated
    if (user.getCrpUser() != null) {

      int cont = 0;
      for (CrpUser crpUser : user.getCrpUser()) {

        if (crpUser.getId() == -1) {
          cont++;

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


            if (crpUser.isAdmin()) {

              Role guestRole =
                roles.stream().filter(r -> r.getAcronym().equals("CRP-Admin")).collect(Collectors.toList()).get(0);

              userRole.setRole(guestRole);
              userRole.setUser(newUser);

              long userRoleID = userRoleManager.saveUserRole(userRole);

              if (cont == 1) {
                this.sendMailToUser(newUser, crp, APConstants.CRP_ADMIN_ROLE, isNewUser);
              } else {
                this.sendMailToUser(newUser, crp, APConstants.CRP_ADMIN_ROLE, false);
              }


            } else {

              Role guestRole =
                roles.stream().filter(r -> r.getAcronym().equals("G")).collect(Collectors.toList()).get(0);

              userRole.setRole(guestRole);
              userRole.setUser(newUser);

              long userRoleID = userRoleManager.saveUserRole(userRole);


              if (cont == 1) {
                this.sendMailToUser(newUser, crp, APConstants.CRP_GUEST_ROLE, isNewUser);
              } else {
                this.sendMailToUser(newUser, crp, APConstants.CRP_GUEST_ROLE, false);
              }


            }


          }


        }

      }
    }
  }

  /**
   * Create a new user in the system.
   * 
   * @return SUCCESS if user could be successfully created, INPUT if some information is needed and ERROR if some error
   *         appeared.
   * @throws Exception
   */
  public String create() throws Exception {
    if (newUser.getEmail() != null) {
      boolean emailExists = false;
      // We need to validate that the email does not exist yet into our database.
      emailExists = userManager.getUserByEmail(newUser.getEmail()) == null ? false : true;

      // If email already exists.
      if (emailExists) {
        // If email already exists into our database.
        message = this.getText("manageUsers.email.existing");
        newUser = null;
        return SUCCESS; // Stop here!
      }

      // Validate if is a CGIAR email.
      if (newUser.getEmail().toLowerCase().endsWith(APConstants.OUTLOOK_EMAIL)) {
        newUser.setCgiarUser(true); // marking it as CGIAR user.

        // Validate and populate the information that is coming from the CGIAR Outlook Active Directory.
        newUser = this.validateOutlookUser(newUser.getEmail());
        // If user was not found in the Active Directory.
        if (newUser == null) {
          message = this.getText("manageUsers.email.doesNotExist");
          return SUCCESS; // Stop here!
        } else {
          // If user was found, let's add it into our database.
          this.addUser();
        }
      } else {
        // If the email does not belong to the CGIAR.
        if (newUser.getFirstName() != null && newUser.getLastName() != null) {
          newUser.setCgiarUser(false);
          // Generating a random password.
          // String newPassword = RandomStringUtils.random(6, "0123456789abcdefghijkmnpqrstuvwxyz");
          String newPassword = RandomStringUtils.randomNumeric(6);
          newUser.setPassword(newPassword);
          if (!this.addUser()) {
            // If user could not be added.
            newUser = null;
            message = this.getText("manageUsers.email.notAdded");
          }
          return SUCCESS;
        } else {
          message = this.getText("manageUsers.email.validation");
          return SUCCESS;
        }
      }
    }
    return SUCCESS;
  }

  @Override
  public String execute() throws Exception {
    // Nothing to do here yet.
    return SUCCESS;
  }


  public CrpUserManager getCrpUserManager() {
    return crpUserManager;
  }


  public Boolean getIsNewUser() {
    return isNewUser;
  }

  /**
   * Get a message of the result of the query.
   * 
   * @return a confirmation message of the result
   */
  public String getMessage() {
    return this.message;
  }


  public String getSuccessMessage() {
    return successMessage;
  }

  public User getUser() {
    return user;
  }


  public List<Map<String, Object>> getUsers() {
    return users;
  }


  public String getWarningMessage() {
    return warningMessage;
  }


  /**
   * Manage a new user in MARLO.
   * You can create a new user with GUEST privileges in one or more CRPs
   * You can edit that privileges
   * If the user is new you send a personalized GUEST mail
   * 
   * @author Julián Rodríguez C. - CIAT/CCAFS
   * @since 01/09/2017
   */

  public String manageUserWithPrivileges() throws Exception {
    User newUser;
    long newUserID;
    boolean existsUser = false;


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
      newUser.setCreatedBy(this.getCurrentUser());
      existsUser = userManager.getUserByUsername(newUser.getUsername()) == null ? false : true;

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
        newUser.setKeepPassword(false);
      } else {
        newUser.setKeepPassword(true);
      }
      newUser.setUsername(user.getUsername());
      newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
    } else {
      User usrTmp = this.validateOutlookUser(user.getEmail());

      if (usrTmp != null) {
        newUser.setFirstName(usrTmp.getFirstName());
        newUser.setLastName(usrTmp.getLastName());
        newUser.setUsername(usrTmp.getUsername());
      } else {
        message = this.getText("guestusers.email.invalid");
        return SUCCESS;
      }
    }


    if (existsUser) {
      message = this.getText("guestusers.username.invalid");
    } else {
      // saving or update the user
      newUserID = userManager.saveUser(newUser, this.getCurrentUser());

      // if the user saved without errors
      if (newUserID != -1) {
        userID = newUserID;
        newUser = userManager.getUser(newUserID);

        // validate crps that the user has in Database
        this.checkCRPsInDB(newUserID, user);

        // check if the user has new CRPs
        this.checkNewCRPs(user, newUser);

      }

      successMessage = this.getText("saving.saved");
    }


    return SUCCESS;
  }

  private void notifyNewUserCreated(User user) {
    user = userManager.getUser(user.getId());

    String toEmail = user.getEmail();
    String ccEmail = null;
    String bbcEmails = this.config.getEmailNotification();
    String subject = this.getText("email.newUser.subject", new String[] {user.getFirstName()});
    // Setting the password
    String password = this.getText("email.outlookPassword");
    if (!user.isCgiarUser()) {
      // Generating a random password.
      password = RandomStringUtils.randomNumeric(6);
      // Applying the password to the user.
      user.setPassword(password);
    }

    // Building the Email message:
    StringBuilder message = new StringBuilder();
    message.append(this.getText("email.dear", new String[] {user.getFirstName()}));

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

    message.append(this.getText("email.newUser.part1", new String[] {this.getText("email.newUser.listRoles"),
      config.getBaseUrl(), user.getEmail(), password, this.getText("email.support", new String[] {crpAdmins})}));
    message.append(this.getText("email.bye"));

    // Saving the new user configuration.
    user.setActive(true);
    userManager.saveUser(user, this.getCurrentUser());

    // Send UserManual.pdf
    String contentType = "application/pdf";
    String fileName = "Introduction_To_MARLO_v2.1.pdf";
    byte[] buffer = null;
    InputStream inputStream = null;

    try {
      inputStream = this.getClass().getResourceAsStream("/manual/Introduction_To_MARLO_v2.1.pdf");
      buffer = readFully(inputStream);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    if (buffer != null && fileName != null && contentType != null) {
      sendMail.send(toEmail, ccEmail, bbcEmails, subject, message.toString(), buffer, contentType, fileName, true);
    } else {
      sendMail.send(toEmail, ccEmail, bbcEmails, subject, message.toString(), null, null, null, true);
    }

  }


  @Override
  public void prepare() throws Exception {
    Map<String, Object> parameters = this.getParameters();
    // if searching a user, we need to get the queried String.
    if (ActionContext.getContext().getName().equals("searchUsers")) {

      queryParameter = StringUtils.trim(((String[]) parameters.get(APConstants.QUERY_PARAMETER))[0]);
    } else if (ActionContext.getContext().getName().equals("createUser")) {
      // if Adding a new user, we need to get the info to be added.
      newUser = new User();
      newUser.setId((long) -1);
      if (!StringUtils.trim(((String[]) parameters.get(PARAM_EMAIL))[0]).toLowerCase()
        .endsWith(APConstants.OUTLOOK_EMAIL)) {
        newUser.setFirstName(StringUtils.trim(((String[]) parameters.get(PARAM_FIRST_NAME))[0]));
        newUser.setLastName(StringUtils.trim(((String[]) parameters.get(PARAM_LAST_NAME))[0]));
      }
      newUser.setEmail(StringUtils.trim(((String[]) parameters.get(PARAM_EMAIL))[0]));
      newUser.setActive(StringUtils.trim(((String[]) parameters.get(PARAM_IS_ACTIVE))[0]).equals("1") ? true : false);

      actionName = StringUtils.trim(((String[]) parameters.get("actionName"))[0]);
    }

  }


  /**
   * Search a user in the database
   * 
   * @return SUCCESS if the search was successfully made.
   * @throws Exception if some error appear.
   */
  public String search() throws Exception {
    List<User> users = userManager.searchUser(queryParameter);
    this.users = new ArrayList<>();
    for (User user : users) {
      Map<String, Object> userMap = new HashMap<>();
      userMap.put("id", user.getId());
      userMap.put("composedName", user.getComposedName());
      userMap.put("username", user.getUsername());
      userMap.put("roles", user.getUserRoles().toString());
      userMap.put("name", user.getComposedCompleteName());
      userMap.put("email", user.getEmail());
      userMap.put("isActive", user.isActive());
      userMap.put("autoSaveActive", user.isAutoSave());
      userMap.put("lastLogin", user.getLastLogin());
      this.users.add(userMap);
    }

    LOG.info("The search of users by '{}' was made successfully.", queryParameter);
    return SUCCESS;
  }

  /**
   * Send a mail to a new CRP Admin user.
   * 
   * @author Julián Rodríguez C. - CIAT/CCAFS
   * @since 04/09/2017
   * @param user - the user who recives the mail
   * @param crp - the crp who the user has permissions
   * @throws NoSuchAlgorithmException
   */
  private void sendMailNewAdminUser(User user, Crp crp) throws NoSuchAlgorithmException {
    String toEmail = user.getEmail();
    String ccEmail = null;
    String bbcEmails = this.config.getEmailNotification();
    String subject = this.getText("email.adminuser.subject", new String[] {crp.getAcronym()});
    // Setting the password
    String password = this.getText("email.outlookPassword");
    if (!user.isCgiarUser()) {
      password = this.user.getPassword();
      // Applying the password to the user.
      user.setPassword(password);
    }

    // get CRPAdmin contacts
    String crpAdmins = "";
    List<Role> roles = new ArrayList<>(crp.getRoles());
    Role roleAdmin = roles.stream().filter(r -> r.getAcronym().equals("CRP-Admin")).collect(Collectors.toList()).get(0);
    List<UserRole> userRoles = roleAdmin.getUserRoles().stream()
      .filter(ur -> ur.getUser() != null && ur.getUser().isActive()).collect(Collectors.toList());
    for (UserRole userRole : userRoles) {
      if (crpAdmins.isEmpty()) {
        crpAdmins += userRole.getUser().getComposedName().replace("<", "&lt;").replace("&gt;", ">");
      } else {
        crpAdmins += ", " + userRole.getUser().getComposedName().replace("<", "&lt;").replace("&gt;", ">");
      }
    }

    String crpAdminsEmail = "";
    for (UserRole userRole : userRoles) {
      if (crpAdminsEmail.isEmpty()) {
        crpAdminsEmail += userRole.getUser().getEmail();
      } else {
        crpAdminsEmail += ", " + userRole.getUser().getEmail();
      }
    }

    // add the ccEmail
    ccEmail = this.getCurrentUser().getEmail() + ", " + crpAdminsEmail;


    // Building the Email message:
    StringBuilder message = new StringBuilder();
    message.append(this.getText("email.dear", new String[] {user.getFirstName()}));

    message.append(this.getText("email.adminuser.body",
      new String[] {crp.getAcronym(), this.getText("email.support", new String[] {crpAdmins}), config.getBaseUrl()}));
    message.append(this.getText("email.bye"));


    sendMail.send(toEmail, ccEmail, bbcEmails, subject, message.toString(), null, null, null, true);


  }


  /**
   * Send a mail to a new Guest user.
   * 
   * @author Julián Rodríguez C. - CIAT/CCAFS
   * @since 30/08/2017
   * @param user - the user who recives the mail
   * @param crp - the crp who the user has permissions
   * @throws NoSuchAlgorithmException
   */
  private void sendMailNewGuestUser(User user, Crp crp) throws NoSuchAlgorithmException {
    String toEmail = user.getEmail();
    String ccEmail = null;
    String bbcEmails = this.config.getEmailNotification();
    String subject = this.getText("email.guestuser.subject", new String[] {crp.getAcronym()});
    // Setting the password
    String password = this.getText("email.outlookPassword");
    if (!user.isCgiarUser()) {
      password = this.user.getPassword();
      // Applying the password to the user.
      user.setPassword(password);
    }

    // get CRPAdmin contacts
    String crpAdmins = "";

    List<Role> roles = new ArrayList<>(crp.getRoles());
    Role roleAdmin = roles.stream().filter(r -> r.getAcronym().equals("CRP-Admin")).collect(Collectors.toList()).get(0);

    List<UserRole> userRoles = roleAdmin.getUserRoles().stream()
      .filter(ur -> ur.getUser() != null && ur.getUser().isActive()).collect(Collectors.toList());
    for (UserRole userRole : userRoles) {
      if (crpAdmins.isEmpty()) {
        crpAdmins += userRole.getUser().getComposedName().replace("<", "&lt;").replace("&gt;", ">");
      } else {
        crpAdmins += ", " + userRole.getUser().getComposedName().replace("<", "&lt;").replace("&gt;", ">");
      }
    }

    String crpAdminsEmail = "";
    for (UserRole userRole : userRoles) {
      if (crpAdminsEmail.isEmpty()) {
        crpAdminsEmail += userRole.getUser().getEmail();
      } else {
        crpAdminsEmail += ", " + userRole.getUser().getEmail();
      }
    }

    // add the ccEmail
    ccEmail = this.getCurrentUser().getEmail() + ", " + crpAdminsEmail;


    // Building the Email message:
    StringBuilder message = new StringBuilder();
    message.append(this.getText("email.dear", new String[] {user.getFirstName()}));

    message.append(this.getText("email.guestuser.body",
      new String[] {crp.getAcronym(), this.getText("email.support", new String[] {crpAdmins})}));
    message.append(this.getText("email.bye"));

    sendMail.send(toEmail, ccEmail, bbcEmails, subject, message.toString(), null, null, null, true);

  }


  /**
   * Help to the system to choose what kind of email it must send
   * depending of the rol and if the user is new or not
   * 
   * @author Julián Rodríguez Calderón
   * @date 04/10/2017
   * @param user the user
   * @param crp this is the crp when the user has the new role
   * @param newRole this is the new rol
   * @param isNew it is a flag to know if the user is new or not
   */
  public void sendMailToUser(User user, Crp crp, String newRole, boolean isNew) {

    if (isNew) {
      this.notifyNewUserCreated(user);
    }

    try {
      switch (newRole) {
        case APConstants.CRP_ADMIN_ROLE:
          // comment this line because the mail format is no define yet.
          // this.sendMailNewAdminUser(user, crp);
          break;
        case APConstants.CRP_GUEST_ROLE:
          this.sendMailNewGuestUser(user, crp);
          break;
      }
    } catch (Exception e) {
      LOG.error("ManageUserAction.sendMailToUser(). Message: There was an error trying to send the mail to the user: "
        + user.getComposedName());
    }


  }


  public void setCrpUserManager(CrpUserManager crpUserManager) {
    this.crpUserManager = crpUserManager;
  }

  public void setIsNewUser(Boolean isNewUser) {
    this.isNewUser = isNewUser;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  public void setUser(User user) {
    this.user = user;
  }


  /**
   * Validate if a given user exists in the Outlook Active Directory .
   * 
   * @param email is the CGIAR email.
   * @return a populated user with all the information that is coming from the OAD, or null if the email does not exist.
   */
  private User validateOutlookUser(String email) {
    try {
      LDAPService service = new LDAPService();
      if (config.isProduction()) {
        service.setInternalConnection(false);
      } else {
        service.setInternalConnection(true);
      }
      LDAPUser user = service.searchUserByEmail(email);
      if (user != null) {
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getLogin().toLowerCase());
        return newUser;
      }
    } catch (Exception e) {
      LOG.error(this.getText("guestusers.email.invalid"));
    }

    return null;
  }

}
