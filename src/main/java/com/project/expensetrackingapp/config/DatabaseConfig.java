package com.project.expensetrackingapp.config;

import com.project.expensetrackingapp.repository.UserRepository;
import com.project.expensetrackingapp.repository.UserRepositoryImpl;
import com.project.expensetrackingapp.repository.UserRepositoryMongo;
import com.project.expensetrackingapp.repository.UserRepositoryPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public UserRepository userRepository(UserRepositoryImpl userRepositoryImpl){
        return userRepositoryImpl;
    }

    @Bean
    public UserRepositoryImpl userRepositoryImpl(UserRepositoryPostgres userRepositoryPostgres, UserRepositoryMongo userRepositoryMongo) {
        return new UserRepositoryImpl(userRepositoryPostgres, userRepositoryMongo);
    }

}
