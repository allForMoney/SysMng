package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.BudgetImportDetailOld;
import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.view.BudgetImportView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BudgetService {
  @Autowired
  BudgetImport2016Repository budgetImport2016Repository;
  @Autowired
  BudgetImportRepository budgetImportRepository;
  @Autowired
  FileImportLogRepository fileImportLogRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  LeaveMessageRepository leaveMessageRepository;

  /**
   *
   * @param projectId
   * @param importUser
   * @param importType
   * @param uploadFile
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public void importBudgetFormFile(String projectId, String importUser,String importType, File uploadFile) throws InvocationTargetException, IllegalAccessException {

    if(ImportFileType.BUDGET2016.equals(importType)){
      this.importBudget2016FormFile(projectId,importUser,uploadFile);
    }else{
      this.importBudget2015FormFile(projectId,importUser,uploadFile);
    }
  }

  public void importBudget2016FormFile(String projectId, String importUser, File uploadFile) throws InvocationTargetException, IllegalAccessException {
    ImportParams params = new ImportParams();
    //设置标题行
    params.setTitleRows(5);
    params.setHeadRows(5);
    //设置读取的有效行数
    params.setReadRows(28);
    Map<Integer, String> map = new HashMap<Integer, String>();
    for (int i = 0; i < 20; i++) {//此处先按顺序给标题，在handler中转换标题
      map.put(i, Integer.toString(i));

    }
    params.setTitlemap(map);
    //转换标题
    params.setDataHanlder(new BudgetImportHanlder());
    long start = new Date().getTime();
    List<Map<String, Object>> list = ExcelImportUtil.importExcel(uploadFile, Map.class, params);

    FileImportLog log = new FileImportLog();
    log.setFileName(uploadFile.getName());
    log.setImportType(ImportFileType.BUDGET2016);
    log.setImportUserId(importUser);
    log.setProjectId(projectId);
    log.setImportDate(new Date());
    log = fileImportLogRepository.save(log);
    for (Map obj:list){
      BudgetImportDetailNew budgetImportDetailNew = new BudgetImportDetailNew();
      BeanUtils.populate(budgetImportDetailNew,obj);
      budgetImportDetailNew.setFileImportId(log.getId());
      budgetImportDetailNew.setBudgetYear("2015");
      this.budgetImport2016Repository.save(budgetImportDetailNew);
    }
  }

  public void importBudget2015FormFile(String projectId, String importUser, File uploadFile) throws InvocationTargetException, IllegalAccessException {


  }

  public void delete(String id) {
  }

  public Object update(BudgetImportDetailNew budgetImportDetailNew) {
    return this.budgetImport2016Repository.save(budgetImportDetailNew);
  }

  /**
   * 获取明细，返回基本导入信息和详细预算信息
   * @param projetId
   * @return
   * @throws MyException
   */
  public Object get(String projetId) throws MyException {
    try {
      FileImportLog obj  = this.fileImportLogRepository.findByProject(projetId);
      BudgetImportView view = new BudgetImportView();
      BeanUtils.copyProperties(view,obj);
      if(ImportFileType.BUDGET2016.equals(obj.getImportType())) {//2016
        List<BudgetImportDetailNew> detail = this.budgetImport2016Repository.findByBudgetImportId(obj.getId());
        view.setBudgetImportDetaillList(detail);
      }else{//2015
        List<BudgetImportDetailOld> detail = this.budgetImportRepository.findByBudgetImportId(obj.getId());
        view.setBudgetImportDetaillList(detail);
      }
      return view;
    } catch (IllegalAccessException e) {
      throw new MyException(e);
    } catch (InvocationTargetException e) {
      throw new MyException(e);
    }
  }

  /**
   *
   * @param leaveMessage
   * @return
   */
  public Object comment(LeaveMessage leaveMessage) {
    leaveMessage.setMesType(LeaveMessageType.BUDGET);
    leaveMessage.setSubmitDate(new Date());
   return leaveMessageRepository.save(leaveMessage);
  }
}
