package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Fundsout {
  private String id;
  private BigDecimal materialMake;
  private BigDecimal companyCase;
  private BigDecimal courseDevelopment;
  private BigDecimal toolSoftware;
  private BigDecimal applicationPromote;
  private BigDecimal researchProve;
  private BigDecimal expertConsult;
  private BigDecimal otherFee;
  private String projectYear;
  private String quarterNum;
  private Timestamp submitTime;
  private String isDelete;
  private String note;

  @Id
  @Column(name = "ID", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "MaterialMake", nullable = false, precision = 2)
  public BigDecimal getMaterialMake() {
    return materialMake;
  }

  public void setMaterialMake(BigDecimal materialMake) {
    this.materialMake = materialMake;
  }

  @Basic
  @Column(name = "CompanyCase", nullable = false, precision = 2)
  public BigDecimal getCompanyCase() {
    return companyCase;
  }

  public void setCompanyCase(BigDecimal companyCase) {
    this.companyCase = companyCase;
  }

  @Basic
  @Column(name = "CourseDevelopment", nullable = false, precision = 2)
  public BigDecimal getCourseDevelopment() {
    return courseDevelopment;
  }

  public void setCourseDevelopment(BigDecimal courseDevelopment) {
    this.courseDevelopment = courseDevelopment;
  }

  @Basic
  @Column(name = "ToolSoftware", nullable = false, precision = 2)
  public BigDecimal getToolSoftware() {
    return toolSoftware;
  }

  public void setToolSoftware(BigDecimal toolSoftware) {
    this.toolSoftware = toolSoftware;
  }

  @Basic
  @Column(name = "ApplicationPromote", nullable = false, precision = 2)
  public BigDecimal getApplicationPromote() {
    return applicationPromote;
  }

  public void setApplicationPromote(BigDecimal applicationPromote) {
    this.applicationPromote = applicationPromote;
  }

  @Basic
  @Column(name = "ResearchProve", nullable = false, precision = 2)
  public BigDecimal getResearchProve() {
    return researchProve;
  }

  public void setResearchProve(BigDecimal researchProve) {
    this.researchProve = researchProve;
  }

  @Basic
  @Column(name = "ExpertConsult", nullable = false, precision = 2)
  public BigDecimal getExpertConsult() {
    return expertConsult;
  }

  public void setExpertConsult(BigDecimal expertConsult) {
    this.expertConsult = expertConsult;
  }

  @Basic
  @Column(name = "OtherFee", nullable = false, precision = 2)
  public BigDecimal getOtherFee() {
    return otherFee;
  }

  public void setOtherFee(BigDecimal otherFee) {
    this.otherFee = otherFee;
  }

  @Basic
  @Column(name = "ProjectYear", nullable = false, length = 10)
  public String getProjectYear() {
    return projectYear;
  }

  public void setProjectYear(String projectYear) {
    this.projectYear = projectYear;
  }

  @Basic
  @Column(name = "QuarterNum", nullable = false, length = 1)
  public String getQuarterNum() {
    return quarterNum;
  }

  public void setQuarterNum(String quarterNum) {
    this.quarterNum = quarterNum;
  }

  @Basic
  @Column(name = "SubmitTime", nullable = true)
  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
  }

  @Basic
  @Column(name = "IsDelete", nullable = false, length = 1)
  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }

  @Basic
  @Column(name = "Note", nullable = true, length = 50)
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Fundsout fundsout = (Fundsout) o;

    if (id != null ? !id.equals(fundsout.id) : fundsout.id != null) return false;
    if (materialMake != null ? !materialMake.equals(fundsout.materialMake) : fundsout.materialMake != null)
      return false;
    if (companyCase != null ? !companyCase.equals(fundsout.companyCase) : fundsout.companyCase != null) return false;
    if (courseDevelopment != null ? !courseDevelopment.equals(fundsout.courseDevelopment) : fundsout.courseDevelopment != null)
      return false;
    if (toolSoftware != null ? !toolSoftware.equals(fundsout.toolSoftware) : fundsout.toolSoftware != null)
      return false;
    if (applicationPromote != null ? !applicationPromote.equals(fundsout.applicationPromote) : fundsout.applicationPromote != null)
      return false;
    if (researchProve != null ? !researchProve.equals(fundsout.researchProve) : fundsout.researchProve != null)
      return false;
    if (expertConsult != null ? !expertConsult.equals(fundsout.expertConsult) : fundsout.expertConsult != null)
      return false;
    if (otherFee != null ? !otherFee.equals(fundsout.otherFee) : fundsout.otherFee != null) return false;
    if (projectYear != null ? !projectYear.equals(fundsout.projectYear) : fundsout.projectYear != null) return false;
    if (quarterNum != null ? !quarterNum.equals(fundsout.quarterNum) : fundsout.quarterNum != null) return false;
    if (submitTime != null ? !submitTime.equals(fundsout.submitTime) : fundsout.submitTime != null) return false;
    if (isDelete != null ? !isDelete.equals(fundsout.isDelete) : fundsout.isDelete != null) return false;
    if (note != null ? !note.equals(fundsout.note) : fundsout.note != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (materialMake != null ? materialMake.hashCode() : 0);
    result = 31 * result + (companyCase != null ? companyCase.hashCode() : 0);
    result = 31 * result + (courseDevelopment != null ? courseDevelopment.hashCode() : 0);
    result = 31 * result + (toolSoftware != null ? toolSoftware.hashCode() : 0);
    result = 31 * result + (applicationPromote != null ? applicationPromote.hashCode() : 0);
    result = 31 * result + (researchProve != null ? researchProve.hashCode() : 0);
    result = 31 * result + (expertConsult != null ? expertConsult.hashCode() : 0);
    result = 31 * result + (otherFee != null ? otherFee.hashCode() : 0);
    result = 31 * result + (projectYear != null ? projectYear.hashCode() : 0);
    result = 31 * result + (quarterNum != null ? quarterNum.hashCode() : 0);
    result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    return result;
  }
}
