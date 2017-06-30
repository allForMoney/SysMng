package com.resourcemng;

import com.resourcemng.controller.ProjectController;
import com.resourcemng.entitys.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ProjectControllerTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private ProjectController projectController;
  @Test
  public void test() throws Exception {
    Project project = new Project();
    project.setId("2");
    projectController.create(project);
  }

  @Test
  public void testGetAll() throws Exception {
   Object project =  projectController.find(null,null,null,"0","2");
    System.out.println();

  }


}
