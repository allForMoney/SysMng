package com.resourcemng.controller;

import com.resourcemng.FileUitl;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.service.BudgetService;
import com.resourcemng.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/indicator")
public class IndicatorController {
  @Autowired
  IndicatorService service;
  //文件上传保存路径


  /**
   * 上传项目文件
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/import/{projectId}" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@PathVariable String projectId,@RequestParam String importUser,@RequestParam String importType,@RequestParam("file")
  MultipartFile file ) throws Exception {
    if (!file.isEmpty()) {
      try {
        File uploadFile = FileUitl.saveUploadFile(file );
        service.importFormFile(projectId,importUser,uploadFile);
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
