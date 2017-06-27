package com.resourcemng.util;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-6-27.
 */
public class BigDecimalUtil {

  public static BigDecimal getValueForString(String str){
    if(str == null){
      return new BigDecimal("0");
    }else {
      return new BigDecimal(str);
    }

  }
}
