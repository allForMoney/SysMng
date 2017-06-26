package com.resourcemng.view;

import com.resourcemng.Enum.AuditStatus;
import com.resourcemng.Enum.ForViewAuditStatus;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.ReportAuditLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 燕子 on 2017/6/20.
 */
public class EntityUitl {


  public static String getReportAuditLogStatus(ReportAuditLog log) {

   if(AuditStatus.PASS.equals(log.getConutryAuditState())){
     return ForViewAuditStatus.CONUTTRY_AUDIT;
    }else if(AuditStatus.PASS.equals(log.getSchoolAuditState())){
     return ForViewAuditStatus.SCHOOL_AUDIT;
    }else if(AuditStatus.PASS.equals(log.getFinanceAuditState())){
     return ForViewAuditStatus.FINACE_AUDIT;
   }else{
      return ForViewAuditStatus.UN_AUDIT;
   }
  }
}
