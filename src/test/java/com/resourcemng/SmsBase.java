package com.resourcemng;

import com.resourcemng.controller.ProjectController;
import com.resourcemng.entitys.Project;
import com.resourcemng.task.MessageSender;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class SmsBase {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
  }
  @Autowired
  private MessageSender messageSender;
  @Test
  public void test() throws Exception {
    messageSender.sendSms("18538289636","测试是否能发送消息成功");
  }



}
