package com.resourcemng;

import com.resourcemng.controller.BudgetController;
import com.resourcemng.controller.ProjectController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BudgetControllerTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private BudgetController budgetController;
  @Test
  public void test() throws Exception {
    Object obj = budgetController.getByProject("402881f05cf98f57015cf98f68030003");
  }
}
