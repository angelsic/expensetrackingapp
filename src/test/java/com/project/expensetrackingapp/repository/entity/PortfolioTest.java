package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.user.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class PortfolioTest {
    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        String expectedToString = "Portfolio(id=1, name=Test Portfolio, user=User(id=1, username=testUser, password=null, roles=[]))";
        assertThat(portfolio.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        assertThat(portfolio).isEqualTo(portfolio);
    }

    @Test
    void testEqualsWithDifferentType() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        assertThat(portfolio).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        assertThat(portfolio).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testUser1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testUser2");

        Portfolio portfolio1 = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio 1")
                .user(user1)
                .build();

        Portfolio portfolio2 = Portfolio.builder()
                .id(2L)
                .name("Test Portfolio 2")
                .user(user2)
                .build();

        assertThat(portfolio1).isNotEqualTo(portfolio2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Portfolio portfolio1 = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        Portfolio portfolio2 = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        assertThat(portfolio1.hashCode()).isEqualTo(portfolio2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testUser1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testUser2");

        Portfolio portfolio1 = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio 1")
                .user(user1)
                .build();

        Portfolio portfolio2 = Portfolio.builder()
                .id(2L)
                .name("Test Portfolio 2")
                .user(user2)
                .build();

        assertThat(portfolio1.hashCode()).isNotEqualTo(portfolio2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .user(user)
                .build();

        assertThat(portfolio.hashCode()).isNotEqualTo(null);
    }
}
