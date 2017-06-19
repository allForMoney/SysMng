package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Indicatordetail {
  private String id;
  private String budgetImportId;
  private String indicator3;
  private String planTotal;
  private String planYear1;
  private String planYear2;
  private String indicator1;
  private String indicator2;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "BudgetImportId", nullable = false, length = 20)
  public String getBudgetImportId() {
    return budgetImportId;
  }

  public void setBudgetImportId(String budgetImportId) {
    this.budgetImportId = budgetImportId;
  }

  @Basic
  @Column(name = "Indicator3", nullable = true, length = 500)
  public String getIndicator3() {
    return indicator3;
  }

  public void setIndicator3(String indicator3) {
    this.indicator3 = indicator3;
  }

  @Basic
  @Column(name = "PlanTotal", nullable = true, length = 500)
  public String getPlanTotal() {
    return planTotal;
  }

  public void setPlanTotal(String planTotal) {
    this.planTotal = planTotal;
  }

  @Basic
  @Column(name = "PlanYear1", nullable = true, length = 500)
  public String getPlanYear1() {
    return planYear1;
  }

  public void setPlanYear1(String planYear1) {
    this.planYear1 = planYear1;
  }

  @Basic
  @Column(name = "PlanYear2", nullable = true, length = 500)
  public String getPlanYear2() {
    return planYear2;
  }

  public void setPlanYear2(String planYear2) {
    this.planYear2 = planYear2;
  }

  @Basic
  @Column(name = "Indicator1", nullable = true, length = 500)
  public String getIndicator1() {
    return indicator1;
  }

  public void setIndicator1(String indicator1) {
    this.indicator1 = indicator1;
  }

  @Basic
  @Column(name = "Indicator2", nullable = true, length = 500)
  public String getIndicator2() {
    return indicator2;
  }

  public void setIndicator2(String indicator2) {
    this.indicator2 = indicator2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Indicatordetail that = (Indicatordetail) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (budgetImportId != null ? !budgetImportId.equals(that.budgetImportId) : that.budgetImportId != null)
      return false;
    if (indicator3 != null ? !indicator3.equals(that.indicator3) : that.indicator3 != null) return false;
    if (planTotal != null ? !planTotal.equals(that.planTotal) : that.planTotal != null) return false;
    if (planYear1 != null ? !planYear1.equals(that.planYear1) : that.planYear1 != null) return false;
    if (planYear2 != null ? !planYear2.equals(that.planYear2) : that.planYear2 != null) return false;
    if (indicator1 != null ? !indicator1.equals(that.indicator1) : that.indicator1 != null) return false;
    if (indicator2 != null ? !indicator2.equals(that.indicator2) : that.indicator2 != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (budgetImportId != null ? budgetImportId.hashCode() : 0);
    result = 31 * result + (indicator3 != null ? indicator3.hashCode() : 0);
    result = 31 * result + (planTotal != null ? planTotal.hashCode() : 0);
    result = 31 * result + (planYear1 != null ? planYear1.hashCode() : 0);
    result = 31 * result + (planYear2 != null ? planYear2.hashCode() : 0);
    result = 31 * result + (indicator1 != null ? indicator1.hashCode() : 0);
    result = 31 * result + (indicator2 != null ? indicator2.hashCode() : 0);
    return result;
  }
}
