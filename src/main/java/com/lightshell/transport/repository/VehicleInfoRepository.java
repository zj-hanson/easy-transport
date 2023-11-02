package com.lightshell.transport.repository;

import com.lightshell.transport.entity.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Integer>, JpaSpecificationExecutor<VehicleInfo> {

    VehicleInfo findByUIDEquals(String uid);

    List<VehicleInfo> findByOpenId(String openId);

}
