package com.resourcemng.entitys;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "funds_out", schema = "budget_resource", catalog = "")
public class FundsOut {
  private String id;
  private BigDecimal materialMake;
  private BigDecimal companyCase;
  private BigDecimal courseDevelopment;
  private BigDecimal specialTool;
  private BigDecimal applicationPromete;
  private BigDecimal researchProve;
  private BigDecimal expertConsult;
  private BigDecimal otherFee;
  private String projectYear;
  private String quarterNum;
  private Timestamp submitTime;
  private String isDelete;
  private String note;
  private String userId;
  private String pid;

  @Id
  @Column(name = "ID", nullable = false, length = 50)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "MATERIAL_MAKE", nullable = false, precision = 2)
  public BigDecimal getMaterialMake() {
    return materialMake;
  }

  public void setMaterialMake(BigDecimal materialMake) {
    this.materialMake = materialMake;
  }

  @Basic
  @Column(name = "COMPANY_CASE", nullable = false, precision = 2)
  public BigDecimal getCompanyCase() {
    return companyCase;
  }

  public void setCompanyCase(BigDecimal companyCase) {
    this.companyCase = companyCase;
  }

  @Basic
  @Column(name = "COURSE_DEVELOPMENT", nullable = false, precision = 2)
  public BigDecimal getCourseDevelopment() {
    return courseDevelopment;
  }

  public void setCourseDevelopment(BigDecimal courseDevelopment) {
    this.courseDevelopment = courseDevelopment;
  }

  @Basic
  @Column(name = "SPECIAL_TOOL", nullable = false, precision = 2)
  public BigDecimal getSpecialTool() {
    return specialTool;
  }

  public void setSpecialTool(BigDecimal specialTool) {
    this.specialTool = specialTool;
  }

  @Basic
  @Column(name = "APPLICATION_PROMETE", nullable = false, precision = 2)
  public BigDecimal getApplicationPromete() {
    return applicationPromete;
  }

  public void setApplicationPromete(BigDecimal applicationPromete) {
    this.applicationPromete = applicationPromete;
  }

  @Basic
  @Column(name = "RESEARCH_PROVE", nullable = false, precision = 2)
  public BigDecimal getResearchProve() {
    return researchProve;
  }

  public void setResearchProve(BigDecimal researchProve) {
    this.researchProve = researchProve;
  }

  @Basic
  @Column(name = "EXPERT_CONSULT", nullable = false, precision = 2)
  public BigDecimal getExpertConsult() {
    return expertConsult;
  }

  public void setExpertConsult(BigDecimal expertConsult) {
    this.expertConsult = expertConsult;
  }

  @Basic
  @Column(name = "OTHER_FEE", nullable = false, precision = 2)
  public BigDecimal getOtherFee() {
    return otherFee;
  }

  public void setOtherFee(BigDecimal otherFee) {
    this.otherFee = otherFee;
  }

  @Basic
  @Column(name = "PROJECT_YEAR", nullable = false, length = 10)
  public String getProjectYear() {
    return projectYear;
  }

  public void setProjectYear(String projectYear) {
    this.projectYear = projectYear;
  }

  @Basic
  @Column(name = "QUARTER_NUM", nullable = false, length = 5)
  public String getQuarterNum() {
    return quarterNum;
  }

  public void setQuarterNum(String quarterNum) {
    this.quarterNum = quarterNum;
  }

  @Basic
  @Column(name = "SUBMIT_TIME", nullable = true)
  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
  }

  @Basic
  @Column(name = "IS_DELETE", nullable = false, length = 5)
  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }

  @Basic
  @Column(name = "NOTE", nullable = true, length = 50)
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Basic
  @Column(name = "USER_ID", nullable = true, length = 50)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "PID", nullable = true, length = 5)
  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FundsOut fundsOut = (FundsOut) o;

    if (id != null ? !id.equals(fundsOut.id) : fundsOut.id != null) return false;
    if (materialMake != null ? !materialMake.equals(fundsOut.materialMake) : fundsOut.materialMake != null)
      return false;
    if (companyCase != null ? !companyCase.equals(fundsOut.companyCase) : fundsOut.companyCase != null) return false;
    if (courseDevelopment != null ? !courseDevelopment.equals(fundsOut.courseDevelopment) : fundsOut.courseDevelopment != null)
      return false;
    if (specialTool != null ? !specialTool.equals(fundsOut.specialTool) : fundsOut.specialTool != null) return false;
    if (applicationPromete != null ? !applicationPromete.equals(fundsOut.applicationPromete) : fundsOut.applicationPromete != null)
      return false;
    if (researchProve != null ? !researchProve.equals(fundsOut.researchProve) : fundsOut.researchProve != null)
      return false;
    if (expertConsult != null ? !expertConsult.equals(fundsOut.expertConsult) : fundsOut.expertConsult != null)
      return false;
    if (otherFee != null ? !otherFee.equals(fundsOut.otherFee) : fundsOut.otherFee != null) return false;
    if (projectYear != null ? !projectYear.equals(fundsOut.projectYear) : fundsOut.projectYear != null) return false;
    if (quarterNum != null ? !quarterNum.equals(fundsOut.quarterNum) : fundsOut.quarterNum != null) return false;
    if (submitTime != null ? !submitTime.equals(fundsOut.submitTime) : fundsOut.submitTime != null) return false;
    if (isDelete != null ? !isDelete.equals(fundsOut.isDelete) : fundsOut.isDelete != null) return false;
    if (note != null ? !note.equals(fundsOut.note) : fundsOut.note != null) return false;
    if (userId != null ? !userId.equals(fundsOut.userId) : fundsOut.userId != null) return false;
    if (pid != null ? !pid.equals(fundsOut.pid) : fundsOut.pid != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (materialMake != null ? materialMake.hashCode() : 0);
    result = 31 * result + (companyCase != null ? companyCase.hashCode() : 0);
    result = 31 * result + (courseDevelopment != null ? courseDevelopment.hashCode() : 0);
    result = 31 * result + (specialTool != null ? specialTool.hashCode() : 0);
    result = 31 * result + (applicationPromete != null ? applicationPromete.hashCode() : 0);
    result = 31 * result + (researchProve != null ? researchProve.hashCode() : 0);
    result = 31 * result + (expertConsult != null ? expertConsult.hashCode() : 0);
    result = 31 * result + (otherFee != null ? otherFee.hashCode() : 0);
    result = 31 * result + (projectYear != null ? projectYear.hashCode() : 0);
    result = 31 * result + (quarterNum != null ? quarterNum.hashCode() : 0);
    result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    result = 31 * result + (pid != null ? pid.hashCode() : 0);
    return result;
  }
}
