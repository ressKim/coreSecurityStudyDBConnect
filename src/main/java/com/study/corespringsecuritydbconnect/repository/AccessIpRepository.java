package com.study.corespringsecuritydbconnect.repository;

import com.study.corespringsecuritydbconnect.domain.entity.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    AccessIp findByIpAddress(String IpAddress);
}
