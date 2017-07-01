package com.resourcemng.view;

import com.resourcemng.Enum.IsDelete;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
public class ResultInfoView {
  private BigDecimal total;
  private BigDecimal materialMake;
  private BigDecimal companyCase;
  private BigDecimal courseDevelopment;
  private BigDecimal specialTool;
  private BigDecimal applicationPromete;
  private BigDecimal researchProve;
  private BigDecimal expertConsult;
  private BigDecimal otherFee;

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getMaterialMake() {
    return materialMake;
  }

  public void setMaterialMake(BigDecimal materialMake) {
    this.materialMake = materialMake;
  }

  public BigDecimal getCompanyCase() {
    return companyCase;
  }

  public void setCompanyCase(BigDecimal companyCase) {
    this.companyCase = companyCase;
  }

  public BigDecimal getCourseDevelopment() {
    return courseDevelopment;
  }

  public void setCourseDevelopment(BigDecimal courseDevelopment) {
    this.courseDevelopment = courseDevelopment;
  }

  public BigDecimal getSpecialTool() {
    return specialTool;
  }

  public void setSpecialTool(BigDecimal specialTool) {
    this.specialTool = specialTool;
  }

  public BigDecimal getApplicationPromete() {
    return applicationPromete;
  }

  public void setApplicationPromete(BigDecimal applicationPromete) {
    this.applicationPromete = applicationPromete;
  }

  public BigDecimal getResearchProve() {
    return researchProve;
  }

  public void setResearchProve(BigDecimal researchProve) {
    this.researchProve = researchProve;
  }

  public BigDecimal getExpertConsult() {
    return expertConsult;
  }

  public void setExpertConsult(BigDecimal expertConsult) {
    this.expertConsult = expertConsult;
  }

  public BigDecimal getOtherFee() {
    return otherFee;
  }

  public void setOtherFee(BigDecimal otherFee) {
    this.otherFee = otherFee;
  }
}
