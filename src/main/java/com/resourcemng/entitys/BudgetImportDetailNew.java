package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "budget_import_detail_new")
public class BudgetImportDetailNew implements Serializable {
  private String id;
  private String sequenceNo;
  private String useFor;
  private String consult;
  private String print;
  private String travel;
  private String metting;
  private String trainning;
  private String specialMaterial;
  private String delegation;
  private String otherExpense;
  private String specialDevice;
  private String infomationTechnology;
  private String totalMoney;
  private String countryTotal;
  private String countryPercent;
  private String projectTotal;
  private String projectPercent;
  private String local;
  private String enterprise;
  private String university;
  private String budgetYear;
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
  @Column(name = "CONSULT", nullable = true, length = 50)
  public String getConsult() {
    return consult;
  }

  public void setConsult(String consult) {
    this.consult = consult;
  }

  @Basic
  @Column(name = "PRINT", nullable = true, length = 50)
  public String getPrint() {
    return print;
  }

  public void setPrint(String print) {
    this.print = print;
  }

  @Basic
  @Column(name = "TRAVEL", nullable = true, length = 50)
  public String getTravel() {
    return travel;
  }

  public void setTravel(String travel) {
    this.travel = travel;
  }

  @Basic
  @Column(name = "METTING", nullable = true, length = 50)
  public String getMetting() {
    return metting;
  }

  public void setMetting(String metting) {
    this.metting = metting;
  }

  @Basic
  @Column(name = "TRAINNING", nullable = true, length = 50)
  public String getTrainning() {
    return trainning;
  }

  public void setTrainning(String trainning) {
    this.trainning = trainning;
  }

  @Basic
  @Column(name = "SPECIAL_MATERIAL", nullable = true, length = 50)
  public String getSpecialMaterial() {
    return specialMaterial;
  }

  public void setSpecialMaterial(String specialMaterial) {
    this.specialMaterial = specialMaterial;
  }

  @Basic
  @Column(name = "DELEGATION", nullable = true, length = 50)
  public String getDelegation() {
    return delegation;
  }

  public void setDelegation(String delegation) {
    this.delegation = delegation;
  }

  @Basic
  @Column(name = "OTHER_EXPENSE", nullable = true, length = 50)
  public String getOtherExpense() {
    return otherExpense;
  }

  public void setOtherExpense(String otherExpense) {
    this.otherExpense = otherExpense;
  }

  @Basic
  @Column(name = "SPECIAL_DEVICE", nullable = true, length = 50)
  public String getSpecialDevice() {
    return specialDevice;
  }

  public void setSpecialDevice(String specialDevice) {
    this.specialDevice = specialDevice;
  }

  @Basic
  @Column(name = "INFOMATION_TECHNOLOGY", nullable = true, length = 50)
  public String getInfomationTechnology() {
    return infomationTechnology;
  }

  public void setInfomationTechnology(String infomationTechnology) {
    this.infomationTechnology = infomationTechnology;
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
  @Column(name = "LOCAL", nullable = true, length = 50)
  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  @Basic
  @Column(name = "ENTERPRISE", nullable = true, length = 50)
  public String getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(String enterprise) {
    this.enterprise = enterprise;
  }

  @Basic
  @Column(name = "UNIVERSITY", nullable = true, length = 50)
  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  @Basic
  @Column(name = "BUDGET_YEAR", nullable = false, length = 10)
  public String getBudgetYear() {
    return budgetYear;
  }

  public void setBudgetYear(String budgetYear) {
    this.budgetYear = budgetYear;
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

    BudgetImportDetailNew that = (BudgetImportDetailNew) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (sequenceNo != null ? !sequenceNo.equals(that.sequenceNo) : that.sequenceNo != null) return false;
    if (useFor != null ? !useFor.equals(that.useFor) : that.useFor != null) return false;
    if (consult != null ? !consult.equals(that.consult) : that.consult != null) return false;
    if (print != null ? !print.equals(that.print) : that.print != null) return false;
    if (travel != null ? !travel.equals(that.travel) : that.travel != null) return false;
    if (metting != null ? !metting.equals(that.metting) : that.metting != null) return false;
    if (trainning != null ? !trainning.equals(that.trainning) : that.trainning != null) return false;
    if (specialMaterial != null ? !specialMaterial.equals(that.specialMaterial) : that.specialMaterial != null)
      return false;
    if (delegation != null ? !delegation.equals(that.delegation) : that.delegation != null) return false;
    if (otherExpense != null ? !otherExpense.equals(that.otherExpense) : that.otherExpense != null) return false;
    if (specialDevice != null ? !specialDevice.equals(that.specialDevice) : that.specialDevice != null) return false;
    if (infomationTechnology != null ? !infomationTechnology.equals(that.infomationTechnology) : that.infomationTechnology != null)
      return false;
    if (totalMoney != null ? !totalMoney.equals(that.totalMoney) : that.totalMoney != null) return false;
    if (countryTotal != null ? !countryTotal.equals(that.countryTotal) : that.countryTotal != null) return false;
    if (countryPercent != null ? !countryPercent.equals(that.countryPercent) : that.countryPercent != null)
      return false;
    if (projectTotal != null ? !projectTotal.equals(that.projectTotal) : that.projectTotal != null) return false;
    if (projectPercent != null ? !projectPercent.equals(that.projectPercent) : that.projectPercent != null)
      return false;
    if (local != null ? !local.equals(that.local) : that.local != null) return false;
    if (enterprise != null ? !enterprise.equals(that.enterprise) : that.enterprise != null) return false;
    if (university != null ? !university.equals(that.university) : that.university != null) return false;
    if (budgetYear != null ? !budgetYear.equals(that.budgetYear) : that.budgetYear != null) return false;
    if (fileImportId != null ? !fileImportId.equals(that.fileImportId) : that.fileImportId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (sequenceNo != null ? sequenceNo.hashCode() : 0);
    result = 31 * result + (useFor != null ? useFor.hashCode() : 0);
    result = 31 * result + (consult != null ? consult.hashCode() : 0);
    result = 31 * result + (print != null ? print.hashCode() : 0);
    result = 31 * result + (travel != null ? travel.hashCode() : 0);
    result = 31 * result + (metting != null ? metting.hashCode() : 0);
    result = 31 * result + (trainning != null ? trainning.hashCode() : 0);
    result = 31 * result + (specialMaterial != null ? specialMaterial.hashCode() : 0);
    result = 31 * result + (delegation != null ? delegation.hashCode() : 0);
    result = 31 * result + (otherExpense != null ? otherExpense.hashCode() : 0);
    result = 31 * result + (specialDevice != null ? specialDevice.hashCode() : 0);
    result = 31 * result + (infomationTechnology != null ? infomationTechnology.hashCode() : 0);
    result = 31 * result + (totalMoney != null ? totalMoney.hashCode() : 0);
    result = 31 * result + (countryTotal != null ? countryTotal.hashCode() : 0);
    result = 31 * result + (countryPercent != null ? countryPercent.hashCode() : 0);
    result = 31 * result + (projectTotal != null ? projectTotal.hashCode() : 0);
    result = 31 * result + (projectPercent != null ? projectPercent.hashCode() : 0);
    result = 31 * result + (local != null ? local.hashCode() : 0);
    result = 31 * result + (enterprise != null ? enterprise.hashCode() : 0);
    result = 31 * result + (university != null ? university.hashCode() : 0);
    result = 31 * result + (budgetYear != null ? budgetYear.hashCode() : 0);
    result = 31 * result + (fileImportId != null ? fileImportId.hashCode() : 0);
    return result;
  }
}
