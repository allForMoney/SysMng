package com.resourcemng.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
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
