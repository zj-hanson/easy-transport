package com.lightshell.transport.service;

import com.lightshell.transport.common.SuperService;
import com.lightshell.transport.entity.TransportTime;
import com.lightshell.transport.repository.TransportTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportTimeServiceImpl implements SuperService<TransportTime, Integer> {

    @Autowired
    private TransportTimeRepository transportTimeRepository;

    @Override
    public JpaRepository<TransportTime, Integer> getJpaRepository() {
        return transportTimeRepository;
    }

    @Override
    public JpaSpecificationExecutor<TransportTime> getJpaSpecificationExecutor() {
        return transportTimeRepository;
    }

    @Override
    public TransportTime findByUID(String uid) {
        return transportTimeRepository.findByUIDEquals(uid);
    }

    public List<TransportTime> findByOpenId(String openId) {
        return transportTimeRepository.findByOpenId(openId);
    }

    public List<TransportTime> findByOpenIdAndStatus(String openId, String status) {
        return transportTimeRepository.findByOpenIdAndStatus(openId, status);
    }

}
