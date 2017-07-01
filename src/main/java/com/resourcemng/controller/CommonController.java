package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.CommonService;
import com.resourcemng.service.ProjectService;
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
@RequestMapping("/common")
public class CommonController {
  @Autowired
  CommonService service;

  /**
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/provences" ,method = RequestMethod.POST)
    @ResponseBody
    public Object getProvences() throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "创建项目成功.",   service.getPorvences());
    }


}
