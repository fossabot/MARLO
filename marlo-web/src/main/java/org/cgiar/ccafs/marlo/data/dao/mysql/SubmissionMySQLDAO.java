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


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.SubmissionDAO;
import org.cgiar.ccafs.marlo.data.model.Submission;

import java.util.List;

import com.google.inject.Inject;

public class SubmissionMySQLDAO implements SubmissionDAO {

  private StandardDAO dao;

  @Inject
  public SubmissionMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteSubmission(long submissionId) {
    Submission submission = this.find(submissionId);

    return dao.delete(submission);
  }

  @Override
  public boolean existSubmission(long submissionID) {
    Submission submission = this.find(submissionID);
    if (submission == null) {
      return false;
    }
    return true;

  }

  @Override
  public Submission find(long id) {
    return dao.find(Submission.class, id);

  }

  @Override
  public List<Submission> findAll() {
    String query = "from " + Submission.class.getName() + "";
    List<Submission> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(Submission submission) {
    if (submission.getId() == null) {
      dao.save(submission);
    } else {
      dao.update(submission);
    }


    return submission.getId();
  }


}