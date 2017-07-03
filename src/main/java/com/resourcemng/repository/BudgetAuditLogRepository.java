package com.resourcemng.repository;

import com.resourcemng.entitys.BudgetAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface BudgetAuditLogRepository extends JpaRepository<BudgetAuditLog, String> {
  /**
   * 根据指定的单个ID查找
   * @param adjustId
   * @return
   */
  BudgetAuditLog findByAdjustId(String adjustId);

  /**
   * 根据调整ID查找
   * @param adjustIds
   * @return
   */
  List<BudgetAuditLog> findByAdjustIdIn(Collection adjustIds);
}
