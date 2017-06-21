package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.entity.Budgetimportdetail2016;
import com.resourcemng.entity.Fileimportlog;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.BudgetImport2016Repository;
import com.resourcemng.repository.FileImportLogRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BudgetService {
  @Autowired
  BudgetImport2016Repository repository;
  @Autowired
  FileImportLogRepository fileImportLogRepository;


  public void importBudgetFormFile(String projectId, String importUser, File uploadFile) throws InvocationTargetException, IllegalAccessException {
    ImportParams params = new ImportParams();
    params.setTitleRows(5);
    params.setHeadRows(5);
    params.setReadRows(28);
    Map<Integer, String> map = new HashMap<Integer, String>();
    for (int i = 0; i < 20; i++) {//此处先按顺序给标题，在handler中转换标题
      map.put(i, Integer.toString(i));

    }
    params.setTitlemap(map);
    params.setDataHanlder(new BudgetImportHanlder());
    long start = new Date().getTime();
    List<Map<String, Object>> list = ExcelImportUtil.importExcel(
      new File("E:\\项目资料\\新建文件夹\\预算20163.xlsx"), Map.class, params);

    Fileimportlog log = new Fileimportlog();
    log.setFileName(uploadFile.getName());
    log = fileImportLogRepository.save(log);
    for (Map obj:list){
      Budgetimportdetail2016 budgetimportdetail2016 = new Budgetimportdetail2016();
       BeanUtils.populate(budgetimportdetail2016,obj);
//      budgetimportdetail2016.set
       this.repository.save(budgetimportdetail2016);
    }
  }

  public void delete(String id) {
  }

  public Object update(Budgetimportdetail2016 budgetimportdetail2016) {
    return null;
  }

  public Object get(String id) {
    return null;
  }
}
