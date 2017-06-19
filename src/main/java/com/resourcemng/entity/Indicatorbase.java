package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Indicatorbase {
  private String id;
  private String budgetImportId;
  private String objective;
  private String objectiveYear1;
  private String objectiveYear2;

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
  @Column(name = "Objective", nullable = true, length = -1)
  public String getObjective() {
    return objective;
  }

  public void setObjective(String objective) {
    this.objective = objective;
  }

  @Basic
  @Column(name = "ObjectiveYear1", nullable = true, length = -1)
  public String getObjectiveYear1() {
    return objectiveYear1;
  }

  public void setObjectiveYear1(String objectiveYear1) {
    this.objectiveYear1 = objectiveYear1;
  }

  @Basic
  @Column(name = "ObjectiveYear2", nullable = true, length = -1)
  public String getObjectiveYear2() {
    return objectiveYear2;
  }

  public void setObjectiveYear2(String objectiveYear2) {
    this.objectiveYear2 = objectiveYear2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Indicatorbase that = (Indicatorbase) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (budgetImportId != null ? !budgetImportId.equals(that.budgetImportId) : that.budgetImportId != null)
      return false;
    if (objective != null ? !objective.equals(that.objective) : that.objective != null) return false;
    if (objectiveYear1 != null ? !objectiveYear1.equals(that.objectiveYear1) : that.objectiveYear1 != null)
      return false;
    if (objectiveYear2 != null ? !objectiveYear2.equals(that.objectiveYear2) : that.objectiveYear2 != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (budgetImportId != null ? budgetImportId.hashCode() : 0);
    result = 31 * result + (objective != null ? objective.hashCode() : 0);
    result = 31 * result + (objectiveYear1 != null ? objectiveYear1.hashCode() : 0);
    result = 31 * result + (objectiveYear2 != null ? objectiveYear2.hashCode() : 0);
    return result;
  }
}
