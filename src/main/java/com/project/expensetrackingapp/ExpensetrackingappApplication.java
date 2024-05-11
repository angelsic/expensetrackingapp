package com.project.expensetrackingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.project.expensetrackingapp"})
@EnableJpaRepositories(basePackages = {"com.project.expensetrackingapp.repository"})
@EnableMongoRepositories(basePackages = {"com.project.expensetrackingapp.repository"})
public class ExpensetrackingappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensetrackingappApplication.class, args);
	}

}
