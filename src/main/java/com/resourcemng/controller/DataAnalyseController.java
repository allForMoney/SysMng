package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.service.BudgetAdjustService;
import com.resourcemng.service.DataAnalyseService;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.BudgetAdjustView;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/dataanalyse")
public class DataAnalyseController {
  protected static final String HSSF         = ".xls";
  protected static final String XSSF         = ".xlsx";

  @Autowired
  DataAnalyseService service;
  @Autowired
  FileUitl fileUitl;

  /**
   * 导入文件
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/byyear" ,method = RequestMethod.GET)
  @ResponseBody
  public Object byYear(String year,String projectYear) throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "上传成功",   service.byYear(year,projectYear));
  }
  /**
   * 导入文件
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/byyear/download" ,method = RequestMethod.GET)
  @ResponseBody
  public void byYearDownload(String year,String projectYear, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Workbook workbook =  service.byYearDownload(year,projectYear);

    String codedFileName = "预算执行情况汇总报表"+year;
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
