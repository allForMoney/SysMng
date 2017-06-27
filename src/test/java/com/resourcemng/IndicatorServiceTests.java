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
    service.importFormFile("ddd","ddw", new File("F:\\个人文件夹\\workspace\\绩效目标 - 副本.xlsx"));
  }


}
