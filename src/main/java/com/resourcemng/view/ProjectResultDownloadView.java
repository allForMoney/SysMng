package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.Project;

import java.util.List;

/**
 * 项目执行结果下载用视图
 * Created by Administrator on 2017-6-22.
 */
public class ProjectResultDownloadView extends FileImportLog {
  private Project project;
  private String quarterNum;
  private String projectYear;
  private ProjectBudgetInfoView budget;
  private ProjectFundInInfoView fundin;
  private ProjectFundOutInfoView fundout;

  public String getQuarterNum() {
    return quarterNum;
  }

  public void setQuarterNum(String quarterNum) {
    this.quarterNum = quarterNum;
  }

  public String getProjectYear() {
    return projectYear;
  }

  public void setProjectYear(String projectYear) {
    this.projectYear = projectYear;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public ProjectBudgetInfoView getBudget() {
    return budget;
  }

  public void setBudget(ProjectBudgetInfoView budget) {
    this.budget = budget;
  }

  public ProjectFundInInfoView getFundin() {
    return fundin;
  }

  public void setFundin(ProjectFundInInfoView fundin) {
    this.fundin = fundin;
  }

  public ProjectFundOutInfoView getFundout() {
    return fundout;
  }

  public void setFundout(ProjectFundOutInfoView fundout) {
    this.fundout = fundout;
  }
}
