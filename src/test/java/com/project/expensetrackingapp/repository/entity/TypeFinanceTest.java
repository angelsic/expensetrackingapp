package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeFinanceTest {
    @Test
    void testToString() {
        TypeFinance typeFinance = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        String expectedToString = "TypeFinance(id=1, name=Expense)";
        assertThat(typeFinance.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        TypeFinance typeFinance = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        assertThat(typeFinance).isEqualTo(typeFinance);
    }

    @Test
    void testEqualsWithDifferentType() {
        TypeFinance typeFinance = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        assertThat(typeFinance).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        TypeFinance typeFinance = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        assertThat(typeFinance).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        TypeFinance typeFinance1 = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        TypeFinance typeFinance2 = TypeFinance.builder()
                .id(2L)
                .name("Income")
                .build();

        assertThat(typeFinance1).isNotEqualTo(typeFinance2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        TypeFinance typeFinance1 = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        TypeFinance typeFinance2 = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        assertThat(typeFinance1.hashCode()).isEqualTo(typeFinance2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        TypeFinance typeFinance1 = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        TypeFinance typeFinance2 = TypeFinance.builder()
                .id(2L)
                .name("Income")
                .build();

        assertThat(typeFinance1.hashCode()).isNotEqualTo(typeFinance2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        TypeFinance typeFinance = TypeFinance.builder()
                .id(1L)
                .name("Expense")
                .build();

        assertThat(typeFinance.hashCode()).isNotEqualTo(null);
    }
}
