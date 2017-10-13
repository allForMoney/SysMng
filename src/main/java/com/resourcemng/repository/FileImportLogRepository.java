package com.resourcemng.repository;

import com.resourcemng.entitys.FileImportLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface FileImportLogRepository extends JpaRepository<FileImportLog, String> {
//  @Query("from FileImportLog p where p.projectId=:projectId")
    List<FileImportLog> findByProjectIdAndImportTypeOrderByImportDateDesc(String projectId, String importType);

    List<FileImportLog> findByProjectIdInAndImportTypeOrderByImportDateDesc(List ids, String importType);

    Page findByProjectIdInAndImportTypeOrderByImportDateDesc(List ids, String importType, Pageable pageable);

    List<FileImportLog>  findByImportType(String importType);

    void  deleteByProjectId(String projectId);

    Page  findByImportType(String importType,Pageable pageable);

    List<FileImportLog> findByProjectId(String projectId);

  Page findByImportTypeAndProjectId(String importType,String projectId,Pageable pageable);

  @Query("from FileImportLog p where  p.projectId in (:projectIds) and p.importType='yusuan2' or p.importType='adjust2016' order by importDate desc")
  List<FileImportLog> findBudgetAdjustByProjectIds(@Param("projectIds")List<String> projectIds);


  @Query("from FileImportLog p where p.projectId like CONCAT('%',:projectId,'%')")
  List<FileImportLog> findByParam(@Param("projectId")String projectId);

  @Query("from FileImportLog p where p.importType='yusuan' or p.importType='yusuan2016' order by importDate desc")
  Page  findBudgetImport(Pageable pageable);

  @Query("from FileImportLog p where  p.projectId like CONCAT('%',:projectId,'%') and p.importType='yusuan' or p.importType='yusuan2016' order by importDate desc")
  Page  findBudgetImportByProjectId(@Param("projectId")String projectId, Pageable pageable);

  @Query("from FileImportLog p where  p.projectId like CONCAT('%',:projectId,'%') and p.importType='yusuan2' or p.importType='adjust2016' order by importDate desc")
  List<FileImportLog>   findBudgetAdjustImportByProjectId(@Param("projectId")String projectId);

  @Query("from FileImportLog p where  p.projectId like CONCAT('%',:projectId,'%') and p.importType='yusuan' or p.importType='yusuan2016' order by importDate desc")
  List<FileImportLog>   findBudgetImportByProjectId(@Param("projectId")String projectId);
//  @Query("from FileImportLog p where p.projectId=:projectId LEFT JOIN BudgetAuditLog log on log.adjustId = p.id and log.status=5")
//  List<FileImportLog>  findAuditAdjustLog(@Param("projectId")String projectId);

}
