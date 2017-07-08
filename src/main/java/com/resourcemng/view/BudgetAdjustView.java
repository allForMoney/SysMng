package com.resourcemng.view;

import com.resourcemng.entitys.BudgetAuditLog;

/**
 * Created by 燕子 on 2017/7/8.
 */
public class BudgetAdjustView extends BudgetAuditLog {
  String projectNo;
  String majorName;
  String schoolName;

  public String getProjectNo() {
    return projectNo;
  }

  public void setProjectNo(String projectNo) {
    this.projectNo = projectNo;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }
}
