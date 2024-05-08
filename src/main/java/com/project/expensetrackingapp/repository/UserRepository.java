package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    User findFirstById(Long id);
}
