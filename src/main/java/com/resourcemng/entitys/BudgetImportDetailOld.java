package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "budget_import_detail_old", schema = "budget_resource", catalog = "")
public class BudgetImportDetailOld {
  private String id;
  private String sequenceNo;
  private String useFor;
  private String totalMoney;
  private String countryTotal;
  private String countryPercent;
  private String countryFirstYear;
  private String countrySecondYear;
  private String projectTotal;
  private String projectPercent;
  private String localFirstYear;
  private String localSecondYear;
  private String localThreeYear;
  private String enterpriseFirstYear;
  private String enterpriseSecondYear;
  private String enterpriseThreeYear;
  private String universityFirstYear;
  private String universitySecondYear;
  private String universityThreeYear;
  private String fileImportId;

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
  @Column(name = "SEQUENCE_NO", nullable = true, length = 10)
  public String getSequenceNo() {
    return sequenceNo;
  }

  public void setSequenceNo(String sequenceNo) {
    this.sequenceNo = sequenceNo;
  }

  @Basic
  @Column(name = "USE_FOR", nullable = true, length = 50)
  public String getUseFor() {
    return useFor;
  }

  public void setUseFor(String useFor) {
    this.useFor = useFor;
  }

  @Basic
  @Column(name = "TOTAL_MONEY", nullable = true, length = 50)
  public String getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(String totalMoney) {
    this.totalMoney = totalMoney;
  }

  @Basic
  @Column(name = "COUNTRY_TOTAL", nullable = true, length = 50)
  public String getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(String countryTotal) {
    this.countryTotal = countryTotal;
  }

  @Basic
  @Column(name = "COUNTRY_PERCENT", nullable = true, length = 50)
  public String getCountryPercent() {
    return countryPercent;
  }

  public void setCountryPercent(String countryPercent) {
    this.countryPercent = countryPercent;
  }

  @Basic
  @Column(name = "COUNTRY_FIRST_YEAR", nullable = true, length = 50)
  public String getCountryFirstYear() {
    return countryFirstYear;
  }

  public void setCountryFirstYear(String countryFirstYear) {
    this.countryFirstYear = countryFirstYear;
  }

  @Basic
  @Column(name = "COUNTRY_SECOND_YEAR", nullable = true, length = 50)
  public String getCountrySecondYear() {
    return countrySecondYear;
  }

  public void setCountrySecondYear(String countrySecondYear) {
    this.countrySecondYear = countrySecondYear;
  }

  @Basic
  @Column(name = "PROJECT_TOTAL", nullable = true, length = 50)
  public String getProjectTotal() {
    return projectTotal;
  }

  public void setProjectTotal(String projectTotal) {
    this.projectTotal = projectTotal;
  }

  @Basic
  @Column(name = "PROJECT_PERCENT", nullable = true, length = 50)
  public String getProjectPercent() {
    return projectPercent;
  }

  public void setProjectPercent(String projectPercent) {
    this.projectPercent = projectPercent;
  }

  @Basic
  @Column(name = "LOCAL_FIRST_YEAR", nullable = true, length = 50)
  public String getLocalFirstYear() {
    return localFirstYear;
  }

  public void setLocalFirstYear(String localFirstYear) {
    this.localFirstYear = localFirstYear;
  }

  @Basic
  @Column(name = "LOCAL_SECOND_YEAR", nullable = true, length = 50)
  public String getLocalSecondYear() {
    return localSecondYear;
  }

  public void setLocalSecondYear(String localSecondYear) {
    this.localSecondYear = localSecondYear;
  }

  @Basic
  @Column(name = "LOCAL_THREE_YEAR", nullable = true, length = 50)
  public String getLocalThreeYear() {
    return localThreeYear;
  }

  public void setLocalThreeYear(String localThreeYear) {
    this.localThreeYear = localThreeYear;
  }

  @Basic
  @Column(name = "ENTERPRISE_FIRST_YEAR", nullable = true, length = 50)
  public String getEnterpriseFirstYear() {
    return enterpriseFirstYear;
  }

  public void setEnterpriseFirstYear(String enterpriseFirstYear) {
    this.enterpriseFirstYear = enterpriseFirstYear;
  }

  @Basic
  @Column(name = "ENTERPRISE_SECOND_YEAR", nullable = true, length = 50)
  public String getEnterpriseSecondYear() {
    return enterpriseSecondYear;
  }

  public void setEnterpriseSecondYear(String enterpriseSecondYear) {
    this.enterpriseSecondYear = enterpriseSecondYear;
  }

  @Basic
  @Column(name = "ENTERPRISE_THREE_YEAR", nullable = true, length = 50)
  public String getEnterpriseThreeYear() {
    return enterpriseThreeYear;
  }

  public void setEnterpriseThreeYear(String enterpriseThreeYear) {
    this.enterpriseThreeYear = enterpriseThreeYear;
  }

  @Basic
  @Column(name = "UNIVERSITY_FIRST_YEAR", nullable = true, length = 50)
  public String getUniversityFirstYear() {
    return universityFirstYear;
  }

  public void setUniversityFirstYear(String universityFirstYear) {
    this.universityFirstYear = universityFirstYear;
  }

  @Basic
  @Column(name = "UNIVERSITY_SECOND_YEAR", nullable = true, length = 50)
  public String getUniversitySecondYear() {
    return universitySecondYear;
  }

  public void setUniversitySecondYear(String universitySecondYear) {
    this.universitySecondYear = universitySecondYear;
  }

  @Basic
  @Column(name = "UNIVERSITY_THREE_YEAR", nullable = true, length = 50)
  public String getUniversityThreeYear() {
    return universityThreeYear;
  }

  public void setUniversityThreeYear(String universityThreeYear) {
    this.universityThreeYear = universityThreeYear;
  }

  @Basic
  @Column(name = "FILE_IMPORT_ID", nullable = true, length = 50)
  public String getFileImportId() {
    return fileImportId;
  }

  public void setFileImportId(String fileImportId) {
    this.fileImportId = fileImportId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BudgetImportDetailOld that = (BudgetImportDetailOld) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (sequenceNo != null ? !sequenceNo.equals(that.sequenceNo) : that.sequenceNo != null) return false;
    if (useFor != null ? !useFor.equals(that.useFor) : that.useFor != null) return false;
    if (totalMoney != null ? !totalMoney.equals(that.totalMoney) : that.totalMoney != null) return false;
    if (countryTotal != null ? !countryTotal.equals(that.countryTotal) : that.countryTotal != null) return false;
    if (countryPercent != null ? !countryPercent.equals(that.countryPercent) : that.countryPercent != null)
      return false;
    if (countryFirstYear != null ? !countryFirstYear.equals(that.countryFirstYear) : that.countryFirstYear != null)
      return false;
    if (countrySecondYear != null ? !countrySecondYear.equals(that.countrySecondYear) : that.countrySecondYear != null)
      return false;
    if (projectTotal != null ? !projectTotal.equals(that.projectTotal) : that.projectTotal != null) return false;
    if (projectPercent != null ? !projectPercent.equals(that.projectPercent) : that.projectPercent != null)
      return false;
    if (localFirstYear != null ? !localFirstYear.equals(that.localFirstYear) : that.localFirstYear != null)
      return false;
    if (localSecondYear != null ? !localSecondYear.equals(that.localSecondYear) : that.localSecondYear != null)
      return false;
    if (localThreeYear != null ? !localThreeYear.equals(that.localThreeYear) : that.localThreeYear != null)
      return false;
    if (enterpriseFirstYear != null ? !enterpriseFirstYear.equals(that.enterpriseFirstYear) : that.enterpriseFirstYear != null)
      return false;
    if (enterpriseSecondYear != null ? !enterpriseSecondYear.equals(that.enterpriseSecondYear) : that.enterpriseSecondYear != null)
      return false;
    if (enterpriseThreeYear != null ? !enterpriseThreeYear.equals(that.enterpriseThreeYear) : that.enterpriseThreeYear != null)
      return false;
    if (universityFirstYear != null ? !universityFirstYear.equals(that.universityFirstYear) : that.universityFirstYear != null)
      return false;
    if (universitySecondYear != null ? !universitySecondYear.equals(that.universitySecondYear) : that.universitySecondYear != null)
      return false;
    if (universityThreeYear != null ? !universityThreeYear.equals(that.universityThreeYear) : that.universityThreeYear != null)
      return false;
    if (fileImportId != null ? !fileImportId.equals(that.fileImportId) : that.fileImportId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (sequenceNo != null ? sequenceNo.hashCode() : 0);
    result = 31 * result + (useFor != null ? useFor.hashCode() : 0);
    result = 31 * result + (totalMoney != null ? totalMoney.hashCode() : 0);
    result = 31 * result + (countryTotal != null ? countryTotal.hashCode() : 0);
    result = 31 * result + (countryPercent != null ? countryPercent.hashCode() : 0);
    result = 31 * result + (countryFirstYear != null ? countryFirstYear.hashCode() : 0);
    result = 31 * result + (countrySecondYear != null ? countrySecondYear.hashCode() : 0);
    result = 31 * result + (projectTotal != null ? projectTotal.hashCode() : 0);
    result = 31 * result + (projectPercent != null ? projectPercent.hashCode() : 0);
    result = 31 * result + (localFirstYear != null ? localFirstYear.hashCode() : 0);
    result = 31 * result + (localSecondYear != null ? localSecondYear.hashCode() : 0);
    result = 31 * result + (localThreeYear != null ? localThreeYear.hashCode() : 0);
    result = 31 * result + (enterpriseFirstYear != null ? enterpriseFirstYear.hashCode() : 0);
    result = 31 * result + (enterpriseSecondYear != null ? enterpriseSecondYear.hashCode() : 0);
    result = 31 * result + (enterpriseThreeYear != null ? enterpriseThreeYear.hashCode() : 0);
    result = 31 * result + (universityFirstYear != null ? universityFirstYear.hashCode() : 0);
    result = 31 * result + (universitySecondYear != null ? universitySecondYear.hashCode() : 0);
    result = 31 * result + (universityThreeYear != null ? universityThreeYear.hashCode() : 0);
    result = 31 * result + (fileImportId != null ? fileImportId.hashCode() : 0);
    return result;
  }
}
