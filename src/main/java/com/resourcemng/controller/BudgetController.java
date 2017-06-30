package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
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
  @Autowired
  FileUitl fileUitl;
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
        // 这里只是简单例子，文件直接输出到项目路径下。
        // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
        // 还有关于文件格式限制、文件大小限制，详见：中配置。
        File uploadFile = fileUitl.saveUploadFile(file );
        service.importBudgetFormFile(projectId,importUser,importType,budgetYear,uploadFile);
      return new RequestResult(ResultCode.SUCCESS, "上传成功",   null);
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
  @RequestMapping(value = "/comment/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object comment(@RequestBody LeaveMessage leaveMessage) throws Exception {
    return service.comment(leaveMessage);
  }

    @RequestMapping(value = "/comment/update" ,method = RequestMethod.POST)
    @ResponseBody
    public Object update(@ModelAttribute BudgetImportDetailNew budgetImportDetailNew) throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "提交成功",   service.update(budgetImportDetailNew));
    }

  /**
   * 查看所有的导入，支持根据项目ID进行过滤
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "allimport" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getAll(@RequestParam String projectId) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "提交成功",  service.getImportByProject(projectId));
  }
  /**
   * 根据导入ID获取预算
   * @param id
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "delete" ,method = RequestMethod.POST)
  @ResponseBody
  public Object delete(@RequestParam String id) throws Exception {
    service.delete(id);
    return new RequestResult(ResultCode.SUCCESS, "删除成功",  null);

  }

  /**
   * 根据导入ID获取预算
   * @param importId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/getByImportId" ,method = RequestMethod.GET)
  @ResponseBody
  public Object get(@RequestParam String importId) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "获取预算成功",  service.get(importId));
  }

  /**
   * 获取某个项目的预算
   * @param projectId
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/getByProjectId" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getByProject(@RequestParam String projectId) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "删除成功",  service.getByProject(projectId));
  }

}
