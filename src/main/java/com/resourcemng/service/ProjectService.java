package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.UserRole;
import com.resourcemng.basic.MyException;
import com.resourcemng.entity.Project;
import com.resourcemng.entity.Tuser;
import com.resourcemng.repository.ProjectRepository;
import com.resourcemng.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository userRepository;
   public Project createPorject(Project project) throws MyException {
     try {
       //TODO 创建三个关联用户
       Tuser reportUser = new Tuser();
       // 可以不要
       reportUser.setMajorName(project.getMajorName());
       reportUser.setUserRole(UserRole.REPORT);
       reportUser.setUserNo(project.getProjectNo() + "-1");
       reportUser.setUserPassword("654321");
       //用户名空字段，可以不要
//     reportUser.setUserName(project.getReportHead());
       reportUser.setTelephone(project.getRhTel());
       //用户名空字段，可以不要
//     reportUser.setIsDelete();
       Tuser reportUserA = userRepository.save(reportUser);

       //财务
       Tuser financeUser = new Tuser();
       financeUser.setUserRole(UserRole.FINANCE);
       financeUser.setMajorName(project.getMajorName());
       financeUser.setUserNo(project.getProjectNo() + "-2");
       financeUser.setUserPassword("654321");
       financeUser.setTelephone(project.getFhTel());
       userRepository.save(financeUser);
       //项目负责人
       Tuser projectUser = new Tuser();
       projectUser.setUserRole(UserRole.PROJECTHEADER);
       projectUser.setMajorName(project.getMajorName());
       projectUser.setUserNo(project.getProjectNo() + "-3");
       projectUser.setUserPassword("654321");
       projectUser.setTelephone(project.getPhTel());
       userRepository.save(projectUser);

       //设置项目报告人ID
       project.setUserId(reportUserA.getUserId());
       return projectRepository.save(project);
     }catch (Exception e) {
       e.printStackTrace();
       throw new MyException(e);
     }
   }

  /**
   *
   * @param project
   * @return
   */
  public Project  updatePorject(Project project) throws MyException {
    //删除关联用户
    this.deletePorject(project.getProjectNo());
    return this.createPorject(project);
  }

  /**
   *
   * @param projectNo
   */
  public void  deletePorject(String projectNo){
    //删除关联用户
    userRepository.deleteById(projectNo);
    //删除项目信息
    projectRepository.deleteById(projectNo);
  }
  /**
   *
   */
  public void  queryProject(){

    projectRepository.findAll();

  }

  /**
   * 解析上传文件，导入project
   * @param uploadFile
   */
  public void importPorjectByFile(File uploadFile) throws MyException {
    ImportParams params = new ImportParams();
    params.setTitleRows(0);
    params.setHeadRows(1);
    params.setNeedSave(true);
    List<Project> list = ExcelImportUtil.importExcel(uploadFile, Project.class, params);
    if(list == null){
      throw new MyException("您的文件中没用任何项目信息，请确认文件");
    }
    for(Project p:list){
      if (p.getProjectNo() != null){
       this.createPorject(p);
      }
    }

  }

  /**
   *
   * @param projectNo
   * @return
   */
  public Object get(String projectNo) {
    return this.projectRepository.findById(projectNo);
  }
}
