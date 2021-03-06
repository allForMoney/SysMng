package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.resourcemng.Enum.FoundSourceType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.ReportStatus;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.handler.BudgetImportHanlderOld;
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
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    List<FileImportLog>    fileImportLogs  = this.fileImportLogRepository.findBudgetImportByProjectId(projectId);
    if(fileImportLogs != null && fileImportLogs.size()>0) {//不让重复导入？
      throw new MyException("预算已经导入过，不能重复导入，请删除后重试");
    }
    if(ImportFileType.BUDGET2016.equals(importType)){
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
      FileImportLog log = new FileImportLog();
      log.setFileName(uploadFile.getName());
      log.setImportType(ImportFileType.BUDGET2015);
      log.setImportUserId(importUser);
      log.setProjectId(projectId);
      log.setImportDate(new Date());
      log = fileImportLogRepository.save(log);
      //导入数据
      List<BudgetImportDetailOld> list =this.importBudget2015FormFile(projectId,importUser,log);
      //预计算
      this.computeBudgetImport2015(projectId,list);
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
   if ("1".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
   } else if ("2".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
   } else if ("3".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
   } else if ("4".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
   } else if ("5".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
   } else if ("6".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
   } else if ("7".equals(budgetImportDetailNew.getSequenceNo())) {
     totalfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
     countryfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
     localfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
     enterprisefundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
     universityfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));
//   } else if ("6.2专家论证".equals(budgetImportDetailNew.getUseFor())) {
//     totalfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getTotalMoney()));
//     countryfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getCountryTotal()));
//     localfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getLocal()));
//     enterprisefundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getEnterprise()));
//     universityfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailNew.getUniversity()));

   }
 }
    this.fundsBudgetRepository.save(totalfundsBudget);
    this.fundsBudgetRepository.save(countryfundsBudget);
    this.fundsBudgetRepository.save(localfundsBudget);
    this.fundsBudgetRepository.save(enterprisefundsBudget);
    this.fundsBudgetRepository.save(universityfundsBudget);

  }



  public List<BudgetImportDetailOld> importBudget2015FormFile(String projectId, String importUser,FileImportLog log) throws MyException {
    try {
      ImportParams params = new ImportParams();
      //设置标题行
      params.setTitleRows(5);
      params.setHeadRows(5);
      //设置读取的有效行数
//      params.setReadRows(28);
      Map<Integer, String> map = new HashMap<Integer, String>();
      for (int i = 0; i < 19; i++) {//此处先按顺序给标题，在handler中转换标题
        map.put(i, Integer.toString(i));

      }
      params.setTitlemap(map);
      //转换标题
      params.setDataHanlder(new BudgetImportHanlderOld());
      long start = new Date().getTime();
      File file = fileUtil.getFile(log.getFileName());
      List<Map<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, params);

      List<BudgetImportDetailOld> budgetImportDetailOlds = new ArrayList<>();
      for (Map obj : list) {
        BudgetImportDetailOld budgetImportDetailOld = new BudgetImportDetailOld();
        if(StringUtils.isEmpty(obj.get("useFor"))){//没有用途的数据忽略
          continue;
        }
        BeanUtils.populate(budgetImportDetailOld, obj);
        //设置关联ID
        budgetImportDetailOld.setFileImportId(log.getId());
//        //设置预算年份
//        budgetImportDetailOld.setBudgetYear("2016");
        budgetImportDetailOlds.add(budgetImportDetailOld);


      }
      this.budgetImportRepository.saveAll(budgetImportDetailOlds);
      return budgetImportDetailOlds;
    }catch (Exception e){
      throw new MyException(e);
    }
  }

  /**
   * 根据数据与计算
   * @param projectId
   * @param budgetImportDetailOlds
   */
  public void computeBudgetImport2015(String projectId, List<BudgetImportDetailOld> budgetImportDetailOlds) {
      if(budgetImportDetailOlds==null){
        return;
      }
    List<BudgetImportDetailOld> list = budgetImportDetailOlds.stream().filter(detail->(!StringUtils.isEmpty(detail.getSequenceNo()))).collect(Collectors.toList());
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
    for(BudgetImportDetailOld budgetImportDetailOld:list){
      double localValue = Arrays.asList(budgetImportDetailOld.getLocalFirstYear(),budgetImportDetailOld.getLocalSecondYear(),budgetImportDetailOld.getLocalThreeYear()).stream().mapToDouble(v->Double.parseDouble(v)).sum();
      double enterpriceValue = Arrays.asList(budgetImportDetailOld.getEnterpriseFirstYear(),budgetImportDetailOld.getEnterpriseSecondYear(),budgetImportDetailOld.getEnterpriseThreeYear()).stream().mapToDouble(v->Double.parseDouble(v)).sum();
      double universityValue = Arrays.asList(budgetImportDetailOld.getUniversityFirstYear(),budgetImportDetailOld.getUniversitySecondYear(),budgetImportDetailOld.getUniversityThreeYear()).stream().mapToDouble(v->Double.parseDouble(v)).sum();

      //获取统计数据到预算统计表中
      if ("1".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setMaterialMake(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
       localfundsBudget.setMaterialMake(new BigDecimal(localValue));
        enterprisefundsBudget.setMaterialMake(new BigDecimal(enterpriceValue));
        universityfundsBudget.setMaterialMake(new BigDecimal(universityValue));
      } else if ("2".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setCompanyCase(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setCompanyCase(new BigDecimal(localValue));;
        enterprisefundsBudget.setCompanyCase(new BigDecimal(enterpriceValue));
        universityfundsBudget.setCompanyCase(new BigDecimal(universityValue));
      } else if ("3".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setCourseDevelopment(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setCourseDevelopment(new BigDecimal(localValue));;
        enterprisefundsBudget.setCourseDevelopment(new BigDecimal(enterpriceValue));
        universityfundsBudget.setCourseDevelopment(new BigDecimal(universityValue));
      } else if ("4".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setToolSoftware(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setToolSoftware(new BigDecimal(localValue));;
        enterprisefundsBudget.setToolSoftware(new BigDecimal(enterpriceValue));
        universityfundsBudget.setToolSoftware(new BigDecimal(universityValue));
      } else if ("5".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setApplicationPromote(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setApplicationPromote(new BigDecimal(localValue));;
        enterprisefundsBudget.setApplicationPromote(new BigDecimal(enterpriceValue));
        universityfundsBudget.setApplicationPromote(new BigDecimal(universityValue));
      } else if ("6".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setResearchProve(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setResearchProve(new BigDecimal(localValue));;
        enterprisefundsBudget.setResearchProve(new BigDecimal(enterpriceValue));
        universityfundsBudget.setResearchProve(new BigDecimal(universityValue));
      } else if ("7".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setOtherFee(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setOtherFee(new BigDecimal(localValue));;
        enterprisefundsBudget.setOtherFee(new BigDecimal(enterpriceValue));
        universityfundsBudget.setOtherFee(new BigDecimal(universityValue));
      } else if ("8".equals(budgetImportDetailOld.getSequenceNo())) {
        totalfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailOld.getTotalMoney()));
        countryfundsBudget.setExpertConsult(BigDecimalUtil.getValueForString(budgetImportDetailOld.getCountryTotal()));
        localfundsBudget.setExpertConsult(new BigDecimal(localValue));;
        enterprisefundsBudget.setExpertConsult(new BigDecimal(enterpriceValue));
        universityfundsBudget.setExpertConsult(new BigDecimal(universityValue));

      }
    }
    this.fundsBudgetRepository.save(totalfundsBudget);
    this.fundsBudgetRepository.save(countryfundsBudget);
    this.fundsBudgetRepository.save(localfundsBudget);
    this.fundsBudgetRepository.save(enterprisefundsBudget);
    this.fundsBudgetRepository.save(universityfundsBudget);

  }

  /**
   * 删除
   * @param importId
   */
  public void delete(String importId) {
    FileImportLog importLog = this.fileImportLogRepository.findById(importId).get();
    if(ImportFileType.BUDGET2016.equals(importLog.getImportType())){
      this.budgetImport2016Repository.deleteByBudgetImportId(importId);
    }else{
      this.budgetImportRepository.deleteByBudgetImportId(importId);
    }
    this.fileImportLogRepository.deleteById(importId);

  }

  public Object update(BudgetImportDetailNew budgetImportDetailNew) {
    return this.budgetImport2016Repository.save(budgetImportDetailNew);
  }

  /**
   *
   * @param budgetImportDetailOld
   * @return
   */
  public Object update2015(BudgetImportDetailOld budgetImportDetailOld) {
    return this.budgetImportRepository.save(budgetImportDetailOld);
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
      if(ImportFileType.BUDGET2016.equals(obj.getImportType()) ||ImportFileType.BUDGET_ADJUST_2016.equals(obj.getImportType())) {//2016
        List<BudgetImportDetailNew> detail = this.budgetImport2016Repository.findByBudgetImportId(obj.getId());
        view.setBudgetImportDetaillList(detail);
      } else{//2015
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
   * 获取明细，返回基本导入信息和详细预算信息,获取最后一次调整的数据
   * @param projetId
   * @return
   * @throws MyException
   */
  public BudgetImportView getByProject(String projetId) throws MyException {
    //先看看有没有已经审核通过的调整记录
      //TODO 预算如果导入多次怎么办,取最新的？

//    List<FileImportLog> allLogs = new ArrayList<>();
    //获取调整记录
//    List<FileImportLog>   logs2016  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDateDesc(projetId,ImportFileType.BUDGET_ADJUST_2016);
//    List<FileImportLog>   logs2015  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDateDesc(projetId,ImportFileType.BUDGET_ADJUST);
//    if(logs2016 !=null) {
//      allLogs.addAll(logs2016);
//    }
//    if(logs2015 !=null) {
//      allLogs.addAll(logs2015);
//    }
    //预算调整记录
    List<FileImportLog>  allLogs = fileImportLogRepository.findBudgetAdjustImportByProjectId(projetId);

    FileImportLog fileImportLog =null;
    if(allLogs !=null &&  allLogs.size()>0){
      List<BudgetAuditLog> allBudgetAuditLog =  budgetAuditLogRepository.findByStatusOrderByConutryAuditTimeDesc(ReportStatus.COUNTRY_PASS);
      if(allBudgetAuditLog !=null &&allBudgetAuditLog.size() >0) {
        //拿到所有的审核记录，判断最新的审核通过的数据
        Map<String, FileImportLog> importLog = new HashMap<>();
        for (FileImportLog temp : allLogs) {
          importLog.put(temp.getId(), temp);
        }
        for (BudgetAuditLog budgetAuditLog : allBudgetAuditLog) {//匹配
          fileImportLog = importLog.get(budgetAuditLog.getAdjustId());
         if(fileImportLog!=null){
           break;
         }

        }
      }

    }
    if(fileImportLog ==null){//没有预算调整，获取预算导入的数据
//      allLogs  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDateDesc(projetId,ImportFileType.BUDGET2016);
//      if(allLogs !=null &&  allLogs.size()>0){//先获取2016导入数据
//        fileImportLog = allLogs.get(0);
//      }else{
//        allLogs  = this.fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDateDesc(projetId,ImportFileType.BUDGET2015);
//        if(allLogs !=null &&  allLogs.size()>0){
//          fileImportLog = allLogs.get(0);
//        }
//      }
      allLogs = fileImportLogRepository.findBudgetImportByProjectId(projetId);
      if(allLogs !=null &&  allLogs.size()>0){//获取最新的
          fileImportLog = allLogs.get(0);
        }
    }
    if(fileImportLog ==null){
      return null;
    }
    //先取调整数据再取导入数据

      return this.getDetail(fileImportLog);
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
    TemplateExportParams params =null;
//导出文件
    if(ImportFileType.BUDGET2016.equals(budgetImportView.getImportType()) || ImportFileType.BUDGET_ADJUST_2016.equals(budgetImportView.getImportType()) )  {
      params = new TemplateExportParams(
        fileUtil.getTempleteFilePath("templete/预算2016模板.xlsx"), true);
    }else{
      params = new TemplateExportParams(
        fileUtil.getTempleteFilePath("templete/预算2015模板.xlsx"), true);
    }
    Map<String,Object> tempDataMap = new HashMap<>();
    tempDataMap.put("project",project);
    tempDataMap.put("resultlist",budgetImportView.getBudgetImportDetaillList());
    Workbook workbook = ExcelExportUtil.exportExcel(params, tempDataMap);
    map.put("project",project);
    map.put("workbook",workbook);

    return map;
  }

}
