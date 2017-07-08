package com.resourcemng;

import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.service.BudgetService;
import com.resourcemng.service.IndicatorService;
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


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class IndicatorServiceTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private IndicatorService service;
  @Test
  public void testUploadFile() throws Exception {
    service.importFormFile("402881f05cf98f57015cf98f68030003","402881f05cf98f57015cf98f67b40000", new File("E:\\项目资料\\新建文件夹\\模板\\绩效目标.xlsx"));
  }
  @Test
  public void get() throws Exception {
    Object obj =  service.getIndicatorDetail("402881f05cf98f57015cf98f68030003");
  }
  @Test
  public void find() throws Exception {
    Object obj =  service.find("2017","","",null);
  }
}
