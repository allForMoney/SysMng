package com.resourcemng.view;

import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.IndicatorBase;
import com.resourcemng.entitys.IndicatorDetail;

import java.util.List;

/**
 * Created by Administrator on 2017-6-22.
 */
public class ImportFileView extends FileImportLog {
  String projectNo;
  String majorName;
  String schoolName;

  String importUserNo;

  public String getImportUserNo() {
    return importUserNo;
  }

  public void setImportUserNo(String importUserNo) {
    this.importUserNo = importUserNo;
  }

  ;

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
