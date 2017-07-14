package com.resourcemng.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

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
  public static BigDecimal sum(BigDecimal source,BigDecimal target){
    BigDecimal result = new BigDecimal(0);
    if(source == null){
      source = new BigDecimal(0);
    }
    result = result.add(source);
    if(target == null){
      target = new BigDecimal(0);
    }
    result= source.add(target);
    return  result;
  }

  /**
   *求半分比
   * @param value
   * @param total
   * @return
   */
  public static String percent(double value,BigDecimal total){
    if(total == null){
      total = new BigDecimal(0);
    }
    BigDecimal result = null;
    if(value == total.doubleValue() && total.doubleValue() ==0){
      result =new BigDecimal(0);
    }else if(total.doubleValue() ==0){
      result =new BigDecimal(1);
    }else if(value ==0){
      result =new BigDecimal(0);

    }else {
      //保留两位小数
      result = new BigDecimal(value).divide(total, 4, BigDecimal.ROUND_HALF_UP);
    }
    NumberFormat percent = NumberFormat.getPercentInstance();
    percent.setMaximumFractionDigits(2);

    return percent.format(result.doubleValue());
  }
}
