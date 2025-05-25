package com.khuongkayyy.ddd.domain.service.impl;

import com.khuongkayyy.ddd.domain.repository.HiDomainRepository;
import com.khuongkayyy.ddd.domain.service.HiDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiDomainServiceImpl implements HiDomainService {

    @Autowired
    private HiDomainRepository hiDomainRepository;

    @Override
    public String sayHi(String text){
        return hiDomainRepository.sayHi(text);
    }
}
