package com.lightshell.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lightshell.transport.entity.SystemUser;

/**
 * @author kevindong
 */
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>, JpaSpecificationExecutor<SystemUser> {

    SystemUser findByUIDEquals(String uid);

    SystemUser findByUseridEquals(String userid);

}
