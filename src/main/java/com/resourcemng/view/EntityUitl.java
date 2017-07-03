package com.resourcemng.view;

import com.resourcemng.Enum.AuditStatus;
import com.resourcemng.entitys.ReportAuditLog;

/**
 * Created by 燕子 on 2017/6/20.
 */
public class EntityUitl {


  public static String getReportAuditLogStatus(ReportAuditLog log) {
   return log.getStatus();
  }
}
