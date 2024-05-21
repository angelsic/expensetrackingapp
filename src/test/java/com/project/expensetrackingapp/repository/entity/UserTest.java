package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.entity.user.User;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
public class UserTest {
    @Test
    void testToString() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        User user = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles)
                .build();

        String expectedToString = "User(id=1, username=testUser, password=testPassword, roles=[UserRole(id=1, name=ADMIN)])";
        assertThat(user.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        User user = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles)
                .build();

        assertThat(user).isEqualTo(user);
    }

    @Test
    void testEqualsWithDifferentType() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        User user = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles)
                .build();

        assertThat(user).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        User user = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles)
                .build();

        assertThat(user).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        User user1 = User.builder()
                .id(1L)
                .username("testUser1")
                .password("testPassword1")
                .roles(roles1)
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("testUser2")
                .password("testPassword2")
                .roles(roles2)
                .build();

        assertThat(user1).isNotEqualTo(user2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(1L, "ADMIN"));

        User user1 = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles1)
                .build();

        User user2 = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles2)
                .build();

        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        User user1 = User.builder()
                .id(1L)
                .username("testUser1")
                .password("testPassword1")
                .roles(roles1)
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("testUser2")
                .password("testPassword2")
                .roles(roles2)
                .build();

        assertThat(user1.hashCode()).isNotEqualTo(user2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        User user = User.builder()
                .id(1L)
                .username("testUser")
                .password("testPassword")
                .roles(roles)
                .build();

        assertThat(user.hashCode()).isNotEqualTo(null);
    }
}
