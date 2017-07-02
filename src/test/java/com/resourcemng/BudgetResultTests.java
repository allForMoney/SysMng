package com.resourcemng;

import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.entitys.FundsIn;
import com.resourcemng.entitys.FundsOut;
import com.resourcemng.service.BudgetResultService;
import com.resourcemng.service.BudgetService;
import com.resourcemng.view.BudgetReportView;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BudgetResultTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private BudgetResultService service;
  @Test
  public void testQuarterlyReport() throws Exception {
    BudgetReportView view = new BudgetReportView();
    view.setUserId("40289e375ced4dff015ced4e0efe0000");
    view.setProjectYear("2016-02");
    view.setQuarterNum("3");
    List<FundsIn> fundsIns = new ArrayList<>();
    FundsIn foundsIn = new FundsIn();
    foundsIn.setAmountMoney(new BigDecimal(2.5));
    foundsIn.setPid("4");
    fundsIns.add(foundsIn);
    view.setFundsIns(fundsIns);
    List<FundsOut> fundsOuts = new ArrayList<>();
    FundsOut fundsOut = new FundsOut();
    fundsOut.setMaterialMake(new BigDecimal(2.5));
    fundsOut.setApplicationPromete(new BigDecimal(5));
    fundsOut.setCompanyCase(new BigDecimal(5));
    fundsOut.setCourseDevelopment(new BigDecimal(5));
    fundsOut.setExpertConsult(new BigDecimal(5));
    fundsOut.setOtherFee(new BigDecimal(5));
    fundsOut.setResearchProve(new BigDecimal(5));
    fundsOut.setSpecialTool(new BigDecimal(5));
    fundsOut.setPid("2");
    fundsOuts.add(fundsOut);
    view.setFundsOuts(fundsOuts);
    service.quarterlyReport(view);
  }
  @Test
  public void testQuarterlyReportGet() throws Exception {
    service.getQuarterlyDetail("40289e375ce817c2015ce817d1190003","2016-02","3");
    System.out.println();
  }
  @Test
  public void testExport() throws Exception {
    Workbook book  = service.exportFile("402881f05cf98f57015cf98f68030003","2017","1");
    if(book == null){
      return;
    }
    File savefile = new File("E:/excel/");
    if (!savefile.exists()) {
      savefile.mkdirs();
    }
    FileOutputStream fos = new FileOutputStream("E:/excel/foreach.xlsx");
    book.write(fos);
    fos.close();
  }
}
