package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Reportauditlog {
  private String id;
  private String year;
  private String quarter;
  private String status;
  private Timestamp reportTime;
  private Timestamp financeAuditTime;
  private Timestamp schoolAuditTime;
  private Timestamp countryAuditTime;
  private String financeAuditState;
  private String schoolAuditState;
  private String countryAuditState;
  private String auditOpinion;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "Year", nullable = false, length = 10)
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  @Basic
  @Column(name = "Quarter", nullable = false, length = 1)
  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  @Basic
  @Column(name = "Status", nullable = false, length = 1)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Basic
  @Column(name = "ReportTime", nullable = true)
  public Timestamp getReportTime() {
    return reportTime;
  }

  public void setReportTime(Timestamp reportTime) {
    this.reportTime = reportTime;
  }

  @Basic
  @Column(name = "FinanceAuditTime", nullable = true)
  public Timestamp getFinanceAuditTime() {
    return financeAuditTime;
  }

  public void setFinanceAuditTime(Timestamp financeAuditTime) {
    this.financeAuditTime = financeAuditTime;
  }

  @Basic
  @Column(name = "SchoolAuditTime", nullable = true)
  public Timestamp getSchoolAuditTime() {
    return schoolAuditTime;
  }

  public void setSchoolAuditTime(Timestamp schoolAuditTime) {
    this.schoolAuditTime = schoolAuditTime;
  }

  @Basic
  @Column(name = "CountryAuditTime", nullable = true)
  public Timestamp getCountryAuditTime() {
    return countryAuditTime;
  }

  public void setCountryAuditTime(Timestamp countryAuditTime) {
    this.countryAuditTime = countryAuditTime;
  }

  @Basic
  @Column(name = "FinanceAuditState", nullable = false, length = 1)
  public String getFinanceAuditState() {
    return financeAuditState;
  }

  public void setFinanceAuditState(String financeAuditState) {
    this.financeAuditState = financeAuditState;
  }

  @Basic
  @Column(name = "SchoolAuditState", nullable = false, length = 1)
  public String getSchoolAuditState() {
    return schoolAuditState;
  }

  public void setSchoolAuditState(String schoolAuditState) {
    this.schoolAuditState = schoolAuditState;
  }

  @Basic
  @Column(name = "CountryAuditState", nullable = false, length = 1)
  public String getCountryAuditState() {
    return countryAuditState;
  }

  public void setCountryAuditState(String countryAuditState) {
    this.countryAuditState = countryAuditState;
  }

  @Basic
  @Column(name = "AuditOpinion", nullable = true, length = 500)
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

    Reportauditlog that = (Reportauditlog) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (year != null ? !year.equals(that.year) : that.year != null) return false;
    if (quarter != null ? !quarter.equals(that.quarter) : that.quarter != null) return false;
    if (status != null ? !status.equals(that.status) : that.status != null) return false;
    if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
    if (financeAuditTime != null ? !financeAuditTime.equals(that.financeAuditTime) : that.financeAuditTime != null)
      return false;
    if (schoolAuditTime != null ? !schoolAuditTime.equals(that.schoolAuditTime) : that.schoolAuditTime != null)
      return false;
    if (countryAuditTime != null ? !countryAuditTime.equals(that.countryAuditTime) : that.countryAuditTime != null)
      return false;
    if (financeAuditState != null ? !financeAuditState.equals(that.financeAuditState) : that.financeAuditState != null)
      return false;
    if (schoolAuditState != null ? !schoolAuditState.equals(that.schoolAuditState) : that.schoolAuditState != null)
      return false;
    if (countryAuditState != null ? !countryAuditState.equals(that.countryAuditState) : that.countryAuditState != null)
      return false;
    if (auditOpinion != null ? !auditOpinion.equals(that.auditOpinion) : that.auditOpinion != null) return false;

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
    result = 31 * result + (countryAuditTime != null ? countryAuditTime.hashCode() : 0);
    result = 31 * result + (financeAuditState != null ? financeAuditState.hashCode() : 0);
    result = 31 * result + (schoolAuditState != null ? schoolAuditState.hashCode() : 0);
    result = 31 * result + (countryAuditState != null ? countryAuditState.hashCode() : 0);
    result = 31 * result + (auditOpinion != null ? auditOpinion.hashCode() : 0);
    return result;
  }
}
