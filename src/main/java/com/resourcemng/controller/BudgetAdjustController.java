package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.service.BudgetAdjustService;
import com.resourcemng.util.FileUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
  @RequestMapping(value = "/import/{projectId}" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@PathVariable String projectId,/*@RequestParam String year,*/HttpServletRequest request) throws Exception {
    List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
    if (!files.isEmpty() && files.size() >=3) {
        File requestFile = fileUitl.saveUploadFile(files.get(0) );
        File adjustFile = fileUitl.saveUploadFile(files.get(1) );
        File descriptionFile = fileUitl.saveUploadFile(files.get(2) );
        service.adjust(projectId,/*year,*/requestFile,adjustFile,descriptionFile);
      return new RequestResult(ResultCode.SUCCESS, "上传成功",   null);
    } else {
      return new RequestResult(ResultCode.FAILED, "上传成功",   null);
    }
  }

  /**
   *
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
   *
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getAll( String projectId) throws Exception {


    return new RequestResult(ResultCode.SUCCESS, "提交审批成功.",    service.find(projectId));
  }
}
