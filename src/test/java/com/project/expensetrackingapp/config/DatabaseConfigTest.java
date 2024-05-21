package com.project.expensetrackingapp.config;

import com.project.expensetrackingapp.repository.category.CategoryMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.category.CategoryPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinanceMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinancePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.role.UserRoleMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.role.UserRolePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinancePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserPostgresDatabaseStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DatabaseConfigTest {

    @InjectMocks
    private DatabaseConfig databaseConfig;

    @Mock
    private UserMongoDatabaseStrategy userMongoDatabaseStrategy;
    @Mock
    private UserRoleMongoDatabaseStrategy userRoleMongoDatabaseStrategy;
    @Mock
    private PortfolioMongoDatabaseStrategy portfolioMongoDatabaseStrategy;
    @Mock
    private CategoryMongoDatabaseStrategy categoryMongoDatabaseStrategy;
    @Mock
    private TypeFinanceMongoDatabaseStrategy typeFinanceMongoDatabaseStrategy;
    @Mock
    private FinanceMongoDatabaseStrategy financeMongoDatabaseStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testPostgresDataSource() {
        databaseConfig.setDataSource("postgres");
        assertThat(databaseConfig.connectDatabaseUserStrategy()).isInstanceOf(UserPostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseUserRoleStrategy()).isInstanceOf(UserRolePostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabasePortfolioStrategy()).isInstanceOf(PortfolioPostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseCategoryStrategy()).isInstanceOf(CategoryPostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseTypeFinanceStrategy()).isInstanceOf(TypeFinancePostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseFinanceStrategy()).isInstanceOf(FinancePostgresDatabaseStrategy.class);
    }

    @Test
    void testOtherDataSource() {
        databaseConfig.setDataSource("other");
        assertThat(databaseConfig.connectDatabaseUserStrategy()).isInstanceOf(UserPostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseUserRoleStrategy()).isInstanceOf(UserRolePostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabasePortfolioStrategy()).isInstanceOf(PortfolioPostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseCategoryStrategy()).isInstanceOf(CategoryPostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseTypeFinanceStrategy()).isInstanceOf(TypeFinancePostgresDatabaseStrategy.class);
        assertThat(databaseConfig.connectDatabaseFinanceStrategy()).isInstanceOf(FinancePostgresDatabaseStrategy.class);
    }
}