package com.resourcemng.repository;

import com.resourcemng.entitys.Project;
import org.springframework.data.jpa.repository.JpaRepository;
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
   @Query("from Project p where p.schoolName=:schoolName")
    List<Project> findByParams(@Param("schoolName")String schoolName);

//   @Query("delete from Project p where p.projectNo=:projectNo")
//    List<Project> deleteByProjectNo(String projectNo);


//    @Query("from User u where u.name=:name")
//    List<TUserEntity> findUser(@Param("name") String name);


}
