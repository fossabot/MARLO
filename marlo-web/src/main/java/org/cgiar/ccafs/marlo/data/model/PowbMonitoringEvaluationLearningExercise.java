package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 9, 2018 11:37:50 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * PowbMonitoringEvaluationLearningExercise generated by hbm2java
 */
public class PowbMonitoringEvaluationLearningExercise extends MarloAuditableEntity
  implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 8703481544292834746L;

  @Expose
  private PowbMonitoringEvaluationLearning powbMonitoringEvaluationLearning;

  @Expose
  private String exercise;

  @Expose
  private String comments;

  public PowbMonitoringEvaluationLearningExercise() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    PowbMonitoringEvaluationLearningExercise other = (PowbMonitoringEvaluationLearningExercise) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public String getComments() {
    return comments;
  }


  public String getExercise() {
    return exercise;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public PowbMonitoringEvaluationLearning getPowbMonitoringEvaluationLearning() {
    return powbMonitoringEvaluationLearning;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public void setExercise(String exercise) {
    this.exercise = exercise;
  }

  public void setPowbMonitoringEvaluationLearning(PowbMonitoringEvaluationLearning powbMonitoringEvaluationLearning) {
    this.powbMonitoringEvaluationLearning = powbMonitoringEvaluationLearning;
  }


}

