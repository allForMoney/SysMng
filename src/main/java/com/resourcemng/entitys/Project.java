package com.resourcemng.entitys;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.resourcemng.Enum.IsDelete;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
public class Project implements Serializable{
  private String id;
  @Excel(name="学校名称")
  private String schoolName;
  @Excel(name="法定代表人")
  private String schoolHead;
@Excel(name="财务部门负责人")
  private String finaceHeader;
  @Excel(name="财务部门负责人电话")
  private String finaceHeaderTel;
  @Excel(name="财务负责人QQ")
  private String finaceHeaderQq;
  @Excel(name="项目负责人")
  private String projectHeader;
  @Excel(name="项目负责人电话")
  private String projectHeaderTel;
  @Excel(name="填报人")
  private String reporter;
  @Excel(name="填报人电话")
  private String reporterTel;
  @Excel(name="填报人QQ")
  private String reporterQq;
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
  private String unionSchool;
  @Excel(name="参与建设单位")
  private String partnerSchool;
  @Excel(name="省份")
  private String provenceId;
  private String importUserId;

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
  @Column(name = "SCHOOL_NAME", nullable = false, length = 200)
  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  @Basic
  @Column(name = "SCHOOL_HEAD", nullable = false, length = 50)
  public String getSchoolHead() {
    return schoolHead;
  }

  public void setSchoolHead(String schoolHead) {
    this.schoolHead = schoolHead;
  }

  @Basic
  @Column(name = "FINACE_HEADER", nullable = false, length = 50)
  public String getFinaceHeader() {
    return finaceHeader;
  }

  public void setFinaceHeader(String finaceHeader) {
    this.finaceHeader = finaceHeader;
  }

  @Basic
  @Column(name = "FINACE_HEADER_TEL", nullable = false, length = 50)
  public String getFinaceHeaderTel() {
    return finaceHeaderTel;
  }

  public void setFinaceHeaderTel(String finaceHeaderTel) {
    this.finaceHeaderTel = finaceHeaderTel;
  }

  @Basic
  @Column(name = "FINACE_HEADER_QQ", nullable = true, length = 50)
  public String getFinaceHeaderQq() {
    return finaceHeaderQq;
  }

  public void setFinaceHeaderQq(String finaceHeaderQq) {
    this.finaceHeaderQq = finaceHeaderQq;
  }

  @Basic
  @Column(name = "PROJECT_HEADER", nullable = false, length = 50)
  public String getProjectHeader() {
    return projectHeader;
  }

  public void setProjectHeader(String projectHeader) {
    this.projectHeader = projectHeader;
  }

  @Basic
  @Column(name = "PROJECT_HEADER_TEL", nullable = false, length = 50)
  public String getProjectHeaderTel() {
    return projectHeaderTel;
  }

  public void setProjectHeaderTel(String projectHeaderTel) {
    this.projectHeaderTel = projectHeaderTel;
  }

  @Basic
  @Column(name = "REPORTER", nullable = false, length = 50)
  public String getReporter() {
    return reporter;
  }

  public void setReporter(String reporter) {
    this.reporter = reporter;
  }

  @Basic
  @Column(name = "REPORTER_TEL", nullable = false, length = 50)
  public String getReporterTel() {
    return reporterTel;
  }

  public void setReporterTel(String reporterTel) {
    this.reporterTel = reporterTel;
  }

  @Basic
  @Column(name = "REPORTER_QQ", nullable = true, length = 50)
  public String getReporterQq() {
    return reporterQq;
  }

  public void setReporterQq(String reporterQq) {
    this.reporterQq = reporterQq;
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
  @Column(name = "SUBMIT_TIME", nullable = true)
  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
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
  @Column(name = "PROJECT_NO", nullable = false, length = 100)
  public String getProjectNo() {
    return projectNo;
  }

  public void setProjectNo(String projectNo) {
    this.projectNo = projectNo;
  }

  @Basic
  @Column(name = "MAJOR_NAME", nullable = false, length = 100)
  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  @Basic
  @Column(name = "CREATE_YEAR", nullable = false, length = 10)
  public String getCreateYear() {
    return createYear;
  }

  public void setCreateYear(String createYear) {
    this.createYear = createYear;
  }

  @Basic
  @Column(name = "UNION_SCHOOL", nullable = true, length = -1)
  public String getUnionSchool() {
    return unionSchool;
  }

  public void setUnionSchool(String unionSchool) {
    this.unionSchool = unionSchool;
  }

  @Basic
  @Column(name = "PARTNER_SCHOOL", nullable = true, length = -1)
  public String getPartnerSchool() {
    return partnerSchool;
  }

  public void setPartnerSchool(String partnerSchool) {
    this.partnerSchool = partnerSchool;
  }

  @Basic
  @Column(name = "IMPORT_USER_ID", nullable = true, length = 50)
  public String getImportUserId() {
    return importUserId;
  }

  public void setImportUserId(String importUserId) {
    this.importUserId = importUserId;
  }
  @Basic
  @Column(name = "PROVENCE_ID", nullable = true, length = -1)
  public String getProvenceId() {
    return provenceId;
  }

  public void setProvenceId(String provenceId) {
    this.provenceId = provenceId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Project project = (Project) o;

    if (id != null ? !id.equals(project.id) : project.id != null) return false;
    if (schoolName != null ? !schoolName.equals(project.schoolName) : project.schoolName != null) return false;
    if (schoolHead != null ? !schoolHead.equals(project.schoolHead) : project.schoolHead != null) return false;
    if (finaceHeader != null ? !finaceHeader.equals(project.finaceHeader) : project.finaceHeader != null) return false;
    if (finaceHeaderTel != null ? !finaceHeaderTel.equals(project.finaceHeaderTel) : project.finaceHeaderTel != null)
      return false;
    if (finaceHeaderQq != null ? !finaceHeaderQq.equals(project.finaceHeaderQq) : project.finaceHeaderQq != null)
      return false;
    if (projectHeader != null ? !projectHeader.equals(project.projectHeader) : project.projectHeader != null)
      return false;
    if (projectHeaderTel != null ? !projectHeaderTel.equals(project.projectHeaderTel) : project.projectHeaderTel != null)
      return false;
    if (reporter != null ? !reporter.equals(project.reporter) : project.reporter != null) return false;
    if (reporterTel != null ? !reporterTel.equals(project.reporterTel) : project.reporterTel != null) return false;
    if (reporterQq != null ? !reporterQq.equals(project.reporterQq) : project.reporterQq != null) return false;
    if (note != null ? !note.equals(project.note) : project.note != null) return false;
    if (submitTime != null ? !submitTime.equals(project.submitTime) : project.submitTime != null) return false;
    if (isDelete != null ? !isDelete.equals(project.isDelete) : project.isDelete != null) return false;
    if (projectNo != null ? !projectNo.equals(project.projectNo) : project.projectNo != null) return false;
    if (majorName != null ? !majorName.equals(project.majorName) : project.majorName != null) return false;
    if (createYear != null ? !createYear.equals(project.createYear) : project.createYear != null) return false;
    if (unionSchool != null ? !unionSchool.equals(project.unionSchool) : project.unionSchool != null) return false;
    if (partnerSchool != null ? !partnerSchool.equals(project.partnerSchool) : project.partnerSchool != null)
      return false;
    if (importUserId != null ? !importUserId.equals(project.importUserId) : project.importUserId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
    result = 31 * result + (schoolHead != null ? schoolHead.hashCode() : 0);
    result = 31 * result + (finaceHeader != null ? finaceHeader.hashCode() : 0);
    result = 31 * result + (finaceHeaderTel != null ? finaceHeaderTel.hashCode() : 0);
    result = 31 * result + (finaceHeaderQq != null ? finaceHeaderQq.hashCode() : 0);
    result = 31 * result + (projectHeader != null ? projectHeader.hashCode() : 0);
    result = 31 * result + (projectHeaderTel != null ? projectHeaderTel.hashCode() : 0);
    result = 31 * result + (reporter != null ? reporter.hashCode() : 0);
    result = 31 * result + (reporterTel != null ? reporterTel.hashCode() : 0);
    result = 31 * result + (reporterQq != null ? reporterQq.hashCode() : 0);
    result = 31 * result + (note != null ? note.hashCode() : 0);
    result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
    result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
    result = 31 * result + (projectNo != null ? projectNo.hashCode() : 0);
    result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
    result = 31 * result + (createYear != null ? createYear.hashCode() : 0);
    result = 31 * result + (unionSchool != null ? unionSchool.hashCode() : 0);
    result = 31 * result + (partnerSchool != null ? partnerSchool.hashCode() : 0);
    result = 31 * result + (importUserId != null ? importUserId.hashCode() : 0);
    return result;
  }
}
