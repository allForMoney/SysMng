package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.resourcemng.Enum.UserRole;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.ProjectRepository;
import com.resourcemng.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class ProjectService {
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository userRepository;
  @Transactional
   public Project createPorject(Project project) throws MyException {
     try {
       // 创建三个关联用户
       Tuser reportUser = new Tuser();
       // 可以不要
       reportUser.setMajorName(project.getMajorName());
       reportUser.setUserRole(UserRole.REPORT);
       reportUser.setUserNo(project.getProjectNo() + "-1");
       reportUser.setUserPassword("654321");
       //用户名空字段，可以不要
//     reportUser.setUserName(project.getReportHead());
       reportUser.setTelephoneNum(project.getReporterTel());
       //用户名空字段，可以不要
//     reportUser.setIsDelete();
       Tuser reportUserA = userRepository.save(reportUser);

       //财务
       Tuser financeUser = new Tuser();
       financeUser.setUserRole(UserRole.FINANCE);
       financeUser.setMajorName(project.getMajorName());
       financeUser.setUserNo(project.getProjectNo() + "-2");
       financeUser.setUserPassword("654321");
       financeUser.setTelephoneNum(project.getFinaceHeaderTel());
       userRepository.save(financeUser);
       //项目负责人
       Tuser projectUser = new Tuser();
       projectUser.setUserRole(UserRole.PROJECTHEADER);
       projectUser.setMajorName(project.getMajorName());
       projectUser.setUserNo(project.getProjectNo() + "-3");
       projectUser.setUserPassword("654321");
       projectUser.setTelephoneNum(project.getProjectHeaderTel());
       userRepository.save(projectUser);

       //设置项目报告人ID
       project.setImportUserId(reportUserA.getId());
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
    userRepository.deleteByProjectNo(projectNo);
    //删除项目信息
    projectRepository.deleteByNo(projectNo);
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
  public Project get(String projectNo) {
    return this.projectRepository.findByProjectNo(projectNo);
  }

  /**
   * 查询
   * @param projectNo
   * @param majorName
   * @param schoolName
   */
  public  List<Project> find(String projectNo, String majorName, String schoolName) {
    projectNo = projectNo ==null?"":projectNo;
    majorName = majorName ==null?"":majorName;
    schoolName = schoolName ==null?"":schoolName;
      return this.projectRepository.findByParams(projectNo,majorName,schoolName);
   }

  /**
   * 修改项目联系人电话
   * @param user
   * @return
   */
  public void changePorjectUser(Tuser user) throws MyException {
    try {
      String userNo = user.getUserNo();
      String projectNo = userNo.substring(0, userNo.lastIndexOf('-') );
      String lastIndex = userNo.substring(userNo.length() - 1, userNo.length());
      Tuser tuser = this.userRepository.findByUserNo(userNo);
      Project project = this.projectRepository.findByProjectNo(projectNo);
      tuser.setTelephoneNum(user.getTelephoneNum());
      switch (lastIndex) {
        case "1"://修改填报人信息
          project.setReporterTel(user.getTelephoneNum());
          break;
        case "2"://修改财务信息
          project.setFinaceHeaderTel(user.getTelephoneNum());
          break;
        case "3"://修改项目负责人信息
          project.setProjectHeaderTel(user.getTelephoneNum());
          break;
        default:
          break;
      }
      this.userRepository.save(tuser);//更新用户信息
      this.projectRepository.save(project);//更新项目信息
    }catch (Exception e){
        throw new MyException(e);
    }
  }
}
