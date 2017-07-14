package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-6-22.
 */
public class ProjectBudgetView extends FileImportLog {
  String projectId;
  /**
   * 项目编号
   */
  String projectNo;
  /**
   * 项目名称
   */
  String majorName;
  /**
   * 院校
   */
  String schoolName;
  /**
   * 项目预算  行企
   */
  BigDecimal budgetEnterprise;
  /**
   * 项目预算 地方财政
   */
  BigDecimal budgetLocal;
  /**
   * 项目预算  自筹
   */
  BigDecimal budgetUniversity;
  /**
   * 项目预算 部本专项
   */
  BigDecimal budgetCountry;
  /**
   * 项目预算 总额
   */
  BigDecimal budgetTotal;
  /**
   * 行企 到位率
   */
  String  enterpriseInPercent;
  /**
   * 行企 支出率
   */
  String enterpriseOutPercent;
  /**
   * 行企 收入
   */
  BigDecimal fundsInEnterprise;
  /**
   * 地方 收入
   */
  BigDecimal  fundsInLocal;
  /**
   *自筹 收入
   */
  BigDecimal fundsInUniversity;
  /**
   * 部本专项 收入
   */
  BigDecimal  fundsInCountry;
  /**
   * 收入总额
   */
  BigDecimal  fundsInTotal;
  /**
   * 行企 支出
   */
  BigDecimal fundsOutEnterprise;
  /**
   * 地方财政 支出
   */
  BigDecimal fundsOutLocal;
  /**
   * 自筹 支出
   */
  BigDecimal fundsOutUniversity;
  /**
   * 部本专项 支出
   */
  BigDecimal fundsOutCountry;
  /**
   * 支出总计
   */
  BigDecimal fundsOutTotal;
  /**
   * 地方 收入到位率
   */
  String  localInPercent;
  /**
   * 地方支出到位率
   */
  String  localOutPercent;
  /**
   * 自筹 收入到位率
   */
  String universityInPercent;
  /**
   * 自筹 支出到位率
   */
  String universityOutPercent;
  /**
   * 部本专项 收入 到位率
   */
  String countryInPercent;
  /**
   * 部本专项 支出 到位率
   */
  String countryOutPercent;
  /**
   * 收入 总计到位率
   */
  String  totalInPercent;
  /**
   * 支出率
   */
  String totalOutPercent;

  @Override
  public String getProjectId() {
    return projectId;
  }

  @Override
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getProjectNo() {
    return projectNo;
  }

  public void setProjectNo(String projectNo) {
    this.projectNo = projectNo;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public BigDecimal getBudgetEnterprise() {
    return budgetEnterprise;
  }

  public void setBudgetEnterprise(BigDecimal budgetEnterprise) {
    this.budgetEnterprise = budgetEnterprise;
  }

  public BigDecimal getBudgetLocal() {
    return budgetLocal;
  }

  public void setBudgetLocal(BigDecimal budgetLocal) {
    this.budgetLocal = budgetLocal;
  }

  public BigDecimal getBudgetUniversity() {
    return budgetUniversity;
  }

  public void setBudgetUniversity(BigDecimal budgetUniversity) {
    this.budgetUniversity = budgetUniversity;
  }

  public BigDecimal getBudgetCountry() {
    return budgetCountry;
  }

  public void setBudgetCountry(BigDecimal budgetCountry) {
    this.budgetCountry = budgetCountry;
  }

  public BigDecimal getBudgetTotal() {
    return budgetTotal;
  }

  public void setBudgetTotal(BigDecimal budgetTotal) {
    this.budgetTotal = budgetTotal;
  }

  public String getEnterpriseInPercent() {
    return enterpriseInPercent;
  }

  public void setEnterpriseInPercent(String enterpriseInPercent) {
    this.enterpriseInPercent = enterpriseInPercent;
  }

  public String getEnterpriseOutPercent() {
    return enterpriseOutPercent;
  }

  public void setEnterpriseOutPercent(String enterpriseOutPercent) {
    this.enterpriseOutPercent = enterpriseOutPercent;
  }

  public BigDecimal getFundsInEnterprise() {
    return fundsInEnterprise;
  }

  public void setFundsInEnterprise(BigDecimal fundsInEnterprise) {
    this.fundsInEnterprise = fundsInEnterprise;
  }

  public BigDecimal getFundsInLocal() {
    return fundsInLocal;
  }

  public void setFundsInLocal(BigDecimal fundsInLocal) {
    this.fundsInLocal = fundsInLocal;
  }

  public BigDecimal getFundsInUniversity() {
    return fundsInUniversity;
  }

  public void setFundsInUniversity(BigDecimal fundsInUniversity) {
    this.fundsInUniversity = fundsInUniversity;
  }

  public BigDecimal getFundsInCountry() {
    return fundsInCountry;
  }

  public void setFundsInCountry(BigDecimal fundsInCountry) {
    this.fundsInCountry = fundsInCountry;
  }

  public BigDecimal getFundsInTotal() {
    return fundsInTotal;
  }

  public void setFundsInTotal(BigDecimal fundsInTotal) {
    this.fundsInTotal = fundsInTotal;
  }

  public BigDecimal getFundsOutEnterprise() {
    return fundsOutEnterprise;
  }

  public void setFundsOutEnterprise(BigDecimal fundsOutEnterprise) {
    this.fundsOutEnterprise = fundsOutEnterprise;
  }

  public BigDecimal getFundsOutLocal() {
    return fundsOutLocal;
  }

  public void setFundsOutLocal(BigDecimal fundsOutLocal) {
    this.fundsOutLocal = fundsOutLocal;
  }

  public BigDecimal getFundsOutUniversity() {
    return fundsOutUniversity;
  }

  public void setFundsOutUniversity(BigDecimal fundsOutUniversity) {
    this.fundsOutUniversity = fundsOutUniversity;
  }

  public BigDecimal getFundsOutCountry() {
    return fundsOutCountry;
  }

  public void setFundsOutCountry(BigDecimal fundsOutCountry) {
    this.fundsOutCountry = fundsOutCountry;
  }

  public BigDecimal getFundsOutTotal() {
    return fundsOutTotal;
  }

  public void setFundsOutTotal(BigDecimal fundsOutTotal) {
    this.fundsOutTotal = fundsOutTotal;
  }

  public String getLocalInPercent() {
    return localInPercent;
  }

  public void setLocalInPercent(String localInPercent) {
    this.localInPercent = localInPercent;
  }

  public String getLocalOutPercent() {
    return localOutPercent;
  }

  public void setLocalOutPercent(String localOutPercent) {
    this.localOutPercent = localOutPercent;
  }


  public String getCountryInPercent() {
    return countryInPercent;
  }

  public void setCountryInPercent(String countryInPercent) {
    this.countryInPercent = countryInPercent;
  }

  public String getCountryOutPercent() {
    return countryOutPercent;
  }

  public String getUniversityInPercent() {
    return universityInPercent;
  }

  public void setUniversityInPercent(String universityInPercent) {
    this.universityInPercent = universityInPercent;
  }

  public String getUniversityOutPercent() {
    return universityOutPercent;
  }

  public void setUniversityOutPercent(String universityOutPercent) {
    this.universityOutPercent = universityOutPercent;
  }

  public void setCountryOutPercent(String countryOutPercent) {
    this.countryOutPercent = countryOutPercent;
  }

  public String getTotalInPercent() {
    return totalInPercent;
  }

  public void setTotalInPercent(String totalInPercent) {
    this.totalInPercent = totalInPercent;
  }

  public String getTotalOutPercent() {
    return totalOutPercent;
  }

  public void setTotalOutPercent(String totalOutPercent) {
    this.totalOutPercent = totalOutPercent;
  }
}
