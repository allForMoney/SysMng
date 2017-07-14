package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.resourcemng.Enum.AuditStatus;
import com.resourcemng.Enum.AuditType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.ReportStatus;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.BudgetAuditLog;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.Project;
import com.resourcemng.repository.*;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class DataAnalyseService {
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  BudgetResultService budgetResultService;

  @Autowired
  FileUitl fileUtil;

  public List<ProjectBudgetView> byYear(String year,String projectYear) throws InvocationTargetException, IllegalAccessException {
    projectYear = projectYear==null?"":projectYear;
   List<Project> projects =  projectRepository.findByYear(projectYear);
   List<ProjectBudgetView> list = new ArrayList<>();
   //数据总计
    ProjectBudgetView total = new ProjectBudgetView();
    list.add(total);
   for (Project project :projects){
     Map map = budgetResultService.computeData(project.getId(),year,null);
     ProjectBudgetInfoView projectBudgetInfoView = (ProjectBudgetInfoView) map.get("budget");
     ProjectFundInInfoView projectFundInInfoView = (ProjectFundInInfoView) map.get("foudin");
     ProjectFundOutInfoView projectFundOutInfoView = (ProjectFundOutInfoView) map.get("fundout");

     ProjectBudgetView view = new ProjectBudgetView();
     view.setProjectId(project.getId());
     view.setProjectNo(project.getProjectNo());
     view.setMajorName(project.getMajorName());
     view.setSchoolName(project.getSchoolName());
     //预算
     view.setBudgetEnterprise(projectBudgetInfoView.getEnterprise().getTotal());
     view.setBudgetLocal(projectBudgetInfoView.getLocal().getTotal());
     view.setBudgetUniversity(projectBudgetInfoView.getUniversity().getTotal());
     view.setBudgetCountry(projectBudgetInfoView.getCountryTotal().getTotal());
     view.setBudgetTotal(projectBudgetInfoView.getTotal().getTotal());

//     收入
     view.setEnterpriseInPercent(projectFundInInfoView.getEnterprisePrecent());
     view.setFundsInEnterprise(projectFundInInfoView.getEnterprise());
     view.setFundsInCountry(projectFundInInfoView.getCountryTotal());
     view.setCountryInPercent(projectFundInInfoView.getCountryPrecent());
     view.setFundsInLocal(projectFundInInfoView.getLocal());
     view.setLocalInPercent(projectFundInInfoView.getLocalPrecent());
     view.setUniversityInPercent(projectFundInInfoView.getUniversityPrecent());
     view.setFundsInUniversity(projectFundInInfoView.getUniversity());
     view.setFundsInTotal(projectFundInInfoView.getTotal());
     view.setTotalInPercent(projectFundInInfoView.getPrecent());

     //支出
     view.setFundsOutEnterprise(projectFundOutInfoView.getEnterprise().getTotal());
     view.setEnterpriseOutPercent(projectFundOutInfoView.getEnterprise().getTotalPrecent());
     view.setUniversityOutPercent(projectFundOutInfoView.getUniversity().getTotalPrecent());
     view.setFundsOutUniversity(projectFundOutInfoView.getUniversity().getTotal());
     view.setFundsOutLocal(projectFundOutInfoView.getLocal().getTotal());
     view.setLocalOutPercent(projectFundOutInfoView.getLocal().getTotalPrecent());
     view.setFundsOutCountry(projectFundOutInfoView.getCountryTotal().getTotal());
     view.setCountryOutPercent(projectFundOutInfoView.getCountryTotal().getTotalPrecent());
     view.setFundsOutTotal(projectFundOutInfoView.getTotal().getTotal());
     view.setTotalOutPercent(projectFundOutInfoView.getTotal().getTotalPrecent());
     list.add(view);
   }

  this.computeTotal(year,total,list);
   return list;
  }

  private void computeTotal(String year,ProjectBudgetView view,List<ProjectBudgetView> list){
    view.setProjectNo("合计");
    view.setMajorName(year+"立项");
    //预算
    view.setBudgetEnterprise(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getBudgetEnterprise()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setBudgetLocal(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getBudgetLocal()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setBudgetUniversity(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getBudgetUniversity()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setBudgetCountry(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getBudgetCountry()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setBudgetTotal(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getBudgetTotal()).map(t->t.doubleValue()).orElse(0.0);}).sum()));

//     收入
    view.setFundsInEnterprise(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsInEnterprise()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setEnterpriseInPercent(BigDecimalUtil.percent(view.getFundsInEnterprise().doubleValue(),view.getBudgetEnterprise()));

    view.setFundsInCountry(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsInCountry()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setCountryInPercent(BigDecimalUtil.percent(view.getFundsInCountry().doubleValue(),view.getBudgetCountry()));

    view.setFundsInLocal(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsInLocal()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setLocalInPercent(BigDecimalUtil.percent(view.getFundsInLocal().doubleValue(),view.getBudgetLocal()));


    view.setFundsInUniversity(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsInUniversity()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setUniversityInPercent(BigDecimalUtil.percent(view.getFundsInUniversity().doubleValue(),view.getBudgetUniversity()));

    view.setFundsInTotal(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsInTotal()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setTotalInPercent(BigDecimalUtil.percent(view.getFundsInTotal().doubleValue(),view.getBudgetTotal()));


    //支出
    view.setFundsOutEnterprise(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsOutEnterprise()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setEnterpriseOutPercent(BigDecimalUtil.percent(view.getFundsOutEnterprise().doubleValue(),view.getBudgetEnterprise()));

    view.setFundsOutUniversity(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsOutUniversity()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setUniversityOutPercent(BigDecimalUtil.percent(view.getFundsOutUniversity().doubleValue(),view.getBudgetUniversity()));

    view.setFundsOutLocal(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsOutLocal()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setLocalOutPercent(BigDecimalUtil.percent(view.getFundsOutLocal().doubleValue(),view.getBudgetLocal()));

    view.setFundsOutCountry(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsOutCountry()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setCountryOutPercent(BigDecimalUtil.percent(view.getFundsOutCountry().doubleValue(),view.getBudgetCountry()));

    view.setFundsOutTotal(new BigDecimal(list.stream().mapToDouble(p -> {
      return Optional.ofNullable(p.getFundsOutTotal()).map(t->t.doubleValue()).orElse(0.0);}).sum()));
    view.setTotalOutPercent(BigDecimalUtil.percent(view.getFundsOutTotal().doubleValue(),view.getBudgetTotal()));
  }

  public Workbook byYearDownload(String year,String projectYear) throws InvocationTargetException, IllegalAccessException {
    //TODO 校验
    List<ProjectBudgetView> list = this.byYear(year,projectYear);
    Map map = new HashMap();
    map.put("resultlist",list);
    TemplateExportParams params = new TemplateExportParams(
      fileUtil.getTempleteFilePath("templete/预算执行情况汇总报表.xlsx"), true);
    params.setHeadingRows(3);
    Workbook workbook = ExcelExportUtil.exportExcel(params, map);
    return workbook;
  }
}
