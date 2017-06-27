package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.Project;

/**
 * 项目执行结果下载用视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectBudgetInfoView  {
  /**
   * 合计
   */
 private  ResultInfoView total;
  /**
   * 部本专项资金
   */
 private  ResultInfoView countryTotal;
  /**
   * 院校举办方或地方财政投入资金
   */
 private  ResultInfoView local;
  /**
   * 行业企业支持资金
   */
 private  ResultInfoView enterprise;
  /**
   * 相关院校自筹资金
   */
 private  ResultInfoView university;

  public ResultInfoView getTotal() {
    return total;
  }

  public void setTotal(ResultInfoView total) {
    this.total = total;
  }

  public ResultInfoView getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(ResultInfoView countryTotal) {
    this.countryTotal = countryTotal;
  }

  public ResultInfoView getLocal() {
    return local;
  }

  public void setLocal(ResultInfoView local) {
    this.local = local;
  }

  public ResultInfoView getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(ResultInfoView enterprise) {
    this.enterprise = enterprise;
  }

  public ResultInfoView getUniversity() {
    return university;
  }

  public void setUniversity(ResultInfoView university) {
    this.university = university;
  }
}
