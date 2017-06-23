package com.resourcemng.repository;

import com.resourcemng.entitys.Experts;
import com.resourcemng.entitys.FundsOut;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface FundsOutRepository extends JpaRepository<FundsOut, String> {
}
