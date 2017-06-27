package com.resourcemng;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.entitys.Project;
import com.resourcemng.service.BudgetService;
import com.resourcemng.service.ProjectService;
import net.minidev.json.JSONUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ProjectServiceTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private ProjectService service;
  @Test
  public void test() throws Exception {
    service.importPorjectByFile(new File("F:\\个人文件夹\\workspace\\项目模板.xlsx"));
  }

  @Test
  public void testGet() throws Exception {
    Project project =  service.get("2016-02");
    System.out.println();

  }
  @Test
  public void testGetAll() throws Exception {
    List<Project>  project =  service.find(null,null,null);
    System.out.println();

  }

}
