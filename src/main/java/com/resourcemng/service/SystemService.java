package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.QuarterSettinEnum;
import com.resourcemng.Enum.UserRole;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.ReportDeadlineSetting;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.ProjectRepository;
import com.resourcemng.repository.ReportDeadlineSettingRepository;
import com.resourcemng.repository.TUserRepository;
import com.resourcemng.task.DynamicScheduledTask;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.view.ReportDeadLineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Transactional
@Service
public class SystemService {
  @Autowired
  ReportDeadlineSettingRepository repository;
  @Autowired
  DynamicScheduledTask dynamicScheduledTask;

  public ReportDeadLineView getScheduView(){
    ReportDeadlineSetting reportDeadlineSettingOne = repository.findById(QuarterSettinEnum.ONE).get();
    ReportDeadlineSetting reportDeadlineSettingTwo = repository.findById(QuarterSettinEnum.TWO).get();
    ReportDeadlineSetting reportDeadlineSettingThree = repository.findById(QuarterSettinEnum.THREE).get();
    ReportDeadlineSetting reportDeadlineSettingFour = repository.findById(QuarterSettinEnum.FOUR).get();
    ReportDeadLineView reportDeadLineView = new ReportDeadLineView();
    reportDeadLineView.setQuarterOneSetting(reportDeadlineSettingOne.getValue());
    reportDeadLineView.setQuarterTwoSetting(reportDeadlineSettingTwo.getValue());
    reportDeadLineView.setQuarterThreeSetting(reportDeadlineSettingThree.getValue());
    reportDeadLineView.setQuarterFourSetting(reportDeadlineSettingFour.getValue());
    return reportDeadLineView;

  }

  /**
   *
   * @param reportDeadLineView
   * @return
   */
  public void saveScheduView(ReportDeadLineView reportDeadLineView ){
    this.saveScheduleSetting(QuarterSettinEnum.ONE,reportDeadLineView.getQuarterOneSetting());
    this.saveScheduleSetting(QuarterSettinEnum.TWO,reportDeadLineView.getQuarterTwoSetting());
    this.saveScheduleSetting(QuarterSettinEnum.THREE,reportDeadLineView.getQuarterThreeSetting());
    this.saveScheduleSetting(QuarterSettinEnum.FOUR,reportDeadLineView.getQuarterFourSetting());
    dynamicScheduledTask.updateTask();
  }

  /**
   *
   * @param id
   * @param value
   * @return
   */
  private void saveScheduleSetting(String id,String value ){
    ReportDeadlineSetting reportDeadlineSetting = repository.findById(id).get();
    reportDeadlineSetting.setValue(value);
    repository.save(reportDeadlineSetting);
  }

}
