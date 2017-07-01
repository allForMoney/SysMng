package com.resourcemng.service;

import com.resourcemng.Enum.CodeClassType;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.CommonRepository;
import com.resourcemng.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonService  /*implements  UserDetailsService*/{
  @Autowired
  CommonRepository commonRepository;

  public Object getPorvences(){
    return  commonRepository.findByClassName(CodeClassType.PROVENCE);
  }

}



