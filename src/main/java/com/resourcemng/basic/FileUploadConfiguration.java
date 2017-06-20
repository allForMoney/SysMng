package com.resourcemng.basic;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * Created by 燕子 on 2017/6/20.
 */
public class FileUploadConfiguration {
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    // 设置文件大小限制 ,超出设置页面会抛出异常信息，
    // 这样在文件上传的地方就需要进行异常信息的处理了;
    factory.setMaxFileSize("5M"); // KB,MB
    /// 设置总上传数据总大小
    factory.setMaxRequestSize("20M");
    // 设置文件保存路径
     factory.setLocation("路径地址");
    return factory.createMultipartConfig();
  }
}
