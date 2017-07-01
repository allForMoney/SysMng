package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class DeleteProjectView  implements Serializable {

  String[]  projectNos;

  public String[] getProjectNos() {
    return projectNos;
  }

  public void setProjectNos(String[] projectNos) {
    this.projectNos = projectNos;
  }
}
