package com.study.corespringsecuritydbconnect.repository;

import com.study.corespringsecuritydbconnect.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
  int countByUsername(String username);
}