package com.lightshell.transport.service;

import com.lightshell.transport.common.SuperService;
import com.lightshell.transport.entity.TransportInfo;
import com.lightshell.transport.entity.TransportTime;
import com.lightshell.transport.repository.TransportInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransportInfoServiceImpl implements SuperService<TransportInfo, Integer> {

    @Autowired
    private TransportInfoRepository transportInfoRepository;

    @Autowired
    private TransportTimeServiceImpl transportTimeService;

    @Override
    public JpaRepository<TransportInfo, Integer> getJpaRepository() {
        return transportInfoRepository;
    }

    @Override
    public JpaSpecificationExecutor<TransportInfo> getJpaSpecificationExecutor() {
        return transportInfoRepository;
    }

    @Override
    public TransportInfo findByUID(String uid) {
        return transportInfoRepository.findByUIDEquals(uid);
    }

    public List<TransportInfo> findByOpenIdAndStatus(String openId, String status) {
        return transportInfoRepository.findByOpenIdAndStatus(openId, status);
    }

    public TransportTime findTransportTimeByUID(String uid) {
        return transportTimeService.findByUID(uid);
    }

    public List<TransportTime> findTransportTimeByOpenId(String openId) {
        return transportTimeService.findByOpenId(openId);
    }

    public List<TransportTime> findTransportTimeByOpenIdAndStatus(String openId, String status) {
        return transportTimeService.findByOpenIdAndStatus(openId, status);
    }

    public TransportTime saveTransportTime(TransportTime entity) {
        return transportTimeService.save(entity);
    }

}
