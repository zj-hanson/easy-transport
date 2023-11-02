package com.lightshell.transport.repository;

import com.lightshell.transport.entity.TransportTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TransportTimeRepository extends JpaRepository<TransportTime, Integer>, JpaSpecificationExecutor<TransportTime> {

    TransportTime findByUIDEquals(String uid);

    List<TransportTime> findByOpenId(String openId);

    List<TransportTime> findByOpenIdAndStatus(String openId, String status);

}
