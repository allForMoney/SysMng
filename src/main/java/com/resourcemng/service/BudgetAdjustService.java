package com.resourcemng.service;

import com.resourcemng.Enum.*;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.repository.*;
import com.resourcemng.view.BudgetAdjustCompareView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

@Service
@Transactional
public class BudgetAdjustService {
  @Autowired
  BudgetAuditLogRepository budgetAuditLogRepository;
  @Autowired
  BudgetService budgetService;
  @Autowired
  FileImportLogRepository fileImportLogRepository;
  FundsBudgetRepository fundsBudgetRepository;
  BudgetImport2016Repository budgetImport2016Repository;
  /**
   * 预算调整
   * @param projectId
   * @param importUser
   * @param adjustType
   * @param requestFile
   * @param adjustFile
   * @param descriptionFile
   * @throws MyException
   */
  public Object adjust(String projectId,String importUser,String adjustType,File requestFile,File adjustFile,File descriptionFile ) throws MyException {
    List<FileImportLog> fileImportLogs = null;
    if(ImportFileType.BUDGET_ADJUST_2016.equals(adjustType)) {
      fileImportLogs = fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projectId, ImportFileType.BUDGET2016);
    }else{
      fileImportLogs = fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projectId, ImportFileType.BUDGET2015);

    }
    if(fileImportLogs == null || fileImportLogs.size()==0){
      throw new MyException("预算还没有导入，不能调整，请导入预算");
    }
    FileImportLog fileImportLog = fileImportLogs.get(0);
//    if(!fileImportLog.getImportType().equals(adjustType)){
//      throw new MyException("不支持不同格式的预算调整");
//    }
    if(ImportFileType.BUDGET_ADJUST_2016.equals(adjustType)){
//导入记录
      FileImportLog log = new FileImportLog();
      log.setFileName(adjustFile.getName());
      log.setDesFile(descriptionFile.getName());
      log.setRequestFile(requestFile.getName());
      log.setImportType(adjustType);
      log.setImportUserId(importUser);
      log.setProjectId(projectId);
      log.setImportDate(new Date());
      log = fileImportLogRepository.save(log);
    //调整审核记录
      BudgetAuditLog auditLog = new BudgetAuditLog();
      auditLog.setReportTime(new Date());
      auditLog.setAdjustId(log.getId());
      budgetAuditLogRepository.save(auditLog);
      //存储调整数据
      budgetService.importBudget2016FormFile (projectId, importUser, log);
      //导入数据
      List<BudgetImportDetailNew> list =budgetService.importBudget2016FormFile(projectId,importUser,log);
      //预计算
      budgetService.computeBudgetImport2016(projectId,list);
      return log;
    }else{
      //TODO
      return null;
    }




  }

  /**
   * 预算调整审核
   * @param id
   * @param auditType
   * @param auditContent
   */
  public void audit(String id, String auditType,String auditContent) {
    BudgetAuditLog log = budgetAuditLogRepository.findByAdjustId(id);
    if(log == null){
      log = new BudgetAuditLog();
      log.setAdjustId(id);
    }
    switch(auditType)
    {
      case AuditType.FINACE_AUDIT:
        log.setFinanceAuditState(AuditStatus.PASS);
        log.setFinanceAuditTime(new Date());
        log.setStatus(ReportStatus.F_PASS);
        break;
      case AuditType.SCHOOL_AUDIT:
        log.setSchoolAuditState(AuditStatus.PASS);
        log.setSchoolAuditTime(new Date());
        log.setStatus(ReportStatus.P_PASS);
        break;
      case AuditType.COUNTRY_AUDIT:
        log.setConutryAuditState(AuditStatus.PASS);
        log.setConutryAuditTime(new Date());
        log.setAuditOpinion(auditContent);
        log.setStatus(ReportStatus.COUNTRY_PASS);

        String importId = log.getAdjustId();
        FileImportLog fileImportLog = fileImportLogRepository.findById(importId).get();
        String projectId = fileImportLog.getProjectId();
        //审核通过，更新预计算数据
        //删除预计算数据
        fundsBudgetRepository.deleteByProjectId(projectId);

        if(ImportFileType.BUDGET_ADJUST_2016.equals(fileImportLog)){
          List<BudgetImportDetailNew> budgetImportDetailNews = budgetImport2016Repository.findByBudgetImportId(fileImportLog.getId());
          budgetService.computeBudgetImport2016(projectId,budgetImportDetailNews);
        }else{//2015预算调整审核
          //TODO
        }

        break;

    }
  }

  /**
   * 指定项目ID查找
   * @param projectId
   * @return
   */
  public List<BudgetAuditLog> find(String projectId) {
    if(StringUtils.isEmpty(projectId)){//查找所有预算
      return  budgetAuditLogRepository.findAll();

    }else{
      List<FileImportLog> budgetAdjusts = fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projectId,ImportFileType.BUDGET_ADJUST_2016);
      if(budgetAdjusts == null){
      budgetAdjusts = fileImportLogRepository.findByProjectIdAndImportTypeOrderByImportDate(projectId,ImportFileType.BUDGET_ADJUST);
      }
      if(budgetAdjusts == null){
        return null;
      }
      List<String> ids = new ArrayList<>();
      for(FileImportLog budgetAdjust:budgetAdjusts){
        ids.add(budgetAdjust.getId());
      }
      return  budgetAuditLogRepository.findByAdjustIdIn(ids);
    }

  }

  /**
   *指定预算审核记录的ID，查看调整资源的比较信息
   * @param id
   * @return
   */
  public Object getCompareInfo(String id) {
    BudgetAuditLog log = budgetAuditLogRepository.findByAdjustId(id);
    String importId = log.getAdjustId();
    FileImportLog fileImportLog = fileImportLogRepository.findById(importId).get();
    if(ImportFileType.BUDGET_ADJUST_2016.equals(fileImportLog)) {//2016

      List<BudgetImportDetailNew> budgetImportDetailNews = budgetImport2016Repository.findByBudgetImportId(fileImportLog.getId());
      if (budgetImportDetailNews == null) {
        return null;
      }

      List<BudgetAdjustCompareView> compareList = new ArrayList<BudgetAdjustCompareView>();
      for (BudgetImportDetailNew budgetImportDetailNew : budgetImportDetailNews) {
        BudgetAdjustCompareView budgetAdjustCompareView = new BudgetAdjustCompareView();
        BudgetImportDetailNew oldRecord = budgetImport2016Repository.findById(budgetImportDetailNew.getOriginalId()).get();
        budgetAdjustCompareView.setAfterAdjust(budgetImportDetailNew);
        budgetAdjustCompareView.setBeforeAdjust(oldRecord);
        compareList.add(budgetAdjustCompareView);
      }
      //返回比较数据
      return compareList;
    }else{
        return null;
    }
  }
}
