package com.resourcemng.util;

import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.resourcemng.basic.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by 燕子 on 2017/6/20.
 */
public class ApplicationUitl {

  public static String getPorjectNoByReportUserNo(String userNo){
    String projectNo = userNo.substring(0, userNo.lastIndexOf('-') );
    return projectNo;
  }

  public static String getWebRootPath(String filePath) {
    try {
      String path = ApplicationUitl.class.getClassLoader().getResource("").toURI().getPath();
      path = path.replace("WEB-INF/classes/", "");
      path = path.replace("file:/", "");
      return path + filePath;
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean isIE(HttpServletRequest request) {
    return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0
      || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0
      || request.getHeader("USER-AGENT").toLowerCase().indexOf("edge") > 0) ? true
      : false;
  }
}
