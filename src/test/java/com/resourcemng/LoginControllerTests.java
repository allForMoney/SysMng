package com.resourcemng;

import com.resourcemng.controller.AuthController;
import com.resourcemng.controller.BudgetController;
import org.apache.xmlbeans.impl.util.Base64;
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
public class LoginControllerTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private AuthController authController;
//  @Test
//  public void test() throws Exception {
//    System.out.println( new String(Base64.encode("654321".getBytes())));
//    authController.loginPost("","cws666","654321",null);
//  }
}
