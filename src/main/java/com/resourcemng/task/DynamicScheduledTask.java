package com.resourcemng.task;

import com.resourcemng.Enum.UserRole;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.TUserRepository;
import com.resourcemng.service.SystemService;
import com.resourcemng.view.ReportDeadLineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 燕子 on 2017/7/7.
 */
@Component
public class DynamicScheduledTask  implements SchedulingConfigurer {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  private String cronOne;
  private String cronTwo;
  private String cronThree;
  private String cronFour;


  @Autowired
  private SystemService systemService;
  @Autowired
  private TUserRepository userRepository;
  @Autowired
  private MessageSender messageSender;


  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    this.updateTask();
    this.addSchedule(taskRegistrar,this.cronOne);
    this.addSchedule(taskRegistrar,this.cronTwo);
    this.addSchedule(taskRegistrar,this.cronThree);
    this.addSchedule(taskRegistrar,this.cronFour);
  }

  private void addSchedule(ScheduledTaskRegistrar taskRegistrar,String cron){
    taskRegistrar.addTriggerTask(new Runnable() {
      @Override
      public void run() {
        doTask();
      }
    }, new Trigger() {
      @Override
      public Date nextExecutionTime(TriggerContext triggerContext) {
        // 定时任务触发，可修改定时任务的执行周期
        CronTrigger trigger = new CronTrigger(cron);
        Date nextExecDate = trigger.nextExecutionTime(triggerContext);

        return nextExecDate;
      }
    });
  }


  private void doTask() {
    List<Tuser> users = userRepository.findByUserRole(UserRole.REPORT);
    if(users == null){
      return;
    }

    for(Tuser tuser: users){
      messageSender.sendSms(tuser.getTelephoneNum(),"尊敬的"+tuser.getUsername()+"：您好，添加季报仅仅剩下两天，请抓紧时间。");
    }

  }

  public void updateTask() {
    ReportDeadLineView view =  systemService.getScheduView();
    this.cronOne ="0 0 8 "+(Integer.parseInt(view.getQuarterOneSetting())-2)+" APR *";
    this.cronTwo ="0 0 8 "+(Integer.parseInt(view.getQuarterTwoSetting())-2)+" JUL *";
    this.cronThree ="0 0 8 "+(Integer.parseInt(view.getQuarterThreeSetting())-2)+" OCT *";
    this.cronFour ="0 0 8 "+(Integer.parseInt(view.getQuarterFourSetting())-2)+" JAN *";
  }
}
