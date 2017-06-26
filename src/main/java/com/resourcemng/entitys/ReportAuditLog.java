package com.resourcemng.entitys;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "report_audit_log", schema = "budget_resource", catalog = "")
public class ReportAuditLog {
  private String id;
  private String year;
  private String quarter;
  private String status;
  private Date reportTime;
  private Date financeAuditTime;
  private Date schoolAuditTime;
  private Date conutryAuditTime;
  private String financeAuditState;
  private String schoolAuditState;
  private String conutryAuditState;
  private String auditOpinion;
  private String projectId;

  @Id
  @Column(name = "ID", nullable = false, length = 50)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "YEAR", nullable = false, length = 10)
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  @Basic
  @Column(name = "QUARTER", nullable = false, length = 5)
  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  @Basic
  @Column(name = "STATUS", nullable = false, length = 5)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Basic
  @Column(name = "REPORT_TIME", nullable = true)
  public Date getReportTime() {
    return reportTime;
  }

  public void setReportTime(Date reportTime) {
    this.reportTime = reportTime;
  }

  @Basic
  @Column(name = "FINANCE_AUDIT_TIME", nullable = true)
  public Date getFinanceAuditTime() {
    return financeAuditTime;
  }

  public void setFinanceAuditTime(Date financeAuditTime) {
    this.financeAuditTime = financeAuditTime;
  }

  @Basic
  @Column(name = "SCHOOL_AUDIT_TIME", nullable = true)
  public Date getSchoolAuditTime() {
    return schoolAuditTime;
  }

  public void setSchoolAuditTime(Date schoolAuditTime) {
    this.schoolAuditTime = schoolAuditTime;
  }

  @Basic
  @Column(name = "CONUTRY_AUDIT_TIME", nullable = true)
  public Date getConutryAuditTime() {
    return conutryAuditTime;
  }

  public void setConutryAuditTime(Date conutryAuditTime) {
    this.conutryAuditTime = conutryAuditTime;
  }

  @Basic
  @Column(name = "FINANCE_AUDIT_STATE", nullable = false, length = 5)
  public String getFinanceAuditState() {
    return financeAuditState;
  }

  public void setFinanceAuditState(String financeAuditState) {
    this.financeAuditState = financeAuditState;
  }

  @Basic
  @Column(name = "SCHOOL_AUDIT_STATE", nullable = false, length = 5)
  public String getSchoolAuditState() {
    return schoolAuditState;
  }

  public void setSchoolAuditState(String schoolAuditState) {
    this.schoolAuditState = schoolAuditState;
  }

  @Basic
  @Column(name = "CONUTRY_AUDIT_STATE", nullable = false, length = 5)
  public String getConutryAuditState() {
    return conutryAuditState;
  }

  public void setConutryAuditState(String conutryAuditState) {
    this.conutryAuditState = conutryAuditState;
  }

  @Basic
  @Column(name = "AUDIT_OPINION", nullable = true, length = 500)
  public String getAuditOpinion() {
    return auditOpinion;
  }

  public void setAuditOpinion(String auditOpinion) {
    this.auditOpinion = auditOpinion;
  }

  @Basic
  @Column(name = "PROJECT_ID", nullable = true, length = 50)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ReportAuditLog that = (ReportAuditLog) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (year != null ? !year.equals(that.year) : that.year != null) return false;
    if (quarter != null ? !quarter.equals(that.quarter) : that.quarter != null) return false;
    if (status != null ? !status.equals(that.status) : that.status != null) return false;
    if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
    if (financeAuditTime != null ? !financeAuditTime.equals(that.financeAuditTime) : that.financeAuditTime != null)
      return false;
    if (schoolAuditTime != null ? !schoolAuditTime.equals(that.schoolAuditTime) : that.schoolAuditTime != null)
      return false;
    if (conutryAuditTime != null ? !conutryAuditTime.equals(that.conutryAuditTime) : that.conutryAuditTime != null)
      return false;
    if (financeAuditState != null ? !financeAuditState.equals(that.financeAuditState) : that.financeAuditState != null)
      return false;
    if (schoolAuditState != null ? !schoolAuditState.equals(that.schoolAuditState) : that.schoolAuditState != null)
      return false;
    if (conutryAuditState != null ? !conutryAuditState.equals(that.conutryAuditState) : that.conutryAuditState != null)
      return false;
    if (auditOpinion != null ? !auditOpinion.equals(that.auditOpinion) : that.auditOpinion != null) return false;
    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + (quarter != null ? quarter.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
    result = 31 * result + (financeAuditTime != null ? financeAuditTime.hashCode() : 0);
    result = 31 * result + (schoolAuditTime != null ? schoolAuditTime.hashCode() : 0);
    result = 31 * result + (conutryAuditTime != null ? conutryAuditTime.hashCode() : 0);
    result = 31 * result + (financeAuditState != null ? financeAuditState.hashCode() : 0);
    result = 31 * result + (schoolAuditState != null ? schoolAuditState.hashCode() : 0);
    result = 31 * result + (conutryAuditState != null ? conutryAuditState.hashCode() : 0);
    result = 31 * result + (auditOpinion != null ? auditOpinion.hashCode() : 0);
    result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
    return result;
  }
}
