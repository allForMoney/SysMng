package com.resourcemng.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

import java.util.Map;

public class IndicatorBaseInfoImportHanlder extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

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
          return "targetImplement";
        case "3":
          return "targetFirstYear";
        case "5":
          return "targetSecondYear";
      }
      return null;
    }
}
