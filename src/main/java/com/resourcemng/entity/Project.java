package com.resourcemng.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.resourcemng.Enum.IsDelete;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Project {
  private String id;
  @Excel(name="学校名称")
  private String schoolName;
  @Excel(name="法定代表人")
  private String schoolHead;
  @Excel(name="财务部门负责人")
  private String financeHead;
  @Excel(name="财务部门负责人电话")
  private String fhTel;
  @Excel(name="财务负责人QQ")
  private String fhqq;
  @Excel(name="项目负责人")
  private String projectHead;
  @Excel(name="项目负责人电话")
  private String phTel;
  @Excel(name="填报人")
  private String reportHead;
  @Excel(name="填报人电话")
  private String rhTel;
  @Excel(name="填报人QQ")
  private String rhqq;
  @Excel(name="备注")
  private String note;
  private Timestamp submitTime;
  private String isDelete = IsDelete.UN_DELETE;
  @Excel(name="项目编号")
  private String projectNo;
  @Excel(name="专业名称")
  private String majorName;
  @Excel(name="立项年度")
  private String createYear;
  @Excel(name="联合主持单位")
  private String school2;
  @Excel(name="参与建设单位")
  private String school3;

  private String userId;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
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
  @Column(name = "IsDelete", nullable = false, length = 1)
  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }


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
  @Column(name = "CreateYear", nullable = false, length = 10)
  public String getCreateYear() {
    return createYear;
  }

  public void setCreateYear(String createYear) {
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
  @Column(name = "UserID", nullable = true)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Project project = (Project) o;

    if (id != null ? !id.equals(project.id) : project.id != null) return false;
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
    if (isDelete != null ? !isDelete.equals(project.isDelete) : project.isDelete != null) return false;
    if (projectNo != null ? !projectNo.equals(project.projectNo) : project.projectNo != null) return false;
    if (majorName != null ? !majorName.equals(project.majorName) : project.majorName != null) return false;
    if (createYear != null ? !createYear.equals(project.createYear) : project.createYear != null) return false;
    if (school2 != null ? !school2.equals(project.school2) : project.school2 != null) return false;
    if (school3 != null ? !school3.equals(project.school3) : project.school3 != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
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
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (projectNo != null ? projectNo.hashCode() : 0);
    result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
    result = 31 * result + (createYear != null ? createYear.hashCode() : 0);
    result = 31 * result + (school2 != null ? school2.hashCode() : 0);
    result = 31 * result + (school3 != null ? school3.hashCode() : 0);
    return result;
  }
}
