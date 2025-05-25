package com.khuongkayyy.ddd.infrastructure.persistence.repository;

import com.khuongkayyy.ddd.domain.repository.HiDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class HiInfrasRepositoryImpl implements HiDomainRepository {
    @Override
    public String sayHi(String text){
        return "Hi Infrastructure";
    }
}
