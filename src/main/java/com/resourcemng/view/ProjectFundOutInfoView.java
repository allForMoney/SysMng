package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.math.BigDecimal;

/**
 * 项目执行结果下载用视图，季报支出视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectFundOutInfoView {
  /**
   * 合计
   */
  private  ResultPrecentInfoView total;
  /**
   * 部本专项资金
   */
  private  ResultPrecentInfoView countryTotal;
  /**
   * 院校举办方或地方财政投入资金
   */
  private  ResultPrecentInfoView local;
  /**
   * 行业企业支持资金
   */
  private  ResultPrecentInfoView enterprise;
  /**
   * 相关院校自筹资金
   */
  private  ResultPrecentInfoView university;

  public ResultPrecentInfoView getTotal() {
    return total;
  }

  public void setTotal(ResultPrecentInfoView total) {
    this.total = total;
  }

  public ResultPrecentInfoView getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(ResultPrecentInfoView countryTotal) {
    this.countryTotal = countryTotal;
  }

  public ResultPrecentInfoView getLocal() {
    return local;
  }

  public void setLocal(ResultPrecentInfoView local) {
    this.local = local;
  }

  public ResultPrecentInfoView getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(ResultPrecentInfoView enterprise) {
    this.enterprise = enterprise;
  }

  public ResultPrecentInfoView getUniversity() {
    return university;
  }

  public void setUniversity(ResultPrecentInfoView university) {
    this.university = university;
  }
}
