package com.resourcemng.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.resourcemng.FileUitl;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.BudgetResultService;
import com.resourcemng.service.ProjectService;
import com.resourcemng.view.BudgetReportView;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 季报
 */
@Controller
@RequestMapping("/budgetresult")
public class BudgetResultController {
  BudgetResultService budgetResultService;
  @RequestMapping(value = "all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object findAll() throws Exception {
    return budgetResultService.findAll();
  }

  /**
   * 季报添加
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/quarterly" ,method = RequestMethod.POST)
  @ResponseBody
  public Object quarterlyReport(@RequestBody BudgetReportView view) throws Exception {
    budgetResultService.quarterlyReport(view);
    return "报告成功";
  }

  /**
   * 季报更新
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/quarterly" ,method = RequestMethod.PUT)
  @ResponseBody
  public Object quarterlyReportUpdate(@RequestBody BudgetReportView view) throws Exception {
    budgetResultService.quarterlyReportUpdate(view);
    return "更新报告成功";
  }

  /**
   * 获取季报信息
   * @param projectNo
   * @param projectYear
   * @param quarterNum
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/quarterly/detail" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getQuarterlyDetail(@RequestParam String projectNo,@RequestParam String projectYear,@RequestParam String quarterNum) throws Exception {

    budgetResultService.getQuarterlyDetail(projectNo,projectYear,quarterNum);
    return "报告成功";
  }

  /**
   * 季报审批
   * @param projectId
   * @param projectYear
   * @param quarterNum
   * @param auditType
   * @param auditContent
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/quarterly/audit" ,method = RequestMethod.POST)
  @ResponseBody
  public Object quarterlyReportAudit(@RequestParam String projectId,@RequestParam String projectYear,@RequestParam String quarterNum,@RequestParam String auditType, String auditContent) throws Exception {

    budgetResultService.quarterlyReportAudit(projectId,projectYear,quarterNum,auditType,auditContent);
    return "报告成功";
  }
  @RequestMapping(value = "/quarterly/download" ,method = RequestMethod.POST)
  @ResponseBody
  public void quarterlyReportDownload(@RequestParam String projectId,@RequestParam String projectYear,@RequestParam String quarterNum, HttpServletResponse response) throws Exception {

    Workbook workbook =  budgetResultService.exportFile(projectId,projectYear,quarterNum);
    String fileName = "预算执行情况.xls";
    response.setHeader("content-Type", "application/vnd.ms-excel");
    // 下载文件的默认名称
    response.setHeader("Content-Disposition", "attachment;filename=user.xls");
    workbook.write(response.getOutputStream());
  }
}
