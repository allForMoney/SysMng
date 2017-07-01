package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BudgetComputeView  {
  ProjectBudgetItemView budget;

  public ProjectBudgetItemView getBudget() {
    return budget;
  }

  public void setBudget(ProjectBudgetItemView budget) {
    this.budget = budget;
  }
}
