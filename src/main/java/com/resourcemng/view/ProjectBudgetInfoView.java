package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.Project;

import java.io.Serializable;

/**
 * 项目执行结果下载用视图，预算金额视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectBudgetInfoView  implements Serializable{
  /**
   * 合计
   */
 private  ResultInfoView total = new ResultInfoView();
  /**
   * 部本专项资金
   */
 private  ResultInfoView countryTotal= new ResultInfoView();
  /**
   * 院校举办方或地方财政投入资金
   */
 private  ResultInfoView local= new ResultInfoView();
  /**
   * 行业企业支持资金
   */
 private  ResultInfoView enterprise= new ResultInfoView();
  /**
   * 相关院校自筹资金
   */
 private  ResultInfoView university= new ResultInfoView();

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
