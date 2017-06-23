package com.resourcemng.repository;

import com.resourcemng.entitys.Tuser;
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
public interface TUserRepository extends JpaRepository<Tuser, String> {
  @Query("from Tuser u where u.userNo like CONCAT('%',:projectNo,'%')")
    List<Tuser> findByProject(@Param("projectNo") String projectNo);

  @Modifying
  @Query("delete from Tuser u where u.userNo like CONCAT('%',:projectNo,'%')")
    void deleteByProject(@Param("projectNo")String projectNo);


//    @Query("from User u where u.name=:name")
//    List<TUserEntity> findUser(@Param("name") String name);


}
