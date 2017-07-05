package com.resourcemng.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.entity.vo.TemplateExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.service.BudgetResultService;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.view.BudgetReportView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 季报
 */
@Controller
@RequestMapping("/budget/report")
public class BudgetResultController {
  protected static final String HSSF         = ".xls";
  protected static final String XSSF         = ".xlsx";

  @Autowired
  BudgetResultService budgetResultService;

  /**
   * 查看所有项目的季报
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "all" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getAll(@RequestParam String projectId,String year,String quarter) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "获取成功.",   budgetResultService.findByParam(projectId,year,quarter));

  }

  /**
   * 季报添加
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object quarterlyReport(@RequestBody BudgetReportView view) throws Exception {
    budgetResultService.quarterlyReport(view);
    return new RequestResult(ResultCode.SUCCESS, "创建报告成功.",   null);

  }

  /**
   * 季报更新
   * @param view
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/update" ,method = RequestMethod.POST)
  @ResponseBody
  public Object quarterlyReportUpdate(@RequestBody BudgetReportView view) throws Exception {
    budgetResultService.quarterlyReportUpdate(view);
    return new RequestResult(ResultCode.SUCCESS, "更新报告成功.",   null);
  }

  /**
   * 获取季报信息
   * @param projectId
   * @param projectYear
   * @param quarterNum
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/quarterly/detail" ,method = RequestMethod.GET)
  @ResponseBody
  public Object getQuarterlyDetail(@RequestParam String projectId,@RequestParam String projectYear,@RequestParam String quarterNum) throws Exception {

    return new RequestResult(ResultCode.SUCCESS, "获取明细成功.",   budgetResultService.getQuarterlyDetail(projectId,projectYear,quarterNum));
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
    return new RequestResult(ResultCode.SUCCESS, "提交审批成功.",   null);
  }

  /**
   * 季报下载
   * @param projectId
   * @param projectYear
   * @param quarterNum
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/quarterly/download" ,method = RequestMethod.GET)
  @ResponseBody
  public void quarterlyReportDownload(@RequestParam String projectId, @RequestParam String projectYear,
                                      @RequestParam String quarterNum, HttpServletRequest request, HttpServletResponse response) throws Exception {

    Workbook workbook =  budgetResultService.exportFile(projectId,projectYear,quarterNum);
//    String fileName = "预算执行情况.xls";
//    response.setHeader("content-Type", "application/vnd.ms-excel");
//    // 下载文件的默认名称
//    response.setHeader("Content-Disposition", "attachment;filename=预算执行情况"+projectYear+"_"+quarterNum+".xlsx");
//    workbook.write(response.getOutputStream());

    String codedFileName = "预算执行情况"+projectYear+"_"+quarterNum;
    if (workbook instanceof HSSFWorkbook) {
      codedFileName += HSSF;
    } else {
      codedFileName += XSSF;
    }
    if (ApplicationUitl.isIE(request)) {
      codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF8");
    } else {
      codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
    }
    response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
    ServletOutputStream out = response.getOutputStream();
    workbook.write(out);
    out.flush();

  }
}
