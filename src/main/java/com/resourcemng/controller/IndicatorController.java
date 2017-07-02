package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.IndicatorDetail;
import com.resourcemng.util.FileUitl;
import com.resourcemng.service.IndicatorService;
import com.resourcemng.view.IndicatorView;
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
  @Autowired
  FileUitl fileUitl;
  //文件上传保存路径


  /**
   * 上传项目文件
   * @param file
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/import" ,method = RequestMethod.POST)
  @ResponseBody
  public Object uploadBudget(@RequestParam String projectId,@RequestParam String importUser,@RequestParam String importType,@RequestParam("file")
  MultipartFile file ) throws Exception {
    if (!file.isEmpty()) {
        File uploadFile = fileUitl.saveUploadFile(file );
        service.importFormFile(projectId,importUser,uploadFile);
      return new RequestResult(ResultCode.SUCCESS, "上传成功.",   null);
    } else {
      return new RequestResult(ResultCode.SUCCESS, "上传失败，因为文件是空的.",   null);
    }

  }

  /**
   * 绩效修改明细
   * @param detail
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/update/detail" ,method = RequestMethod.POST)
  @ResponseBody
  public Object updateIndicatorDetail(IndicatorDetail detail) throws Exception {
     service.updateIndicatorDetail(detail);
    return new RequestResult(ResultCode.SUCCESS, "获取明细成功",   null);

  }
  /**
   * 绩效手动填写
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object updateIndicatorDetail(IndicatorView view) throws Exception {
    service.saveIndicator(view);
    return new RequestResult(ResultCode.SUCCESS, "绩效添加成功",   null);
  }

  /**
   * 绩效手动填写
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/detail" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getIndicatorDetail(@RequestParam String projectId) throws Exception {

    return new RequestResult(ResultCode.SUCCESS, "绩效添加成功",   service.getIndicatorDetail(projectId));
  }

}
