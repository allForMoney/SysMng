package com.resourcemng.repository;

import com.resourcemng.entitys.FundsIn;
import com.resourcemng.entitys.ReportAuditLog;
import com.resourcemng.entitys.Sitelog;
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
public interface ReportAuditLogRepository extends JpaRepository<ReportAuditLog, String> {
  @Query("from ReportAuditLog p where p.projectId=:projectId and  p.quarter=:quarter and  p.year=:year")
  ReportAuditLog findByParam(@Param("projectId")String projectId, @Param("quarter")String quarter, @Param("year")String year);

}
