package com.resourcemng.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Tuser {
  private String userId;
  private String userNo;
  private String majorName;
  private String userPassword;
  private String userRole;
  private String note;
  private String isDelete;
  private String telephone;
  private String userName;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "UserID", nullable = false, length = 20)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "UserNo", nullable = false, length = 100)
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
  @Column(name = "UserRole", nullable = false, length = 1)
  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
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
  @Column(name = "IsDelete", nullable = false, length = 1)
  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }

  @Basic
  @Column(name = "Telephone", nullable = true, length = 20)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
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

    Tuser tuser = (Tuser) o;

    if (userId != null ? !userId.equals(tuser.userId) : tuser.userId != null) return false;
    if (userNo != null ? !userNo.equals(tuser.userNo) : tuser.userNo != null) return false;
    if (majorName != null ? !majorName.equals(tuser.majorName) : tuser.majorName != null) return false;
    if (userPassword != null ? !userPassword.equals(tuser.userPassword) : tuser.userPassword != null) return false;
    if (userRole != null ? !userRole.equals(tuser.userRole) : tuser.userRole != null) return false;
    if (note != null ? !note.equals(tuser.note) : tuser.note != null) return false;
    if (isDelete != null ? !isDelete.equals(tuser.isDelete) : tuser.isDelete != null) return false;
    if (telephone != null ? !telephone.equals(tuser.telephone) : tuser.telephone != null) return false;
    if (userName != null ? !userName.equals(tuser.userName) : tuser.userName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = userId != null ? userId.hashCode() : 0;
    result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
    result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
    result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
    result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
