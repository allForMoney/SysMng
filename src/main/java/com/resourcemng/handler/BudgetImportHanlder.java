package com.resourcemng.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

import java.util.Map;

public class BudgetImportHanlder extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

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
          return "consult";
        case "3":
          return "print";
        case "4":
          return "travel";
        case "5":
          return "meeting";
        case "6":
          return "training";
        case "7":
          return "specialMaterial";
        case "8":
          return "delegation";
        case "9":
          return "otherExpense";
        case "10":
          return "specialDevice";
        case "11":
          return "informationTechnology";
        case "12":
          return "totalMoney";
        case "13":
          return "countryTotal";
        case "14":
          return "countryPercent";
        case "15":
          return "projectTotal";
        case "16":
          return "projectPercent";
        case "17":
          return "local";
        case "18":
          return "enterprise";
        case "19":
          return "university";
        case "20":
          return "oraginalId";
      }
      return null;
    }
}
