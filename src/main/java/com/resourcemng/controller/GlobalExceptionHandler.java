package com.resourcemng.controller;

import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 燕子 on 2017/6/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value={Exception.class})
  @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
 public RequestResult exceptionHandler(RuntimeException e, HttpServletResponse response) {
      e.printStackTrace();
    return new RequestResult(ResultCode.FAILED, e.getMessage(), e.getMessage());
  }
}
