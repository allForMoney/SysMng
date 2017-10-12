package com.resourcemng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;
import java.util.logging.Logger;

@SpringBootApplication
//@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Application.class);

  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
    factory.setMaxFileSize("100MB"); //KB,MB
    /// 设置总上传数据总大小
    factory.setMaxRequestSize("100MB");
    //Sets the directory location wherefiles will be stored.
    //factory.setLocation("路径地址");
    return factory.createMultipartConfig();
  }

  public static void main(String[] args) {
      try {
        SpringApplication.run(Application.class, args);
      }catch(Exception e) {
        logger.error(e);
      }

    }
    //打包成war包需要
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }
}
