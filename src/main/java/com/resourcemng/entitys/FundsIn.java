package com.resourcemng.entitys;

import com.resourcemng.Enum.IsDelete;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "funds_in", schema = "budget_resource", catalog = "")
public class FundsIn implements Serializable {
  private String id;
  private BigDecimal amountMoney;
  private String projectYear;
  private String quarterNum;
  private Date submitTime;
  private String isDelete = IsDelete.UN_DELETE;
  private String note;
  private String userId;
  private String pid;

  private String pName;
  public FundsIn() {
  }

  public FundsIn(String pid, String pName) {
    this.pid = pid;
    this.pName = pName;
  }
@Transient
  public String getpName() {
    return pName;
  }

  public void setpName(String pName) {
    this.pName = pName;
  }

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
  @Column(name = "AMOUNT_MONEY", precision = 2)
  public BigDecimal getAmountMoney() {
    return amountMoney;
  }

  public void setAmountMoney(BigDecimal amountMoney) {
    this.amountMoney = amountMoney;
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
  @Column(name = "QUARTER_NUM", length = 5)
  public String getQuarterNum() {
    return quarterNum;
  }

  public void setQuarterNum(String quarterNum) {
    this.quarterNum = quarterNum;
  }

  @Basic
  @Column(name = "SUBMIT_TIME", nullable = true)
  public Date getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Date submitTime) {
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

    FundsIn fundsIn = (FundsIn) o;

    if (id != null ? !id.equals(fundsIn.id) : fundsIn.id != null) return false;
    if (amountMoney != null ? !amountMoney.equals(fundsIn.amountMoney) : fundsIn.amountMoney != null) return false;
    if (projectYear != null ? !projectYear.equals(fundsIn.projectYear) : fundsIn.projectYear != null) return false;
    if (quarterNum != null ? !quarterNum.equals(fundsIn.quarterNum) : fundsIn.quarterNum != null) return false;
    if (submitTime != null ? !submitTime.equals(fundsIn.submitTime) : fundsIn.submitTime != null) return false;
    if (isDelete != null ? !isDelete.equals(fundsIn.isDelete) : fundsIn.isDelete != null) return false;
    if (note != null ? !note.equals(fundsIn.note) : fundsIn.note != null) return false;
    if (userId != null ? !userId.equals(fundsIn.userId) : fundsIn.userId != null) return false;
    if (pid != null ? !pid.equals(fundsIn.pid) : fundsIn.pid != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (amountMoney != null ? amountMoney.hashCode() : 0);
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
