package org.cgiar.ccafs.marlo.data.model;

import com.google.gson.annotations.Expose;

// Generated Jan 5, 2017 7:38:48 AM by Hibernate Tools 3.4.0.CR1


/**
 * DeliverableDataSharingFile generated by hbm2java
 */
public class DeliverableDataSharingFile implements java.io.Serializable {

  private static final long serialVersionUID = -5369311052598007274L;

  @Expose
  private Long id;


  private Deliverable deliverable;

  @Expose
  private FileDB file;

  @Expose
  private Integer typeId;

  @Expose
  private String externalFile;


  public DeliverableDataSharingFile() {
  }


  public DeliverableDataSharingFile(Deliverable deliverable) {
    this.deliverable = deliverable;
  }


  public DeliverableDataSharingFile(Deliverable deliverable, FileDB file, Integer type) {
    this.deliverable = deliverable;
    this.file = file;

  }


  public Deliverable getDeliverable() {
    return deliverable;
  }


  public String getExternalFile() {
    return externalFile;
  }


  public FileDB getFile() {
    return file;
  }


  public Long getId() {
    return id;
  }


  public Integer getTypeId() {
    return typeId;
  }

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setExternalFile(String externalFile) {
    this.externalFile = externalFile;
  }


  public void setFile(FileDB file) {
    this.file = file;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }


}

