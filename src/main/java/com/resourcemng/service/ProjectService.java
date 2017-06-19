package com.resourcemng.service;

import com.resourcemng.entity.Project;
import com.resourcemng.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
  @Autowired
  ProjectRepository projectRepository;
   public Project createPorject(Project project){
     return projectRepository.save(project);
   }

  /**
   *
   * @param project
   * @return
   */
  public Project  updatePorject(Project project){
    return projectRepository.save(project);
  }

  /**
   *
   * @param id
   */
  public void  deletePorject(Long id){
     projectRepository.deleteById(id);
  }
  /**
   *
   */
  public void  queryProject(){
     projectRepository.findAll();
  }

}
