package com.lightshell.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import com.lightshell.transport.common.SuperService;
import com.lightshell.transport.entity.SystemUser;
import com.lightshell.transport.repository.SystemUserRepository;

/**
 * @author kevindong
 */
@Service
public class SystemUserServiceImpl implements SuperService<SystemUser, Integer> {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Override
    public JpaRepository<SystemUser, Integer> getJpaRepository() {
        return systemUserRepository;
    }

    @Override
    public JpaSpecificationExecutor<SystemUser> getJpaSpecificationExecutor() {
        return systemUserRepository;
    }

    @Override
    public SystemUser findByUID(String uid) {
        return systemUserRepository.findByUIDEquals(uid);
    }

    public SystemUser findByUserid(String userid) {
        return systemUserRepository.findByUseridEquals(userid);
    }

}