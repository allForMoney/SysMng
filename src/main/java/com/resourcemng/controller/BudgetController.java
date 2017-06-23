package com.resourcemng.controller;

import com.resourcemng.FileUitl;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.service.BudgetService;
import com.resourcemng.service.ProjectService;
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
@RequestMapping("/budget")
public class BudgetController {
  @Autowired
  BudgetService service;
  //文件上传保存路径


  /**
   * 上传项目文件
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/uploadFile" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@RequestParam String projectId,@RequestParam String importUser,@RequestParam String importType,@RequestParam("file")
  MultipartFile file ) throws Exception {
    if (!file.isEmpty()) {
      try {
        // 这里只是简单例子，文件直接输出到项目路径下。
        // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
        // 还有关于文件格式限制、文件大小限制，详见：中配置。
        File uploadFile = FileUitl.saveUploadFile(file );
        service.importBudgetFormFile(projectId,importUser,importType,uploadFile);
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


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@ModelAttribute BudgetImportDetailNew budgetImportDetailNew) throws Exception {
    return service.update(budgetImportDetailNew);
    }

  @RequestMapping(method = RequestMethod.DELETE)
  @ResponseBody
  public Object delete(@RequestParam String id) throws Exception {
    service.delete(id);
    return "删除成功";
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Object get(@RequestParam String id) throws Exception {
    return service.get(id);
  }

}
