package com.resourcemng.repository;

import com.resourcemng.entitys.Tuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface TUserRepository extends JpaRepository<Tuser, String> {
  @Query("from Tuser u where u.username like CONCAT('%',:projectNo,'%')")
    List<Tuser> findByProject(@Param("projectNo") String projectNo);

//  @Query("delete from Tuser u where u.username like CONCAT('%',:projectNo,'%')")
  @Transactional
  void deleteByUsernameLike(/*@Param("projectNo")*/String username);

  @Query("from Tuser u where u.username=:userNo")
  Tuser findByUserNo(@Param("userNo")String userNo);

//    @Query("from User u where u.name=:name")
//    List<TUserEntity> findUser(@Param("name") String name);


}
