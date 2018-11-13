package org.cgiar.ccafs.marlo.data.model;
// Generated Nov 6, 2018 6:04:00 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * PowbProgramChange generated by hbm2java
 */
public class PowbProgramChange extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 6735084453295940640L;


  private PowbSynthesis powbSynthesis;


  @Expose
  private String programChange;


  public PowbProgramChange() {
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
  }

  public String getProgramChange() {
    return programChange;
  }


  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }


  public void setProgramChange(String programChange) {
    this.programChange = programChange;
  }


}
