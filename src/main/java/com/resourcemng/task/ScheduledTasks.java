package com.resourcemng.task;

/**
 * Created by 燕子 on 2017/7/8.
 */

import com.resourcemng.Enum.UserRole;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 每个季度提前一天发送短信通知
 */
@Component
public class ScheduledTasks {

  @Autowired
  private TUserRepository userRepository;
  @Autowired
  private MessageSender messageSender;
  //每个季度提前一天八点执行短信提醒
//  @Scheduled(cron = "0 0 8 L 2,5,8,11 ? ")//注解竟然不支持L
  @Scheduled(cron = "0 0 8 30 MAR ?")
  public void sendNodifyForOne(){
    List<Tuser> users = userRepository.findByUserRole(UserRole.REPORT);
    if(users == null){
      return;
    }

    for(Tuser tuser: users){
      messageSender.sendSms(tuser.getTelephoneNum(),"尊敬的"+tuser.getUsername()+"：您好，又到了填写第一季度季报的时间了，请填写季报。");
    }
  }
  @Scheduled(cron = "0 0 8 30 JUN ?")
  public void sendNodifyForTwo(){
    List<Tuser> users = userRepository.findByUserRole(UserRole.REPORT);
    if(users == null){
      return;
    }

    for(Tuser tuser: users){
      messageSender.sendSms(tuser.getTelephoneNum(),"尊敬的"+tuser.getUsername()+"：您好，又到了填写第二季度季报的时间了，请填写季报。");
    }
  }
  @Scheduled(cron = "0 0 8 30 SEP ?")
  public void sendNodifyForThree(){
    List<Tuser> users = userRepository.findByUserRole(UserRole.REPORT);
    if(users == null){
      return;
    }

    for(Tuser tuser: users){
      messageSender.sendSms(tuser.getTelephoneNum(),"尊敬的"+tuser.getUsername()+"：您好，又到了填写第三季度的时间了，请填写季报。");
    }
  }
  @Scheduled(cron = "0 0 8 31 DEC ?")
  public void sendNodifyForFour(){
    List<Tuser> users = userRepository.findByUserRole(UserRole.REPORT);
    if(users == null){
      return;
    }

    for(Tuser tuser: users){
      messageSender.sendSms(tuser.getTelephoneNum(),"尊敬的"+tuser.getUsername()+"：您好，又到了填写第四季度季报的时间了，请填写季报。");
    }
  }


}
