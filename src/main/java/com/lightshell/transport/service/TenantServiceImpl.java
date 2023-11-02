package com.lightshell.transport.service;

import com.lightshell.transport.common.SuperService;
import com.lightshell.transport.entity.Tenant;
import com.lightshell.transport.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements SuperService<Tenant, Integer> {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public JpaRepository<Tenant, Integer> getJpaRepository() {
        return tenantRepository;
    }

    @Override
    public JpaSpecificationExecutor<Tenant> getJpaSpecificationExecutor() {
        return tenantRepository;
    }

    @Override
    public Tenant findByUID(String uid) {
        return tenantRepository.findByUIDEquals(uid);
    }

    public Tenant findByUIDAndParentId(String uid, String parentId) {
        return tenantRepository.findByUIDAndParentId(uid, parentId);
    }
}
