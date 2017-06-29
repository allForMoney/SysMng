package com.resourcemng.controller;

import com.resourcemng.util.FileUitl;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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

  /**
   * 添加项目
   * @param project
   * @return
   * @throws Exception
   */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody Project project) throws Exception {
       return service.createPorject(project);
    }

  /**
   * 上传项目文件
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/import" ,method = RequestMethod.POST)
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

  /**
   * 修改项目信息
   * @param project
   * @return
   * @throws Exception
   */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@RequestBody Project project) throws Exception {
    return service.updatePorject(project);
    }

  /**
   * 删除项目信息
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/{projectNo}",method = RequestMethod.DELETE)
  @ResponseBody
  public Object delete(@PathVariable("projectNo") String projectNo) throws Exception {
    service.deletePorject(projectNo);
    return "删除成功";
  }

  /**
   * 删除项目信息
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/all",method = RequestMethod.GET)
  @ResponseBody
  public Object find(String projectNo,String majorName,String schoolName) throws Exception {
    service.find(projectNo,majorName,schoolName);
    return "删除成功";
  }

  /**
   * 获取项目详细信息
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/{projectNo}", method = RequestMethod.GET)
  @ResponseBody
  public Object get(@PathVariable("projectNo") String projectNo) throws Exception {
    return service.get(projectNo);
  }

  /**
   * 更新联系人信息
   * @param user
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/contacter/change", method = RequestMethod.POST)
  @ResponseBody
  public Object changePorjectUser(@ModelAttribute Tuser user) throws Exception {
     service.changePorjectUser(user);
     return "保存成功";
  }

}
