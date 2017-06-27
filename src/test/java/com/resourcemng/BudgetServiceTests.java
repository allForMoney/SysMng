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
    service.importBudgetFormFile("ddd","ddw", ImportFileType.BUDGET2016,new File("F:\\个人文件夹\\workspace\\预算2016.xlsx"));
  }


}
