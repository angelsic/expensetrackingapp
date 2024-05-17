package com.project.expensetrackingapp.repository.role;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserRolePostgresDatabaseStrategy implements UserRoleDatabaseStrategy{

    @Autowired
    private UserRoleRepositoryPostgres repository;

    @Override
    public List<UserRole> findAll() {
        return (List<UserRole>) repository.findAll();
    }

    @Override
    public UserRole save(UserRole entity) {
        Optional<UserRole> newRol = findByName(entity.getName());
        if(newRol.isEmpty()) {
            return repository.save(entity);
        }
        return entity;
    }

    @Override
    public Optional<UserRole> findByName(String name) {
        return ((List<UserRole>) repository.findAll()).stream().filter(r -> r.getName().equals(name)).findFirst();
    }
}
