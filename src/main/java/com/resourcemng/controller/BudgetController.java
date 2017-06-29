package com.resourcemng.controller;

import com.resourcemng.util.FileUitl;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.service.BudgetService;
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
   * @param projectId
   * @param importUser
   * 导入用户，一般是管理员
   * @param importType
   * @param budgetYear
   * 导入类型，是2015还是2016
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/import/{projectId}" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@PathVariable String projectId,@RequestParam String importUser,@RequestParam String importType,@RequestParam String budgetYear,@RequestParam("file")
  MultipartFile file ) throws Exception {
    if (!file.isEmpty()) {
      try {
        // 这里只是简单例子，文件直接输出到项目路径下。
        // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
        // 还有关于文件格式限制、文件大小限制，详见：中配置。
        File uploadFile = FileUitl.saveUploadFile(file );
        service.importBudgetFormFile(projectId,importUser,importType,budgetYear,uploadFile);
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

  /**
   * 添加预算留言
   * @param leaveMessage
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/{id}/comment" ,method = RequestMethod.POST)
  @ResponseBody
  public Object comment(@RequestBody LeaveMessage leaveMessage) throws Exception {
    return service.comment(leaveMessage);
  }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@ModelAttribute BudgetImportDetailNew budgetImportDetailNew) throws Exception {
    return service.update(budgetImportDetailNew);
    }

  /**
   * 查看所有的导入，支持根据项目ID进行过滤
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getAll(@RequestParam String projectId) throws Exception {
    return service.getImportByProject(projectId);
  }
  /**
   * 根据导入ID获取预算
   * @param id
   * @return
   * @throws Exception
   */
  @RequestMapping(method = RequestMethod.DELETE)
  @ResponseBody
  public Object delete(@RequestParam String id) throws Exception {
    service.delete(id);
    return "删除成功";
  }

  /**
   * 根据导入ID获取预算
   * @param importId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/{importId}" ,method = RequestMethod.GET)
  @ResponseBody
  public Object get(@PathVariable String importId) throws Exception {
    return service.get(importId);
  }

  /**
   * 获取某个项目的预算
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/project/{projectId}" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getByProject(@RequestParam String projectId) throws Exception {
    return service.getByProject(projectId);
  }

}
