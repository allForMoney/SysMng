package com.resourcemng.repository;

import com.resourcemng.entitys.BudgetImportDetailNew;
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
public interface BudgetImport2016Repository extends JpaRepository<BudgetImportDetailNew, String> {

  @Query("from BudgetImportDetailNew p where p.fileImportId=:fileImportId")
  List<BudgetImportDetailNew> findByBudgetImportId(@Param("fileImportId")String fileImportId);
}
