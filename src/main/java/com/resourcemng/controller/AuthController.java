package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
@Controller
public class AuthController {
  @Autowired
  UserService service;
  String SESSION_KEY = "user";

  /**
   * 登录
   * @param tuser
   * @return
   */
  @RequestMapping (value = "/login",method =  RequestMethod.POST)
  public @ResponseBody
  Object loginPost(Tuser tuser ) {
    Map<String, Object> map = new HashMap<>();
    Tuser user =  service.getUser(tuser.getUsername());
    if (user == null ||!user.getPassword().equals(tuser.getPassword())) {
     return new MyException("用户名密码不正确");
    }
    // 设置session
//    session.setAttribute(SESSION_KEY, user);
    return user;
  }

  /**
   * 登出
   * @param session
   * @return
   */
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    // 移除session
    session.removeAttribute(SESSION_KEY);
    return "redirect:/login";
  }
}
