package com.resourcemng.util;

import com.resourcemng.entitys.Experts;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2017-6-28.
 */
public class MD5 {
  // 加密

  public static String encrypt(String str){
    try {
      //确定计算方法
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      BASE64Encoder base64en = new BASE64Encoder();
      //加密后的字符串
      String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
      return newstr;
    }catch (Exception e){
      return str;
    }
  }
  public static String decrypt(String str){
    return str;
  }
}
