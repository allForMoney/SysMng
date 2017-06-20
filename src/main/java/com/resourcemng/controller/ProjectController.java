package com.resourcemng.controller;

import com.resourcemng.FileUitl;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entity.Project;
import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  ProjectService service;
  //文件上传保存路径


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object create(@ModelAttribute  Project project) throws Exception {
       return service.createPorject(project);
    }

  /**
   * 上传项目文件
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/uploadProject" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadProject(@RequestParam("file")
  MultipartFile file ) throws Exception {
    if (!file.isEmpty()) {
      try {
        // 这里只是简单例子，文件直接输出到项目路径下。
        // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
        // 还有关于文件格式限制、文件大小限制，详见：中配置。
        File uploadFile = FileUitl.saveUploadFile(file );
        service.importPorjectByFile(uploadFile);
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
    public Object update(@ModelAttribute Project project) throws Exception {
    return service.updatePorject(project);
    }

  @RequestMapping(method = RequestMethod.DELETE)
  @ResponseBody
  public Object delete(@RequestParam String projectNo) throws Exception {
    service.deletePorject(projectNo);
    return "删除成功";
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Object get(@RequestParam String projectNo) throws Exception {
    return service.get(projectNo);
  }

}
