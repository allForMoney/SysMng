package com.resourcemng;

import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.service.BudgetAdjustService;
import com.resourcemng.service.BudgetService;
import com.resourcemng.util.FileUitl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BudgetAdjustServiceTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private BudgetAdjustService service;
  @Autowired
  private FileUitl fileUitl;
  @Test
  public void testUploadFile() throws Exception {
    service.adjust("ddd","ddw", ImportFileType.BUDGET_ADJUST_2016,new File("E:\\项目资料\\新建文件夹\\模板\\2015-10预算调整请示及说明20170113.pdf"),new File("E:\\项目资料\\新建文件夹\\模板\\2015-10预算明细20170113上传版.xlsx"),new File("E:\\项目资料\\新建文件夹\\模板\\2015-10预算调整说明20170113.pdf"));
  }
  @Test
  public void getBudgetForProject() throws Exception {
    Pageable pageable = new PageRequest(Integer.parseInt("1")-1,Integer.parseInt("4"));
   Object obj =  service.find("402881f05d094788015d0949f20e000b",pageable);

  }
  @Test
  public void getLastAdjust() throws Exception {
    Pageable pageable = new PageRequest(Integer.parseInt("1")-1,Integer.parseInt("4"));
   Object obj =  service.find("402881f05d094788015d0949f20e000b",null);

  }


}
