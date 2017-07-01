package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.FoundSourceType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.view.*;
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
  @Autowired
  FundsBudgetRepository fundsBudgetRepository;

  /**
   *
   * @param projectId
   * @param importUser
   * @param importType
   * @param uploadFile
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public void importBudgetFormFile(String projectId, String importUser,String importType,String budgetYear, File uploadFile) throws MyException {

    if(ImportFileType.BUDGET2016.equals(importType)){
      this.importBudget2016FormFile(projectId,importUser,budgetYear,uploadFile);
    }else{
      this.importBudget2015FormFile(projectId,importUser,budgetYear,uploadFile);
    }
  }

  public void importBudget2016FormFile(String projectId, String importUser, String budgetYear,File uploadFile) throws MyException {
    FileImportLog  fileImportLogs  = this.fileImportLogRepository.findByProjectIdAndImportType(projectId,ImportFileType.BUDGET2015);
    if(fileImportLogs != null) {//不让重复导入？
      throw new MyException("预算已经导入过，不能重复导入，请删除后重试");
    }
    try {
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
      //保存统计信息
      FundsBudget totalfundsBudget = new FundsBudget();
      totalfundsBudget.setPid(FoundSourceType.TOTAL);
      totalfundsBudget.setSubmitTime(new Date());
      totalfundsBudget.setProjectId(projectId);
      totalfundsBudget.setBudgetYear(budgetYear);
      FundsBudget countryfundsBudget = new FundsBudget();
      countryfundsBudget.setPid(FoundSourceType.COUNTRY);
      countryfundsBudget.setSubmitTime(new Date());
      countryfundsBudget.setProjectId(projectId);
      countryfundsBudget.setBudgetYear(budgetYear);
      FundsBudget localfundsBudget = new FundsBudget();
      localfundsBudget.setPid(FoundSourceType.LOCAL);
      localfundsBudget.setSubmitTime(new Date());
      localfundsBudget.setProjectId(projectId);
      localfundsBudget.setBudgetYear(budgetYear);
      FundsBudget enterprisefundsBudget = new FundsBudget();
      enterprisefundsBudget.setPid(FoundSourceType.ENTERPRICE);
      enterprisefundsBudget.setSubmitTime(new Date());
      enterprisefundsBudget.setProjectId(projectId);
      enterprisefundsBudget.setBudgetYear(budgetYear);
      FundsBudget universityfundsBudget = new FundsBudget();
      universityfundsBudget.setPid(FoundSourceType.UNIVERSITY);
      universityfundsBudget.setSubmitTime(new Date());
      universityfundsBudget.setProjectId(projectId);
      universityfundsBudget.setBudgetYear(budgetYear);
      for (Map obj : list) {
        BudgetImportDetailNew budgetImportDetailNew = new BudgetImportDetailNew();
        BeanUtils.populate(budgetImportDetailNew, obj);
        //设置关联ID
        budgetImportDetailNew.setFileImportId(log.getId());
        //设置预算年份
        budgetImportDetailNew.setBudgetYear("2016");
        //获取统计数据到预算统计表中
        if ("1．素材制作".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("2．企业案例收集制作".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("3．课程开发".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("4．特殊工具软件制作".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("5．应用推广".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("6．调研论证".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("7．其他".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
        } else if ("6.2专家论证".equals(budgetImportDetailNew.getUseFor())) {
          totalfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
          countryfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
          localfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
          enterprisefundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
          universityfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));

        }
        this.budgetImport2016Repository.save(budgetImportDetailNew);

      }
      this.fundsBudgetRepository.save(totalfundsBudget);
      this.fundsBudgetRepository.save(countryfundsBudget);
      this.fundsBudgetRepository.save(localfundsBudget);
      this.fundsBudgetRepository.save(enterprisefundsBudget);
      this.fundsBudgetRepository.save(universityfundsBudget);
    }catch (Exception e){
      throw new MyException(e);
    }
  }

  public void importBudget2015FormFile(String projectId, String importUser, String budgetYear, File uploadFile) throws MyException {
    //TODO

  }

  /**
   * 删除
   * @param importId
   */
  public void delete(String importId) {
    this.budgetImport2016Repository.deleteByBudgetImportId(importId);
    this.fileImportLogRepository.deleteById(importId);

  }

  public Object update(BudgetImportDetailNew budgetImportDetailNew) {
    return this.budgetImport2016Repository.save(budgetImportDetailNew);
  }

  public Object getImportByProject(String projectId) {
    projectId = projectId ==null?"":projectId;
   return this.fileImportLogRepository.findByParam(projectId);
  }

  /**
   * 获取明细，返回基本导入信息和详细预算信息
   * @param importId
   * @return
   * @throws MyException
   */
  public Object get(String importId) throws MyException {
      FileImportLog obj  = this.fileImportLogRepository.findById(importId).get();
     return this.getDetail(obj);
  }

  private Object getDetail( FileImportLog obj) throws MyException {
    try {
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
   * 获取明细，返回基本导入信息和详细预算信息
   * @param projetId
   * @return
   * @throws MyException
   */
  public Object getByProject(String projetId) throws MyException {
      //TODO 预算如果导入多次怎么办,取最新的？
    FileImportLog log  = this.fileImportLogRepository.findByProjectIdAndImportType(projetId,ImportFileType.BUDGET2016);
    if(log !=null){
      return this.getDetail(log);
    }
    return null;
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

  public ProjectBudgetInfoView getBudgetForProject(String projectId) throws InvocationTargetException, IllegalAccessException {
    ProjectBudgetInfoView view = new ProjectBudgetInfoView();
    List<FundsBudget> list = fundsBudgetRepository.findByProjectId( projectId);
    for(FundsBudget fundsBudget:list){
      if(FoundSourceType.COUNTRY.equals(fundsBudget.getPid())){
        ResultInfoView resultInfoView = new ResultInfoView();
        BeanUtils.copyProperties(resultInfoView,fundsBudget);
        view.setCountryTotal(resultInfoView);
      }else if(FoundSourceType.LOCAL.equals(fundsBudget.getPid())){
        ResultInfoView resultInfoView = new ResultInfoView();
        BeanUtils.copyProperties(resultInfoView,fundsBudget);
        view.setLocal(resultInfoView);
      }else if(FoundSourceType.ENTERPRICE.equals(fundsBudget.getPid())){
        ResultInfoView resultInfoView = new ResultInfoView();
        BeanUtils.copyProperties(resultInfoView,fundsBudget);
        view.setEnterprise(resultInfoView);
      }else if(FoundSourceType.UNIVERSITY.equals(fundsBudget.getPid())){
        ResultInfoView resultInfoView = new ResultInfoView();
        BeanUtils.copyProperties(resultInfoView,fundsBudget);
        view.setUniversity(resultInfoView);
      }
    }
return view;

  }
}
