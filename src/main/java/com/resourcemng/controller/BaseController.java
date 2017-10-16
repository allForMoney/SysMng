package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 燕子 on 2017/10/16.
 */
public class BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

  /**
   * 异常页面控制
   * @param exception
   * @return
   */
  @ExceptionHandler(Exception.class)
  public @ResponseBody
  Object runtimeExceptionHandler(Exception exception) throws Exception {
    LOGGER.error(exception.getLocalizedMessage());
    exception.printStackTrace();
    return new RequestResult(ResultCode.FAILED, exception.getLocalizedMessage(),   exception.getLocalizedMessage());

//    throw exception;

  }
}
