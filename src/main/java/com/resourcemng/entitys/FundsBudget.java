package com.resourcemng.entitys;

import com.resourcemng.Enum.IsDelete;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "funds_budget")
public class FundsBudget implements Serializable {
  private String id;
  private String projectId;
  private BigDecimal materialMake = new BigDecimal(0);
  private BigDecimal companyCase = new BigDecimal(0);
  private BigDecimal courseDevelopment = new BigDecimal(0);
  private BigDecimal toolSoftware = new BigDecimal(0);
  private BigDecimal applicationPromote = new BigDecimal(0);
  private BigDecimal researchProve = new BigDecimal(0);
  private BigDecimal expertConsult = new BigDecimal(0);
  private BigDecimal otherFee = new BigDecimal(0);
  private String budgetYear;
  private Date submitTime;
  private String isDelete = IsDelete.UN_DELETE;
  private String note;
  private String pid;

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

  @Column(name = "ProjectId", nullable = false, length = 50)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
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
  @Column(name = "BudgetYear", nullable = false, length = 10)
  public String getBudgetYear() {
    return budgetYear;
  }

  public void setBudgetYear(String budgetYear) {
    this.budgetYear = budgetYear;
  }

  @Basic
  @Column(name = "SubmitTime", nullable = true)
  public Date getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Date submitTime) {
    this.submitTime = submitTime;
  }

  @Basic
  @Column(name = "IsDelete", nullable = false, length = 5)
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

    FundsBudget that = (FundsBudget) o;

    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
    if (materialMake != null ? !materialMake.equals(that.materialMake) : that.materialMake != null) return false;
    if (companyCase != null ? !companyCase.equals(that.companyCase) : that.companyCase != null) return false;
    if (courseDevelopment != null ? !courseDevelopment.equals(that.courseDevelopment) : that.courseDevelopment != null)
      return false;
    if (toolSoftware != null ? !toolSoftware.equals(that.toolSoftware) : that.toolSoftware != null) return false;
    if (applicationPromote != null ? !applicationPromote.equals(that.applicationPromote) : that.applicationPromote != null)
      return false;
    if (researchProve != null ? !researchProve.equals(that.researchProve) : that.researchProve != null) return false;
    if (expertConsult != null ? !expertConsult.equals(that.expertConsult) : that.expertConsult != null) return false;
    if (otherFee != null ? !otherFee.equals(that.otherFee) : that.otherFee != null) return false;
    if (budgetYear != null ? !budgetYear.equals(that.budgetYear) : that.budgetYear != null) return false;
    if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
    if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null) return false;
    if (note != null ? !note.equals(that.note) : that.note != null) return false;
    if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = projectId != null ? projectId.hashCode() : 0;
    result = 31 * result + (materialMake != null ? materialMake.hashCode() : 0);
    result = 31 * result + (companyCase != null ? companyCase.hashCode() : 0);
    result = 31 * result + (courseDevelopment != null ? courseDevelopment.hashCode() : 0);
    result = 31 * result + (toolSoftware != null ? toolSoftware.hashCode() : 0);
    result = 31 * result + (applicationPromote != null ? applicationPromote.hashCode() : 0);
    result = 31 * result + (researchProve != null ? researchProve.hashCode() : 0);
    result = 31 * result + (expertConsult != null ? expertConsult.hashCode() : 0);
    result = 31 * result + (otherFee != null ? otherFee.hashCode() : 0);
    result = 31 * result + (budgetYear != null ? budgetYear.hashCode() : 0);
    result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (pid != null ? pid.hashCode() : 0);
    return result;
  }
}
