package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.model.Role;
import com.radix.usermanagement.repository.RoleRepository;
import com.radix.usermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findOrCreateRole(String roleName) {
        // Check if the role already exists
        Optional<Role> optionalRole = roleRepository.findByName(roleName);

        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }

        // If not exist, create a new role
        Role newRole = Role.builder()
                .name(roleName)
                .build();
        return roleRepository.save(newRole);
    }
}