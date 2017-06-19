package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.entity.BudgetImportDetail2016;
import com.resourcemng.repository.BudgetImport2016Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class BudgetService {
  @Autowired
  BudgetImport2016Repository repository;

  /**
   *
   */
  public void  importService(String filePath){
    ImportParams params = new ImportParams();
    //params.setSheetNum(9);
    params.setNeedSave(true);
    params.setStartRows(1);
    params.setTitleRows(1);
    params.setHeadRows(1);
//    long start = new Date().getTime();
    List<BudgetImportDetail2016> list = ExcelImportUtil.importExcel(new File(
      filePath), BudgetImportDetail2016.class, params);
//    repository.saveAll(list);
  }

}
