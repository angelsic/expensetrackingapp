package com.project.expensetrackingapp.repository.role;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User Role Postgres Connection Interface
 * @author Angel Sic
 */
@Repository
public interface UserRoleRepositoryPostgres extends CrudRepository<UserRole, Long> {
}
