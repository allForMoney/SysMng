package com.resourcemng.service;

import com.resourcemng.Enum.*;
import com.resourcemng.entitys.*;
import com.resourcemng.repository.*;
import com.resourcemng.view.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
  public Object findByParam(String projectId,String year,String quarter) {
    return this.reportAuditLogRepository.findByParam(projectId,year,quarter);
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
  public void quarterlyReportAudit(String projectId,String reportYear,String quarterNum ,String auditType ,String auditContent) {
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

        break;

    }

  }

  /**
   * 文件下载
   * @param projectId
   * @param projectYear
   * @param quarterNum
   * @return
   */
  public Workbook exportFile(String projectId, String projectYear, String quarterNum) throws InvocationTargetException,
    IllegalAccessException {
    ProjectResultDownloadView view = new ProjectResultDownloadView();
    //项目信息
    Project project = projectRepository.findById(projectId).get();

    //预算信息
    ProjectBudgetInfoView projectBudgetInfoView = budgetService.getBudgetForProject(projectId);
    view.setBudget(projectBudgetInfoView);

    BigDecimal temp = new BigDecimal(0);
    BigDecimal countryBudget = projectBudgetInfoView.getCountryTotal().getTotal();
    BigDecimal localBudget = projectBudgetInfoView.getLocal().getTotal();
    BigDecimal enterpriseBudget = projectBudgetInfoView.getLocal().getTotal();
    List<ResultInfoView> budgetList = new ArrayList();
//    ResultInfoView ResultInfoView = new ResultInfoView();
//    ResultInfoView.setTotal(temp.add(projectBudgetInfoView.getCountryTotal().getTotal()).
//      add(projectBudgetInfoView.getLocal().getTotal()).add(projectBudgetInfoView.getEnterprise().getTotal()).
//      add(projectBudgetInfoView.getUniversity().getTotal()));
//    temp = new BigDecimal(0);
//    ResultInfoView.setApplicationPromete(temp.add(projectBudgetInfoView.getCountryTotal().getApplicationPromete()).
//      add(projectBudgetInfoView.getLocal().getApplicationPromete()).add(projectBudgetInfoView.getEnterprise().getApplicationPromete()).
//      add(projectBudgetInfoView.getUniversity().getApplicationPromete()));
//    temp = new BigDecimal(0);
//    ResultInfoView.setMaterialMake(temp.add(projectBudgetInfoView.getCountryTotal().getApplicationPromete()).
//      add(projectBudgetInfoView.getLocal().getApplicationPromete()).add(projectBudgetInfoView.getEnterprise().getApplicationPromete()).
//      add(projectBudgetInfoView.getUniversity().getApplicationPromete()));
    //收入信息
    List<FundsIn> fundsIns = fundsInRepository.findByParams(project.getProjectNo() + "-1", quarterNum, projectYear);
    ProjectFundInInfoView projectFundInInfoView = new ProjectFundInInfoView();
    if (fundsIns != null) {
      BigDecimal totalFundsIn = new BigDecimal(0);
      for (FundsIn fundsIn : fundsIns) {
        if (FoundSourceType.COUNTRY.equals(fundsIn.getPid())) {
          projectFundInInfoView.setCountryTotal(fundsIn.getAmountMoney());
          totalFundsIn.add(fundsIn.getAmountMoney());
        } else if (FoundSourceType.LOCAL.equals(fundsIn.getPid())) {
          projectFundInInfoView.setLocal(fundsIn.getAmountMoney());
          totalFundsIn.add(fundsIn.getAmountMoney());
        } else if (FoundSourceType.ENTERPRICE.equals(fundsIn.getPid())) {
          projectFundInInfoView.setEnterprise(fundsIn.getAmountMoney());
          totalFundsIn.add(fundsIn.getAmountMoney());
        } else if (FoundSourceType.UNIVERSITY.equals(fundsIn.getPid())) {
          projectFundInInfoView.setUniversity(fundsIn.getAmountMoney());
          totalFundsIn.add(fundsIn.getAmountMoney());

        }
        //设置总输入
        projectFundInInfoView.setTotal(totalFundsIn);
        totalFundsIn.add(fundsIn.getAmountMoney());
      }


    }
    return null;
  }
}
