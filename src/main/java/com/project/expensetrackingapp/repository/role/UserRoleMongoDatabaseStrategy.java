package com.project.expensetrackingapp.repository.role;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRoleMongoDatabaseStrategy implements UserRoleDatabaseStrategy{

    @Autowired
    private UserRoleRepositoryMongo repository;

    @Override
    public List<UserRole> findAll() {
        return repository.findAll();
    }

    @Override
    public UserRole save(UserRole entity) {
        Optional<UserRole> newRol = findByName(entity.getName());
        if(newRol.isEmpty()) {
            entity.setId(Math.abs(System.currentTimeMillis() + UUID.randomUUID().getMostSignificantBits()));
            return repository.save(entity);
        }
        return entity;
    }

    @Override
    public Optional<UserRole> findByName(String name) {
        return repository.findAll().stream().filter(r -> r.getName().equals(name)).findFirst();
    }
}
