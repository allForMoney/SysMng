package com.resourcemng;

import com.resourcemng.entity.Tuser;
import com.resourcemng.repository.SUserRepository;
import com.resourcemng.repository.TUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ApplicationTests {
  private MockMvc mvc;
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new Application()).build();
  }
  //@Test
  public void getHello() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(equalTo("Hello World!")));
  }
  @Autowired
  private SUserRepository userRepository;
  @Autowired
  private TUserRepository tUserRepository;
  @Test
  public void test() throws Exception {

    // 创建10条记录
    Tuser entity = new Tuser();
    entity.setUserId("12l");
    entity.setUserNo("AAAAA");
    entity.setUserName("AAAAA");
    entity.setUserPassword("AAAAA");
    entity.setMajorName("AAAAA");
    entity.setUserRole("2");
    entity.setIsDelete("1");
    tUserRepository.save(entity);

//    TUser s = new TUser();
//    s.setUserId(4444);
//    s.setUserName("ssss");
//    s.setUserNo("SSS");
//    s.setMajorName("DDDD");
//    s.setUserPassword("DSDWE");
//    s.setUserRole(1);
//    s.setIsDelete(1);
//    tUserRepository.save(s);

//    List<SUser> u=userRepository.findByName("AAAAA");
//    Assert.assertEquals(1, u.size());

  }
}
