package com.resourcemng.controller;

import com.resourcemng.FileUitl;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.IndicatorBase;
import com.resourcemng.entitys.IndicatorDetail;
import com.resourcemng.entitys.Project;
import com.resourcemng.service.IndicatorService;
import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @author yuqing.he
 */
@Controller
@RequestMapping("/indicator")
public class IndicatorController {
  @Autowired
  IndicatorService service;
  //文件上传保存路径
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object create(@ModelAttribute IndicatorBase base, List<IndicatorDetail> detailList) throws Exception {
       return service.create(base,detailList);
    }




}
