package com.lightshell.transport.repository;

import com.lightshell.transport.entity.TransportInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TransportInfoRepository extends JpaRepository<TransportInfo, Integer>, JpaSpecificationExecutor<TransportInfo> {

    TransportInfo findByUIDEquals(String uid);

    List<TransportInfo> findByOpenIdAndStatus(String openId, String status);

}
