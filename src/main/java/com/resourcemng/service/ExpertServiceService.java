package com.resourcemng.service;

import com.resourcemng.entitys.Experts;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.repository.ExpertsRepository;
import com.resourcemng.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpertServiceService  /*implements  UserDetailsService*/{
  @Autowired
  ExpertsRepository expertsRepository;


  public Object update(Experts experts){

    return  expertsRepository.save(experts);
  }
  public void batchDelete(List ids){

      expertsRepository.deleteByIdIn(ids);
  }

  public Object create(Experts experts) {
    return  expertsRepository.save(experts);
  }
  public Object find() {
    return  expertsRepository.findAll();
  }
}



