package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.IndicatorBaseInfoImportHanlder;
import com.resourcemng.handler.IndicatorImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.view.IndicatorView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  @Autowired
  ProjectRepository projectRepository;
  public void importFormFile(String projectId, String importUser, File uploadFile) throws MyException {
    try {
      //仅仅允许导入一次啊
      List<FileImportLog> logs = fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDateDesc(projectId,ImportFileType.TARGET);
      if(logs !=null &&logs.size() >0){
        throw new MyException("同一个项目绩效指标只能导入一次，如果需要修改，请删除记录后重试。");
      }
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

  /**
   *
   * @param projectId
   */
  public IndicatorView getIndicatorDetail(String projectId) throws MyException, InvocationTargetException, IllegalAccessException {
    IndicatorView view  = new IndicatorView ();
    //仅仅允许导入一次啊
    List<FileImportLog>   logs = fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDateDesc(projectId,ImportFileType.TARGET);
    if(logs == null){
      throw new MyException("没有记录，请联系管理员导入指标数据。");
    }
    FileImportLog fileImportLog = logs.get(0);
    BeanUtils.copyProperties(view, fileImportLog);
    IndicatorBase indicatorBase = indicatorBaseRepository.findByFileImportId(fileImportLog.getId());
    view.setIndicatorBase(indicatorBase);
    view.setIndicatorDetails(indicatorDetailRepository.findByFileImportId(fileImportLog.getId()));
    return view;

  }
  /**
   *
   * @param fileImportId
   */
  public IndicatorView getIndicatorDetailByImportId(String fileImportId) throws MyException, InvocationTargetException, IllegalAccessException {
    IndicatorView view  = new IndicatorView ();
    //仅仅允许导入一次啊
    FileImportLog  fileImportLog = fileImportLogRepository.findById(fileImportId).get();
    if(fileImportLog == null){
      throw new MyException("没有记录，请联系管理员导入指标数据。");
    }
    BeanUtils.copyProperties(view, fileImportLog);
    IndicatorBase indicatorBase = indicatorBaseRepository.findByFileImportId(fileImportLog.getId());
    view.setIndicatorBase(indicatorBase);
    view.setIndicatorDetails(indicatorDetailRepository.findByFileImportId(fileImportLog.getId()));
    return view;

  }

  public Page find(String projectNo, String majorName, String schoolName,  Pageable pageable ) throws InvocationTargetException, IllegalAccessException, MyException {
    projectNo = projectNo == null ? "" : projectNo;
    majorName = majorName ==null?"":majorName;
    schoolName = schoolName ==null?"":schoolName;
    List<Project> projects = this.projectRepository.findByProjectNoLikeAndMajorNameLikeAndSchoolNameLike(projectNo,majorName,schoolName);
    if(projects == null || projects.size() == 0){
      pageable = pageable==null?new PageRequest(1,10):pageable;//这里随便构造一个对象，不然校验不通过
      return new PageImpl(new ArrayList<>(),pageable,0);
    }
    List<String> projectIds = new ArrayList<>();
    Map<String,Project> projectMap = new HashMap();
    for(Project project:projects){//缓存
      projectIds.add(project.getId());
      projectMap.put(project.getId(),project);
    }
  //分页查询
    Page page = fileImportLogRepository.findByProjectIdInAndImportTypeOrderByImportDateDesc(projectIds,ImportFileType.TARGET,pageable);
    List<FileImportLog> indictorImportLogs = page.getContent();
    if(indictorImportLogs == null){
      pageable = pageable==null?new PageRequest(1,10):pageable;//这里随便构造一个对象，不然校验不通过
      return new PageImpl(new ArrayList<>(),pageable,0);
    }
    List indicatorList = new ArrayList();
    //返回
    for(FileImportLog budgetAuditLog:indictorImportLogs){
      IndicatorView view = this.getIndicatorDetailByImportId(budgetAuditLog.getId());
      indicatorList.add(view);
    }
    return new PageImpl(indicatorList,page.getPageable(),page.getTotalElements());
  }

  }
