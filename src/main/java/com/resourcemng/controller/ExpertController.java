package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.Experts;
import com.resourcemng.entitys.Project;
import com.resourcemng.service.ExpertServiceService;
import com.resourcemng.service.ProjectService;
import com.resourcemng.view.BatchDeleteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/experts")
public class ExpertController  extends BaseController{
@Autowired
  ExpertServiceService service;
  /**
   * 添加
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object create(@RequestBody Experts experts) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "创建项目成功.",   service.create(experts));
  }
  /**
   *
   * @param experts
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/update" ,method = RequestMethod.POST)
  @ResponseBody
  public Object update(@RequestBody Experts experts) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "更新成功",   service.update(experts));
  }
  /**
   *批量删除
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
  @ResponseBody
  public Object batchDelete(@RequestBody BatchDeleteView view ) throws Exception {
    service.batchDelete(Arrays.asList(view.getIds()));
    return new RequestResult(ResultCode.SUCCESS, "更新成功", null  );
  }
  /**
   *
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object find() throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "更新成功",   service.find());
  }
}
