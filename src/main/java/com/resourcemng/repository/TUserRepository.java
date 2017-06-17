package com.resourcemng.repository;

import com.resourcemng.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface TUserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List <User> findByNameAndAge(String name, Integer age);

//    @Query("from User u where u.name=:name")
//    List<TUserEntity> findUser(@Param("name") String name);


}
