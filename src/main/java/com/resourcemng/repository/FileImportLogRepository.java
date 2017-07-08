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
    List<FileImportLog> findByProjectIdAndImportTypeOrderByImportDate(String projectId, String importType);

    List<FileImportLog> findByProjectIdInAndImportTypeOrderByImportDateDesc(List ids, String importType);

    Page findByProjectIdInAndImportTypeOrderByImportDateDesc(List ids, String importType, Pageable pageable);

    List<FileImportLog>  findByImportType(String importType);

    List<FileImportLog> findByProjectId(String projectId);

  @Query("from FileImportLog p where p.projectId like CONCAT('%',:projectId,'%')")
  List<FileImportLog> findByParam(@Param("projectId")String projectId);
}
