package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.math.BigDecimal;

/**
 * 项目执行结果下载用视图，季报收入视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectFundInInfoView extends FileImportLog {
  private BigDecimal total;
  private BigDecimal precent;
  private BigDecimal countryTotal;
  private BigDecimal countryPrecent;
  private BigDecimal local;
  private BigDecimal localPrecent;
  private BigDecimal enterprise;
  private BigDecimal enterprisePrecent;
  private BigDecimal university;
  private BigDecimal universityPrecent;

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getPrecent() {
    return precent;
  }

  public void setPrecent(BigDecimal precent) {
    this.precent = precent;
  }

  public BigDecimal getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(BigDecimal countryTotal) {
    this.countryTotal = countryTotal;
  }

  public BigDecimal getCountryPrecent() {
    return countryPrecent;
  }

  public void setCountryPrecent(BigDecimal countryPrecent) {
    this.countryPrecent = countryPrecent;
  }

  public BigDecimal getLocal() {
    return local;
  }

  public void setLocal(BigDecimal local) {
    this.local = local;
  }

  public BigDecimal getLocalPrecent() {
    return localPrecent;
  }

  public void setLocalPrecent(BigDecimal localPrecent) {
    this.localPrecent = localPrecent;
  }

  public BigDecimal getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(BigDecimal enterprise) {
    this.enterprise = enterprise;
  }

  public BigDecimal getEnterprisePrecent() {
    return enterprisePrecent;
  }

  public void setEnterprisePrecent(BigDecimal enterprisePrecent) {
    this.enterprisePrecent = enterprisePrecent;
  }

  public BigDecimal getUniversity() {
    return university;
  }

  public void setUniversity(BigDecimal university) {
    this.university = university;
  }

  public BigDecimal getUniversityPrecent() {
    return universityPrecent;
  }

  public void setUniversityPrecent(BigDecimal universityPrecent) {
    this.universityPrecent = universityPrecent;
  }
}
