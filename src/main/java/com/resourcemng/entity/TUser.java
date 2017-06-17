package com.resourcemng.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 燕子 on 2017/6/17.
 */
@Entity
@Table(name = "TUser" )
public class TUser {
  private int userId;
  private String userNo ="1";
  private String majorName =null;
  private String userPassword =null;
  private int userRole;
  private String note =null;
  private int isDelete;
  private String reserve1 =null;
  private String reserve2 =null;
  private String reserve3 =null;
  private String telephone =null;
  private Date expireDate;
  private String userName =null;

  @Id
  @Column(name = "UserID", nullable = false)
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "UserNo",/* nullable = false,*/ length = 100)
  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  @Basic
  @Column(name = "MajorName", nullable = false, length = 200)
  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  @Basic
  @Column(name = "UserPassword", nullable = false, length = 200)
  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  @Basic
  @Column(name = "UserRole", nullable = false)
  public int getUserRole() {
    return userRole;
  }

  public void setUserRole(int userRole) {
    this.userRole = userRole;
  }

  @Basic
  @Column(name = "Note", nullable = true, length = 100)
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Basic
  @Column(name = "IsDelete", nullable = false)
  public int getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(int isDelete) {
    this.isDelete = isDelete;
  }

  @Basic
  @Column(name = "Reserve1", nullable = true, length = 50)
  public String getReserve1() {
    return reserve1;
  }

  public void setReserve1(String reserve1) {
    this.reserve1 = reserve1;
  }

  @Basic
  @Column(name = "Reserve2", nullable = true, length = 50)
  public String getReserve2() {
    return reserve2;
  }

  public void setReserve2(String reserve2) {
    this.reserve2 = reserve2;
  }

  @Basic
  @Column(name = "Reserve3", nullable = true, length = 50)
  public String getReserve3() {
    return reserve3;
  }

  public void setReserve3(String reserve3) {
    this.reserve3 = reserve3;
  }

  @Basic
  @Column(name = "Telephone", nullable = true, length = 20)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "expiredate", nullable = true)
  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  @Basic
  @Column(name = "UserName", nullable = true, length = 20)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TUser tUser = (TUser) o;

    if (userId != tUser.userId) return false;
    if (userRole != tUser.userRole) return false;
    if (isDelete != tUser.isDelete) return false;
    if (userNo != null ? !userNo.equals(tUser.userNo) : tUser.userNo != null) return false;
    if (majorName != null ? !majorName.equals(tUser.majorName) : tUser.majorName != null) return false;
    if (userPassword != null ? !userPassword.equals(tUser.userPassword) : tUser.userPassword != null) return false;
    if (note != null ? !note.equals(tUser.note) : tUser.note != null) return false;
    if (reserve1 != null ? !reserve1.equals(tUser.reserve1) : tUser.reserve1 != null) return false;
    if (reserve2 != null ? !reserve2.equals(tUser.reserve2) : tUser.reserve2 != null) return false;
    if (reserve3 != null ? !reserve3.equals(tUser.reserve3) : tUser.reserve3 != null) return false;
    if (telephone != null ? !telephone.equals(tUser.telephone) : tUser.telephone != null) return false;
    if (expireDate != null ? !expireDate.equals(tUser.expireDate) : tUser.expireDate != null) return false;
    if (userName != null ? !userName.equals(tUser.userName) : tUser.userName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = userId;
    result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
    result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
    result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
    result = 31 * result + userRole;
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + isDelete;
    result = 31 * result + (reserve1 != null ? reserve1.hashCode() : 0);
    result = 31 * result + (reserve2 != null ? reserve2.hashCode() : 0);
    result = 31 * result + (reserve3 != null ? reserve3.hashCode() : 0);
    result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
    result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
