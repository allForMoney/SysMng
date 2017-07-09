package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.ImportFileType;
import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.*;
import com.resourcemng.handler.BudgetImportHanlder;
import com.resourcemng.repository.*;
import com.resourcemng.view.BudgetImportView;
import com.resourcemng.view.LeaveMessageView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Transactional
public class LeaveMessageService {
  @Autowired
  LeaveMessageRepository leaveMessageRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository userRepository;
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
  public List findByType(String mesType,String projectId) throws InvocationTargetException, IllegalAccessException {
    List<LeaveMessage> list = null;
    if (projectId == null) {
      list = leaveMessageRepository.findByType(mesType);
    } else {
      list = leaveMessageRepository.findByMesTypeaAndProjectId(mesType,projectId);
    }
    List<Project> projects = projectRepository.findAll();
    projects = projects == null ? new ArrayList<>() : projects;
    List<Tuser> users = userRepository.findAll();
    users = users == null ? new ArrayList<>() : users;

    Map<String, Project> projectsMap = new HashMap();
    for (Project project : projects) {
      projectsMap.put(project.getId(), project);
    }
    Map<String, Tuser> usersMap = new HashMap();
    for (Tuser user : users) {
      usersMap.put(user.getId(), user);
    }

    List<LeaveMessageView> result = new ArrayList<>();
    for (LeaveMessage leaveMessage : list) {
      LeaveMessageView view = new LeaveMessageView();
      BeanUtils.copyProperties(view, leaveMessage);
      view.setProjectNos(projectsMap.get(leaveMessage.getProjectId()).getProjectNo());
      view.setSubmitUserName(usersMap.get(leaveMessage.getSubmitUserId()).getUsername());
      if (leaveMessage.getReplyUserId() != null) {
        view.setReplyUserName(usersMap.get(leaveMessage.getReplyUserId()).getUsername());
      }
      result.add(view);
    }
    return result;
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
