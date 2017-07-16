package com.resourcemng.service;

import com.resourcemng.Enum.CodeClassType;
import com.resourcemng.entitys.FileImportLog;
import com.resourcemng.entitys.LeaveMessage;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.CommonRepository;
import com.resourcemng.repository.FileImportLogRepository;
import com.resourcemng.repository.ProjectRepository;
import com.resourcemng.repository.TUserRepository;
import com.resourcemng.view.ImportFileView;
import com.resourcemng.view.LeaveMessageView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommonService  /*implements  UserDetailsService*/{
  @Autowired
  CommonRepository commonRepository;
  @Autowired
  FileImportLogRepository fileImportLogRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository userRepository;

  public Object getPorvences(){
    return  commonRepository.findByClassName(CodeClassType.PROVENCE);
  }

  public Object getImportLog(String importType,String projectId,Pageable pageable) throws InvocationTargetException, IllegalAccessException {

    List<FileImportLog> list = null;
    Page pageResult = null;
    if (projectId == null) {
      pageResult = fileImportLogRepository.findByImportType(importType,pageable);
    } else {
      pageResult = fileImportLogRepository.findByImportTypeAndProjectId(importType,projectId,pageable);
    }
    return getImportDetail(pageResult);

  }
  public Object getBudgetImportLog(String projectId,Pageable pageable) throws InvocationTargetException, IllegalAccessException {


    Page pageResult = null;
    if (projectId == null) {
      pageResult = fileImportLogRepository.findBudgetImport(pageable);
    } else {
      pageResult = fileImportLogRepository.findBudgetImportByProjectId(projectId,pageable);
    }
    return getImportDetail(pageResult);

  }


  private Page getImportDetail(Page pageResult) throws InvocationTargetException, IllegalAccessException {
    List<FileImportLog> list = null;
    list = pageResult.getContent();
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


    List<ImportFileView> result = new ArrayList<>();
    for (FileImportLog fileImportLog : list) {
      ImportFileView view = new ImportFileView();
      BeanUtils.copyProperties(view, fileImportLog);
      Project project = projectsMap.get(fileImportLog.getProjectId());
      BeanUtils.copyProperties(view, project);
      view.setImportUserNo(usersMap.get(fileImportLog.getImportUserId()).getUsername());
      result.add(view);
    }
    return new PageImpl(result,pageResult.getPageable(),pageResult.getTotalElements());
  }
}



