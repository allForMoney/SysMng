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
public class Fundsin {
  private String id;
  private BigDecimal moneyAmount;
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
  @Column(name = "MoneyAmount", nullable = false, precision = 2)
  public BigDecimal getMoneyAmount() {
    return moneyAmount;
  }

  public void setMoneyAmount(BigDecimal moneyAmount) {
    this.moneyAmount = moneyAmount;
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

    Fundsin fundsin = (Fundsin) o;

    if (id != null ? !id.equals(fundsin.id) : fundsin.id != null) return false;
    if (moneyAmount != null ? !moneyAmount.equals(fundsin.moneyAmount) : fundsin.moneyAmount != null) return false;
    if (projectYear != null ? !projectYear.equals(fundsin.projectYear) : fundsin.projectYear != null) return false;
    if (quarterNum != null ? !quarterNum.equals(fundsin.quarterNum) : fundsin.quarterNum != null) return false;
    if (submitTime != null ? !submitTime.equals(fundsin.submitTime) : fundsin.submitTime != null) return false;
    if (isDelete != null ? !isDelete.equals(fundsin.isDelete) : fundsin.isDelete != null) return false;
    if (note != null ? !note.equals(fundsin.note) : fundsin.note != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (moneyAmount != null ? moneyAmount.hashCode() : 0);
    result = 31 * result + (projectYear != null ? projectYear.hashCode() : 0);
    result = 31 * result + (quarterNum != null ? quarterNum.hashCode() : 0);
    result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    return result;
  }
}
