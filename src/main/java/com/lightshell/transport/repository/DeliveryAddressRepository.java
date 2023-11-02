package com.lightshell.transport.repository;

import com.lightshell.transport.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer>, JpaSpecificationExecutor<DeliveryAddress> {

    DeliveryAddress findByUID(String uid);

    List<DeliveryAddress> findByOpenId(String openId);

}
