package com.resourcemng.repository;

import com.resourcemng.entitys.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface ProjectRepository extends JpaRepository<Project, String> {
  @Query("from Project p where p.projectNo like CONCAT('%',:projectNo,'%') and  p.majorName like CONCAT('%',:majorName,'%') and  p.schoolName like CONCAT('%',:schoolName,'%')")
    Page findByProjectNoLikeAndMajorNameLikeAndSchoolNameLike(@Param("projectNo")String projectNo, @Param("majorName")String majorName, @Param("schoolName") String schoolName , Pageable pageable);
//   @Query("from Project p where p.projectNo like CONCAT('%',:projectNo,'%') and  p.majorName like CONCAT('%',:majorName,'%') and  p.schoolName like CONCAT('%',:schoolName,'%')")
//    List<Project> findByParams( @Param("projectNo")String projectNo,  @Param("majorName")String majorName, @Param("schoolName") String schoolName);

  @Query("from Project p where p.projectNo=:projectNo")
  Project findByProjectNo( @Param("projectNo")String projectNo);

//  @Query("delete from Project p where p.projectNo=:projectNo")
  void deleteByProjectNo(String projectNo);


}
