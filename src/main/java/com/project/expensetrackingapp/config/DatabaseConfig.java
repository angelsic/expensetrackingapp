package com.project.expensetrackingapp.config;

import com.project.expensetrackingapp.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Value("${database.use}")
    private String dataSource;

    @Bean(name = "strategy")
    public DatabaseStrategy connectDatabaseStrategy(){
        if(dataSource.equals("mongo")){
            return new MongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new PostgresDatabaseStrategy();
        }else{
            return new PostgresDatabaseStrategy();
        }
    }

}
