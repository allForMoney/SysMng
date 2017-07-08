package com.resourcemng.task;

/**
 * Created by 燕子 on 2017/7/8.
 */

import com.resourcemng.Enum.UserRole;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 每个季度提前一天发送短信通知
 */
@Component
public class MessageSender {


  @Value("${app.msg.username}")

  private String userName;
  @Value("${app.msg.password}")
  private String password;



  public String sendSms(String mobile, String content)  {

    Integer x_ac=10;//发送信息
    HttpURLConnection httpconn = null;

    String result="Error";
    try {
    StringBuilder sb = new StringBuilder();
    sb.append("http://service.winic.org:8009/sys_port/gateway/index.asp?");

//以下是参数
//为了你的测试方便收到短信！请短信内容编辑为：你的验证码为：123456【中正云通信】
    sb.append("id=").append(URLEncoder.encode(userName, "gb2312"));
    sb.append("&pwd=").append(password);
    sb.append("&to=").append(mobile);
    sb.append("&content=").append(URLEncoder.encode(content, "gb2312"));
    sb.append("&time=").append("");

      URL url = new URL(sb.toString());
      httpconn = (HttpURLConnection) url.openConnection();
      BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
      result = rd.readLine();
      rd.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally{
      if(httpconn!=null){
        httpconn.disconnect();
        httpconn=null;
      }

    }
    return result;
  }

}
