package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.User;
import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository{
    @Autowired
    UserRepositoryPostgres userRepositoryPostgres;

    @Autowired
    UserRepositoryMongo userRepositoryMongo;

    @Value("${database.use}")
    private String databaseUse;

    private boolean isPostgresConfigured(){
        return this.databaseUse.equals("postgres");
    }

    private boolean isMongoConfigured(){
        return this.databaseUse.equals("mongodb");
    }

    @Autowired
    public UserRepositoryImpl(UserRepositoryPostgres userRepositoryPostgres, UserRepositoryMongo userRepositoryMongo){
        this.userRepositoryPostgres = userRepositoryPostgres;
        this.userRepositoryMongo = userRepositoryMongo;
    }


    @Override
    public User findByUsername(String username) {
        if(isPostgresConfigured()) {
            return userRepositoryPostgres.findByUsername(username);
        }else if (isMongoConfigured()){
            return userRepositoryMongo.findByUsername(username);
        }else{
            return userRepositoryPostgres.findByUsername(username);
        }
    }

    @Override
    public List<User> findAll() {
        if(isPostgresConfigured()){
            return userRepositoryPostgres.findAll();
        }else if(isMongoConfigured()){
            return userRepositoryMongo.findAll();
        }else {
            return userRepositoryPostgres.findAll();
        }
    }

    @Override
    public User save(User user){
        if(isPostgresConfigured()){
            return userRepositoryPostgres.save(user);
        }else if(isMongoConfigured()){
            user.setId(Math.abs(System.currentTimeMillis()+ UUID.randomUUID().getMostSignificantBits()));
            return userRepositoryMongo.save(user);
        }else {
            return userRepositoryPostgres.save(user);
        }
    }

}
