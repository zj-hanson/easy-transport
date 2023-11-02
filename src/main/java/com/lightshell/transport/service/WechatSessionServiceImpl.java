package com.lightshell.transport.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lightshell.comm.BaseLib;
import com.lightshell.transport.entity.WechatSession;
import com.lightshell.transport.repository.WechatSessionRepository;

@Service
public class WechatSessionServiceImpl {

    @Autowired
    private WechatSessionRepository wechatSessionRepository;

    public WechatSessionServiceImpl() {

    }

    public WechatSession save(WechatSession entity) {
        return wechatSessionRepository.save(entity);
    }

    public WechatSession findByOpenIdAndSessionId(String openId, String sessionId) {
        return wechatSessionRepository.findByOpenIdAndSessionId(openId, sessionId);
    }

    public WechatSession findBySessionId(String sessionId) {
        return wechatSessionRepository.findBySessionId(sessionId);
    }

    public WechatSession findBySessionIdAndCheckCode(String sessionId, String checkCode) {
        return wechatSessionRepository.findBySessionIdAndCheckCode(sessionId, checkCode);
    }

    public String getCheckCode() {
        String base = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public boolean hasSession(String sessionId) {
        WechatSession session = findBySessionId(sessionId);
        return session != null;
    }

    public boolean hasSession(String openId, String sessionId) {
        WechatSession session = findByOpenIdAndSessionId(openId, sessionId);
        return session != null;
    }

    public void persistIfNotExist(String openId, String sessionKey, String unionId, String sessionId) {
        if (!hasSession(openId, sessionId)) {
            WechatSession session = new WechatSession(openId, sessionKey, unionId, sessionId);
            session.setExpiresIn(-1);
            session.setStatus("N");
            session.setCreatorToSystem();
            session.setCredate(BaseLib.getDate());
            wechatSessionRepository.save(session);
        }
    }

}
