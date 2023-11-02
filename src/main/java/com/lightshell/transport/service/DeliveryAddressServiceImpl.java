package com.lightshell.transport.service;

import com.lightshell.transport.common.SuperService;
import com.lightshell.transport.entity.DeliveryAddress;
import com.lightshell.transport.repository.DeliveryAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeliveryAddressServiceImpl implements SuperService<DeliveryAddress, Integer> {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Override
    public JpaRepository<DeliveryAddress, Integer> getJpaRepository() {
        return deliveryAddressRepository;
    }

    @Override
    public JpaSpecificationExecutor<DeliveryAddress> getJpaSpecificationExecutor() {
        return deliveryAddressRepository;
    }

    @Override
    public DeliveryAddress findByUID(String uid) {
        return deliveryAddressRepository.findByUID(uid);
    }

    public List<DeliveryAddress> findByOpenId(String openId) {
        return deliveryAddressRepository.findByOpenId(openId);
    }

}
