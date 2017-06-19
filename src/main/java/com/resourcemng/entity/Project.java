package com.resourcemng.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by 燕子 on 2017/6/17.
 */
@Entity
public class Project {
  private int id;
  @Excel(name = "项目编号", orderNum = "1")
  private String projectNo;
  @Excel(name = "专业名称", orderNum = "2")
  private String majorName;
  @Excel(name = "立项年度", orderNum = "3")
  private int createYear;
  @Excel(name = "学校名称", orderNum = "4")


  private String schoolName;
  @Excel(name = "省份", orderNum = "5")
  private String provinceId;
  @Excel(name = "法定代表人", orderNum = "6")
  private String schoolHead;
  @Excel(name = "财务部门负责人", orderNum = "7")
  private String financeHead;
  @Excel(name = "财务部门负责人电话", orderNum = "8")
  private String fhTel;
  @Excel(name = "财务负责人QQ", orderNum = "9")
  private String fhqq;
  @Excel(name = "项目负责人", orderNum = "10")
  private String projectHead;
  @Excel(name = "项目负责人电话", orderNum = "11")

  private String phTel;
  @Excel(name = "填报人", orderNum = "12")
  private String reportHead;
  @Excel(name = "填报人电话", orderNum = "13")
  private String rhTel;
  @Excel(name = "填报人QQ", orderNum = "14")
  private String rhqq;
  @Excel(name = "备注", orderNum = "15")
  private String note;
  @Excel(name = "联合主持单位", orderNum = "16")
  private String school2;
  @Excel(name = "参与建设单位", orderNum = "17")
  private String school3;
  private Timestamp submitTime;
  private int isDelete;
  private int uiStatus;
  private String reserve1;
  private String reserve2;
  private String reserve3;

  private Integer smallNo;

  @Id
  @Column(name = "Id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "SchoolName", nullable = false, length = 200)
  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  @Basic
  @Column(name = "SchoolHead", nullable = false, length = 50)
  public String getSchoolHead() {
    return schoolHead;
  }

  public void setSchoolHead(String schoolHead) {
    this.schoolHead = schoolHead;
  }

  @Basic
  @Column(name = "FinanceHead", nullable = false, length = 50)
  public String getFinanceHead() {
    return financeHead;
  }

  public void setFinanceHead(String financeHead) {
    this.financeHead = financeHead;
  }

  @Basic
  @Column(name = "FHTel", nullable = false, length = 50)
  public String getFhTel() {
    return fhTel;
  }

  public void setFhTel(String fhTel) {
    this.fhTel = fhTel;
  }

  @Basic
  @Column(name = "FHQQ", nullable = true, length = 50)
  public String getFhqq() {
    return fhqq;
  }

  public void setFhqq(String fhqq) {
    this.fhqq = fhqq;
  }

  @Basic
  @Column(name = "ProjectHead", nullable = false, length = 50)
  public String getProjectHead() {
    return projectHead;
  }

  public void setProjectHead(String projectHead) {
    this.projectHead = projectHead;
  }

  @Basic
  @Column(name = "PHTel", nullable = false, length = 50)
  public String getPhTel() {
    return phTel;
  }

  public void setPhTel(String phTel) {
    this.phTel = phTel;
  }

  @Basic
  @Column(name = "ReportHead", nullable = false, length = 50)
  public String getReportHead() {
    return reportHead;
  }

  public void setReportHead(String reportHead) {
    this.reportHead = reportHead;
  }

  @Basic
  @Column(name = "RHTel", nullable = false, length = 50)
  public String getRhTel() {
    return rhTel;
  }

  public void setRhTel(String rhTel) {
    this.rhTel = rhTel;
  }

  @Basic
  @Column(name = "RHQQ", nullable = true, length = 50)
  public String getRhqq() {
    return rhqq;
  }

  public void setRhqq(String rhqq) {
    this.rhqq = rhqq;
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
  @Column(name = "SubmitTime", nullable = true)
  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
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
  @Column(name = "UIStatus", nullable = false)
  public int getUiStatus() {
    return uiStatus;
  }

  public void setUiStatus(int uiStatus) {
    this.uiStatus = uiStatus;
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
  @Column(name = "ProjectNo", nullable = false, length = 100)
  public String getProjectNo() {
    return projectNo;
  }

  public void setProjectNo(String projectNo) {
    this.projectNo = projectNo;
  }

  @Basic
  @Column(name = "MajorName", nullable = false, length = 100)
  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  @Basic
  @Column(name = "CreateYear", nullable = false)
  public int getCreateYear() {
    return createYear;
  }

  public void setCreateYear(int createYear) {
    this.createYear = createYear;
  }

  @Basic
  @Column(name = "School2", nullable = true, length = -1)
  public String getSchool2() {
    return school2;
  }

  public void setSchool2(String school2) {
    this.school2 = school2;
  }

  @Basic
  @Column(name = "School3", nullable = true, length = -1)
  public String getSchool3() {
    return school3;
  }

  public void setSchool3(String school3) {
    this.school3 = school3;
  }

  @Basic
  @Column(name = "SmallNo", nullable = true)
  public Integer getSmallNo() {
    return smallNo;
  }

  public void setSmallNo(Integer smallNo) {
    this.smallNo = smallNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Project project = (Project) o;

    if (id != project.id) return false;
    if (isDelete != project.isDelete) return false;
    if (uiStatus != project.uiStatus) return false;
    if (createYear != project.createYear) return false;
    if (schoolName != null ? !schoolName.equals(project.schoolName) : project.schoolName != null) return false;
    if (schoolHead != null ? !schoolHead.equals(project.schoolHead) : project.schoolHead != null) return false;
    if (financeHead != null ? !financeHead.equals(project.financeHead) : project.financeHead != null) return false;
    if (fhTel != null ? !fhTel.equals(project.fhTel) : project.fhTel != null) return false;
    if (fhqq != null ? !fhqq.equals(project.fhqq) : project.fhqq != null) return false;
    if (projectHead != null ? !projectHead.equals(project.projectHead) : project.projectHead != null) return false;
    if (phTel != null ? !phTel.equals(project.phTel) : project.phTel != null) return false;
    if (reportHead != null ? !reportHead.equals(project.reportHead) : project.reportHead != null) return false;
    if (rhTel != null ? !rhTel.equals(project.rhTel) : project.rhTel != null) return false;
    if (rhqq != null ? !rhqq.equals(project.rhqq) : project.rhqq != null) return false;
    if (note != null ? !note.equals(project.note) : project.note != null) return false;
    if (submitTime != null ? !submitTime.equals(project.submitTime) : project.submitTime != null) return false;
    if (reserve1 != null ? !reserve1.equals(project.reserve1) : project.reserve1 != null) return false;
    if (reserve2 != null ? !reserve2.equals(project.reserve2) : project.reserve2 != null) return false;
    if (reserve3 != null ? !reserve3.equals(project.reserve3) : project.reserve3 != null) return false;
    if (projectNo != null ? !projectNo.equals(project.projectNo) : project.projectNo != null) return false;
    if (majorName != null ? !majorName.equals(project.majorName) : project.majorName != null) return false;
    if (school2 != null ? !school2.equals(project.school2) : project.school2 != null) return false;
    if (school3 != null ? !school3.equals(project.school3) : project.school3 != null) return false;
    if (smallNo != null ? !smallNo.equals(project.smallNo) : project.smallNo != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
    result = 31 * result + (schoolHead != null ? schoolHead.hashCode() : 0);
    result = 31 * result + (financeHead != null ? financeHead.hashCode() : 0);
    result = 31 * result + (fhTel != null ? fhTel.hashCode() : 0);
    result = 31 * result + (fhqq != null ? fhqq.hashCode() : 0);
    result = 31 * result + (projectHead != null ? projectHead.hashCode() : 0);
    result = 31 * result + (phTel != null ? phTel.hashCode() : 0);
    result = 31 * result + (reportHead != null ? reportHead.hashCode() : 0);
    result = 31 * result + (rhTel != null ? rhTel.hashCode() : 0);
    result = 31 * result + (rhqq != null ? rhqq.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
    result = 31 * result + isDelete;
    result = 31 * result + uiStatus;
    result = 31 * result + (reserve1 != null ? reserve1.hashCode() : 0);
    result = 31 * result + (reserve2 != null ? reserve2.hashCode() : 0);
    result = 31 * result + (reserve3 != null ? reserve3.hashCode() : 0);
    result = 31 * result + (projectNo != null ? projectNo.hashCode() : 0);
    result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
    result = 31 * result + createYear;
    result = 31 * result + (school2 != null ? school2.hashCode() : 0);
    result = 31 * result + (school3 != null ? school3.hashCode() : 0);
    result = 31 * result + (smallNo != null ? smallNo.hashCode() : 0);
    return result;
  }
}
