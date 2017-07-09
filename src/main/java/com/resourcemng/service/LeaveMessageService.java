package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.BudgetImportDetailOld;
import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.view.BudgetImportView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LeaveMessageService {
  @Autowired
  LeaveMessageRepository leaveMessageRepository;
  /**
   *
   * @param leaveMessage
   * @return
   */
  public Object comment(LeaveMessage leaveMessage) {
    leaveMessage.setSubmitDate(new Date());
    return leaveMessageRepository.save(leaveMessage);
  }
  /**
   * 根据类型查找
   * @param mesType
   * @return
   */
  public List findByType(String mesType) {
    return leaveMessageRepository.findByType(mesType);
  }

  /**
   *
   * @param id
   * @param content
   * @param userId
   * @return
   */
  public Object replyCommont(String id, String content, String userId) {
    LeaveMessage message  = this.leaveMessageRepository.findById(id).get();
    message.setReplyContents(content);
    message.setReplyUserId(userId);
    message.setReplyDate(new Date());
    return  this.leaveMessageRepository.save(message);
  }
}
