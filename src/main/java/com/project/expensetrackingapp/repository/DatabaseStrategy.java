package com.project.expensetrackingapp.repository;

import java.util.List;
import com.project.expensetrackingapp.repository.entity.User;

public interface DatabaseStrategy {
    User findByUsername(String username);
    List<User> findAll();
    User save(User entity);
    void deleteById(Long id);
}
