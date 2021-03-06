package com.resourcemng.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.resourcemng.Enum.UserRole;
import com.resourcemng.basic.MyException;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.FileImportLogRepository;
import com.resourcemng.repository.ProjectRepository;
import com.resourcemng.repository.TUserRepository;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ProjectService {
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  TUserRepository userRepository;
  @Autowired
  FileImportLogRepository fileImportLogRepository;
  @Autowired
  BudgetAdjustService budgetAdjustService;

  @Value("${app.user.defaultpassword}")
  private String defaultPass = "4QrcOUm6Wau+VuBX8g+IPg==";
  private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

  public Project createPorject(Project project) throws MyException {
    try {
      String pass = defaultPass;
//      String pass = MD5.encrypt(defaultPass);
      // 创建三个关联用户
      Tuser reportUser = new Tuser();
      Project existProject =  projectRepository.findByProjectNo(project.getProjectNo());
      if(existProject !=null){
        throw new MyException("项目编号不能重复，重复的项目编号是："+project.getProjectNo());
      }
      // 可以不要
      reportUser.setMajorName(project.getMajorName());
      reportUser.setUserRole(UserRole.REPORT);
      reportUser.setUsername(project.getProjectNo() + "-1");
      reportUser.setPassword(pass);
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
      financeUser.setUsername(project.getProjectNo() + "-2");
      financeUser.setPassword(pass);
      financeUser.setTelephoneNum(project.getFinaceHeaderTel());
      userRepository.save(financeUser);
      //项目负责人
      Tuser projectUser = new Tuser();
      projectUser.setUserRole(UserRole.PROJECTHEADER);
      projectUser.setMajorName(project.getMajorName());
      projectUser.setUsername(project.getProjectNo() + "-3");
      projectUser.setPassword(pass);
      projectUser.setTelephoneNum(project.getProjectHeaderTel());
      userRepository.save(projectUser);

      //设置项目报告人ID
      project.setImportUserId(reportUserA.getId());
      return projectRepository.save(project);
    } catch (Exception e) {
      e.printStackTrace();
      throw new MyException(e);
    }
  }

  /**
   * @param project
   * @return
   */
  public Project updatePorject(Project project) throws MyException {
    //删除关联用户
    this.deletePorject(project.getProjectNo(),false);
    return this.createPorject(project);
  }

  /**
   * @param projectNos
   */
  public void deletePorjects(List<String> projectNos) {
    if (projectNos == null) {
      return;
    }
    for (String projectNo : projectNos) {
      this.deletePorject(projectNo,true);
    }
  }

  /**
   * @param projectNo
   */
  public void deletePorject(String projectNo,boolean deleteRelatedInfo) {
    //删除关联用户
//    userRepository.deleteByUsernameLike(projectNo);
    List<Tuser> users = userRepository.findByProject(projectNo);
    if (users != null) {
      userRepository.deleteAll(users);
    }
    if(deleteRelatedInfo){
      Project project = projectRepository.findByProjectNo(projectNo);
      fileImportLogRepository.deleteByProjectId(project.getId());
    }
    //删除项目信息
    projectRepository.deleteByProjectNo(projectNo);
  }

  /**
   *
   */
  public void queryProject() {

    projectRepository.findAll();

  }

  /**
   * 解析上传文件，导入project
   *
   * @param uploadFile
   */
  public void importPorjectByFile(File uploadFile) throws MyException {
    ImportParams params = new ImportParams();
    params.setTitleRows(0);
    params.setHeadRows(1);
    if(!uploadFile.exists()){
      throw new MyException("您上传的文件不存在，文件路径：" + uploadFile.getAbsolutePath());
    }
    LOGGER.info("上传的文件路径：" + uploadFile.getAbsolutePath());
    List<Project> list = ExcelImportUtil.importExcel(uploadFile, Project.class, params);
    if (list == null) {
      throw new MyException("您的文件中没用任何项目信息，请确认文件");
    }
    //检查
    checkProjects(list);

    for (Project p : list) {
      if (p.getProjectNo() != null) {//项目编号不能为空
        this.createPorject(p);
      }
    }

  }


  protected void checkProjects(List<Project> list) throws MyException{
    //检查是否有重复的projectNo
    //
    List<Project> existP = projectRepository.findAll();
    Map<String,Project> map = new HashMap<String,Project>();
    List<String> existProjectNo = new ArrayList<String> ();
    int noProjectNo = 0;

    for(Project p:existP){
      map.put(p.getProjectNo(),p);
    }
    //循环检查
    for (Project p : list) {
      if (p.getProjectNo() == null) {
        noProjectNo++;
      }else if(map.get(p.getProjectNo())!=null){
        existProjectNo.add(p.getProjectNo());
      }
    }
    String msg = null;
    if(noProjectNo>0){
      msg = "有"+noProjectNo+"个项目没有项目编号";
    }
    if(existProjectNo.size()>0 &&noProjectNo>0){
      msg = msg+"，同时";
    }
    if(existProjectNo.size()>0){
      msg = (msg==null?"":msg)+"以下项目编号已经存在："+existProjectNo.toString();
    }
    if (msg!=null){
    throw new MyException(msg);
    }
  }
  /**
   * @param projectNo
   * @return
   */
  public Project get(String projectNo) {
    return this.projectRepository.findByProjectNo(projectNo);
  }

  /**
   * 查询
   *
   * @param projectNo
   * @param majorName
   * @param schoolName
   */
  public Page find(String projectNo, String majorName, String schoolName, Pageable pageable) {
    projectNo = projectNo == null ? "" : projectNo;
    majorName = majorName == null ? "" : majorName;
    schoolName = schoolName == null ? "" : schoolName;
    return this.projectRepository.findByProjectNoLikeAndMajorNameLikeAndSchoolNameLike(projectNo, majorName, schoolName, pageable);
  }

  /**
   * 修改项目联系人电话
   *
   * @return
   */
  public void changePorjectUser(Project project) throws MyException {
    try {
      Project projectJap = this.projectRepository.findByProjectNo(project.getProjectNo());
      projectJap.setFinaceHeaderTel(project.getFinaceHeaderTel());
      projectJap.setProjectHeaderTel(project.getProjectHeaderTel());
      projectJap.setReporterTel(project.getReporterTel());
      this.projectRepository.save(projectJap);
      String projectNo = project.getProjectNo();
      updateUserTel(projectNo + "-1", project.getReporterTel());//修改填报人信息
      updateUserTel(projectNo + "-2", project.getFinaceHeaderTel());//修改财务信息
      updateUserTel(projectNo + "-3", project.getProjectHeaderTel());//修改项目负责人信息
    } catch (Exception e) {
      throw new MyException(e);
    }
  }

  private void updateUserTel(String userNo, String tel) {
    Tuser user = this.userRepository.findByUserNo(userNo);
    user.setTelephoneNum(tel);
    this.userRepository.save(user);
  }
}
