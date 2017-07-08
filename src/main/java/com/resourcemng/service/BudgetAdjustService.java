package com.resourcemng.service;

import com.resourcemng.Enum.*;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.repository.*;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.BudgetAdjustCompareView;
import com.resourcemng.view.BudgetAdjustView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
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
  @Autowired
  FundsBudgetRepository fundsBudgetRepository;
  @Autowired
  BudgetImport2016Repository budgetImport2016Repository;
  @Autowired
  ProjectRepository projectRepository;
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
//      //预计算
//      budgetService.computeBudgetImport2016(projectId,list);
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
   * @return
   */
  public Page find(String projectNo,String majorName,String schoolName, Pageable  pageable ) throws InvocationTargetException, IllegalAccessException {
    projectNo = projectNo ==null?"":projectNo;
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

    List<FileImportLog> budgetAdjustsImportLog = fileImportLogRepository.findByProjectIdInAndImportTypeOrderByImportDate(projectIds,ImportFileType.BUDGET_ADJUST_2016);
    if(budgetAdjustsImportLog == null){
      budgetAdjustsImportLog = fileImportLogRepository.findByProjectIdInAndImportTypeOrderByImportDate(projectIds,ImportFileType.BUDGET_ADJUST);
    }
    if(budgetAdjustsImportLog == null){
      return null;
    }
    List<String> ids = new ArrayList<>();
    Map<String,FileImportLog> adjustMap = new HashMap();
    for(FileImportLog budgetAdjust:budgetAdjustsImportLog){
      ids.add(budgetAdjust.getId());
      adjustMap.put(budgetAdjust.getId(),budgetAdjust);
    }
    Page result =   budgetAuditLogRepository.findByAdjustIdIn(ids,pageable);
    List<BudgetAuditLog> adjustList = result.getContent();
    if(adjustList == null){
      return result;
    }
    List adjustViewList = new ArrayList();
    for(BudgetAuditLog budgetAuditLog:adjustList){
      BudgetAdjustView view = new BudgetAdjustView();
      BeanUtils.copyProperties(view,budgetAuditLog);
      FileImportLog fileImportLog= adjustMap.get(budgetAuditLog.getAdjustId());
      Project project= projectMap.get(fileImportLog.getProjectId());
      BeanUtils.copyProperties(view,project);
      adjustViewList.add(view);
    }
    return new PageImpl(adjustViewList,result.getPageable(),result.getTotalElements());

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

  public Object getById(String adjustId) {
    FileImportLog fileImportLog = fileImportLogRepository.findById(adjustId).get();
    return fileImportLog;
  }
}
