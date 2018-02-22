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


package org.cgiar.ccafs.marlo.data.model.dto;

import java.io.Serializable;
import java.util.List;


public class FundingSourceDTO implements Serializable {


  private static final long serialVersionUID = 1L;

  private Long id;
  private GlobalUnitDTO crp;
  private boolean active;

  private List<FundingSourceBudgetDTO> budgets;
  private List<FundingSourceInstitutionDTO> institutions;


  public List<FundingSourceBudgetDTO> getBudgets() {
    return budgets;
  }


  public GlobalUnitDTO getCrp() {
    return crp;
  }


  public Long getId() {
    return id;
  }


  public List<FundingSourceInstitutionDTO> getInstitutions() {
    return institutions;
  }


  public boolean isActive() {
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
  }


  public void setBudgets(List<FundingSourceBudgetDTO> budgets) {
    this.budgets = budgets;
  }


  public void setCrp(GlobalUnitDTO crp) {
    this.crp = crp;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setInstitutions(List<FundingSourceInstitutionDTO> institutions) {
    this.institutions = institutions;
  }


}
