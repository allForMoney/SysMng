package com.resourcemng.view;

/**
 * 项目执行结果下载用视图，季报支出视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectBudgetItemView {
  /**
   * 合计
   */
  private  ProjectBudgetDetailView total;
  /**
   * 部本专项资金
   */
  private  ProjectBudgetDetailView countryTotal;
  /**
   * 院校举办方或地方财政投入资金
   */
  private  ProjectBudgetDetailView local;
  /**
   * 行业企业支持资金
   */
  private  ProjectBudgetDetailView enterprise;
  /**
   * 相关院校自筹资金
   */
  private  ProjectBudgetDetailView university;

  public ProjectBudgetDetailView getTotal() {
    return total;
  }

  public void setTotal(ProjectBudgetDetailView total) {
    this.total = total;
  }

  public ProjectBudgetDetailView getCountryTotal() {
    return countryTotal;
  }

  public void setCountryTotal(ProjectBudgetDetailView countryTotal) {
    this.countryTotal = countryTotal;
  }

  public ProjectBudgetDetailView getLocal() {
    return local;
  }

  public void setLocal(ProjectBudgetDetailView local) {
    this.local = local;
  }

  public ProjectBudgetDetailView getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(ProjectBudgetDetailView enterprise) {
    this.enterprise = enterprise;
  }

  public ProjectBudgetDetailView getUniversity() {
    return university;
  }

  public void setUniversity(ProjectBudgetDetailView university) {
    this.university = university;
  }
}
