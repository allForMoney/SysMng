package com.resourcemng.repository;

import com.resourcemng.entitys.Experts;
import com.resourcemng.entitys.LeaveMessage;
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
public interface LeaveMessageRepository extends JpaRepository<LeaveMessage, String> {
  @Query("from LeaveMessage p where p.mesType=:mesType")
  List findByType( @Param("mesType")String mesType);


  List findByMesTypeaAndProjectId(String mesType,String projectId);
}
