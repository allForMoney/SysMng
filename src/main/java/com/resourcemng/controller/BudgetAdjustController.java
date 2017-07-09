package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.service.BudgetAdjustService;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.BudgetAdjustView;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/budgetadjust")
public class BudgetAdjustController {
  @Autowired
  BudgetAdjustService service;
  @Autowired
  FileUitl fileUitl;

  /**
   * 导入文件
   * @param projectId
   * @param adjustUserId
   * @param adjustType
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/import" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@RequestParam String projectId,@RequestParam String adjustUserId,@RequestParam String adjustType,HttpServletRequest request) throws Exception {
    List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
    if (!files.isEmpty() && files.size() >=3) {
        File requestFile = fileUitl.saveUploadFile(files.get(0) );
        File adjustFile = fileUitl.saveUploadFile(files.get(1) );
        File descriptionFile = fileUitl.saveUploadFile(files.get(2) );
      return new RequestResult(ResultCode.SUCCESS, "上传成功",   service.adjust(projectId,adjustUserId,adjustType,requestFile,adjustFile,descriptionFile));
    } else {
      return new RequestResult(ResultCode.FAILED, "上传失败，您没有提交任何文件",   null);
    }
  }

  /**
   * @param adjustId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/getById" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getById(@RequestParam String adjustId) throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "根据调整ID获取调整详情",   service.getById(adjustId));
  }


  /**
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/getLastAdjust" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getLastAdjust(@RequestParam String projectNo) throws Exception {
    List list = (List) service.find(projectNo,null,null,null,null).getContent();
    if(list == null || list.size()==0){
      return new RequestResult(ResultCode.SUCCESS, "还没有调整过",   null);
    }
   BudgetAdjustView view = (BudgetAdjustView) list.get(list.size()-1);

    return new RequestResult(ResultCode.SUCCESS, "最后一次的调整如下：",  service.getById(view.getAdjustId()));
  }
  /**
   *审核调整记录
   * @param id
   * @param auditType
   * @param auditContent
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/audit" ,method = RequestMethod.POST)
  @ResponseBody
  public Object audit(@RequestParam String id,@RequestParam String auditType, String auditContent) throws Exception {

    service.audit(id,auditType,auditContent);
    return new RequestResult(ResultCode.SUCCESS, "提交审批成功.",   null);
  }

  /**
   * 获取项目所有的调整记录
   * @param projectNo 模糊查询
   * @param majorName 模糊查询
   * @param schoolName 模糊查询
   * @param status  精确查询
   * @param page
   * @param size
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getAll( String projectNo,String majorName,String schoolName,String status,String page,String size) throws Exception {
    Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(size));

    return new RequestResult(ResultCode.SUCCESS, "提交审批成功.",    service.find(projectNo,majorName,schoolName,status,pageable));
  }
  /**
   *获取项目所有的调整记录
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/download/file" ,method = RequestMethod.GET)
  @ResponseBody
  public void downloadFile( @RequestParam String projectId,@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
     File file = fileUitl.getFile(fileName);

    if(!file.exists()){
      String errorMessage = "对不起，你下载的文件不存在";
     throw new MyException(errorMessage);
    }
    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
    if(mimeType==null){
      System.out.println("mimetype is not detectable, will take default");
      mimeType = "application/octet-stream";
    }
    response.setContentType(mimeType);
    response.setContentLength((int)file.length());
    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
    if (StringUtils.isNoneBlank(fileName)) {
      fileName = fileName;
    }
    if (ApplicationUitl.isIE(request)) {
      fileName = java.net.URLEncoder.encode(fileName, "UTF8");
    } else {
      fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
    }

    response.setHeader("content-disposition", "attachment;filename=" + fileName);
    FileCopyUtils.copy(inputStream, response.getOutputStream());

  }


  /**
   *指定预算审核记录的ID，查看调整资源的比较信息
   * @param id
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/getCompareInfo" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getCompareInfo(@RequestParam String id) throws Exception {


    return new RequestResult(ResultCode.SUCCESS, "提交审批成功.",    service.getCompareInfo(id));
  }
}
