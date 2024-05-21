package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.finance.FinanceRequest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

public class FinanceRequestTest {

    @Test
    void testToString() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        FinanceRequest financeRequest = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);

        String expectedToString = "FinanceRequest(id=1, idPortfolio=1, idTypeFinance=1, idCategory=1, initAmount=100.0, endAmount=200.0, start=" + start + ", end=" + end + ")";
        assertThat(financeRequest.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        FinanceRequest financeRequest = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);

        assertThat(financeRequest).isEqualTo(financeRequest);
    }

    @Test
    void testEqualsWithDifferentType() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        FinanceRequest financeRequest = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);

        assertThat(financeRequest).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        FinanceRequest financeRequest = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);

        assertThat(financeRequest).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        LocalDateTime start1 = LocalDateTime.now();
        LocalDateTime end1 = LocalDateTime.now().plusDays(7);

        LocalDateTime start2 = LocalDateTime.now().plusDays(1);
        LocalDateTime end2 = LocalDateTime.now().plusDays(8);

        FinanceRequest financeRequest1 = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start1, end1);
        FinanceRequest financeRequest2 = new FinanceRequest(2L, 2L, 2L, 2L, 150.0, 250.0, start2, end2);

        assertThat(financeRequest1).isNotEqualTo(financeRequest2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        FinanceRequest financeRequest1 = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);
        FinanceRequest financeRequest2 = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);

        assertThat(financeRequest1.hashCode()).isEqualTo(financeRequest2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        LocalDateTime start1 = LocalDateTime.now();
        LocalDateTime end1 = LocalDateTime.now().plusDays(7);

        LocalDateTime start2 = LocalDateTime.now().plusDays(1);
        LocalDateTime end2 = LocalDateTime.now().plusDays(8);

        FinanceRequest financeRequest1 = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start1, end1);
        FinanceRequest financeRequest2 = new FinanceRequest(2L, 2L, 2L, 2L, 150.0, 250.0, start2, end2);

        assertThat(financeRequest1.hashCode()).isNotEqualTo(financeRequest2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        FinanceRequest financeRequest = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, start, end);

        assertThat(financeRequest.hashCode()).isNotEqualTo(null);
    }

    @Test
    void testSetters() {
        FinanceRequest financeRequest = new FinanceRequest();

        financeRequest.setId(1L);
        financeRequest.setIdPortfolio(1L);
        financeRequest.setIdTypeFinance(1L);
        financeRequest.setIdCategory(1L);
        financeRequest.setInitAmount(100.0);
        financeRequest.setEndAmount(200.0);
        financeRequest.setStart(LocalDateTime.now());
        financeRequest.setEnd(LocalDateTime.now().plusDays(1));

        assertThat(financeRequest.getId()).isEqualTo(1L);
        assertThat(financeRequest.getIdPortfolio()).isEqualTo(1L);
        assertThat(financeRequest.getIdTypeFinance()).isEqualTo(1L);
        assertThat(financeRequest.getIdCategory()).isEqualTo(1L);
        assertThat(financeRequest.getInitAmount()).isEqualTo(100.0);
        assertThat(financeRequest.getEndAmount()).isEqualTo(200.0);
        assertThat(financeRequest.getStart()).isInstanceOf(LocalDateTime.class);
        assertThat(financeRequest.getEnd()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void testEquals() {
        FinanceRequest financeRequest1 = new FinanceRequest();
        financeRequest1.setId(1L);
        financeRequest1.setIdPortfolio(1L);
        financeRequest1.setIdTypeFinance(1L);
        financeRequest1.setIdCategory(1L);
        financeRequest1.setInitAmount(100.0);
        financeRequest1.setEndAmount(200.0);
        financeRequest1.setStart(LocalDateTime.now());
        financeRequest1.setEnd(LocalDateTime.now().plusDays(1));

        FinanceRequest financeRequest2 = new FinanceRequest();
        financeRequest2.setId(1L);
        financeRequest2.setIdPortfolio(1L);
        financeRequest2.setIdTypeFinance(1L);
        financeRequest2.setIdCategory(1L);
        financeRequest2.setInitAmount(100.0);
        financeRequest2.setEndAmount(200.0);
        financeRequest2.setStart(LocalDateTime.now());
        financeRequest2.setEnd(LocalDateTime.now().plusDays(1));

        assertThat(financeRequest1).isEqualTo(financeRequest2);
    }

    @Test
    void testNotEquals() {
        FinanceRequest financeRequest1 = new FinanceRequest();
        financeRequest1.setId(1L);
        financeRequest1.setIdPortfolio(1L);
        financeRequest1.setIdTypeFinance(1L);
        financeRequest1.setIdCategory(1L);
        financeRequest1.setInitAmount(100.0);
        financeRequest1.setEndAmount(200.0);
        financeRequest1.setStart(LocalDateTime.now());
        financeRequest1.setEnd(LocalDateTime.now().plusDays(1));

        FinanceRequest financeRequest2 = new FinanceRequest();
        financeRequest2.setId(2L);
        financeRequest2.setIdPortfolio(2L);
        financeRequest2.setIdTypeFinance(2L);
        financeRequest2.setIdCategory(2L);
        financeRequest2.setInitAmount(150.0);
        financeRequest2.setEndAmount(250.0);
        financeRequest2.setStart(LocalDateTime.now().plusDays(2));
        financeRequest2.setEnd(LocalDateTime.now().plusDays(3));

        assertThat(financeRequest1).isNotEqualTo(financeRequest2);
    }
}