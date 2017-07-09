package com.resourcemng.view;

import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;

/**
 * Created by 燕子 on 2017/6/29.
 */
public class UserView extends Tuser {
  private String projectNo;
  private String majorName;
  private String roleName;

  public String getProjectNo() {
    return projectNo;
  }

  public void setProjectNo(String projectNo) {
    this.projectNo = projectNo;
  }

  @Override
  public String getMajorName() {
    return majorName;
  }

  @Override
  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
