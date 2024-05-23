package com.project.expensetrackingapp.repository.role;

import com.project.expensetrackingapp.repository.entity.role.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * User Role Database Strategy Interface
 * @author Angel Sic
 */
public interface UserRoleDatabaseStrategy {
    List<UserRole> findAll();
    UserRole save(UserRole entity);
    Optional<UserRole> findByName(String name);
}
