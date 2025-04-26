package com.radix.usermanagement.service;

import com.radix.usermanagement.model.Role;

public interface RoleService {

    Role findOrCreateRole(String roleName);
}