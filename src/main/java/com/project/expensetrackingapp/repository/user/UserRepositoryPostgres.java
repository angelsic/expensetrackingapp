package com.project.expensetrackingapp.repository.user;

import com.project.expensetrackingapp.repository.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryPostgres extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
