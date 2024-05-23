package com.project.expensetrackingapp.config;

import com.project.expensetrackingapp.repository.category.CategoryDatabaseStrategy;
import com.project.expensetrackingapp.repository.category.CategoryMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.category.CategoryPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinanceDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinanceMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinancePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.role.UserRoleDatabaseStrategy;
import com.project.expensetrackingapp.repository.role.UserRoleMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.role.UserRolePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinancePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserPostgresDatabaseStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DatabaseConfig allows to define project database source,
 * depends of database.use in application.properties file.
 * Sets the information source for all repositories
 * @author Angel Sic
 */
@Configuration
public class DatabaseConfig {

    @Value("${database.use}")
    private String dataSource;

    /**
     * Define dataSource type inside application
     * @param dataSource
     */
    public void setDataSource(String dataSource){
        this.dataSource = dataSource;
    }

    @Bean(name = "userStrategy")
    public UserDatabaseStrategy connectDatabaseUserStrategy(){
        if(dataSource.equals("mongo")){
            return new UserMongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new UserPostgresDatabaseStrategy();
        }else{
            return new UserPostgresDatabaseStrategy();
        }
    }

    @Bean(name = "roleStrategy")
    public UserRoleDatabaseStrategy connectDatabaseUserRoleStrategy(){
        if(dataSource.equals("mongo")){
            return new UserRoleMongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new UserRolePostgresDatabaseStrategy();
        }else{
            return new UserRolePostgresDatabaseStrategy();
        }
    }

    @Bean(name = "portfolioStrategy")
    public PortfolioDatabaseStrategy connectDatabasePortfolioStrategy(){
        if(dataSource.equals("mongo")){
            return new PortfolioMongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new PortfolioPostgresDatabaseStrategy();
        }else{
            return new PortfolioPostgresDatabaseStrategy();
        }
    }

    @Bean(name = "categoryStrategy")
    public CategoryDatabaseStrategy connectDatabaseCategoryStrategy(){
        if(dataSource.equals("mongo")){
            return new CategoryMongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new CategoryPostgresDatabaseStrategy();
        }else{
            return new CategoryPostgresDatabaseStrategy();
        }
    }

    @Bean(name = "typeFinanceStrategy")
    public TypeFinanceDatabaseStrategy connectDatabaseTypeFinanceStrategy(){
        if(dataSource.equals("mongo")){
            return new TypeFinanceMongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new TypeFinancePostgresDatabaseStrategy();
        }else{
            return new TypeFinancePostgresDatabaseStrategy();
        }
    }

    @Bean(name = "financeStrategy")
    public FinanceDatabaseStrategy connectDatabaseFinanceStrategy(){
        if(dataSource.equals("mongo")){
            return new FinanceMongoDatabaseStrategy();
        }else if (dataSource.equals("postgres")){
            return new FinancePostgresDatabaseStrategy();
        }else{
            return new FinancePostgresDatabaseStrategy();
        }
    }

}
