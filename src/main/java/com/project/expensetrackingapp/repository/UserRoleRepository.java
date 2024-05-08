package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository  extends CrudRepository<UserRole, Long> {
}
