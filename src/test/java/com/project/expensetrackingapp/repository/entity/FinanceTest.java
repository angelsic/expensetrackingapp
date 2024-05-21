package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

public class FinanceTest {

    @Test
    void testToString() {
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", null);
        TypeFinance typeFinance = new TypeFinance(1L, "Expense");
        Category category = new Category(1L, "Test Category");
        LocalDateTime dateTime = LocalDateTime.now();

        Finance finance = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        String expectedToString = "Finance(id=1, portfolio=Portfolio(id=1, name=Test Portfolio, user=null), typeFinance=TypeFinance(id=1, name=Expense), category=Category(id=1, name=Test Category), name=Test Finance, amount=100.0, dateTime=" + dateTime + ")";
        assertThat(finance.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", null);
        TypeFinance typeFinance = new TypeFinance(1L, "Expense");
        Category category = new Category(1L, "Test Category");
        LocalDateTime dateTime = LocalDateTime.now();

        Finance finance = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        assertThat(finance).isEqualTo(finance);
    }

    @Test
    void testEqualsWithDifferentType() {
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", null);
        TypeFinance typeFinance = new TypeFinance(1L, "Expense");
        Category category = new Category(1L, "Test Category");
        LocalDateTime dateTime = LocalDateTime.now();

        Finance finance = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        assertThat(finance).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", null);
        TypeFinance typeFinance = new TypeFinance(1L, "Expense");
        Category category = new Category(1L, "Test Category");
        LocalDateTime dateTime = LocalDateTime.now();

        Finance finance = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        assertThat(finance).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Portfolio portfolio1 = new Portfolio(1L, "Test Portfolio 1", null);
        Portfolio portfolio2 = new Portfolio(2L, "Test Portfolio 2", null);

        TypeFinance typeFinance1 = new TypeFinance(1L, "Expense");
        TypeFinance typeFinance2 = new TypeFinance(2L, "Income");

        Category category1 = new Category(1L, "Test Category 1");
        Category category2 = new Category(2L, "Test Category 2");

        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now().plusDays(1);

        Finance finance1 = Finance.builder()
                .id(1L)
                .portfolio(portfolio1)
                .typeFinance(typeFinance1)
                .category(category1)
                .name("Test Finance 1")
                .amount(100.0)
                .dateTime(dateTime1)
                .build();

        Finance finance2 = Finance.builder()
                .id(2L)
                .portfolio(portfolio2)
                .typeFinance(typeFinance2)
                .category(category2)
                .name("Test Finance 2")
                .amount(200.0)
                .dateTime(dateTime2)
                .build();

        assertThat(finance1).isNotEqualTo(finance2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", null);
        TypeFinance typeFinance = new TypeFinance(1L, "Expense");
        Category category = new Category(1L, "Test Category");
        LocalDateTime dateTime = LocalDateTime.now();

        Finance finance1 = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        Finance finance2 = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        assertThat(finance1.hashCode()).isEqualTo(finance2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Portfolio portfolio1 = new Portfolio(1L, "Test Portfolio 1", null);
        Portfolio portfolio2 = new Portfolio(2L, "Test Portfolio 2", null);

        TypeFinance typeFinance1 = new TypeFinance(1L, "Expense");
        TypeFinance typeFinance2 = new TypeFinance(2L, "Income");

        Category category1 = new Category(1L, "Test Category 1");
        Category category2 = new Category(2L, "Test Category 2");

        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now().plusDays(1);

        Finance finance1 = Finance.builder()
                .id(1L)
                .portfolio(portfolio1)
                .typeFinance(typeFinance1)
                .category(category1)
                .name("Test Finance 1")
                .amount(100.0)
                .dateTime(dateTime1)
                .build();

        Finance finance2 = Finance.builder()
                .id(2L)
                .portfolio(portfolio2)
                .typeFinance(typeFinance2)
                .category(category2)
                .name("Test Finance 2")
                .amount(200.0)
                .dateTime(dateTime2)
                .build();

        assertThat(finance1.hashCode()).isNotEqualTo(finance2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", null);
        TypeFinance typeFinance = new TypeFinance(1L, "Expense");
        Category category = new Category(1L, "Test Category");
        LocalDateTime dateTime = LocalDateTime.now();

        Finance finance = Finance.builder()
                .id(1L)
                .portfolio(portfolio)
                .typeFinance(typeFinance)
                .category(category)
                .name("Test Finance")
                .amount(100.0)
                .dateTime(dateTime)
                .build();

        assertThat(finance.hashCode()).isNotEqualTo(null);
    }

    @Test
    void testSetters() {
        Finance finance = new Finance();

        finance.setId(1L);
        finance.setPortfolio(new Portfolio());
        finance.setTypeFinance(new TypeFinance());
        finance.setCategory(new Category());
        finance.setName("Test Finance");
        finance.setAmount(100.0);
        finance.setDateTime(LocalDateTime.now());

        assertThat(finance.getId()).isEqualTo(1L);
        assertThat(finance.getPortfolio()).isInstanceOf(Portfolio.class);
        assertThat(finance.getTypeFinance()).isInstanceOf(TypeFinance.class);
        assertThat(finance.getCategory()).isInstanceOf(Category.class);
        assertThat(finance.getName()).isEqualTo("Test Finance");
        assertThat(finance.getAmount()).isEqualTo(100.0);
        assertThat(finance.getDateTime()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void testEquals() {
        Finance finance1 = new Finance();
        finance1.setId(1L);
        finance1.setPortfolio(new Portfolio());
        finance1.setTypeFinance(new TypeFinance());
        finance1.setCategory(new Category());
        finance1.setName("Test Finance");
        finance1.setAmount(100.0);
        finance1.setDateTime(LocalDateTime.now());

        Finance finance2 = new Finance();
        finance2.setId(1L);
        finance2.setPortfolio(new Portfolio());
        finance2.setTypeFinance(new TypeFinance());
        finance2.setCategory(new Category());
        finance2.setName("Test Finance");
        finance2.setAmount(100.0);
        finance2.setDateTime(LocalDateTime.now());

        assertThat(finance1).isEqualTo(finance2);
    }

    @Test
    void testNotEquals() {
        Finance finance1 = new Finance();
        finance1.setId(1L);
        finance1.setPortfolio(new Portfolio());
        finance1.setTypeFinance(new TypeFinance());
        finance1.setCategory(new Category());
        finance1.setName("Test Finance");
        finance1.setAmount(100.0);
        finance1.setDateTime(LocalDateTime.now());

        Finance finance2 = new Finance();
        finance2.setId(2L);
        finance2.setPortfolio(new Portfolio());
        finance2.setTypeFinance(new TypeFinance());
        finance2.setCategory(new Category());
        finance2.setName("Test Finance 2");
        finance2.setAmount(200.0);
        finance2.setDateTime(LocalDateTime.now().plusDays(1));

        assertThat(finance1).isNotEqualTo(finance2);
    }
}
