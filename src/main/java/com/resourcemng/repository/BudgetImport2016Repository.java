package com.resourcemng.repository;

import com.resourcemng.entity.BudgetImportDetail2016;
import com.resourcemng.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface BudgetImport2016Repository extends JpaRepository<BudgetImportDetail2016, Long> {


}
