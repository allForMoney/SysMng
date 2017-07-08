package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.FoundSourceType;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.util.MD5;
import com.resourcemng.view.BudgetImportView;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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
public class UserService  /*implements  UserDetailsService*/{
  @Autowired
  TUserRepository userRepository;

  public Object regiestUser(Tuser user){
    return  userRepository.save(user);
  }

  public Tuser getUserByNO(String userNo){
    return  userRepository.findByUserNo(userNo);
  }

  public Object changePassword(String userNo, String newPassword) {
   Tuser user =  userRepository.findByUserNo(userNo);
    user.setPassword(MD5.encrypt(newPassword));
    return userRepository.save(user);
  }

}



