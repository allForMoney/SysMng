package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Budgetimportdetail2016 {
  private String id;
  private String sequenceNo;
  private String usedFor;
  private String consult;
  private String print;
  private String travel;
  private String meeting;
  private String training;
  private String specialMaterial;
  private String delegation;
  private String otherExpense;
  private String specialDevice;
  private String informationTechnology;
  private String totalMoney;
  private String countryTotal;
  private String countryPercent;
  private String projectTotal;
  private String projectPercent;
  private String local;
  private String enterprise;
  private String university1;
  private String budgetYear1;

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
  @Column(name = "Consult", nullable = true, length = 50)
  public String getConsult() {
    return consult;
  }

  public void setConsult(String consult) {
    this.consult = consult;
  }

  @Basic
  @Column(name = "Print", nullable = true, length = 50)
  public String getPrint() {
    return print;
  }

  public void setPrint(String print) {
    this.print = print;
  }

  @Basic
  @Column(name = "Travel", nullable = true, length = 50)
  public String getTravel() {
    return travel;
  }

  public void setTravel(String travel) {
    this.travel = travel;
  }

  @Basic
  @Column(name = "Meeting", nullable = true, length = 50)
  public String getMeeting() {
    return meeting;
  }

  public void setMeeting(String meeting) {
    this.meeting = meeting;
  }

  @Basic
  @Column(name = "Training", nullable = true, length = 50)
  public String getTraining() {
    return training;
  }

  public void setTraining(String training) {
    this.training = training;
  }

  @Basic
  @Column(name = "SpecialMaterial", nullable = true, length = 50)
  public String getSpecialMaterial() {
    return specialMaterial;
  }

  public void setSpecialMaterial(String specialMaterial) {
    this.specialMaterial = specialMaterial;
  }

  @Basic
  @Column(name = "Delegation", nullable = true, length = 50)
  public String getDelegation() {
    return delegation;
  }

  public void setDelegation(String delegation) {
    this.delegation = delegation;
  }

  @Basic
  @Column(name = "OtherExpense", nullable = true, length = 50)
  public String getOtherExpense() {
    return otherExpense;
  }

  public void setOtherExpense(String otherExpense) {
    this.otherExpense = otherExpense;
  }

  @Basic
  @Column(name = "SpecialDevice", nullable = true, length = 50)
  public String getSpecialDevice() {
    return specialDevice;
  }

  public void setSpecialDevice(String specialDevice) {
    this.specialDevice = specialDevice;
  }

  @Basic
  @Column(name = "InformationTechnology", nullable = true, length = 50)
  public String getInformationTechnology() {
    return informationTechnology;
  }

  public void setInformationTechnology(String informationTechnology) {
    this.informationTechnology = informationTechnology;
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
  @Column(name = "Local", nullable = true, length = 50)
  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  @Basic
  @Column(name = "Enterprise", nullable = true, length = 50)
  public String getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(String enterprise) {
    this.enterprise = enterprise;
  }

  @Basic
  @Column(name = "University1", nullable = true, length = 50)
  public String getUniversity1() {
    return university1;
  }

  public void setUniversity1(String university1) {
    this.university1 = university1;
  }

  @Basic
  @Column(name = "BudgetYear1", nullable = false, length = 10)
  public String getBudgetYear1() {
    return budgetYear1;
  }

  public void setBudgetYear1(String budgetYear1) {
    this.budgetYear1 = budgetYear1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Budgetimportdetail2016 that = (Budgetimportdetail2016) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (sequenceNo != null ? !sequenceNo.equals(that.sequenceNo) : that.sequenceNo != null) return false;
    if (usedFor != null ? !usedFor.equals(that.usedFor) : that.usedFor != null) return false;
    if (consult != null ? !consult.equals(that.consult) : that.consult != null) return false;
    if (print != null ? !print.equals(that.print) : that.print != null) return false;
    if (travel != null ? !travel.equals(that.travel) : that.travel != null) return false;
    if (meeting != null ? !meeting.equals(that.meeting) : that.meeting != null) return false;
    if (training != null ? !training.equals(that.training) : that.training != null) return false;
    if (specialMaterial != null ? !specialMaterial.equals(that.specialMaterial) : that.specialMaterial != null)
      return false;
    if (delegation != null ? !delegation.equals(that.delegation) : that.delegation != null) return false;
    if (otherExpense != null ? !otherExpense.equals(that.otherExpense) : that.otherExpense != null) return false;
    if (specialDevice != null ? !specialDevice.equals(that.specialDevice) : that.specialDevice != null) return false;
    if (informationTechnology != null ? !informationTechnology.equals(that.informationTechnology) : that.informationTechnology != null)
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
    if (university1 != null ? !university1.equals(that.university1) : that.university1 != null) return false;
    if (budgetYear1 != null ? !budgetYear1.equals(that.budgetYear1) : that.budgetYear1 != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (sequenceNo != null ? sequenceNo.hashCode() : 0);
    result = 31 * result + (usedFor != null ? usedFor.hashCode() : 0);
    result = 31 * result + (consult != null ? consult.hashCode() : 0);
    result = 31 * result + (print != null ? print.hashCode() : 0);
    result = 31 * result + (travel != null ? travel.hashCode() : 0);
    result = 31 * result + (meeting != null ? meeting.hashCode() : 0);
    result = 31 * result + (training != null ? training.hashCode() : 0);
    result = 31 * result + (specialMaterial != null ? specialMaterial.hashCode() : 0);
    result = 31 * result + (delegation != null ? delegation.hashCode() : 0);
    result = 31 * result + (otherExpense != null ? otherExpense.hashCode() : 0);
    result = 31 * result + (specialDevice != null ? specialDevice.hashCode() : 0);
    result = 31 * result + (informationTechnology != null ? informationTechnology.hashCode() : 0);
    result = 31 * result + (totalMoney != null ? totalMoney.hashCode() : 0);
    result = 31 * result + (countryTotal != null ? countryTotal.hashCode() : 0);
    result = 31 * result + (countryPercent != null ? countryPercent.hashCode() : 0);
    result = 31 * result + (projectTotal != null ? projectTotal.hashCode() : 0);
    result = 31 * result + (projectPercent != null ? projectPercent.hashCode() : 0);
    result = 31 * result + (local != null ? local.hashCode() : 0);
    result = 31 * result + (enterprise != null ? enterprise.hashCode() : 0);
    result = 31 * result + (university1 != null ? university1.hashCode() : 0);
    result = 31 * result + (budgetYear1 != null ? budgetYear1.hashCode() : 0);
    return result;
  }
}
