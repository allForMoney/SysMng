package com.resourcemng.controller;

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

  /**
   * 绩效手动填写
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/updatedetail" ,method = RequestMethod.POST)
  @ResponseBody
  public void updateIndicatorDetail(IndicatorDetail detail) throws Exception {
     service.updateIndicatorDetail(detail);
  }
  /**
   * 绩效手动填写
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public void updateIndicatorDetail(IndicatorView view) throws Exception {
     service.saveIndicator(view);
  }

}
