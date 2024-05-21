package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.auth.Token;
import com.project.expensetrackingapp.repository.entity.user.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.Instant;
import java.util.HashSet;

public class TokenTest {

    @Test
    void testToString() {
        Instant expiredDate = Instant.now();
        User user = new User(1L, "testUser", "testPassword", new HashSet<>());

        Token token = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        String expectedToString = "Token(token=testToken, expiredDate=" + expiredDate + ", user=User(id=1, username=testUser, password=testPassword, roles=[]))";
        assertThat(token.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Instant expiredDate = Instant.now();
        User user = new User(1L, "testUser", "testPassword", new HashSet<>());

        Token token = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        assertThat(token).isEqualTo(token);
    }

    @Test
    void testEqualsWithDifferentType() {
        Instant expiredDate = Instant.now();
        User user = new User(1L, "testUser", "testPassword", new HashSet<>());

        Token token = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        assertThat(token).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Instant expiredDate = Instant.now();
        User user = new User(1L, "testUser", "testPassword", new HashSet<>());

        Token token = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        assertThat(token).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Instant expiredDate1 = Instant.now();
        Instant expiredDate2 = Instant.now().plusSeconds(60);
        User user1 = new User(1L, "testUser1", "testPassword1", new HashSet<>());
        User user2 = new User(2L, "testUser2", "testPassword2", new HashSet<>());

        Token token1 = Token.builder()
                .token("testToken1")
                .expiredDate(expiredDate1)
                .user(user1)
                .build();

        Token token2 = Token.builder()
                .token("testToken2")
                .expiredDate(expiredDate2)
                .user(user2)
                .build();

        assertThat(token1).isNotEqualTo(token2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Instant expiredDate = Instant.now();
        User user = new User(1L, "testUser", "testPassword", new HashSet<>());

        Token token1 = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        Token token2 = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Instant expiredDate1 = Instant.now();
        Instant expiredDate2 = Instant.now().plusSeconds(60);
        User user1 = new User(1L, "testUser1", "testPassword1", new HashSet<>());
        User user2 = new User(2L, "testUser2", "testPassword2", new HashSet<>());

        Token token1 = Token.builder()
                .token("testToken1")
                .expiredDate(expiredDate1)
                .user(user1)
                .build();

        Token token2 = Token.builder()
                .token("testToken2")
                .expiredDate(expiredDate2)
                .user(user2)
                .build();

        assertThat(token1.hashCode()).isNotEqualTo(token2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Instant expiredDate = Instant.now();
        User user = new User(1L, "testUser", "testPassword", new HashSet<>());

        Token token = Token.builder()
                .token("testToken")
                .expiredDate(expiredDate)
                .user(user)
                .build();

        assertThat(token.hashCode()).isNotEqualTo(null);
    }
}