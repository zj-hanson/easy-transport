package com.lightshell.transport.repository;

import com.lightshell.transport.entity.WechatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WechatSessionRepository extends JpaRepository<WechatSession, Integer>, JpaSpecificationExecutor<WechatSession> {

    WechatSession findByOpenIdAndSessionId(String openId, String sessionId);

    WechatSession findBySessionId(String sessionId);

    WechatSession findBySessionIdAndCheckCode(String sessionId, String checkCode);

}
