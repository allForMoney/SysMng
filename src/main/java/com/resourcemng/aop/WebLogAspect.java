package com.resourcemng.aop;

import com.resourcemng.entitys.Sitelog;
import com.resourcemng.repository.SitLogRepository;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class WebLogAspect {
  @Autowired
  SitLogRepository sitLogRepository;
  private Logger logger = Logger.getLogger(getClass());

  @Pointcut("execution(public * com.resourcemng.controller..*.*(..))")
  public void webLog(){}

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    try {
      // 接收到请求，记录请求内容
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletRequest request = attributes.getRequest();

      // 记录下请求内容
      logger.info("URL : " + request.getRequestURL().toString());
      logger.info("HTTP_METHOD : " + request.getMethod());
      logger.info("IP : " + request.getRemoteAddr());
      logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
      logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
      Sitelog sitelog = new Sitelog();
      //TODO 设置用户信息
      sitelog.setIp(request.getRemoteAddr());
      sitelog.setAction(request.getMethod());
      sitelog.setUrl(request.getRequestURL().toString());
      sitelog.setController(joinPoint.getSignature().getDeclaringTypeName());
      sitelog.setOperateTime(new Date());
      sitLogRepository.save(sitelog);
    }catch(Throwable e){
      logger.info(e.getMessage());

    }

  }

  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    logger.info("RESPONSE : " + ret);
  }

}
