package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated Jul 26, 2017 8:32:12 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CapdevSupportingDocs generated by hbm2java
 */
public class CapdevSupportingDocs implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Expose
  private Long id;

  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;

  @Expose
  private CenterDeliverableType centerDeliverableTypes;

  @Expose
  private CenterDeliverableType deliverableSubtype;

  @Expose
  private CapacityDevelopment capacityDevelopment;

  @Expose
  private String title;

  @Expose
  private Date publicationDate;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  private Set<CapdevSuppDocsDocuments> capdevSuppDocsDocumentses = new HashSet<CapdevSuppDocsDocuments>(0);

  public CapdevSupportingDocs() {
  }

  public CapdevSupportingDocs(User modifiedBy, User createdBy, CenterDeliverableType centerDeliverableTypes,
    CenterDeliverableType deliverableSubtype, CapacityDevelopment capacityDevelopment, String title,
    Date publicationDate, boolean active, Date activeSince, String modificationJustification,
    Set<CapdevSuppDocsDocuments> capdevSuppDocsDocumentses) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.centerDeliverableTypes = centerDeliverableTypes;
    this.deliverableSubtype = deliverableSubtype;
    this.capacityDevelopment = capacityDevelopment;
    this.title = title;
    this.publicationDate = publicationDate;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.capdevSuppDocsDocumentses = capdevSuppDocsDocumentses;
  }


  public Date getActiveSince() {
    return this.activeSince;
  }


  public CapacityDevelopment getCapacityDevelopment() {
    return this.capacityDevelopment;
  }


  public Set<CapdevSuppDocsDocuments> getCapdevSuppDocsDocumentses() {
    return capdevSuppDocsDocumentses;
  }


  public CenterDeliverableType getCenterDeliverableTypes() {
    return this.centerDeliverableTypes;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public CenterDeliverableType getDeliverableSubtype() {
    return deliverableSubtype;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  @Override
  public String getModificationJustification() {
    return this.modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public Date getPublicationDate() {
    return this.publicationDate;
  }

  public String getTitle() {
    return this.title;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setCapacityDevelopment(CapacityDevelopment capacityDevelopment) {
    this.capacityDevelopment = capacityDevelopment;
  }


  public void setCapdevSuppDocsDocumentses(Set<CapdevSuppDocsDocuments> capdevSuppDocsDocumentses) {
    this.capdevSuppDocsDocumentses = capdevSuppDocsDocumentses;
  }

  public void setCenterDeliverableTypes(CenterDeliverableType centerDeliverableTypes) {
    this.centerDeliverableTypes = centerDeliverableTypes;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setDeliverableSubtype(CenterDeliverableType deliverableSubtype) {
    this.deliverableSubtype = deliverableSubtype;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  public void setTitle(String title) {
    this.title = title;
  }


}

