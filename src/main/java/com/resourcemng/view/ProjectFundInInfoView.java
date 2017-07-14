package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.math.BigDecimal;


/**
 * 项目执行结果下载用视图，季报收入视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectFundInInfoView extends FileImportLog {
  private BigDecimal total;
  private String precent;
  private BigDecimal countryTotal;
  private String countryPrecent;
  private BigDecimal local;
  private String localPrecent;
  private BigDecimal enterprise;
  private String enterprisePrecent;
  private BigDecimal university;
  private String universityPrecent;

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public String getPrecent() {
    return precent;
  }

  public void setPrecent(String precent) {
    this.precent = precent;
  }

  public BigDecimal getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(BigDecimal countryTotal) {
    this.countryTotal = countryTotal;
  }


  public BigDecimal getLocal() {
    return local;
  }

  public void setLocal(BigDecimal local) {
    this.local = local;
  }


  public BigDecimal getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(BigDecimal enterprise) {
    this.enterprise = enterprise;
  }


  public BigDecimal getUniversity() {
    return university;
  }

  public void setUniversity(BigDecimal university) {
    this.university = university;
  }

  public String getCountryPrecent() {
    return countryPrecent;
  }

  public void setCountryPrecent(String countryPrecent) {
    this.countryPrecent = countryPrecent;
  }

  public String getLocalPrecent() {
    return localPrecent;
  }

  public void setLocalPrecent(String localPrecent) {
    this.localPrecent = localPrecent;
  }

  public String getEnterprisePrecent() {
    return enterprisePrecent;
  }

  public void setEnterprisePrecent(String enterprisePrecent) {
    this.enterprisePrecent = enterprisePrecent;
  }

  public String getUniversityPrecent() {
    return universityPrecent;
  }

  public void setUniversityPrecent(String universityPrecent) {
    this.universityPrecent = universityPrecent;
  }
}
