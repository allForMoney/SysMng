package com.resourcemng.repository;

import com.resourcemng.entitys.FundsBudget;
import com.resourcemng.entitys.FundsIn;
import com.resourcemng.entitys.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface FundsBudgetRepository extends JpaRepository<FundsBudget, String> {
  List<FundsBudget> findByProjectId(String projectId);
}
