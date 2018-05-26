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

import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectInfo;

import java.util.HashMap;

import javax.annotation.Generated;

public class ReportingSummaryContext {

  /**
   * Builder to build {@link ReportingSummaryContext}.
   */
  @Generated("SparkTools")
  public static final class Builder {

    private String crpAcronym;
    private Project project;
    private Boolean hasW1W2Co;
    private Boolean hasGender;
    private Phase selectedPhase;
    private int selectedYear;
    private String selectedCycle;
    private ProjectInfo projectInfo;
    private Phase actualPhase;
    private HashMap<Long, String> targetUnitList;
    private boolean activitiesSpecifityEnabled;
    private boolean budgetGenderSpecifityEnabled;
    private boolean hasProgramRegions;
    private boolean ipOutcomeIndicatorSpecificityEnabled;
    private boolean isPhaseOne;
    private boolean isProjectNew;
    private GlobalUnit loggedCrp;

    private Builder() {
    }

    public ReportingSummaryContext build() {
      return new ReportingSummaryContext(this);
    }

    public Builder withActivitiesSpecifityEnabled(boolean activitiesSpecifityEnabled) {
      this.activitiesSpecifityEnabled = activitiesSpecifityEnabled;
      return this;
    }

    public Builder withActualPhase(Phase actualPhase) {
      this.actualPhase = actualPhase;
      return this;
    }

    public Builder withBudgetGenderSpecifityEnabled(boolean budgetGenderSpecifityEnabled) {
      this.budgetGenderSpecifityEnabled = budgetGenderSpecifityEnabled;
      return this;
    }

    public Builder withCrpAcronym(String crpAcronym) {
      this.crpAcronym = crpAcronym;
      return this;
    }

    public Builder withHasGender(Boolean hasGender) {
      this.hasGender = hasGender;
      return this;
    }

    public Builder withHasProgramRegions(boolean hasProgramRegions) {
      this.hasProgramRegions = hasProgramRegions;
      return this;
    }

    public Builder withHasW1W2Co(Boolean hasW1W2Co) {
      this.hasW1W2Co = hasW1W2Co;
      return this;
    }

    public Builder withIpOutcomeIndicatorSpecificityEnabled(boolean ipOutcomeIndicatorSpecificityEnabled) {
      this.ipOutcomeIndicatorSpecificityEnabled = ipOutcomeIndicatorSpecificityEnabled;
      return this;
    }

    public Builder withIsPhaseOne(boolean isPhaseOne) {
      this.isPhaseOne = isPhaseOne;
      return this;
    }

    public Builder withIsProjectNew(boolean isProjectNew) {
      this.isProjectNew = isProjectNew;
      return this;
    }

    public Builder withLoggedCrp(GlobalUnit loggedCrp) {
      this.loggedCrp = loggedCrp;
      return this;
    }

    public Builder withProject(Project project) {
      this.project = project;
      return this;
    }

    public Builder withProjectInfo(ProjectInfo projectInfo) {
      this.projectInfo = projectInfo;
      return this;
    }

    public Builder withSelectedCycle(String selectedCycle) {
      this.selectedCycle = selectedCycle;
      return this;
    }

    public Builder withSelectedPhase(Phase selectedPhase) {
      this.selectedPhase = selectedPhase;
      return this;
    }

    public Builder withSelectedYear(int selectedYear) {
      this.selectedYear = selectedYear;
      return this;
    }

    public Builder withTargetUnitList(HashMap<Long, String> targetUnitList) {
      this.targetUnitList = targetUnitList;
      return this;
    }
  }


  /**
   * Creates builder to build {@link ReportingSummaryContext}.
   * 
   * @return created builder
   */
  @Generated("SparkTools")
  public static Builder builder() {
    return new Builder();
  }


  private final String crpAcronym;

  private final Project project;

  private final Boolean hasW1W2Co;


  private final Boolean hasGender;

  private final Phase selectedPhase;

  private final int selectedYear;

  private final String selectedCycle;


  private final ProjectInfo projectInfo;


  private final Phase actualPhase;


  private final HashMap<Long, String> targetUnitList;

  private final boolean activitiesSpecifityEnabled;

  private final boolean budgetGenderSpecifityEnabled;

  private final boolean hasProgramRegions;

  private final boolean ipOutcomeIndicatorSpecificityEnabled;

  private final boolean isPhaseOne;

  private final boolean isProjectNew;

  private final GlobalUnit loggedCrp;

  @Generated("SparkTools")
  private ReportingSummaryContext(Builder builder) {
    this.crpAcronym = builder.crpAcronym;
    this.project = builder.project;
    this.hasW1W2Co = builder.hasW1W2Co;
    this.hasGender = builder.hasGender;
    this.selectedPhase = builder.selectedPhase;
    this.selectedYear = builder.selectedYear;
    this.selectedCycle = builder.selectedCycle;
    this.projectInfo = builder.projectInfo;
    this.actualPhase = builder.actualPhase;
    this.targetUnitList = builder.targetUnitList;
    this.activitiesSpecifityEnabled = builder.activitiesSpecifityEnabled;
    this.budgetGenderSpecifityEnabled = builder.budgetGenderSpecifityEnabled;
    this.hasProgramRegions = builder.hasProgramRegions;
    this.ipOutcomeIndicatorSpecificityEnabled = builder.ipOutcomeIndicatorSpecificityEnabled;
    this.isPhaseOne = builder.isPhaseOne;
    this.isProjectNew = builder.isProjectNew;
    this.loggedCrp = builder.loggedCrp;
  }

  public Phase getActualPhase() {
    return actualPhase;
  }

  public String getCrpAcronym() {
    return crpAcronym;
  }

  public Boolean getHasGender() {
    return hasGender;
  }

  public Boolean getHasW1W2Co() {
    return hasW1W2Co;
  }

  public GlobalUnit getLoggedCrp() {
    return loggedCrp;
  }

  public Project getProject() {
    return project;
  }

  public ProjectInfo getProjectInfo() {
    return projectInfo;
  }


  public String getSelectedCycle() {
    return selectedCycle;
  }

  public Phase getSelectedPhase() {
    return selectedPhase;
  }

  public int getSelectedYear() {
    return selectedYear;
  }

  public HashMap<Long, String> getTargetUnitList() {
    return targetUnitList;
  }

  public boolean isActivitiesSpecifityEnabled() {
    return activitiesSpecifityEnabled;
  }

  public boolean isBudgetGenderSpecifityEnabled() {
    return budgetGenderSpecifityEnabled;
  }

  public boolean isHasProgramRegions() {
    return hasProgramRegions;
  }

  public boolean isIpOutcomeIndicatorSpecificityEnabled() {
    return ipOutcomeIndicatorSpecificityEnabled;
  }

  public boolean isPhaseOne() {
    return isPhaseOne;
  }

  public boolean isProjectNew() {
    return isProjectNew;
  }


}
