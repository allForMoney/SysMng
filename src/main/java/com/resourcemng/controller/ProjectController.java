package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entity.Project;
import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  ProjectService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object create(@ModelAttribute  Project project) throws Exception {
       return new RequestResult(ResultCode.SUCCESS,"",service.createPorject(project));
    }


    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    @ResponseBody
    public Object update(@ModelAttribute  Project project) throws Exception {
      return new RequestResult(ResultCode.SUCCESS,"",service.updatePorject(project));
    }
}
