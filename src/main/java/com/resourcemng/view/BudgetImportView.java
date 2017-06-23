package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BudgetImportView extends FileImportLog {
    //预算明细
    private List budgetImportDetaillList;
     private String budgetType;
    public List<?> getBudgetImportDetaillList() {
      return budgetImportDetaillList;
    }

    public void setBudgetImportDetaillList(List<?> budgetImportDetaillList) {
      this.budgetImportDetaillList = budgetImportDetaillList;
    }
}
