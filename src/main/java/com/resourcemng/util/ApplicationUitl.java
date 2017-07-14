package com.resourcemng.util;

import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.resourcemng.Enum.TempleteType;
import com.resourcemng.Enum.UserRole;
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
   int index = userNo.lastIndexOf('-');
   if(index<0){
     return null;
   }
    String projectNo = userNo.substring(0, userNo.lastIndexOf('-') );
    return projectNo;
  }

//  public static String getWebRootPath(String filePath) {
//    try {
//      String path = ApplicationUitl.class.getClassLoader().getResource("").toURI().getPath();
//      path = path.replace("WEB-INF/classes/", "");
//      path = path.replace("file:/", "");
//      return path + filePath;
//    } catch (URISyntaxException e) {
//      throw new RuntimeException(e);
//    }
//  }

  public static boolean isIE(HttpServletRequest request) {
    return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0
      || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0
      || request.getHeader("USER-AGENT").toLowerCase().indexOf("edge") > 0) ? true
      : false;
  }
  public static String getTempFileNameByType(String type) {
    switch (type){
      case TempleteType.PROJECT:
        return "项目模板.xlsx";
      case TempleteType.INDICATOR:
        return "绩效目标模板.xlsx";
      case TempleteType.BUDGET2016:
        return "预算2016模板.xlsx";
    }
    return null;
  }
  public static String getRoleNameByRole(String role) {
    switch (role){
      case UserRole.REPORT:
        return "填报人";
      case UserRole.COUNTRY:
        return "教育部";
      case UserRole.FINANCE:
        return "财务部门负责人";
      case UserRole.PROJECTHEADER:
        return "项目负责人";
      case UserRole.ADMIN:
        return "管理员";
      case UserRole.PROVENCE:
        return "教育厅";
    }
    return "";
  }
}
