package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
public class Tuser {
  private String id;
  private String userNo;
  private String majorName;
  private String userPassword;
  private String userRole;
  private String note;
  private String isDelete;
  private String telephoneNum;
  private String userName;

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
  @Column(name = "USER_NO", nullable = false, length = 100)
  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  @Basic
  @Column(name = "MAJOR_NAME", nullable = false, length = 200)
  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  @Basic
  @Column(name = "USER_PASSWORD", nullable = false, length = 200)
  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  @Basic
  @Column(name = "USER_ROLE", nullable = false, length = 5)
  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  @Basic
  @Column(name = "NOTE", nullable = true, length = 100)
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Basic
  @Column(name = "IS_DELETE", nullable = true, length = 5)
  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }

  @Basic
  @Column(name = "TELEPHONE_NUM", nullable = true, length = 20)
  public String getTelephoneNum() {
    return telephoneNum;
  }

  public void setTelephoneNum(String telephoneNum) {
    this.telephoneNum = telephoneNum;
  }

  @Basic
  @Column(name = "USER_NAME", nullable = true, length = 20)
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

    if (id != null ? !id.equals(tuser.id) : tuser.id != null) return false;
    if (userNo != null ? !userNo.equals(tuser.userNo) : tuser.userNo != null) return false;
    if (majorName != null ? !majorName.equals(tuser.majorName) : tuser.majorName != null) return false;
    if (userPassword != null ? !userPassword.equals(tuser.userPassword) : tuser.userPassword != null) return false;
    if (userRole != null ? !userRole.equals(tuser.userRole) : tuser.userRole != null) return false;
    if (note != null ? !note.equals(tuser.note) : tuser.note != null) return false;
    if (isDelete != null ? !isDelete.equals(tuser.isDelete) : tuser.isDelete != null) return false;
    if (telephoneNum != null ? !telephoneNum.equals(tuser.telephoneNum) : tuser.telephoneNum != null) return false;
    if (userName != null ? !userName.equals(tuser.userName) : tuser.userName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
    result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
    result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
    result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (telephoneNum != null ? telephoneNum.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
