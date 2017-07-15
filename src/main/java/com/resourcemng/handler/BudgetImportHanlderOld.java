package com.resourcemng.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

import java.util.Map;

public class BudgetImportHanlderOld extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        if (value instanceof Double) {
            map.put(getRealKey(originKey), PoiPublicUtil.doubleToString((Double) value));
        } else {
            if(value == null){
                map.put(getRealKey(originKey),null);
                return;
            }
            map.put(getRealKey(originKey), value.toString());
        }
    }

    private String getRealKey(String originKey) {



      switch(originKey){
        case "0":
          return "sequenceNo";
        case "1":
          return "useFor";
        case "2":
          return "totalMoney";
        case "3":
          return "countryTotal";
        case "4":
          return "countryPercent";
        case "5":
          return "countryFirstYear";
        case "6":
          return "countrySecondYear";
        case "7":
          return "projectTotal";
        case "8":
          return "projectPercent";
        case "9":
          return "localFirstYear";
        case "10":
          return "localSecondYear";
        case "11":
          return "localThreeYear";
        case "12":
          return "enterpriseFirstYear";
        case "13":
          return "enterpriseSecondYear";
        case "14":
          return "enterpriseThreeYear";
        case "15":
          return "universityFirstYear";
        case "16":
          return "universitySecondYear";
        case "17":
          return "universityThreeYear";
        case "18":
          return "originalId";
      }
      return null;
    }
}
