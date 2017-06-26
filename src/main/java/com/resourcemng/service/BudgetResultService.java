package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.AuditStatus;
import com.resourcemng.Enum.AuditType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.view.BudgetImportView;
import com.resourcemng.view.BudgetReportView;
import com.resourcemng.view.EntityUitl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
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
public class BudgetResultService {
  @Autowired
  FundsInRepository fundsInRepository;
  @Autowired
  FundsOutRepository fundsOutRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository tUserRepository;
  @Autowired
  ReportAuditLogRepository reportAuditLogRepository;

  /**
   *
   * @return
   */
  public Object findAll() {
    return null;
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
    String projectId = user.getUserNo().substring(0,user.getUserNo().lastIndexOf("_")-1);
    view.setProjectId(projectId);
    ReportAuditLog log = new ReportAuditLog();
    log.setProjectId(view.getProjectId());
    log.setYear(view.getProjectYear());
    log.setQuarter(view.getQuarterNum());

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
    ReportAuditLog log = reportAuditLogRepository.findByParam(projectId,quarterNum,projectYear);
    BudgetReportView view = new BudgetReportView();
    view.setProjectYear(projectYear);
    view.setQuarterNum(quarterNum);
    view.setProjectId(project.getId());
    view.setAuditStatus(EntityUitl.getReportAuditLogStatus(log));//返回状态
    //查找数据
    List<FundsIn>  fundsIns =  fundsInRepository.findByParams(user.getId(),quarterNum,projectYear);
    List<FundsOut>  fundsOuts =  fundsOutRepository.findByParams(user.getId(),quarterNum,projectYear);
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
    fundsInRepository.deleteByParams(view.getUserId(),view.getQuarterNum(),view.getProjectYear());
    fundsOutRepository.deleteByParams(view.getUserId(),view.getQuarterNum(),view.getProjectYear());
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
        break;
      case AuditType.SCHOOL_AUDIT:
        log.setSchoolAuditState(AuditStatus.PASS);
        log.setSchoolAuditTime(new Date());
        break;
      case AuditType.COUNTRY_AUDIT:
        log.setConutryAuditState(AuditStatus.PASS);
        log.setConutryAuditTime(new Date());
        log.setAuditOpinion(auditContent);

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
  public Workbook exportFile(String projectId, String projectYear, String quarterNum) {
    return null;
  }
}
