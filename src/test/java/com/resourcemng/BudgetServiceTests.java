package com.resourcemng;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.entitys.Project;
import com.resourcemng.service.BudgetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.util.*;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BudgetServiceTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private BudgetService service;
  @Test
  public void testUploadFile() throws Exception {
    service.importBudgetFormFile("ddd","ddw", ImportFileType.BUDGET2016,new File("E:\\项目资料\\新建文件夹\\模板\\预算2016.xlsx"));
  }


  @Test
  public void test() throws Exception {
    ImportParams params = new ImportParams();
    params.setTitleRows(1);
    params.setHeadRows(1);
    //params.setSheetNum(9);
    params.setNeedSave(true);
    List<Map> list = ExcelImportUtil.importExcel(new File(
      "E:\\项目资料\\新建文件夹\\项目模板.xlsx"), Map.class, params);
    System.out.println();

    Project project = new Project();
    project.setId("4");
//    service.importService("E:\\项目资料\\新建文件夹\\预算2016模板.xlsx");
  }

  @Test
  public void mapTest() {

    ImportParams params = new ImportParams();
    params.setTitleRows(4);
    params.setHeadRows(4);
    params.setReadRows(28);
    Map <Integer,String> map = new HashMap<Integer, String>() ;
    for(int i = 0;i< 20;i++){
      map.put(i,Integer.toString(i));
    }
    params.setTitlemap(map);
    params.setDataHanlder(new MapImportHanlder());
    long start = new Date().getTime();
    List<Map<String, Object>> list = ExcelImportUtil.importExcel(
      new File("E:\\项目资料\\新建文件夹\\预算20163.xlsx"), Map.class, params);
    System.out.println(new Date().getTime() - start);
    System.out.println(list.size());
    System.out.println(list.get(0));

  }


}
