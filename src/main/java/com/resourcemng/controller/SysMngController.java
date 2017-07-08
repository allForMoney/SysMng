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
import com.resourcemng.view.ReportDeadLineView;
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

  /**
   * 修改季报填写的周期
   * @param reportDeadLineView
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/changeReportTime" ,method = RequestMethod.POST)
    @ResponseBody
    public Object create(ReportDeadLineView reportDeadLineView) throws Exception {
      service.saveScheduView(reportDeadLineView);
      return new RequestResult(ResultCode.SUCCESS, "创建项目成功.",null   );
    }

}
