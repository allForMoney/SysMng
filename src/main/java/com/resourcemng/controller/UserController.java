package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.UserService;
import com.resourcemng.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
  UserService service;

  /**
   * 用户NO
   * @param userName
   * @param oldPassword
   * @param newPassword
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/changePassword" ,method = RequestMethod.POST)
  @ResponseBody
  public Object getAll(String userName, String oldPassword, String newPassword) throws Exception {
    Tuser user = service.getUserByNO(userName);
    if (user == null || !user.getPassword().equals(MD5.encrypt(oldPassword))) {//前端没有加密，加密比较
      return new RequestResult(ResultCode.FAILED, "旧密码输入不正确",  "旧密码输入不正确");

    }
    return new RequestResult(ResultCode.SUCCESS, "更新成功",  service.changePassword(userName,newPassword));
  }


}
