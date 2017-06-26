package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "budget_audit_log", schema = "budget_resource", catalog = "")
public class BudgetAuditLog {
  private String id;
  private String year;
  private String adjustId;
  private String status;
  private Timestamp reportTime;
  private Timestamp financeAuditTime;
  private Timestamp schoolAuditTime;
  private Timestamp conutryAuditTime;
  private String financeAuditState;
  private String schoolAuditState;
  private String conutryAuditState;
  private String auditOpinion;

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
  @Column(name = "YEAR", nullable = false, length = 10)
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  @Basic
  @Column(name = "ADJUST_ID", nullable = false, length = 50)
  public String getAdjustId() {
    return adjustId;
  }

  public void setAdjustId(String adjustId) {
    this.adjustId = adjustId;
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
  public Timestamp getReportTime() {
    return reportTime;
  }

  public void setReportTime(Timestamp reportTime) {
    this.reportTime = reportTime;
  }

  @Basic
  @Column(name = "FINANCE_AUDIT_TIME", nullable = true)
  public Timestamp getFinanceAuditTime() {
    return financeAuditTime;
  }

  public void setFinanceAuditTime(Timestamp financeAuditTime) {
    this.financeAuditTime = financeAuditTime;
  }

  @Basic
  @Column(name = "SCHOOL_AUDIT_TIME", nullable = true)
  public Timestamp getSchoolAuditTime() {
    return schoolAuditTime;
  }

  public void setSchoolAuditTime(Timestamp schoolAuditTime) {
    this.schoolAuditTime = schoolAuditTime;
  }

  @Basic
  @Column(name = "CONUTRY_AUDIT_TIME", nullable = true)
  public Timestamp getConutryAuditTime() {
    return conutryAuditTime;
  }

  public void setConutryAuditTime(Timestamp conutryAuditTime) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BudgetAuditLog that = (BudgetAuditLog) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (year != null ? !year.equals(that.year) : that.year != null) return false;
    if (adjustId != null ? !adjustId.equals(that.adjustId) : that.adjustId != null) return false;
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

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + (adjustId != null ? adjustId.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
    result = 31 * result + (financeAuditTime != null ? financeAuditTime.hashCode() : 0);
    result = 31 * result + (schoolAuditTime != null ? schoolAuditTime.hashCode() : 0);
    result = 31 * result + (conutryAuditTime != null ? conutryAuditTime.hashCode() : 0);
    result = 31 * result + (financeAuditState != null ? financeAuditState.hashCode() : 0);
    result = 31 * result + (schoolAuditState != null ? schoolAuditState.hashCode() : 0);
    result = 31 * result + (conutryAuditState != null ? conutryAuditState.hashCode() : 0);
    result = 31 * result + (auditOpinion != null ? auditOpinion.hashCode() : 0);
    return result;
  }
}
