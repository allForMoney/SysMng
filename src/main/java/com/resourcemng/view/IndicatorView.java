package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.IndicatorBase;
import com.resourcemng.entitys.IndicatorDetail;

import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class IndicatorView extends FileImportLog {
  private IndicatorBase indicatorBase;
  private List<IndicatorDetail> indicatorDetails;

  public IndicatorBase getIndicatorBase() {
    return indicatorBase;
  }

  public void setIndicatorBase(IndicatorBase indicatorBase) {
    this.indicatorBase = indicatorBase;
  }

  public List<IndicatorDetail> getIndicatorDetails() {
    return indicatorDetails;
  }

  public void setIndicatorDetails(List<IndicatorDetail> indicatorDetails) {
    this.indicatorDetails = indicatorDetails;
  }
}
