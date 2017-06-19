package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Budgetimportdetailold {
  private String id;
  private String sequenceNo;
  private String usedFor;
  private String totalMoney;
  private String countryTotal;
  private String countryPercent;
  private String countryYear1;
  private String countryYear2;
  private String projectTotal;
  private String projectPercent;
  private String localYear1;
  private String localYear2;
  private String localYear3;
  private String enterpriseYear1;
  private String enterpriseYear2;
  private String enterpriseYear3;
  private String universityYear1;
  private String universityYear2;
  private String universityYear3;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "SequenceNo", nullable = true, length = 10)
  public String getSequenceNo() {
    return sequenceNo;
  }

  public void setSequenceNo(String sequenceNo) {
    this.sequenceNo = sequenceNo;
  }

  @Basic
  @Column(name = "UsedFor", nullable = true, length = 50)
  public String getUsedFor() {
    return usedFor;
  }

  public void setUsedFor(String usedFor) {
    this.usedFor = usedFor;
  }

  @Basic
  @Column(name = "TotalMoney", nullable = true, length = 50)
  public String getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(String totalMoney) {
    this.totalMoney = totalMoney;
  }

  @Basic
  @Column(name = "CountryTotal", nullable = true, length = 50)
  public String getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(String countryTotal) {
    this.countryTotal = countryTotal;
  }

  @Basic
  @Column(name = "CountryPercent", nullable = true, length = 50)
  public String getCountryPercent() {
    return countryPercent;
  }

  public void setCountryPercent(String countryPercent) {
    this.countryPercent = countryPercent;
  }

  @Basic
  @Column(name = "CountryYear1", nullable = true, length = 50)
  public String getCountryYear1() {
    return countryYear1;
  }

  public void setCountryYear1(String countryYear1) {
    this.countryYear1 = countryYear1;
  }

  @Basic
  @Column(name = "CountryYear2", nullable = true, length = 50)
  public String getCountryYear2() {
    return countryYear2;
  }

  public void setCountryYear2(String countryYear2) {
    this.countryYear2 = countryYear2;
  }

  @Basic
  @Column(name = "ProjectTotal", nullable = true, length = 50)
  public String getProjectTotal() {
    return projectTotal;
  }

  public void setProjectTotal(String projectTotal) {
    this.projectTotal = projectTotal;
  }

  @Basic
  @Column(name = "ProjectPercent", nullable = true, length = 50)
  public String getProjectPercent() {
    return projectPercent;
  }

  public void setProjectPercent(String projectPercent) {
    this.projectPercent = projectPercent;
  }

  @Basic
  @Column(name = "LocalYear1", nullable = true, length = 50)
  public String getLocalYear1() {
    return localYear1;
  }

  public void setLocalYear1(String localYear1) {
    this.localYear1 = localYear1;
  }

  @Basic
  @Column(name = "LocalYear2", nullable = true, length = 50)
  public String getLocalYear2() {
    return localYear2;
  }

  public void setLocalYear2(String localYear2) {
    this.localYear2 = localYear2;
  }

  @Basic
  @Column(name = "LocalYear3", nullable = true, length = 50)
  public String getLocalYear3() {
    return localYear3;
  }

  public void setLocalYear3(String localYear3) {
    this.localYear3 = localYear3;
  }

  @Basic
  @Column(name = "EnterpriseYear1", nullable = true, length = 50)
  public String getEnterpriseYear1() {
    return enterpriseYear1;
  }

  public void setEnterpriseYear1(String enterpriseYear1) {
    this.enterpriseYear1 = enterpriseYear1;
  }

  @Basic
  @Column(name = "EnterpriseYear2", nullable = true, length = 50)
  public String getEnterpriseYear2() {
    return enterpriseYear2;
  }

  public void setEnterpriseYear2(String enterpriseYear2) {
    this.enterpriseYear2 = enterpriseYear2;
  }

  @Basic
  @Column(name = "EnterpriseYear3", nullable = true, length = 50)
  public String getEnterpriseYear3() {
    return enterpriseYear3;
  }

  public void setEnterpriseYear3(String enterpriseYear3) {
    this.enterpriseYear3 = enterpriseYear3;
  }

  @Basic
  @Column(name = "UniversityYear1", nullable = true, length = 50)
  public String getUniversityYear1() {
    return universityYear1;
  }

  public void setUniversityYear1(String universityYear1) {
    this.universityYear1 = universityYear1;
  }

  @Basic
  @Column(name = "UniversityYear2", nullable = true, length = 50)
  public String getUniversityYear2() {
    return universityYear2;
  }

  public void setUniversityYear2(String universityYear2) {
    this.universityYear2 = universityYear2;
  }

  @Basic
  @Column(name = "UniversityYear3", nullable = true, length = 50)
  public String getUniversityYear3() {
    return universityYear3;
  }

  public void setUniversityYear3(String universityYear3) {
    this.universityYear3 = universityYear3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Budgetimportdetailold that = (Budgetimportdetailold) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (sequenceNo != null ? !sequenceNo.equals(that.sequenceNo) : that.sequenceNo != null) return false;
    if (usedFor != null ? !usedFor.equals(that.usedFor) : that.usedFor != null) return false;
    if (totalMoney != null ? !totalMoney.equals(that.totalMoney) : that.totalMoney != null) return false;
    if (countryTotal != null ? !countryTotal.equals(that.countryTotal) : that.countryTotal != null) return false;
    if (countryPercent != null ? !countryPercent.equals(that.countryPercent) : that.countryPercent != null)
      return false;
    if (countryYear1 != null ? !countryYear1.equals(that.countryYear1) : that.countryYear1 != null) return false;
    if (countryYear2 != null ? !countryYear2.equals(that.countryYear2) : that.countryYear2 != null) return false;
    if (projectTotal != null ? !projectTotal.equals(that.projectTotal) : that.projectTotal != null) return false;
    if (projectPercent != null ? !projectPercent.equals(that.projectPercent) : that.projectPercent != null)
      return false;
    if (localYear1 != null ? !localYear1.equals(that.localYear1) : that.localYear1 != null) return false;
    if (localYear2 != null ? !localYear2.equals(that.localYear2) : that.localYear2 != null) return false;
    if (localYear3 != null ? !localYear3.equals(that.localYear3) : that.localYear3 != null) return false;
    if (enterpriseYear1 != null ? !enterpriseYear1.equals(that.enterpriseYear1) : that.enterpriseYear1 != null)
      return false;
    if (enterpriseYear2 != null ? !enterpriseYear2.equals(that.enterpriseYear2) : that.enterpriseYear2 != null)
      return false;
    if (enterpriseYear3 != null ? !enterpriseYear3.equals(that.enterpriseYear3) : that.enterpriseYear3 != null)
      return false;
    if (universityYear1 != null ? !universityYear1.equals(that.universityYear1) : that.universityYear1 != null)
      return false;
    if (universityYear2 != null ? !universityYear2.equals(that.universityYear2) : that.universityYear2 != null)
      return false;
    if (universityYear3 != null ? !universityYear3.equals(that.universityYear3) : that.universityYear3 != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (sequenceNo != null ? sequenceNo.hashCode() : 0);
    result = 31 * result + (usedFor != null ? usedFor.hashCode() : 0);
    result = 31 * result + (totalMoney != null ? totalMoney.hashCode() : 0);
    result = 31 * result + (countryTotal != null ? countryTotal.hashCode() : 0);
    result = 31 * result + (countryPercent != null ? countryPercent.hashCode() : 0);
    result = 31 * result + (countryYear1 != null ? countryYear1.hashCode() : 0);
    result = 31 * result + (countryYear2 != null ? countryYear2.hashCode() : 0);
    result = 31 * result + (projectTotal != null ? projectTotal.hashCode() : 0);
    result = 31 * result + (projectPercent != null ? projectPercent.hashCode() : 0);
    result = 31 * result + (localYear1 != null ? localYear1.hashCode() : 0);
    result = 31 * result + (localYear2 != null ? localYear2.hashCode() : 0);
    result = 31 * result + (localYear3 != null ? localYear3.hashCode() : 0);
    result = 31 * result + (enterpriseYear1 != null ? enterpriseYear1.hashCode() : 0);
    result = 31 * result + (enterpriseYear2 != null ? enterpriseYear2.hashCode() : 0);
    result = 31 * result + (enterpriseYear3 != null ? enterpriseYear3.hashCode() : 0);
    result = 31 * result + (universityYear1 != null ? universityYear1.hashCode() : 0);
    result = 31 * result + (universityYear2 != null ? universityYear2.hashCode() : 0);
    result = 31 * result + (universityYear3 != null ? universityYear3.hashCode() : 0);
    return result;
  }
}
