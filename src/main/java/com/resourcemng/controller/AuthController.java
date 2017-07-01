package com.resourcemng.controller;

import com.resourcemng.Enum.UserRole;
import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.ProjectService;
import com.resourcemng.service.UserService;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.view.LoginUserView;
import org.apache.commons.beanutils.BeanUtils;
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
  @Autowired
  ProjectService projectService;
  String SESSION_KEY = "user";
  /**
   * 登录
   * @param tuser
   * @return
   */
  @RequestMapping (value = "/aa",method =  RequestMethod.GET)
  public @ResponseBody
  Object test(Tuser tuser ) {
   return "HD试试 AHA";
  }
  /**
   * 登录
   * @param tuser
   * @return
   */
  @RequestMapping (value = "/login",method =  RequestMethod.POST)
  public @ResponseBody
  Object loginPost(Tuser tuser ) throws MyException {
    try {
      Map<String, Object> map = new HashMap<>();
      Tuser user = service.getUser(tuser.getUsername());
      if (user == null || !user.getPassword().equals(tuser.getPassword())) {
        return new MyException("用户名密码不正确");
      }
      LoginUserView view = new LoginUserView();
      BeanUtils.copyProperties(view, user);
      //学校人员
      if (UserRole.REPORT.equals(user.getUserRole()) || UserRole.FINANCE.equals(user.getUserRole()) || UserRole.PROJECTHEADER.equals(user.getUserRole())) {
        Project project = projectService.get(ApplicationUitl.getPorjectNoByReportUserNo(user.getUsername()));
        view.setProject(project);
      }
      // 设置session
      return new RequestResult(ResultCode.SUCCESS, "登录成功.", view);
    }catch (Exception e){
      throw new MyException(e);
    }
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
