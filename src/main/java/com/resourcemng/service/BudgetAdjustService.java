package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.*;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.view.BudgetImportView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
  BudgetAdjustRepository budgetAdjustRepository;
  @Autowired
  BudgetAuditLogRepository budgetAuditLogRepository;

  /**
   * 预算调整
   * @param projectId
   * @param requestFile
   * @param adjustFile
   * @param descriptionFile
   */
  public void adjust(String projectId,/*String year,*/File requestFile,File adjustFile,File descriptionFile ){
    BudgetAdjust budgetAdjust = new BudgetAdjust();
    budgetAdjust.setFileName(adjustFile.getName());
    budgetAdjust.setAttachment1(requestFile.getName());
    budgetAdjust.setAttachment2(descriptionFile.getName());
    budgetAdjust.setImportDate(new Date());
    budgetAdjust.setProjectId(projectId);
    budgetAdjustRepository.save(budgetAdjust);


    BudgetAuditLog log = new BudgetAuditLog();
    log.setReportTime(new Date());
    log.setAdjustId(budgetAdjust.getId());
    budgetAuditLogRepository.save(log);


  }

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
        break;

    }
  }

  public List<BudgetAuditLog> find(String projectId) {
    if(StringUtils.isEmpty(projectId)){
     return budgetAuditLogRepository.findAll();

    }else{
      List<BudgetAdjust> budgetAdjusts = budgetAdjustRepository.findByProjectId(projectId);
      if(budgetAdjusts == null){
        return null;
      }
      List<String> ids = new ArrayList<>();
      for(BudgetAdjust budgetAdjust:budgetAdjusts){
        ids.add(budgetAdjust.getId());
      }
//      return  budgetAuditLogRepository.findByAdjustId();
      return null;
    }

  }
}
