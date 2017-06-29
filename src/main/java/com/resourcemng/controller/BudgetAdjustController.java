package com.resourcemng.controller;

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
  @RequestMapping(value = "/import/{projectId}" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@PathVariable String projectId,@RequestParam String year,HttpServletRequest request) throws Exception {
    List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
    if (!files.isEmpty() && files.size() >=3) {
      try {
        File requestFile = FileUitl.saveUploadFile(files.get(0) );
        File adjustFile = FileUitl.saveUploadFile(files.get(1) );
        File descriptionFile = FileUitl.saveUploadFile(files.get(2) );
        service.adjust(projectId,year,requestFile,adjustFile,descriptionFile);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        return "上传失败," + e.getMessage();
      } catch (IOException e) {
        e.printStackTrace();
        return "上传失败," + e.getMessage();
      }
      return "上传成功";
    } else {
      return "上传失败，因为文件是空的.";
    }
  }
}
