package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FinanceReportTest {

    @Test
    void testToString() {
        FinanceReport financeReport = new FinanceReport("Expense", 100.0);
        String expectedToString = "FinanceReport(typename=Expense, amount=100.0)";
        assertThat(financeReport.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        FinanceReport financeReport = new FinanceReport("Expense", 100.0);
        assertThat(financeReport).isEqualTo(financeReport);
    }

    @Test
    void testEqualsWithDifferentType() {
        FinanceReport financeReport = new FinanceReport("Expense", 100.0);
        assertThat(financeReport).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        FinanceReport financeReport = new FinanceReport("Expense", 100.0);
        assertThat(financeReport).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        FinanceReport financeReport1 = new FinanceReport("Expense", 100.0);
        FinanceReport financeReport2 = new FinanceReport("Income", 200.0);
        assertThat(financeReport1).isNotEqualTo(financeReport2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        FinanceReport financeReport1 = new FinanceReport("Expense", 100.0);
        FinanceReport financeReport2 = new FinanceReport("Expense", 100.0);
        assertThat(financeReport1.hashCode()).isEqualTo(financeReport2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        FinanceReport financeReport1 = new FinanceReport("Expense", 100.0);
        FinanceReport financeReport2 = new FinanceReport("Income", 200.0);
        assertThat(financeReport1.hashCode()).isNotEqualTo(financeReport2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        FinanceReport financeReport = new FinanceReport("Expense", 100.0);
        assertThat(financeReport.hashCode()).isNotEqualTo(null);
    }

}
