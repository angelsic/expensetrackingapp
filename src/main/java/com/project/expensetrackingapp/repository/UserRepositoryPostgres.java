package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryPostgres extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    User findFirstById(Long id);
}
