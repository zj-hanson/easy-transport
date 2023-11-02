package com.lightshell.transport.repository;

import com.lightshell.transport.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TenantRepository extends JpaRepository<Tenant, Integer>, JpaSpecificationExecutor<Tenant> {

    Tenant findByUIDEquals(String uid);

    Tenant findByUIDAndParentId(String uid, String parentId);

}
