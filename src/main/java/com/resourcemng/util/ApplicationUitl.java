package com.resourcemng.util;

import com.resourcemng.basic.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 燕子 on 2017/6/20.
 */
public class ApplicationUitl {

  public static String getPorjectNoByReportUserNo(String userNo){
    String projectNo = userNo.substring(0, userNo.lastIndexOf('-') );
    return projectNo;
  }

}
