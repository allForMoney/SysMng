package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.resourcemng.Enum.*;
import com.resourcemng.entitys.*;
import com.resourcemng.repository.*;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.view.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BudgetResultService {
  @Autowired
  FundsInRepository fundsInRepository;
  @Autowired
  FundsOutRepository fundsOutRepository;
  @Autowired
  FundsBudgetRepository fundsBudgetRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository tUserRepository;
  @Autowired
  ReportAuditLogRepository reportAuditLogRepository;
  @Autowired
  BudgetService budgetService;

  /**
   *
   * @return
   */
  public Object findByParam(String projectNo, String majorName, String schoolName, Pageable pageable) {
    projectNo = projectNo ==null?"":projectNo;
    majorName = majorName ==null?"":majorName;
    schoolName = schoolName ==null?"":schoolName;
    List<Project> projects =  projectRepository.findByProjectNoLikeAndMajorNameLikeAndSchoolNameLike(projectNo,majorName,schoolName );
    List ids = new ArrayList();
    if(projects  !=null) {
      ids = projects.stream().map(Project::getId).collect(Collectors.toList());
    }
    return this.reportAuditLogRepository.findByProjectIdIn(ids,pageable);
  }

  /**
   * 添加季报
   * @param view
   */
  public void quarterlyReport(BudgetReportView view) {
    //更新详细信息
    view.update();
    Tuser user = tUserRepository.findById(view.getUserId()).get();
    //获取项目ID
    String projectNo = user.getUsername().substring(0,user.getUsername().lastIndexOf("-"));
    Project project = projectRepository.findByProjectNo(projectNo);
    view.setProjectId(project.getId());
    //审核记录
    ReportAuditLog log = reportAuditLogRepository.findByParam(view.getProjectId(),view.getQuarterNum(),view.getProjectYear());
    if(log ==null) {
      log = new ReportAuditLog();
      log.setProjectId(view.getProjectId());
      log.setYear(view.getProjectYear());
      log.setQuarter(view.getQuarterNum());
      log.setReportTime(new Date());
    }
    reportAuditLogRepository.save(log);
    //提交保存季报收入
    List<FundsIn> fundsIns = view.getFundsIns();
    for(FundsIn fundsIn:fundsIns){
      fundsInRepository.save(fundsIn);
    }
    //提交保存季报支出
    List<FundsOut> fundsOuts = view.getFundsOuts();
    for(FundsOut fundsOut:fundsOuts){
      fundsOutRepository.save(fundsOut);
    }
  }

  /**
   * 查询季报明细
   * @param projectId
   * @param projectYear
   * @param quarterNum
   * @return
   */
  public BudgetReportView getQuarterlyDetail(String projectId, String projectYear, String quarterNum) {
    //根据项目编号查询项目
   Project project =  projectRepository.findById(projectId).get();
   //查找关联的用户
    Tuser user = tUserRepository.findByUserNo(project.getProjectNo() +"-1");

    //查找数据
    BudgetReportView view = new BudgetReportView();
    view.setProjectYear(projectYear);
    view.setQuarterNum(quarterNum);
    view.setProjectId(project.getId());
    //审核记录
    ReportAuditLog log = reportAuditLogRepository.findByParam(projectId,quarterNum,projectYear);
    if(log == null){
      view.setAuditStatus(ReportStatus.UN_FILL);
      return view;
    }
    List<FundsIn>  fundsIns =  fundsInRepository.findByParams(user.getId(),quarterNum,projectYear);
    List<FundsOut>  fundsOuts =  fundsOutRepository.findByParams(user.getId(),quarterNum,projectYear);

    view.setAuditStatus(EntityUitl.getReportAuditLogStatus(log));//返回状态

    view.setFundsIns(fundsIns);
    view.setFundsOuts(fundsOuts);
    view.setUserId(user.getId());
    return view;
  }

  public void quarterlyReportUpdate(BudgetReportView view) {
    this.quarterlyReportDelete(view);
   this.quarterlyReport(view);
  }

  /**
   * 删除
   * @param view
   */
  public void quarterlyReportDelete(BudgetReportView view) {
    fundsInRepository.deleteByUserIdAndQuarterNumAndProjectYear(view.getUserId(),view.getQuarterNum(),view.getProjectYear());
    fundsOutRepository.deleteByUserIdAndQuarterNumAndProjectYear(view.getUserId(),view.getQuarterNum(),view.getProjectYear());
  }

  /**
   *
   * @param projectId
   * @param reportYear
   * @param quarterNum
   * @param auditType
   */
  public void quarterlyReportAudit(String projectId,String reportYear,String quarterNum ,String auditType ,String auditStatus) {
    ReportAuditLog log = reportAuditLogRepository.findByParam(projectId,quarterNum,reportYear);
    if(log == null){
      log = new ReportAuditLog();
      log.setProjectId(projectId);
      log.setYear(reportYear);
      log.setQuarter(quarterNum);
    }
    switch(auditType)
    {
      case AuditType.FINACE_AUDIT:
        log.setFinanceAuditState(auditStatus);//AuditStatus ，通过不通过
        log.setFinanceAuditTime(new Date());
        if(AuditStatus.PASS.equals(auditStatus)) {
          log.setStatus(ReportStatus.F_PASS);
        }
        break;
      case AuditType.SCHOOL_AUDIT:
        log.setSchoolAuditState(auditStatus);
        log.setSchoolAuditTime(new Date());
        if(AuditStatus.PASS.equals(auditStatus)) {
          log.setStatus(ReportStatus.P_PASS);
        }
        break;
      case AuditType.COUNTRY_AUDIT:
        log.setConutryAuditState(auditStatus);
        log.setConutryAuditTime(new Date());
//        log.setAuditOpinion(auditContent);
        if(AuditStatus.PASS.equals(auditStatus)) {
          log.setStatus(ReportStatus.COUNTRY_PASS);
        }

        break;

    }

  }

//  /**
//   * 文件下载
//   * @param projectId
//   * @param projectYear
//   * @param quarterNum
//   * @return
//   */
//  public Workbook exportFile(String projectId, String projectYear, String quarterNum) throws InvocationTargetException,
//    IllegalAccessException {
//    ProjectResultDownloadView view = new ProjectResultDownloadView();
//    //项目信息
//    Project project = projectRepository.findById(projectId).get();
//    //TODO 校验
//
//    //预算信息
//    ProjectBudgetInfoView projectBudgetInfoView = budgetService.getBudgetForProject(projectId);
//    //TODO 校验
//    view.setBudget(projectBudgetInfoView);
//    view.setProject(project);
//    view.setProjectYear(projectYear);
//    view.setQuarterNum(quarterNum);
//    Tuser user = tUserRepository.findByUserNo(project.getProjectNo() + "-1");
//    //收入信息
//    List<FundsIn> fundsIns = fundsInRepository.findByParams(user.getId(), quarterNum, projectYear);
//    ProjectFundInInfoView projectFundInInfoView = new ProjectFundInInfoView();
//    if (fundsIns != null) {
//      BigDecimal totalFundsIn = new BigDecimal(0);
//      for (FundsIn fundsIn : fundsIns) {
//        if (FoundSourceType.COUNTRY.equals(fundsIn.getPid())) {
//          projectFundInInfoView.setCountryTotal(fundsIn.getAmountMoney());
//          projectFundInInfoView.setCountryPrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getCountryTotal().getTotal()));
//          totalFundsIn = totalFundsIn.add(fundsIn.getAmountMoney());
//        } else if (FoundSourceType.LOCAL.equals(fundsIn.getPid())) {
//          projectFundInInfoView.setLocal(fundsIn.getAmountMoney());
//          projectFundInInfoView.setLocalPrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getLocal().getTotal()));
//          totalFundsIn =totalFundsIn.add(fundsIn.getAmountMoney());
//        } else if (FoundSourceType.ENTERPRICE.equals(fundsIn.getPid())) {
//          projectFundInInfoView.setEnterprise(fundsIn.getAmountMoney());
//          projectFundInInfoView.setEnterprisePrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getEnterprise().getTotal()));
//          totalFundsIn =totalFundsIn.add(fundsIn.getAmountMoney());
//        } else if (FoundSourceType.UNIVERSITY.equals(fundsIn.getPid())) {
//          projectFundInInfoView.setUniversity(fundsIn.getAmountMoney());
//          projectFundInInfoView.setUniversityPrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getUniversity().getTotal()));
//          totalFundsIn = totalFundsIn.add(fundsIn.getAmountMoney());
//
//        }
//
//      }
//      //设置总输入
//      projectFundInInfoView.setTotal(totalFundsIn);
//      projectFundInInfoView.setPrecent(BigDecimalUtil.percent(totalFundsIn,projectBudgetInfoView.getTotal().getTotal()));
//
//    }
//    view.setFundin(projectFundInInfoView);
//
//    //支出信息
//    List<FundsOut> fundsOuts = fundsOutRepository.findByParams(user.getId(), quarterNum, projectYear);
//    ProjectFundOutInfoView projectFundOutInfoView = new ProjectFundOutInfoView();
//    if (fundsOuts != null) {
//      FundsOut totalFundsOut = new FundsOut();
//      for (FundsOut fundsOut : fundsOuts) {
//        if (FoundSourceType.COUNTRY.equals(fundsOut.getPid())) {
//          projectFundOutInfoView.setCountryTotal(this.getResultPrecentInfoView(projectBudgetInfoView.getCountryTotal(),fundsOut));
//          getTotalFundsOut(totalFundsOut,fundsOut);
//        } else if (FoundSourceType.LOCAL.equals(fundsOut.getPid())) {
//          projectFundOutInfoView.setLocal(this.getResultPrecentInfoView(projectBudgetInfoView.getLocal(),fundsOut));
//          getTotalFundsOut(totalFundsOut,fundsOut);
//        } else if (FoundSourceType.ENTERPRICE.equals(fundsOut.getPid())) {
//          projectFundOutInfoView.setEnterprise(this.getResultPrecentInfoView(projectBudgetInfoView.getEnterprise(),fundsOut));
//          getTotalFundsOut(totalFundsOut,fundsOut);
//        } else if (FoundSourceType.UNIVERSITY.equals(fundsOut.getPid())) {
//          projectFundOutInfoView.setUniversity(this.getResultPrecentInfoView(projectBudgetInfoView.getUniversity(),fundsOut));
//          getTotalFundsOut(totalFundsOut,fundsOut);
//
//        }
//
//      }
//      //设置总支出
//      projectFundOutInfoView.setTotal(this.getResultPrecentInfoView(projectBudgetInfoView.getTotal(),totalFundsOut));
//
//    }
//    view.setFundout(projectFundOutInfoView);
//
//    //导出文件
//    TemplateExportParams params = new TemplateExportParams(
//      ApplicationUitl.getWebRootPath("templete/预算执行情况模板.xlsx"), true);
////    TemplateExportParams params = new TemplateExportParams("E:\\workspace\\SysResourceMngNew\\target\\test-classes\\templete\\预算执行情况模板.xlsx", true);
//    Map<String, Object> map = new HashMap<>();
//    map.put("project",view.getProject());
//    map.put("budget",view.getBudget());
//    map.put("foudin",view.getFundin());
//    map.put("fundout",view.getFundout());
//    map.put("projectYear",view.getProjectYear());
//    map.put("quarterNum",view.getQuarterNum());
//    Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//    return workbook;
//  }
  /**
   * 文件下载
   * @param projectId
   * @param projectYear
   * @param quarterNum
   * @return
   */
  public Workbook exportFile(String projectId, String projectYear, String quarterNum) throws InvocationTargetException,
    IllegalAccessException {

    //TODO 校验
    Map computeData = this.computeData(projectId,projectYear,quarterNum);
    TemplateExportParams params = new TemplateExportParams(
      ApplicationUitl.getWebRootPath("templete/预算执行情况模板.xlsx"), true);
    Workbook workbook = ExcelExportUtil.exportExcel(params, computeData);
    return workbook;
  }


  private  Map computeData(String projectId, String projectYear, String quarterNum) throws InvocationTargetException, IllegalAccessException {
    projectYear = projectYear==null?"":projectYear;
    quarterNum = quarterNum==null?"":quarterNum;
    //项目信息
    Project project = projectRepository.findById(projectId).get();
    //预算信息
    ProjectBudgetInfoView projectBudgetInfoView = budgetService.getBudgetForProject(projectId);
    //TODO 校验
    Tuser user = tUserRepository.findByUserNo(project.getProjectNo() + "-1");
    //收入信息
      List<FundsIn> fundsIns = fundsInRepository.findByParamsLike(user.getId(), quarterNum, projectYear);
    ProjectFundInInfoView projectFundInInfoView = new ProjectFundInInfoView();
    if (fundsIns != null) {
      BigDecimal totalFundsIn = new BigDecimal(0);
      for (FundsIn fundsIn : fundsIns) {
        if (FoundSourceType.COUNTRY.equals(fundsIn.getPid())) {
          projectFundInInfoView.setCountryTotal(fundsIn.getAmountMoney());
          projectFundInInfoView.setCountryPrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getCountryTotal().getTotal()));
          totalFundsIn = totalFundsIn.add(fundsIn.getAmountMoney());
        } else if (FoundSourceType.LOCAL.equals(fundsIn.getPid())) {
          projectFundInInfoView.setLocal(fundsIn.getAmountMoney());
          projectFundInInfoView.setLocalPrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getLocal().getTotal()));
          totalFundsIn =totalFundsIn.add(fundsIn.getAmountMoney());
        } else if (FoundSourceType.ENTERPRICE.equals(fundsIn.getPid())) {
          projectFundInInfoView.setEnterprise(fundsIn.getAmountMoney());
          projectFundInInfoView.setEnterprisePrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getEnterprise().getTotal()));
          totalFundsIn =totalFundsIn.add(fundsIn.getAmountMoney());
        } else if (FoundSourceType.UNIVERSITY.equals(fundsIn.getPid())) {
          projectFundInInfoView.setUniversity(fundsIn.getAmountMoney());
          projectFundInInfoView.setUniversityPrecent(BigDecimalUtil.percent(fundsIn.getAmountMoney(),projectBudgetInfoView.getUniversity().getTotal()));
          totalFundsIn = totalFundsIn.add(fundsIn.getAmountMoney());

        }

      }
      //设置总输入
      projectFundInInfoView.setTotal(totalFundsIn);
      projectFundInInfoView.setPrecent(BigDecimalUtil.percent(totalFundsIn,projectBudgetInfoView.getTotal().getTotal()));

    }

    //支出信息
    List<FundsOut> fundsOuts = fundsOutRepository.findByParamsLike(user.getId(), quarterNum, projectYear);
    ProjectFundOutInfoView projectFundOutInfoView = new ProjectFundOutInfoView();
    if (fundsOuts != null) {
      FundsOut totalFundsOut = new FundsOut();
      for (FundsOut fundsOut : fundsOuts) {
        if (FoundSourceType.COUNTRY.equals(fundsOut.getPid())) {
          projectFundOutInfoView.setCountryTotal(this.getResultPrecentInfoView(projectBudgetInfoView.getCountryTotal(),fundsOut));
          getTotalFundsOut(totalFundsOut,fundsOut);
        } else if (FoundSourceType.LOCAL.equals(fundsOut.getPid())) {
          projectFundOutInfoView.setLocal(this.getResultPrecentInfoView(projectBudgetInfoView.getLocal(),fundsOut));
          getTotalFundsOut(totalFundsOut,fundsOut);
        } else if (FoundSourceType.ENTERPRICE.equals(fundsOut.getPid())) {
          projectFundOutInfoView.setEnterprise(this.getResultPrecentInfoView(projectBudgetInfoView.getEnterprise(),fundsOut));
          getTotalFundsOut(totalFundsOut,fundsOut);
        } else if (FoundSourceType.UNIVERSITY.equals(fundsOut.getPid())) {
          projectFundOutInfoView.setUniversity(this.getResultPrecentInfoView(projectBudgetInfoView.getUniversity(),fundsOut));
          getTotalFundsOut(totalFundsOut,fundsOut);

        }

      }
      //设置总支出
      projectFundOutInfoView.setTotal(this.getResultPrecentInfoView(projectBudgetInfoView.getTotal(),totalFundsOut));

    }

    //导出文件

//    TemplateExportParams params = new TemplateExportParams("E:\\workspace\\SysResourceMngNew\\target\\test-classes\\templete\\预算执行情况模板.xlsx", true);
      Map<String, Object> map = new HashMap<>();
      map.put("project",project);
      map.put("budget",projectBudgetInfoView);
      map.put("foudin",projectFundInInfoView);
      map.put("fundout",projectFundOutInfoView);
      map.put("projectYear",projectYear);
      map.put("quarterNum",quarterNum);
      return map;
  }


  /**
   * 累计各类支出的总计
   * @param totalFundsOut
   * @param fundsOut
   * @return
   */
  private  void getTotalFundsOut(FundsOut totalFundsOut,FundsOut fundsOut) throws InvocationTargetException, IllegalAccessException {
    totalFundsOut.setApplicationPromete(BigDecimalUtil.sum(totalFundsOut.getApplicationPromete(),fundsOut.getApplicationPromete()));
    totalFundsOut.setCompanyCase(BigDecimalUtil.sum(totalFundsOut.getCompanyCase(),fundsOut.getCompanyCase()));
    totalFundsOut.setCourseDevelopment(BigDecimalUtil.sum(totalFundsOut.getCourseDevelopment(),fundsOut.getCourseDevelopment()));
    totalFundsOut.setExpertConsult(BigDecimalUtil.sum(totalFundsOut.getExpertConsult(),fundsOut.getExpertConsult()));
    totalFundsOut.setMaterialMake(BigDecimalUtil.sum(totalFundsOut.getMaterialMake(),fundsOut.getMaterialMake()));
    totalFundsOut.setResearchProve(BigDecimalUtil.sum(totalFundsOut.getResearchProve(),fundsOut.getResearchProve()));
    totalFundsOut.setSpecialTool(BigDecimalUtil.sum(totalFundsOut.getSpecialTool(),fundsOut.getSpecialTool()));
    totalFundsOut.setOtherFee(BigDecimalUtil.sum(totalFundsOut.getOtherFee(),fundsOut.getOtherFee()));
  }


  /**
   * 根据预算数据和支出数据，计算数据视图
   * @param resultInfoView
   * @param fundsOut
   * @return
   */
  private  ResultPrecentInfoView getResultPrecentInfoView( ResultInfoView resultInfoView,FundsOut fundsOut) throws InvocationTargetException, IllegalAccessException {
    ResultPrecentInfoView resultPrecentInfoView = new ResultPrecentInfoView();
    BeanUtils.copyProperties(resultPrecentInfoView,fundsOut);
    BigDecimal subFundsOut = BigDecimalUtil.sum(fundsOut.getApplicationPromete(),fundsOut.getCompanyCase());
    subFundsOut = BigDecimalUtil.sum(subFundsOut,fundsOut.getCourseDevelopment());
    subFundsOut = BigDecimalUtil.sum(subFundsOut,fundsOut.getExpertConsult());
    subFundsOut = BigDecimalUtil.sum(subFundsOut,fundsOut.getMaterialMake());
    subFundsOut = BigDecimalUtil.sum(subFundsOut,fundsOut.getOtherFee());
    subFundsOut = BigDecimalUtil.sum(subFundsOut,fundsOut.getResearchProve());
    subFundsOut = BigDecimalUtil.sum(subFundsOut,fundsOut.getSpecialTool());
    resultPrecentInfoView.setTotal(subFundsOut);
    resultPrecentInfoView.setTotalPrecent(BigDecimalUtil.percent(subFundsOut,resultInfoView.getTotal()));
    resultPrecentInfoView.setMaterialMakePrecent(BigDecimalUtil.percent(fundsOut.getMaterialMake(),resultInfoView.getMaterialMake()));
    resultPrecentInfoView.setApplicationPrometePrecent(BigDecimalUtil.percent(fundsOut.getApplicationPromete(),resultInfoView.getApplicationPromete()));
    resultPrecentInfoView.setCompanyCasePrecent(BigDecimalUtil.percent(fundsOut.getCompanyCase(),resultInfoView.getCompanyCase()));
    resultPrecentInfoView.setCourseDevelopmentPrecent(BigDecimalUtil.percent(fundsOut.getCourseDevelopment(),resultInfoView.getCourseDevelopment()));
    resultPrecentInfoView.setExpertConsultPrecent(BigDecimalUtil.percent(fundsOut.getExpertConsult(),resultInfoView.getExpertConsult()));
    resultPrecentInfoView.setOtherFeePrecent(BigDecimalUtil.percent(fundsOut.getOtherFee(),resultInfoView.getOtherFee()));
    resultPrecentInfoView.setResearchProvePrecent(BigDecimalUtil.percent(fundsOut.getResearchProve(),resultInfoView.getResearchProve()));
    resultPrecentInfoView.setSpecialToolPrecent(BigDecimalUtil.percent(fundsOut.getSpecialTool(),resultInfoView.getSpecialTool()));
  return resultPrecentInfoView;
  }

}
