package org.example.repository;

import org.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByRoleNameContainingIgnoreCase(String roleName);
    Optional<Role> findByRoleName(String roleName);
}
