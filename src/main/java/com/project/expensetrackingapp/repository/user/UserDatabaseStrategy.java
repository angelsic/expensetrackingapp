package com.project.expensetrackingapp.repository.user;

import java.util.List;
import com.project.expensetrackingapp.repository.entity.user.User;

/**
 * User Database Strategy Interface
 * @author Angel Sic
 */
public interface UserDatabaseStrategy {
    User findByUsername(String username);
    List<User> findAll();
    User save(User entity);
    void deleteById(Long id);
}
