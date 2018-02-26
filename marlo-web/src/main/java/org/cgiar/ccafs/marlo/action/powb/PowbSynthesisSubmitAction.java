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

package org.cgiar.ccafs.marlo.action.powb;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.SubmissionManager;
import org.cgiar.ccafs.marlo.data.manager.UserManager;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.Submission;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class PowbSynthesisSubmitAction extends BaseAction {


  private static final long serialVersionUID = 3203658581553903289L;


  private static Logger LOG = LoggerFactory.getLogger(PowbSynthesisSubmitAction.class);


  // Manager
  private SubmissionManager submissionManager;


  private UserManager userManager;


  private GlobalUnitManager crpManager;
  private GlobalUnit loggedCrp;
  private boolean complete;

  @Inject
  public PowbSynthesisSubmitAction(APConfig config, SubmissionManager submissionManager, UserManager userManager,
    GlobalUnitManager crpManager) {
    super(config);
    this.submissionManager = submissionManager;
    this.userManager = userManager;
    this.crpManager = crpManager;
  }

  @Override
  public String execute() throws Exception {
    complete = false;
    if (this.hasPermission("canSubmmit")) {
      if (this.isCompletePowbSynthesis()) {
        List<Submission> submissions = loggedCrp.getSubmissions().stream()
          .filter(c -> c.getCycle().equals(this.getActualPhase().getDescription())
            && c.getYear().intValue() == this.getActualPhase().getYear() && (c.isUnSubmit() == null || !c.isUnSubmit()))
          .collect(Collectors.toList());

        if (submissions.isEmpty()) {
          this.submitPowbSynthesis();
          complete = true;
        } else {
          Submission submission = submissionManager.getSubmissionById(submissions.get(0).getId());
          submission.setUser(userManager.getUser(submission.getUser().getId()));
          this.setSubmission(submission);
          complete = true;
        }
      }

      return INPUT;
    } else {

      return NOT_AUTHORIZED;
    }
  }

  public GlobalUnit getLoggedCrp() {
    return loggedCrp;
  }


  public boolean isComplete() {
    return complete;
  }

  @Override
  public void prepare() throws Exception {
    loggedCrp = (GlobalUnit) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getGlobalUnitById(loggedCrp.getId());

    String params[] = {crpManager.getGlobalUnitById(this.getCrpID()).getAcronym() + ""};
    this.setBasePermission(this.getText(Permission.POWB_SYNTHESIS_MANAGE_PERMISSION, params));

  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  public void setLoggedCrp(GlobalUnit loggedCrp) {
    this.loggedCrp = loggedCrp;
  }


  private void submitPowbSynthesis() {
    Submission submission = new Submission();

    submission.setCycle(this.getActualPhase().getDescription());
    submission.setUser(this.getCurrentUser());


    submission.setYear((short) this.getActualPhase().getYear());
    submission.setDateTime(new Date());
    submission.setGlobalUnit(loggedCrp);

    submission = submissionManager.saveSubmission(submission);
    this.setSubmission(submission);
    if (submission != null) {
      // TODO
      // this.sendNotficationEmail();

    }
  }


}
