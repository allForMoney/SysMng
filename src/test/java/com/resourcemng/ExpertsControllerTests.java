package com.resourcemng;

import com.resourcemng.controller.AuthController;
import com.resourcemng.controller.ExpertController;
import com.resourcemng.entitys.Experts;
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
public class ExpertsControllerTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private ExpertController expertsController;
  @Test
  public void test() throws Exception {
    Experts experts = new Experts();
    experts.setName("张三");
    expertsController.create(experts);

   Object obj =  expertsController.find();
  }
}
