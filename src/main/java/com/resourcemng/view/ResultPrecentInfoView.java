package com.resourcemng.view;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-6-23.
 */
public class ResultPrecentInfoView extends ResultInfoView {
  /**
   *支出率
   */
  private BigDecimal totalPay;
  private BigDecimal materialMakePay;
  private BigDecimal companyCasePay;
  private BigDecimal courseDevelopmentPay;
  private BigDecimal specialToolPay;
  private BigDecimal applicationPrometePay;
  private BigDecimal researchProvePay;
  private BigDecimal expertConsultPay;
  private BigDecimal otherFeePay;

  public BigDecimal getTotalPay() {
    return totalPay;
  }

  public void setTotalPay(BigDecimal totalPay) {
    this.totalPay = totalPay;
  }

  public BigDecimal getMaterialMakePay() {
    return materialMakePay;
  }

  public void setMaterialMakePay(BigDecimal materialMakePay) {
    this.materialMakePay = materialMakePay;
  }

  public BigDecimal getCompanyCasePay() {
    return companyCasePay;
  }

  public void setCompanyCasePay(BigDecimal companyCasePay) {
    this.companyCasePay = companyCasePay;
  }

  public BigDecimal getCourseDevelopmentPay() {
    return courseDevelopmentPay;
  }

  public void setCourseDevelopmentPay(BigDecimal courseDevelopmentPay) {
    this.courseDevelopmentPay = courseDevelopmentPay;
  }

  public BigDecimal getSpecialToolPay() {
    return specialToolPay;
  }

  public void setSpecialToolPay(BigDecimal specialToolPay) {
    this.specialToolPay = specialToolPay;
  }

  public BigDecimal getApplicationPrometePay() {
    return applicationPrometePay;
  }

  public void setApplicationPrometePay(BigDecimal applicationPrometePay) {
    this.applicationPrometePay = applicationPrometePay;
  }

  public BigDecimal getResearchProvePay() {
    return researchProvePay;
  }

  public void setResearchProvePay(BigDecimal researchProvePay) {
    this.researchProvePay = researchProvePay;
  }

  public BigDecimal getExpertConsultPay() {
    return expertConsultPay;
  }

  public void setExpertConsultPay(BigDecimal expertConsultPay) {
    this.expertConsultPay = expertConsultPay;
  }

  public BigDecimal getOtherFeePay() {
    return otherFeePay;
  }

  public void setOtherFeePay(BigDecimal otherFeePay) {
    this.otherFeePay = otherFeePay;
  }
}
