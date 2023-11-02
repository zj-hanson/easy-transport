package com.lightshell.transport.service;

import com.lightshell.transport.entity.WechatUser;
import com.lightshell.transport.repository.WechatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatUserServiceImpl {

    @Autowired
    private WechatUserRepository wechatUserRepository;

    public WechatUser findByOpenId(String openId) {
        return wechatUserRepository.findByOpenId(openId);
    }

    public WechatUser save(WechatUser entity) {
        return wechatUserRepository.save(entity);
    }

}
