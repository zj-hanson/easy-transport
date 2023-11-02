package com.lightshell.transport.service;

import com.lightshell.transport.common.SuperService;
import com.lightshell.transport.entity.VehicleInfo;
import com.lightshell.transport.repository.VehicleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleInfoServiceImpl implements SuperService<VehicleInfo, Integer> {

    @Autowired
    private VehicleInfoRepository vehicleInfoRepository;

    @Override
    public JpaRepository<VehicleInfo, Integer> getJpaRepository() {
        return vehicleInfoRepository;
    }

    @Override
    public JpaSpecificationExecutor<VehicleInfo> getJpaSpecificationExecutor() {
        return vehicleInfoRepository;
    }

    @Override
    public VehicleInfo findByUID(String uid) {
        return vehicleInfoRepository.findByUIDEquals(uid);
    }

    public List<VehicleInfo> findByOpenId(String openId) {
        return vehicleInfoRepository.findByOpenId(openId);
    }

}
