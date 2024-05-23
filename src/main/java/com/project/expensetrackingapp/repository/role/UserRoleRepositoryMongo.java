package com.project.expensetrackingapp.repository.role;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User Role Mongodb Connection Interface
 * @author Angel Sic
 */
public interface UserRoleRepositoryMongo  extends MongoRepository<UserRole, Long> {
}
