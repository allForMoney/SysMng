package com.resourcemng.view;

import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.BudgetImportDetailOld;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BudgetAdjustCompareOld /*extends FileImportLog*/ {
  BudgetImportDetailOld beforeAdjust;
  BudgetImportDetailOld afterAdjust;

  public BudgetImportDetailOld getBeforeAdjust() {
    return beforeAdjust;
  }

  public void setBeforeAdjust(BudgetImportDetailOld beforeAdjust) {
    this.beforeAdjust = beforeAdjust;
  }

  public BudgetImportDetailOld getAfterAdjust() {
    return afterAdjust;
  }

  public void setAfterAdjust(BudgetImportDetailOld afterAdjust) {
    this.afterAdjust = afterAdjust;
  }
}
