package com.resourcemng.view;

import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.FundsIn;
import com.resourcemng.entitys.FundsOut;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BudgetAdjustCompareView extends FileImportLog {
  BudgetImportDetailNew beforeAdjust;
  BudgetImportDetailNew afterAdjust;

  public BudgetImportDetailNew getBeforeAdjust() {
    return beforeAdjust;
  }

  public void setBeforeAdjust(BudgetImportDetailNew beforeAdjust) {
    this.beforeAdjust = beforeAdjust;
  }

  public BudgetImportDetailNew getAfterAdjust() {
    return afterAdjust;
  }

  public void setAfterAdjust(BudgetImportDetailNew afterAdjust) {
    this.afterAdjust = afterAdjust;
  }
}
