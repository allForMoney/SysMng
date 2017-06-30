package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.util.FileUitl;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  @Autowired
  FileUitl fileUitl;
  //文件上传保存路径

  /**
   * 添加项目
   * @param project
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/create" ,method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody Project project) throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "创建项目成功.",   service.createPorject(project));
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
        File uploadFile = fileUitl.saveUploadFile(file);
        service.importPorjectByFile(uploadFile);
        return new RequestResult(ResultCode.SUCCESS, "上传成功", null);
      }catch (Exception e){
        throw new MyException(e);
      }
    } else {
      return new RequestResult(ResultCode.FAILED, "上传失败，因为文件是空的.",   null);
    }
  }

  /**
   * 修改项目信息
   * @param project
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody Project project) throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "更新成功",   service.updatePorject(project));
    }

  /**
   * 删除项目信息
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
  @ResponseBody
  public Object delete(@RequestParam("projectNo") String projectNo) throws Exception {
    service.deletePorject(projectNo);
    return new RequestResult(ResultCode.SUCCESS, "删除成功",  null );
  }

  /**
   * 删除项目信息
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/all",method = RequestMethod.GET)
  @ResponseBody
  public Object find(String projectNo,String majorName,String schoolName,String page,String size) throws Exception {
    Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(size));
    return new RequestResult(ResultCode.SUCCESS, "获取成功",  service.find(projectNo,majorName,schoolName,pageable));
  }

  /**
   * 获取项目详细信息
   * @param projectNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  @ResponseBody
  public Object get(@RequestParam("projectNo") String projectNo) throws Exception {

    return new RequestResult(ResultCode.SUCCESS, "获取明细成功",  service.get(projectNo));
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
    return new RequestResult(ResultCode.SUCCESS, "保存成功", "保存成功");
  }

}
