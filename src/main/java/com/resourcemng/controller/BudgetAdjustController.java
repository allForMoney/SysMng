package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.service.BudgetAdjustService;
import com.resourcemng.service.BudgetService;
import com.resourcemng.util.FileUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
  public Object uploadBudget(@PathVariable String projectId,@RequestParam String year,HttpServletRequest request) throws Exception {
    List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
    if (!files.isEmpty() && files.size() >=3) {
        File requestFile = fileUitl.saveUploadFile(files.get(0) );
        File adjustFile = fileUitl.saveUploadFile(files.get(1) );
        File descriptionFile = fileUitl.saveUploadFile(files.get(2) );
        service.adjust(projectId,year,requestFile,adjustFile,descriptionFile);
      return new RequestResult(ResultCode.SUCCESS, "上传成功",   null);
    } else {
      return new RequestResult(ResultCode.FAILED, "上传成功",   null);
    }
  }
}
