package com.resourcemng.view;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-6-22.
 */
public class ModifyTelView implements Serializable {

  String[]  projectNos;

  public String[] getProjectNos() {
    return projectNos;
  }

  public void setProjectNos(String[] projectNos) {
    this.projectNos = projectNos;
  }
}
