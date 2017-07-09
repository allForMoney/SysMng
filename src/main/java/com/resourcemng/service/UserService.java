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
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.BigDecimalUtil;
import com.resourcemng.util.MD5;
import com.resourcemng.view.BudgetImportView;
import com.resourcemng.view.ImportFileView;
import com.resourcemng.view.UserView;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Transactional
public class UserService  /*implements  UserDetailsService*/{
  @Autowired
  TUserRepository userRepository;
  @Autowired
  ProjectRepository projectRepository;
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

  public Object find(String userNo,Pageable pageable ) throws InvocationTargetException, IllegalAccessException {
   String username = userNo==null?"":userNo;
    List<Project> projects = projectRepository.findAll();
    projects = projects == null ? new ArrayList<>() : projects;
    Page pageResult = userRepository.findByUsernameLike(username,pageable);
    List<Tuser> list = null;
    list = pageResult.getContent();

    Map<String, Project> projectsMap = new HashMap();
    for (Project project : projects) {
      projectsMap.put(project.getProjectNo(), project);
    }
    List<UserView> result = new ArrayList<>();
    for (Tuser user : list) {
      UserView view = new UserView();
      BeanUtils.copyProperties(view, user);
      Project project = projectsMap.get(ApplicationUitl.getPorjectNoByReportUserNo(user.getUsername()));
      if(project!=null) {
        BeanUtils.copyProperties(view, project);
      }
      view.setRoleName(ApplicationUitl.getRoleNameByRole(view.getUserRole()));
      result.add(view);
    }
    return new PageImpl(result,pageResult.getPageable(),pageResult.getTotalElements());
  }
}



