package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.FundsIn;
import com.resourcemng.entitys.FundsOut;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BudgetReportView extends FileImportLog {
  String userId;
  String projectId;
  String quarterNum;
  String projectYear;
  String auditStatus;

  /**
   *
   */
  List<FundsIn> fundsIns;
  /**
   *
   */
  List< FundsOut> fundsOuts;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<FundsIn> getFundsIns() {
    return fundsIns;
  }

  public void setFundsIns(List<FundsIn> fundsIns) {
    this.fundsIns = fundsIns;
  }


  public List<FundsOut> getFundsOuts() {
    return fundsOuts;
  }

  public void setFundsOuts(List<FundsOut> fundsOuts) {
    this.fundsOuts = fundsOuts;
  }

  public String getProjectYear() {
    return projectYear;
  }

  public void setProjectYear(String projectYear) {
    this.projectYear = projectYear;
  }

  public String getQuarterNum() {
    return quarterNum;
  }

  public void setQuarterNum(String quarterNum) {
    this.quarterNum = quarterNum;
  }

  @Override
  public String getProjectId() {
    return projectId;
  }

  @Override
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getAuditStatus() {
    return auditStatus;
  }

  public void setAuditStatus(String auditStatus) {
    this.auditStatus = auditStatus;
  }

  public void update(){
    for(FundsIn fundsIn:fundsIns){
      fundsIn.setUserId(this.getUserId());
      fundsIn.setProjectYear(this.getProjectYear());
      fundsIn.setSubmitTime(new Date());
      fundsIn.setQuarterNum(this.quarterNum);
    }
    for(FundsOut fundsOut:fundsOuts){
      fundsOut.setUserId(this.getUserId());
      fundsOut.setProjectYear(this.getProjectYear());
      fundsOut.setSubmitTime(new Date());
      fundsOut.setQuarterNum(this.quarterNum);
    }
  }
}
