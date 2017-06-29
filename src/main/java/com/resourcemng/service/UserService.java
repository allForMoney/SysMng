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
import com.resourcemng.view.BudgetImportView;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService  implements  UserDetailsService{
  @Autowired
  TUserRepository userRepository;

  public Object regiestUser(Tuser user){
    return  userRepository.save(user);
  }

  public Tuser getUser(String userNo){
    return  userRepository.findByUserNo(userNo);
  }

  @Override
  public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
    if (StringUtils.isBlank(userNo)) {
      throw new UsernameNotFoundException("用户名为空");
    }

    Tuser user = userRepository.findByUserNo(userNo);
    return user;
  }
}



