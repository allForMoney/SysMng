package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "indicator_detail", schema = "budget_resource", catalog = "")
public class IndicatorDetail {
  private String id;
  private String fileImportId;
  private String projectId;
  private String indicatorThreeLevel;
  private String planTotal;
  private String planFirstYear;
  private String planSecondYear;
  private String indicatorOneLevel;
  private String indicatorTowLevel;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "ID", nullable = false, length = 50)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "FILE_IMPORT_ID", nullable = false, length = 50)
  public String getFileImportId() {
    return fileImportId;
  }

  public void setFileImportId(String fileImportId) {
    this.fileImportId = fileImportId;
  }

  @Basic
  @Column(name = "PROJECT_ID", nullable = false, length = 50)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Basic
  @Column(name = "INDICATOR_THREE_LEVEL", nullable = true, length = 500)
  public String getIndicatorThreeLevel() {
    return indicatorThreeLevel;
  }

  public void setIndicatorThreeLevel(String indicatorThreeLevel) {
    this.indicatorThreeLevel = indicatorThreeLevel;
  }

  @Basic
  @Column(name = "PLAN_TOTAL", nullable = true, length = 500)
  public String getPlanTotal() {
    return planTotal;
  }

  public void setPlanTotal(String planTotal) {
    this.planTotal = planTotal;
  }

  @Basic
  @Column(name = "PLAN_FIRST_YEAR", nullable = true, length = 500)
  public String getPlanFirstYear() {
    return planFirstYear;
  }

  public void setPlanFirstYear(String planFirstYear) {
    this.planFirstYear = planFirstYear;
  }

  @Basic
  @Column(name = "PLAN_SECOND_YEAR", nullable = true, length = 500)
  public String getPlanSecondYear() {
    return planSecondYear;
  }

  public void setPlanSecondYear(String planSecondYear) {
    this.planSecondYear = planSecondYear;
  }

  @Basic
  @Column(name = "INDICATOR_ONE_LEVEL", nullable = true, length = 500)
  public String getIndicatorOneLevel() {
    return indicatorOneLevel;
  }

  public void setIndicatorOneLevel(String indicatorOneLevel) {
    this.indicatorOneLevel = indicatorOneLevel;
  }

  @Basic
  @Column(name = "INDICATOR_TOW_LEVEL", nullable = true, length = 500)
  public String getIndicatorTowLevel() {
    return indicatorTowLevel;
  }

  public void setIndicatorTowLevel(String indicatorTowLevel) {
    this.indicatorTowLevel = indicatorTowLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    IndicatorDetail that = (IndicatorDetail) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (fileImportId != null ? !fileImportId.equals(that.fileImportId) : that.fileImportId != null) return false;
    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
    if (indicatorThreeLevel != null ? !indicatorThreeLevel.equals(that.indicatorThreeLevel) : that.indicatorThreeLevel != null)
      return false;
    if (planTotal != null ? !planTotal.equals(that.planTotal) : that.planTotal != null) return false;
    if (planFirstYear != null ? !planFirstYear.equals(that.planFirstYear) : that.planFirstYear != null) return false;
    if (planSecondYear != null ? !planSecondYear.equals(that.planSecondYear) : that.planSecondYear != null)
      return false;
    if (indicatorOneLevel != null ? !indicatorOneLevel.equals(that.indicatorOneLevel) : that.indicatorOneLevel != null)
      return false;
    if (indicatorTowLevel != null ? !indicatorTowLevel.equals(that.indicatorTowLevel) : that.indicatorTowLevel != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (fileImportId != null ? fileImportId.hashCode() : 0);
    result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
    result = 31 * result + (indicatorThreeLevel != null ? indicatorThreeLevel.hashCode() : 0);
    result = 31 * result + (planTotal != null ? planTotal.hashCode() : 0);
    result = 31 * result + (planFirstYear != null ? planFirstYear.hashCode() : 0);
    result = 31 * result + (planSecondYear != null ? planSecondYear.hashCode() : 0);
    result = 31 * result + (indicatorOneLevel != null ? indicatorOneLevel.hashCode() : 0);
    result = 31 * result + (indicatorTowLevel != null ? indicatorTowLevel.hashCode() : 0);
    return result;
  }
}
