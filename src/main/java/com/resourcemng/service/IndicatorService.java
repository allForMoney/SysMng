package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.FoundSourceType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.handler.IndicatorBaseInfoImportHanlder;
import com.resourcemng.handler.IndicatorImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.view.BudgetImportView;
import com.resourcemng.view.IndicatorView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Transactional
public class IndicatorService {
  @Autowired
  FileImportLogRepository fileImportLogRepository;
  @Autowired
  IndicatorDetailRepository indicatorDetailRepository;
  @Autowired
  IndicatorBaseRepository indicatorBaseRepository;
  public void importFormFile(String projectId, String importUser, File uploadFile) throws MyException {
    try {

      //保存导入记录
      FileImportLog log = new FileImportLog();
      log.setFileName(uploadFile.getName());
      log.setImportType(ImportFileType.TARGET);
      log.setImportUserId(importUser);
      log.setProjectId(projectId);
      log.setImportDate(new Date());
      log = fileImportLogRepository.save(log);
      //导入指标明细
      ImportParams params = new ImportParams();
      //设置标题行
      params.setTitleRows(9);
      params.setHeadRows(9);
      //设置读取的有效行数
      Map<Integer, String> map = new HashMap<Integer, String>();
      for (int i = 0; i < 8; i++) {//此处先按顺序给标题，在handler中转换标题
        map.put(i, Integer.toString(i));

      }
      params.setTitlemap(map);
      //绩效对象
      IndicatorView view = new IndicatorView();
      List<IndicatorDetail> details = new ArrayList<>();
      view.setIndicatorDetails(details);

      //转换标题
      params.setDataHanlder(new IndicatorImportHanlder());
      List<Map<String, Object>> list = ExcelImportUtil.importExcel(uploadFile, Map.class, params);


      String indicatorLevelOneName = null;
      String indicatorLevelTwoName = null;
      for (Map dateMap : list) {
        IndicatorDetail indicatorImportDetailNew = new IndicatorDetail();
        BeanUtils.populate(indicatorImportDetailNew, dateMap);
        if(indicatorImportDetailNew.getIndicatorOneLevel() !=null){//保存一级指标
          indicatorLevelOneName =indicatorImportDetailNew.getIndicatorOneLevel();
        }else{//没有一级指标，取上一条数据的一级指标
          indicatorImportDetailNew.setIndicatorOneLevel(indicatorLevelOneName);
        }
        if(indicatorImportDetailNew.getIndicatorTowLevel() !=null){//保存二级指标
          indicatorLevelTwoName =indicatorImportDetailNew.getIndicatorTowLevel();
        }else{//没有二级指标，取上一条数据的二级指标
          indicatorImportDetailNew.setIndicatorTowLevel(indicatorLevelTwoName);
        }

        indicatorImportDetailNew.setFileImportId(log.getId());
        indicatorImportDetailNew.setProjectId(projectId);
        //添加
        details.add(indicatorImportDetailNew);
      }


      //下面导入基本指标信息
      //设置标题行
      params.setTitleRows(5);
      params.setHeadRows(5);
      //设置读取的有效行数
      params.setReadRows(1);
      Map<Integer, String> baseMap = new HashMap<Integer, String>();
      for (int i = 0; i < 3; i++) {//此处先按顺序给标题，在handler中转换标题
        map.put(i, Integer.toString(i));

      }
      params.setTitlemap(map);
      params.setDataHanlder(new IndicatorBaseInfoImportHanlder());
      List<Map<String, Object>> baseInfoList = ExcelImportUtil.importExcel(uploadFile, Map.class, params);
      Map baseInfo = baseInfoList.get(0);
      IndicatorBase indicatorBase = new IndicatorBase();
      BeanUtils.populate(indicatorBase, baseInfo);
      indicatorBase.setFileImportId(log.getId());
      view.setIndicatorBase(indicatorBase);

      //保存
      this.saveIndicator(view);
    }catch (Exception e){
      throw new MyException(e);
    }
  }

  /**
   * 保存
   * @param view
   */
  public void saveIndicator(IndicatorView view) {
    List<IndicatorDetail> details  = view.getIndicatorDetails();
    for (IndicatorDetail indicatorDetail : details) {
      indicatorDetailRepository.save(indicatorDetail);
    }
    indicatorBaseRepository.save(view.getIndicatorBase());
  }

  /**
   * 更新单个指标
   * @param detail
   */
  public void updateIndicatorDetail(IndicatorDetail detail) {
    indicatorDetailRepository.save(detail);
  }
}
