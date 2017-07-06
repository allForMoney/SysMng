package com.resourcemng.aop;

import com.resourcemng.basic.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 燕子 on 2017/7/5.
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {

  /**
   * 登录session key
   */
  public final static String SESSION_KEY = "user";
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    try {

      HttpSession session = request.getSession();
      System.out.println("session  : " + session.getAttribute(SESSION_KEY));
      if (session.getAttribute(SESSION_KEY) == null) {
        response.sendRedirect(request.getContextPath() +"/loginpage.html");
//        throw new MyException("DDDD");
        return false;
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception {
  }
}
