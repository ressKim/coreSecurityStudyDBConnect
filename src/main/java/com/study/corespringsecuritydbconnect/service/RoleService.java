package com.study.corespringsecuritydbconnect.service;

import com.study.corespringsecuritydbconnect.domain.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}