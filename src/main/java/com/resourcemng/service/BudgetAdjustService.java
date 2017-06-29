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
public class BudgetAdjustService {
  @Autowired
  BudgetAdjustRepository budgetAdjustRepository;
  @Autowired
  BudgetAuditLogRepository budgetAuditLogRepository;

  /**
   * 预算调整
   * @param projectId
   * @param year
   * @param requestFile
   * @param adjustFile
   * @param descriptionFile
   */
  public void adjust(String projectId,String year,File requestFile,File adjustFile,File descriptionFile ){
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
    log.setYear(year);
    budgetAuditLogRepository.save(log);


  }

}
