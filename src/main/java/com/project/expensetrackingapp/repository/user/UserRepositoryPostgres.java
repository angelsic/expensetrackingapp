package com.project.expensetrackingapp.repository.user;

import com.project.expensetrackingapp.repository.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User Postgres Connection Interface
 * @author Angel Sic
 */
@Repository
public interface UserRepositoryPostgres extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
