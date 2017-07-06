package com.resourcemng.aop;

import com.resourcemng.Application;
import com.resourcemng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig   extends WebMvcConfigurerAdapter {


  @Bean
  public LoginInterceptor getLoginInterceptor() {
    return new LoginInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    super.addInterceptors(registry);
    InterceptorRegistration addInterceptor = registry.addInterceptor(getLoginInterceptor());
    // 排除配置
    addInterceptor.excludePathPatterns("/error");
    addInterceptor.excludePathPatterns("/login**");
    // 拦截配置
    addInterceptor.addPathPatterns("/**","/index.html");
  }

}
