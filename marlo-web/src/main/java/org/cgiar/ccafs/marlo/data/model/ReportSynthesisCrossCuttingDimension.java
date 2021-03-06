package org.cgiar.ccafs.marlo.data.model;
// Generated May 31, 2018 4:07:34 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisCrossCuttingDimension generated by hbm2java
 */
public class ReportSynthesisCrossCuttingDimension extends MarloAuditableEntity
  implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 187004710426450329L;


  private ReportSynthesis reportSynthesis;

  @Expose
  private String genderDescription;


  @Expose
  private String genderLessons;


  @Expose
  private String youthDescription;


  @Expose
  private String youthLessons;


  @Expose
  private String otherAspects;

  @Expose
  private String capDev;

  @Expose
  private String openData;


  @Expose
  private String intellectualAssets;

  private Set<ReportSynthesisCrossCuttingDimensionAsset> reportSynthesisCrossCuttingDimensionAssets =
    new HashSet<ReportSynthesisCrossCuttingDimensionAsset>(0);

  private Set<ReportSynthesisCrossCuttingDimensionInnovation> reportSynthesisCrossCuttingDimensionInnovations =
    new HashSet<ReportSynthesisCrossCuttingDimensionInnovation>(0);


  private List<ReportSynthesisCrossCuttingDimensionInnovation> plannedInnovations;

  private String innovationsValue;

  private List<ProjectInnovation> innovations;

  private List<ReportSynthesisCrossCuttingDimensionAsset> plannedAssets;

  private String assetsValue;

  private List<DeliverableIntellectualAsset> assets;

  public ReportSynthesisCrossCuttingDimension() {
  }

  /**
   * @return an array of integers (intellectual assets Id).
   */
  public long[] getAssetIds() {

    List<DeliverableIntellectualAsset> intellectualAssets = this.getAssets();
    if (intellectualAssets != null) {
      long[] ids = new long[intellectualAssets.size()];
      for (int i = 0; i < ids.length; i++) {
        ids[i] = intellectualAssets.get(i).getId();
      }
      return ids;
    }
    return null;
  }

  public List<DeliverableIntellectualAsset> getAssets() {
    return assets;
  }

  public String getAssetsValue() {
    return assetsValue;
  }

  public String getCapDev() {
    return capDev;
  }

  public String getGenderDescription() {
    return genderDescription;
  }

  public String getGenderLessons() {
    return genderLessons;
  }


  /**
   * @return an array of integers (project Innovation Id).
   */
  public long[] getInnovationIds() {

    List<ProjectInnovation> projectInnovations = this.getInnovations();
    if (projectInnovations != null) {
      long[] ids = new long[projectInnovations.size()];
      for (int i = 0; i < ids.length; i++) {
        ids[i] = projectInnovations.get(i).getId();
      }
      return ids;
    }
    return null;
  }

  public List<ProjectInnovation> getInnovations() {
    return innovations;
  }

  public String getInnovationsValue() {
    return innovationsValue;
  }

  public String getIntellectualAssets() {
    return intellectualAssets;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getOpenData() {
    return openData;
  }


  public String getOtherAspects() {
    return otherAspects;
  }


  public List<ReportSynthesisCrossCuttingDimensionAsset> getPlannedAssets() {
    return plannedAssets;
  }


  public List<ReportSynthesisCrossCuttingDimensionInnovation> getPlannedInnovations() {
    return plannedInnovations;
  }


  public ReportSynthesis getReportSynthesis() {
    return reportSynthesis;
  }


  public Set<ReportSynthesisCrossCuttingDimensionAsset> getReportSynthesisCrossCuttingDimensionAssets() {
    return reportSynthesisCrossCuttingDimensionAssets;
  }


  public Set<ReportSynthesisCrossCuttingDimensionInnovation> getReportSynthesisCrossCuttingDimensionInnovations() {
    return reportSynthesisCrossCuttingDimensionInnovations;
  }


  public String getYouthDescription() {
    return youthDescription;
  }


  public String getYouthLessons() {
    return youthLessons;
  }

  public void setAssets(List<DeliverableIntellectualAsset> assets) {
    this.assets = assets;
  }


  public void setAssetsValue(String assetsValue) {
    this.assetsValue = assetsValue;
  }


  public void setCapDev(String capDev) {
    this.capDev = capDev;
  }

  public void setGenderDescription(String genderDescription) {
    this.genderDescription = genderDescription;
  }

  public void setGenderLessons(String genderLessons) {
    this.genderLessons = genderLessons;
  }

  public void setInnovations(List<ProjectInnovation> innovations) {
    this.innovations = innovations;
  }

  public void setInnovationsValue(String innovationsValue) {
    this.innovationsValue = innovationsValue;
  }

  public void setIntellectualAssets(String intellectualAssets) {
    this.intellectualAssets = intellectualAssets;
  }

  public void setOpenData(String openData) {
    this.openData = openData;
  }

  public void setOtherAspects(String otherAspects) {
    this.otherAspects = otherAspects;
  }

  public void setPlannedAssets(List<ReportSynthesisCrossCuttingDimensionAsset> plannedAssets) {
    this.plannedAssets = plannedAssets;
  }

  public void setPlannedInnovations(List<ReportSynthesisCrossCuttingDimensionInnovation> plannedInnovations) {
    this.plannedInnovations = plannedInnovations;
  }

  public void setReportSynthesis(ReportSynthesis reportSynthesis) {
    this.reportSynthesis = reportSynthesis;
  }

  public void setReportSynthesisCrossCuttingDimensionAssets(
    Set<ReportSynthesisCrossCuttingDimensionAsset> reportSynthesisCrossCuttingDimensionAssets) {
    this.reportSynthesisCrossCuttingDimensionAssets = reportSynthesisCrossCuttingDimensionAssets;
  }

  public void setReportSynthesisCrossCuttingDimensionInnovations(
    Set<ReportSynthesisCrossCuttingDimensionInnovation> reportSynthesisCrossCuttingDimensionInnovations) {
    this.reportSynthesisCrossCuttingDimensionInnovations = reportSynthesisCrossCuttingDimensionInnovations;
  }

  public void setYouthDescription(String youthDescription) {
    this.youthDescription = youthDescription;
  }


  public void setYouthLessons(String youthLessons) {
    this.youthLessons = youthLessons;
  }


}

