package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.finance.FinanceResponse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
public class FinanceResponseTest {
    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        String expectedToString = "FinanceResponse(id=1, portfolioName=Portfolio, typeFinanceName=Type, categoryName=Category, name=Name, amount=100.0, dateTime=" + dateTime + ")";
        assertThat(financeResponse.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        assertThat(financeResponse).isEqualTo(financeResponse);
    }

    @Test
    void testEqualsWithDifferentType() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        assertThat(financeResponse).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        assertThat(financeResponse).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse1 = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        FinanceResponse financeResponse2 = new FinanceResponse(2L, "Portfolio", "Type", "Category", "Name", 200.0, dateTime);
        assertThat(financeResponse1).isNotEqualTo(financeResponse2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse1 = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        FinanceResponse financeResponse2 = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        assertThat(financeResponse1.hashCode()).isEqualTo(financeResponse2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse1 = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        FinanceResponse financeResponse2 = new FinanceResponse(2L, "Portfolio", "Type", "Category", "Name", 200.0, dateTime);
        assertThat(financeResponse1.hashCode()).isNotEqualTo(financeResponse2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        LocalDateTime dateTime = LocalDateTime.now();
        FinanceResponse financeResponse = new FinanceResponse(1L, "Portfolio", "Type", "Category", "Name", 100.0, dateTime);
        assertThat(financeResponse.hashCode()).isNotEqualTo(null);
    }
}
