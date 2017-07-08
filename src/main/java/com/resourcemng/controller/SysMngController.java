package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.ProjectService;
import com.resourcemng.service.SystemService;
import com.resourcemng.task.DynamicScheduledTask;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.DeleteProjectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/system")
public class SysMngController {
  @Autowired
  SystemService service;
  @Autowired
  DynamicScheduledTask dynamicScheduledTask;

  /**
   * 添加项目
   * @param project
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/changereporttime" ,method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody Project project) throws Exception {
      return null;
    }

}
