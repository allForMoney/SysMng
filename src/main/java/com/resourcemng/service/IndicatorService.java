package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.UserRole;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.IndicatorBase;
import com.resourcemng.entitys.IndicatorDetail;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.IndicatorBaseRepository;
import com.resourcemng.repository.IndicatorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class IndicatorService {
  @Autowired
  IndicatorBaseRepository baseRep;

  @Autowired
  IndicatorDetailRepository detailRep;
  @Transactional
   public IndicatorBase create(IndicatorBase base, List<IndicatorDetail> detailList) throws MyException {
     try {
       for(IndicatorDetail detail : detailList){
         detailRep.save(detail);
       }

       return baseRep.save(base);
     }catch (Exception e) {
       e.printStackTrace();
       throw new MyException(e);
     }
   }
  /**
   *
   * @param projectNo
   * @return
   */
  public Object getIndicatorById(String projectNo) {
    List<IndicatorBase> baseList = baseRep.findByProjectId(projectNo);


    List<IndicatorDetail> detailList = detailRep.findByProjectId(projectNo);
    return detailList;
  }
}
