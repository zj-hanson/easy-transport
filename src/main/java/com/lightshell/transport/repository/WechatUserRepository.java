package com.lightshell.transport.repository;

import com.lightshell.transport.entity.WechatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WechatUserRepository extends JpaRepository<WechatUser, Integer>, JpaSpecificationExecutor<WechatUser> {

    WechatUser findByOpenId(String openId);

}
