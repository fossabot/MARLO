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


package org.cgiar.ccafs.marlo.data.dao;

import org.cgiar.ccafs.marlo.data.dao.mysql.ProjectBasicInfoMySQLDAO;
import org.cgiar.ccafs.marlo.data.model.ProjectBasicInfo;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy(ProjectBasicInfoMySQLDAO.class)
public interface IProjectBasicInfoDAO {

  /**
   * returns a list with all the projects that are active or ongoing in the aplication
   * 
   * @param phaseDescription - Phase Description e.g (Planning, Reporting)
   * @param phaseYear - Phase Year
   * @param crpId - The looged CRP id
   * @return - a list with projects
   */
  public List<ProjectBasicInfo> findAll(String phaseDescription, String phaseYear, Long crpId);

}
