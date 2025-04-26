package com.radix.usermanagement.repository;

import com.radix.usermanagement.model.UserRole;
import com.radix.usermanagement.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}