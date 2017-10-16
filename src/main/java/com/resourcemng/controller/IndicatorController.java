package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.IndicatorDetail;
import com.resourcemng.entitys.Project;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.FileUitl;
import com.resourcemng.service.IndicatorService;
import com.resourcemng.view.IndicatorView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/indicator")
public class IndicatorController  extends BaseController {
  protected static final String HSSF         = ".xls";
  protected static final String XSSF         = ".xlsx";
  @Autowired
  IndicatorService service;
  @Autowired
  FileUitl fileUitl;
  //文件上传保存路径


  /**
   * 上传项目文件
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/import" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@RequestParam String projectId,@RequestParam String importUser,@RequestParam String importType,@RequestParam("file")
  MultipartFile file ) throws Exception {
    if (!file.isEmpty()) {
        File uploadFile = fileUitl.saveUploadFile(file );
        service.importFormFile(projectId,importUser,uploadFile);
      return new RequestResult(ResultCode.SUCCESS, "上传成功.",   null);
    } else {
      return new RequestResult(ResultCode.SUCCESS, "上传失败，因为文件是空的.",   null);
    }

  }

  /**
   * 绩效修改明细
   * @param detail
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/update/detail" ,method = RequestMethod.POST)
  @ResponseBody
  public Object updateIndicatorDetail(IndicatorDetail detail) throws Exception {
     service.updateIndicatorDetail(detail);
    return new RequestResult(ResultCode.SUCCESS, "获取明细成功",   null);

  }
  /**
   * 绩效手动填写
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/update" ,method = RequestMethod.POST)
  @ResponseBody
  public Object updateIndicatorDetail(@RequestBody IndicatorView view) throws Exception {
    service.saveIndicator(view);
    return new RequestResult(ResultCode.SUCCESS, "绩效添加成功",   null);
  }



  /**
   * 绩效手动填写
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/detail" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getIndicatorDetail(@RequestParam String projectId) throws Exception {

    return new RequestResult(ResultCode.SUCCESS, "查询绩效成功",   service.getIndicatorDetail(projectId));
  }

  /**
   * 绩效手动填写
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/download/{projectId}" ,method = RequestMethod.GET)
  @ResponseBody
  public void download(@PathVariable String projectId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,Object> resultMap=  service.exportIndicator(projectId);
    if(resultMap == null){
      return;
    }
    Project project = (Project) resultMap.get("project");
    Workbook workbook = (Workbook) resultMap.get("workbook");
    if(workbook == null){
      return;
    }

    Format format = new SimpleDateFormat("yyyyMMdd");

    String codedFileName = project.getProjectNo()+"绩效目标";
    if (workbook instanceof HSSFWorkbook) {
      codedFileName += HSSF;
    } else {
      codedFileName += XSSF;
    }
    if (ApplicationUitl.isIE(request)) {
      codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF8");
    } else {
      codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
    }
    response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
    ServletOutputStream out = response.getOutputStream();
    workbook.write(out);
    out.flush();
  }
  /**
   * 绩效手动填写
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object all(String projectNo,String majorName,String schoolName,String page,String size) throws Exception {

    return new RequestResult(ResultCode.SUCCESS, "绩效添加成功",   service.find(projectNo,majorName,schoolName,null));
  }

}
