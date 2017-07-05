package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.resourcemng.Enum.FoundSourceType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
  @Autowired
  BudgetAuditLogRepository budgetAuditLogRepository;
  @Autowired
  FileUitl fileUtil;
  /**
   *
   * @param projectId
   * @param importUser
   * @param importType
   * @param uploadFile
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public void importBudgetFormFile(String projectId, String importUser,String importType, File uploadFile) throws MyException {

    if(ImportFileType.BUDGET2016.equals(importType)){
      List<FileImportLog>    fileImportLogs  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projectId,importType);
      if(fileImportLogs != null && fileImportLogs.size()>0) {//不让重复导入？
        throw new MyException("预算已经导入过，不能重复导入，请删除后重试");
      }
      FileImportLog log = new FileImportLog();
      log.setFileName(uploadFile.getName());
      log.setImportType(ImportFileType.BUDGET2016);
      log.setImportUserId(importUser);
      log.setProjectId(projectId);
      log.setImportDate(new Date());
      log = fileImportLogRepository.save(log);
      //导入数据
      List<BudgetImportDetailNew> list =this.importBudget2016FormFile(projectId,importUser,log);
      //预计算
      this.computeBudgetImport2016(projectId,list);
    }else{
      this.importBudget2015FormFile(projectId,importUser,uploadFile,null,null);
    }
  }


  public  List<BudgetImportDetailNew> importBudget2016FormFile(String projectId, String importUser,FileImportLog log) throws MyException {
    try {
      ImportParams params = new ImportParams();
      //设置标题行
      params.setTitleRows(5);
      params.setHeadRows(5);
      //设置读取的有效行数
      params.setReadRows(28);
      Map<Integer, String> map = new HashMap<Integer, String>();
      for (int i = 0; i < 21; i++) {//此处先按顺序给标题，在handler中转换标题
        map.put(i, Integer.toString(i));

      }
      params.setTitlemap(map);
      //转换标题
      params.setDataHanlder(new BudgetImportHanlder());
      long start = new Date().getTime();
      File file = fileUtil.getFile(log.getFileName());
      List<Map<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, params);




      List<BudgetImportDetailNew> budgetImportDetailNews = new ArrayList<>();
      for (Map obj : list) {
        BudgetImportDetailNew budgetImportDetailNew = new BudgetImportDetailNew();
        BeanUtils.populate(budgetImportDetailNew, obj);
        //设置关联ID
        budgetImportDetailNew.setFileImportId(log.getId());
        //设置预算年份
        budgetImportDetailNew.setBudgetYear("2016");

        budgetImportDetailNews.add(budgetImportDetailNew);


      }
      this.budgetImport2016Repository.saveAll(budgetImportDetailNews);
      return budgetImportDetailNews;
    }catch (Exception e){
      throw new MyException(e);
    }
  }
  /**
   * 根据数据与计算
   * @param projectId
   * @param budgetImportDetailNews
   */
  public void computeBudgetImport2016(String projectId, List<BudgetImportDetailNew> budgetImportDetailNews){
    //保存统计信息
    FundsBudget totalfundsBudget = new FundsBudget();
    totalfundsBudget.setPid(FoundSourceType.TOTAL);
    totalfundsBudget.setSubmitTime(new Date());
    totalfundsBudget.setProjectId(projectId);
    FundsBudget countryfundsBudget = new FundsBudget();
    countryfundsBudget.setPid(FoundSourceType.COUNTRY);
    countryfundsBudget.setSubmitTime(new Date());
    countryfundsBudget.setProjectId(projectId);
    FundsBudget localfundsBudget = new FundsBudget();
    localfundsBudget.setPid(FoundSourceType.LOCAL);
    localfundsBudget.setSubmitTime(new Date());
    localfundsBudget.setProjectId(projectId);
    FundsBudget enterprisefundsBudget = new FundsBudget();
    enterprisefundsBudget.setPid(FoundSourceType.ENTERPRICE);
    enterprisefundsBudget.setSubmitTime(new Date());
    enterprisefundsBudget.setProjectId(projectId);
    FundsBudget universityfundsBudget = new FundsBudget();
    universityfundsBudget.setPid(FoundSourceType.UNIVERSITY);
    universityfundsBudget.setSubmitTime(new Date());
    universityfundsBudget.setProjectId(projectId);
 for(BudgetImportDetailNew budgetImportDetailNew:budgetImportDetailNews){
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
 }
    this.fundsBudgetRepository.save(totalfundsBudget);
    this.fundsBudgetRepository.save(countryfundsBudget);
    this.fundsBudgetRepository.save(localfundsBudget);
    this.fundsBudgetRepository.save(enterprisefundsBudget);
    this.fundsBudgetRepository.save(universityfundsBudget);

  }



  public void importBudget2015FormFile(String projectId, String importUser,  File uploadFile,File requestFile,File descriptionFile) throws MyException {
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

  private BudgetImportView getDetail( FileImportLog obj) throws MyException {
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
  public BudgetImportView getByProject(String projetId) throws MyException {
    //先看看有没有已经审核通过的调整记录
      //TODO 预算如果导入多次怎么办,取最新的？

    List<FileImportLog>   logs  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projetId,ImportFileType.BUDGET_ADJUST_2016);
    if(logs ==null ||  logs.size()==0){
      logs  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projetId,ImportFileType.BUDGET2016);
    }
    //先取调整数据再取导入数据
    if(logs ==null ||  logs.size()==0){
      return null;
    }
      FileImportLog fileImportLog = logs.get(0);
      return this.getDetail(fileImportLog);
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
    //下面计算总计，本来可以在存入的时候将总计数据存入，但之前版本的数据没有存放统计数据，只好取出来只好求和。
    List<ResultInfoView> budgetList = new ArrayList();
    budgetList.add(view.getCountryTotal());
    budgetList.add(view.getEnterprise());
    budgetList.add(view.getLocal());
    budgetList.add(view.getUniversity());
    ResultInfoView total = new ResultInfoView();
    total = budgetList.stream().reduce(total,(resultInfoView, element) -> {
      resultInfoView.setMaterialMake(BigDecimalUtil.sum(resultInfoView.getMaterialMake(),element.getMaterialMake()));
      resultInfoView.setApplicationPromete(BigDecimalUtil.sum(resultInfoView.getApplicationPromete(),element.getApplicationPromete()));
      resultInfoView.setCompanyCase(BigDecimalUtil.sum(resultInfoView.getCompanyCase(),element.getCompanyCase()));
      resultInfoView.setCourseDevelopment(BigDecimalUtil.sum(resultInfoView.getCourseDevelopment(),element.getCourseDevelopment()));
      resultInfoView.setExpertConsult(BigDecimalUtil.sum(resultInfoView.getExpertConsult(),element.getExpertConsult()));
      resultInfoView.setOtherFee(BigDecimalUtil.sum(resultInfoView.getOtherFee(),element.getOtherFee()));
      resultInfoView.setResearchProve(BigDecimalUtil.sum(resultInfoView.getResearchProve(),element.getResearchProve()));
      resultInfoView.setSpecialTool(BigDecimalUtil.sum(resultInfoView.getSpecialTool(),element.getSpecialTool()));
      resultInfoView.setTotal(BigDecimalUtil.sum(resultInfoView.getTotal(),element.getTotal()));
      return resultInfoView;
    });
    view.setTotal(total);
  return view;

  }

  /**
   *导出项目预算
   * @param projectId
   * @return
   */
  public Map<String,Object> exportBudget(String projectId) throws MyException {
    Map<String,Object> map = new HashMap<>();
    BudgetImportView budgetImportView = getByProject(projectId);
   Project project =  this.projectRepository.findById(projectId).get();
   //
//导出文件
    TemplateExportParams params = new TemplateExportParams(
      ApplicationUitl.getWebRootPath("templete/预算2016模板.xlsx"), true);
    Map<String,Object> tempDataMap = new HashMap<>();
    tempDataMap.put("project",project);
    tempDataMap.put("resultlist",budgetImportView.getBudgetImportDetaillList());
    Workbook workbook = ExcelExportUtil.exportExcel(params, tempDataMap);
    map.put("project",project);
    map.put("workbook",workbook);

    return map;
  }
}
