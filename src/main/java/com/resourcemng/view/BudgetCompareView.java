package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BudgetCompareView extends FileImportLog {
  List compareList;

  public List getCompareList() {
    return compareList;
  }

  public void setCompareList(List compareList) {
    this.compareList = compareList;
  }
}
