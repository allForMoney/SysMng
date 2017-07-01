package com.resourcemng.repository;

import com.resourcemng.entitys.FundsOut;
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
public interface FundsOutRepository extends JpaRepository<FundsOut, String> {
  @Query("from FundsOut p where p.userId=:userId and  p.quarterNum=:quarterNum and  p.projectYear=:projectYear")
  List<FundsOut> findByParams(@Param("userId")String userId, @Param("quarterNum")String quarterNum, @Param("projectYear")String projectYear);

//  @Query("delete from FundsOut p where p.userId=:userId and  p.quarterNum=:quarterNum and  p.projectYear=:projectYear")
//  void deleteByParams(@Param("userId")String userId, @Param("quarterNum")String quarterNum, @Param("projectYear")String projectYear);

  void deleteByUserIdAndQuarterNumAndProjectYear(String userId, String quarterNum, String projectYear);

}
