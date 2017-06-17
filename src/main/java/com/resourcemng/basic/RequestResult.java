package com.resourcemng.basic;

import com.resourcemng.entity.Project;

/**
 * Created by 燕子 on 2017/6/17.
 */
public class RequestResult {

    private String code;
    private String msg;
    private Object result;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public RequestResult(String code, String msg, Object result) {
    this.code = code;
    this.msg = msg;
    this.result = result;
  }

}
