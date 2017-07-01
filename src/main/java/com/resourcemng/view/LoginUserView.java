package com.resourcemng.view;

import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;

import java.util.List;

/**
 * Created by 燕子 on 2017/6/29.
 */
public class LoginUserView extends Tuser {
  private Project project;

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }
}
